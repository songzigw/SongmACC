/*!
 * 
 * user.edit.js
 * 
 * @author zhangsong
 * 
 */

(function(f, $) {

    'use strict';

    var menus = [{uri: f.contextPath + '/member/user/edit', name: '基本设置', isActive: false},
                 {uri: f.contextPath + '/member/user/avatar', name: '头像设置', isActive: false},
                 {uri: f.contextPath + '/member/user/binds', name: '账号绑定', isActive: false},
                 {uri: f.contextPath + '/member/user/password', name: '密码修改', isActive: false}];
    
    var years = [];
    var months = [];
    var days = [];
    for (var i = 1937; i < new Date().getFullYear(); i++) {
    	years.push(i);
    }
    for (var i  = 1; i <= 12; i++) {
    	months.push(i);
    }
    for (var i = 1; i <= 31; i++) {
    	days.push(i);
    }

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
                view : ''
            },
            components: {
                'songm-user-base': {
                    template: '#songm-user-base-template',
                    data : function() { return {
                    	years: years,
                        months: months,
                        days: days,
                        user: user
                    }},
                    methods: {
                        submit : function() {
                        	if (!this.user.nick) {
                        		toastr.error('用户昵称不能为空', '提示信息', f.alertOpts);
                        		return;
                        	}
                            $.ajax({
                            	url     : f.contextPath + '/member/user/edit.json',
                            	method  : 'POST',
                            	dataType: 'json',
                            	data: {ajax: 'ajax'},
                            	success: function(ret) {
                            		if (!ret.succeed) {
                            			toastr.error(ret.errorDesc, '提示信息', f.alertOpts);
                            			return;
                            		}
                            		toastr.success('修改成功', '提示信息', f.alertOpts);
                            	},
                            	error  : function(err) {
                            		toastr.error('请求异常', '提示信息', f.alertOpts);
                            	}
                            });
                        }
                    }
                },
                'songm-user-avatar': {
                	template: '#songm-user-avatar-template'
                },
                'songm-user-binds': {
                	template: '#songm-user-binds-template'
                },
                'songm-user-password': {
                	template: '#songm-user-password-template'
                }
            }
        });
        _t.selectMenu();
    };
    
    UserEdit.prototype.selectMenu = function() {
        var _t = this;
        var path = location.pathname;
        setActive(path);
        if (path.endsWith('/member/user/edit')) {
            _t.vm.view = 'songm-user-base';
        } else if (path.endsWith('/member/user/avatar')) {
            _t.vm.view = 'songm-user-avatar';
        } else if (path.endsWith('/member/user/binds')) {
            _t.vm.view = 'songm-user-binds';
        } else if (path.endsWith('/member/user/password')) {
            _t.vm.view = 'songm-user-password';
        }
    };
    
    function setActive(path) {
        for (var i in menus) {
            menus[i].isActive = false;
            if (menus[i].uri == path) {
                menus[i].isActive = true;
            }
        }
    }
    
    window.UserEdit = UserEdit;

})(frame, jQuery);
