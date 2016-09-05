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
package songm.account.dao;

/**
 * 数据持久化帮助类
 * @author zhangsong
 *
 */
public class DaoUtils {
	public static enum Order {
		/** 升序排列 */
		ASC,
		/** 降序排列 */
		DESC
	}

	/**
	 * 1升序 2降序
	 * 
	 * @param type
	 * @return
	 */
	public static Order orderType(int type) {
		switch (type) {
		case 1:
			return Order.ASC;
		case 2:
			return Order.DESC;
		default:
			return Order.ASC;
		}
	}

	public static String insertSql(Tables table, String fields,
			String paramMarks) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ");
		sql.append(table).append("(");
		sql.append(fields).append(")");
		sql.append(" values(").append(paramMarks).append(")");
		return sql.toString();
	}
}
