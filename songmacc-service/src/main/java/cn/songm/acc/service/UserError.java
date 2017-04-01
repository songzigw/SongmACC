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
package cn.songm.acc.service;

import cn.songm.common.service.ErrorInfo;

public enum UserError implements ErrorInfo {
    // 账号异常---------------
    /** 账号重复错误码 */
    ACC_101("ACC_101"),
    /** 昵称重复错误码 */
    ACC_102("ACC_102"),
    /** 原始密码异常 */
    ACC_103("ACC_103"),
    /** 日期格式异常 */
    ACC_104("ACC_104"),
    /** 账号格式异常 */
    ACC_105("ACC_105"),
    /** 昵称格式异常 */
    ACC_106("ACC_106"),
    /** 密码格式异常 */
    ACC_107("ACC_107"),
    /** 平台绑定异常 */
    ACC_108("ACC_108"),
    /** 账号或密码错误 */
    ACC_109("ACC_109"),
    /** 账号已经设置过 */
    ACC_110("ACC_110"),
    /** 邮箱已经被绑定 */
    ACC_111("ACC_111"),
    /** 邮箱格式错误 */
    ACC_112("ACC_112"),
    /** 账号禁止使用关键字 */
    ACC_113("ACC_113"),
    /** 昵称禁止使用关键字 */
    ACC_114("ACC_114"),
    /** 用户登入Session失效 */
    ACC_115("ACC_115"),
    
    
    // 验证码异常-------------
    /** 验证码错误 */
    ACC_116("ACC_116");

    private final String errCode;
    
    @Override
    public String getErrCode() {
        return errCode;
    }

    private UserError(String errCode) {
        this.errCode = errCode;
    }

    public UserError getInstance(String errCode) {
        for (UserError m : UserError.values()) {
            if (m.getErrCode().equals(errCode)) {
                return m;
            }
        }
        return null;
    }
}
