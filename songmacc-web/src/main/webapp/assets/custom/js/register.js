/*!
 * 
 * register.js
 * 
 * @author  zhangsong
 * 
 */

(function(r, f, $) {

    'use strict';

    var registerVM = null;
    
    r.init = function() {
        registerVM = new Vue({
            el : '#main_content',
            data: {year : new Date().getFullYear()},
            methods: {
                checkIn: checkIn,
                loadVcode: loadVcode
            }
        });
        loadVcode();
    };

    function checkIn() {
        var $form = $('#regist_form');
        if ($form.data('runing')) return;
        
        $form.data('runing', true);
        var acc = $('[name=account]', $form).val();
        var nic = $('[name=nick]', $form).val();
        var pwd = $('[name=password]', $form).val();
        var vco = $('[name=vcode]', $form).val();
        var agr = $('[name=agreement]:checked', $form).val();
        if (!agr) {
            showError('必须同意协议');
            return;
        }
        var $btn = $('.btn-regist', $form).text('注册中...');
        $.ajax({
            url: f.contextPath + '/register.json',
            method: 'post',
            data: {account: acc, password: pwd, nick: nic, vcode: vco},
            dataType: 'json',
            success: function(ret) {
                if (!ret.succeed) {
                    showError(ret.errorDesc);
                } else {
                    alert("注册成功!!!");
                }
                $btn.text('注册');
                $form.data('runing', false);
            },
            error: function() {
                showError('注册失败');
                $btn.text('注册');
                $form.data('runing', false);
            }
        });
    }
    
    function showError(info) {
        var $error = $('#regist_error');
        $error.html('<div class="alert alert-danger">\
                        <!--<button type="button" class="close" data-dismiss="alert">-->\
                        <!--    <span aria-hidden="true">×</span> <span class="sr-only">Close</span>-->\
                        <!--</button>-->\
                        <span class="text">'+ info +'</span>\
                    </div>');
    }

    function loadVcode() {
        var $t = $('#regist_vcode');
        $t.html('<i>加载中...</i>');
        $t.html('<img alt="vcode" src="vcode?' + (new Date()).getTime() + '" />');
    }
})((function() {
    if (!window.register) {
        window.register = {}
    }
    return window.register;
})(), frame, jQuery);
