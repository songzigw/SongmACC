/*
 * !
 * 
 * user.edit.js
 * 
 * @author zhangsong
 * 
 */

(function(f, $) {

    'use strict';

    var menus = [{uri: f.contextPath + '/member/user/edit', name: '基本设置'},
                 {uri: f.contextPath + '/member/user/avatar', name: '头像设置'},
                 {uri: f.contextPath + '/member/user/binds', name: '账号绑定'},
                 {uri: f.contextPath + '/member/user/password', name: '密码修改'}];
    var user = {"account": null,
            "nick": null,
            "realName": null,
            "avatar": null,
            "gender": null,
            "birthYear": null,
            "birthMonth": null,
            "birthDay": null,
            "summary": null,
            "created": null,
            "updated": null};
    
    var UserEdit = function() {
        this._init();
        var int = setInterval(function() {
            if (f.isInit) {
                user.account = f.currUser.account;
                user.nick = f.currUser.nick;
                user.realName = f.currUser.realName;
                user.avatar = f.currUser.avatar;
                user.gender = f.currUser.gender;
                user.birthYear = f.currUser.birthYear;
                user.birthMonth = f.currUser.birthMonth;
                user.birthDay = f.currUser.birthDay;
                user.summary = f.currUser.summary;
                user.created = f.currUser.created;
                user.updated = f.currUser.updated;
                clearInterval(int);
            }
        }, 200);
    };
    
    UserEdit.prototype._init = function() {
        var _t = this;
        _t.vm = new Vue({
            el     : '#user_edit_page',
            data   : {
                frame: f,
                menus: menus,
                view : 'songm-user-base'
            },
            components: {
                'songm-user-base': {
                    template: '#songm-user-base-template',
                    data : function() { return user; },
                    methods: {
                        submit : function() {
                            alert('songm-user-base');
                        }
                    }
                },
                'songm-user-avatar': {
                    
                }
            }
        });
    };
    
    window.UserEdit = UserEdit;

})(frame, jQuery);
