/**
 * Created by zhanghui on 17/3/17.
 */
$(function(){
    (function(win,doc){
        var rem = 50/375*doc.documentElement.clientWidth;
        doc.documentElement.style.fontSize = rem+'px';
        win.onresize = function(){
            rem = 50/375*doc.documentElement.clientWidth;
            doc.documentElement.style.fontSize = rem+'px';
        };
    })(window,document);
});