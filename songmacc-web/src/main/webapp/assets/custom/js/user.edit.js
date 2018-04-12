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
            "avatarOld": null,
            "gender": null,
            "birthYear": null,
            "birthMonth": null,
            "birthDay": null,
            "summary": null,
            "created": null,
            "updated": null};
    var coordinates = {};
    
    var UserEdit = function() {
        this._init();
        var int = setInterval(function() {
            if (f.isInit) {
                user.account = f.currUser.account;
                user.nick = f.currUser.nick;
                user.realName = f.currUser.realName;
                user.avatarOld = (f.currUser.avatarServer + f.currUser.avatarOldPath) || f.userAvatarDefault;
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
    
    UserEdit.user = user;
    
    UserEdit.prototype._init = function() {
        var _t = this;
        _t.vm = new Vue({
            el     : '#user_edit_page',
            data   : {
                frame: f,
                menus: menus,
                view : null
            },
            components: {
                'songm-user-base': temSongmUserBase,
                'songm-user-avatar': temSongmUserAvatar,
                'songm-user-binds': temSongmUserBinds,
                'songm-user-password': temSongmUserPassword
            }
        });
        _t.selectMenu();
    };
    
    var temSongmUserBase = {
		template: '#songm-user-base-template',
        data : function() {
        	return {
            	years: years,
                months: months,
                days: days,
                user: user
            }
        },
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
    };
    
    var $avatar = null;
    var temSongmUserAvatar = {
		template: '#songm-user-avatar-template',
    	data : function() {
    		return {user: user, coordinates: coordinates}
    	},
    	props: ['frame'],
    	methods: {
    		cutFigure: function() {
    			$.ajax({
                	url     : f.contextPath + '/member/user/avatar.json',
                	method  : 'PUT',
                	dataType: 'json',
                	data: {ajax: 'ajax',
                		avatar_server: user.avatarServer,
                		avatar_old_path: user.avatarOldPath,
                		avatar_old: user.avatarOld,
                		x: coordinates.x,
                		y: coordinates.y,
                		width: coordinates.width,
                		height: coordinates.height
                	},
                	success: function(ret) {
                		if (!ret.succeed) {
                			toastr.error(ret.errorDesc, '提示信息', f.alertOpts);
                			return;
                		}
                		f.current.avatarServer = ret.data.server;
                		f.current.avatarPath = ret.data.path;
                		f.currUser.avatar = ret.data.server + ret.data.path;
                		toastr.success('修改成功', '提示信息', f.alertOpts);
                	},
                	error  : function(err) {
                		toastr.error('请求异常', '提示信息', f.alertOpts);
                	}
                });
    		}
    	},
    	watch: {
    		'user.avatarOld': function(nV, oV) {
    			initImgPreview();
    		}
    	},
    	mounted: function() {
    		$avatar = $(this.$refs.avatar);
    		$('#dropzone-imgupload', $avatar).dropzone({
    	        url: f.userAvatarUpload,
    	        maxFiles: 1,
    	        maxFilesize: 512,
    	        acceptedFiles: '.jpg,.png',
    	        addedfile: function(file) {
    	        	
    	        },
    	        uploadprogress: function(file, progress, bytesSent) {
    	            
    	        },
    	        success: function(file, ret) {
    	        	console.log(JSON.stringify(ret));
    	            user.avatarOld = ret.data.server + ret.data.path;
    	            f.currUser.avatarOld = user.avatarOld;
    	        },
    	        error  : function(file, err) {
    	        	toastr.error('上传异常', '提示信息', f.alertOpts);
    	        }
    	    });
    		initImgPreview();
    	}
    };
    
    function initImgPreview() {
    	$('.img-container', $avatar).empty().append('<img src="'
    	+ user.avatar + '" class="img-responsive cropper-hidden">');
    	$('#img-preview', $avatar).empty();
    	$('.img-container img', $avatar).cropper({
	        aspectRatio: 1,
	        preview: $('#img-preview', $avatar),
	        done: function(data) {
	        	coordinates = data;
	        }
	    });
    	$('#img-preview', $avatar).css({
	        width: 220,
	        height:220
	    });
    }
    
    var temSongmUserBinds = {
    	template: '#songm-user-binds-template',
    	data : function() {
    		return {user: user}
    	}
    };
    
    var temSongmUserPassword = {
    	template: '#songm-user-password-template',
    	data : function() {
    		return {user: user}
    	}
    };
    
    var routes = {
    	'/songmacc-web/member/user': 'songm-user-base',
    	'/songmacc-web/member/user/edit': 'songm-user-base',
    	'/songmacc-web/member/user/avatar': 'songm-user-avatar',
    	'/songmacc-web/member/user/binds': 'songm-user-binds',
    	'/songmacc-web/member/user/password': 'songm-user-password'
    };
    
    UserEdit.prototype.selectMenu = function() {
        var _t = this;
        var path = location.pathname;
        _t.vm.view = routes[path];
        setActive(path);
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
