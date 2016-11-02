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
package songm.account.service;

/**
 * 异常类
 *
 * @author zhangsong
 * @since 0.1, 2016-8-2
 * @version 0.1
 * 
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 5118981894942473582L;

    private ErrorCode errorCode;

    private String description;

    public ServiceException(ErrorCode errorCode, String description) {
        super(errorCode + ": " + description);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ServiceException(ErrorCode errorCode, String description, Throwable cause) {
        super(errorCode + ": " + description, cause);
        this.errorCode = errorCode;
        this.description = description;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public static enum ErrorCode {
        // 账号异常---------------
        /** 账号重复错误码 */
        ACC_101,
        /** 昵称重复错误码 */
        ACC_102,
        /** 原始密码异常 */
        ACC_103,
        /** 日期格式异常 */
        ACC_104,
        /** 账号格式异常 */
        ACC_105,
        /** 昵称格式异常 */
        ACC_106,
        /** 密码格式异常 */
        ACC_107,
        /** 平台绑定异常 */
        ACC_108,
        /** 账号或密码错误 */
        ACC_109,
        /** 账号已经设置过 */
        ACC_110,
        /** 邮箱已经被绑定 */
        ACC_111,
        /** 邮箱格式错误 */
        ACC_112,
        /** 账号禁止使用关键字 */
        ACC_113,
        /** 昵称禁止使用关键字 */
        ACC_114,
        
        
        // 验证码异常-------------
    }

}
