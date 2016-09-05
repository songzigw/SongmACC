/*
 * Copyright [2016] [zhangsong <songm.cn>].
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package songm.account.dao.keygen;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 键值信息封裝
 * @author zhangsong
 *
 */
public class KeyInfo {

    /** 最小值 */
    private long keyMin;
    /** 最大值 */
    private long keyMax;
    /** 下一个值 */
    private long nextKey;
    /** 键值缓存数量 */
    private int poolSize;
    /** 键的名称 */
    private String keyName;
    
    public KeyInfo(int poolSize, String keyName) {
        this.poolSize = poolSize;
        this.keyName = keyName;
    }

    public long getKeyMax() {
        return keyMax;
    }

    public long getKeyMin() {
        return keyMin;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getKeyName() {
        return keyName;
    }
    
    public long getNextKey(BasicDataSource dataSource,
            PlatformTransactionManager tm) {
        if (nextKey > keyMax) {
            this.retrieveFromDB(dataSource, tm);
        }
        return nextKey++;
    }
    
    private void retrieveFromDB(final BasicDataSource dataSource,
            PlatformTransactionManager tm) {
        TransactionTemplate tt = new TransactionTemplate(tm);
        tt.execute(new TransactionCallback<Object>() {
            public Object doInTransaction(TransactionStatus status) {
                JdbcTemplate jt = new JdbcTemplate(dataSource);
                String sql1 = "UPDATE KEY_TABLE SET KEY_VALUE=KEY_VALUE+"
                        + poolSize + " WHERE KEY_NAME='" + keyName + "'";
                String sql2 = "SELECT KEY_VALUE FROM KEY_TABLE WHERE KEY_NAME='"
                        + keyName + "'";

                jt.update(sql1);
                long keyFormDB = jt.query(sql2, new ResultSetExtractor<Long>() {

                    @Override
                    public Long extractData(ResultSet rs) throws SQLException,
                            DataAccessException {
                        if (rs.next()) {
                            return rs.getLong("KEY_VALUE");
                        } else {
                            return 0l;
                        }
                    }

                });
                keyMax = keyFormDB;
                keyMin = keyFormDB - poolSize + 1;
                nextKey = keyMin;
                return null;
            }
        });
    }
}
