/*
 * !
 * 
 * frame.js
 * 
 * @author zhangsong
 * 
 */

(function(f, $) {

    'use strict';

    // context path
    f.contextPath = null;
    // 当前在线用户信息
    f.currUser = {};
    // 初始化完成
    f.isInit = false;
    
    f.init = function(opts) {
        $.extend(f, opts || {});
        $.ajax({
            url : f.contextPath + '/member/user/online.json',
            method: 'POST',
            dataType: 'json',
            data: {ajax: 'ajax'},
            success: function(ret) {
                if (!ret.succeed) {
                    return;
                }
                f.currUser = ret.data;
                f.isInit = true;
            },
            error  : function(err) {
                toastr.error('请求异常', '提示信息', f.alertOpts);
            }
        });
    }

    var $mainCenter = null;
    f.loadMainCenter = function(url) {
        if (!url) {
            url = document.location.href;
        }
        if (!$mainCenter) {
            $mainCenter = $('#main_center');
        }
        sog.loadingBar.show({
            pct : 50,
            delay : 0.5
        });
        $mainCenter.load(url, {
            ajax : 'ajax'
        }, function() {
            sog.loadingBar.show({
                pct : 100,
                delay: .5,
                wait : .5
            });
            sog.mainFooter.toBottom();
        });
    };

    f.emptyMainCenter = function() {
        $mainCenter.empty();
    };

    f.alertOpts = {
        "closeButton" : true,
        "debug" : false,
        "positionClass" : "toast-top-right",
        "onclick" : null,
        "showDuration" : "300",
        "hideDuration" : "1000",
        "timeOut" : "5000",
        "extendedTimeOut" : "1000",
        "showEasing" : "swing",
        "hideEasing" : "linear",
        "showMethod" : "fadeIn",
        "hideMethod" : "fadeOut"
    };
    
    Vue.component('songm-footer', {
        template: '<div class="text-center songm-foot">\
                   <p>&copy; 2016 - {{ year }} Powered by\
                   <strong>SONGM.CN</strong></p></div>',
        data: function() {
            return {year: new Date().getFullYear()};
        }
    });
    
    Vue.component('songm-topbar', {
        template: '#songm-topbar-template',
        props: ['frame']
    });
})((function() {
    if (!window.frame) {
        window.frame = {}
    }
    return window.frame;
})(), jQuery);

