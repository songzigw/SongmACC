/*!
 * 
 * login.js
 * 
 * @author  zhangsong
 * 
 */

(function(l, f, $) {

    'use strict';

    var loginVM = null;
    
    l.init = function() {
        loginVM = new Vue({
            el     : '#main_content',
            data   : {frame: f},
            methods: {
                checkIn  : checkIn,
                loadVcode: loadVcode
            }
        });
        loadVcode();
    };

    function checkIn() {
        var $form = $('#login_form');
        if ($form.data('runing')) return;
        
        $form.data('runing', true);
        var acc = $('[name=account]', $form).val();
        var pwd = $('[name=password]', $form).val();
        var vco = $('[name=vcode]', $form).val();
        var agr = $('[name=agreement]:checked', $form).val();
        if (!agr) {
            showError('必须同意协议');
            return;
        }
        var $btn = $('.btn-login', $form).text('登入中...');
        $.ajax({
            url: f.contextPath + '/login.json',
            method: 'post',
            data: {account: acc, password: pwd, vcode: vco},
            dataType: 'json',
            success: function(ret) {
                $btn.text('登入');
                $form.data('runing', false);
                if (!ret.succeed) {
                    showError(ret.errorDesc);
                    return;
                }
                alert("登入成功 Success!!!");
            },
            error: function() {
                $btn.text('登入');
                $form.data('runing', false);
                showError('登入失败');
            }
        });
    }
    
    function showError(info) {
        var $error = $('#login_error');
        $error.html('<div class="alert alert-danger">\
                        <!--<button type="button" class="close" data-dismiss="alert">-->\
                        <!--    <span aria-hidden="true">×</span> <span class="sr-only">Close</span>-->\
                        <!--</button>-->\
                        <span class="text">'+ info +'</span>\
                    </div>');
    }

    function loadVcode() {
        var $t = $('#login_vcode');
        $t.html('<i>加载中...</i>');
        $t.html('<img alt="vcode" src="vcode?' + (new Date()).getTime() + '" />');
    }
})((function() {
    if (!window.login) {
        window.login = {}
    }
    return window.login;
})(), frame, jQuery);
