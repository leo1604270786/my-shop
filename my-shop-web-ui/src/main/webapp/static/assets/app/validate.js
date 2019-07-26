/*
* 函数对象
* */
var Validate = function () {

    /*
    * 初始化 jQuery Validate
    * */
    var handlerInitValidate = function () {
        handlerInitCustomValidate();
        $("#inputForm").validate({
            rules: {
                username:{
                    required: true
                },
                password:{
                    required: true
                },
                password2:{
                    required: true,
                    equalTo: '#password'
                },
                phone:{
                    required: true,
                    mobile: true
                },
                verification:{
                    required:true
                },
                protocol:{
                    required: true
                }
            },
            messages:{
                username: "请输入用户名",
                password: "请输入密码",
                password2: {
                    required:"请再次输入密码",
                    equalTo:"密码不一致"
                },
                phone: {
                    required:"请输入手机号",
                    mobile: "手机号格式不正确"
                },
                verification: "请输入验证码",
                protocol: "请勾选同意协议"
            },
            errorElement: "div",                  // 验证失败时在元素后增加div，用来放错误提示
            errorPlacement: function (error, element) {   // 验证失败调用的函数
                //显示隐藏box
                element.siblings(".msg-box").children(":last").css("display","inline-block");
                //写入自定义错误信息
                element.siblings(".msg-box").children(":last").html(error.html());
            },
            success:function (lable, element) { //单个元素校验成功处理
                //隐藏提示信息
                $(element).siblings(".msg-box").children(":last").css("display","none");
            }
        });
    };
    /*
    * 添加自定义的验证规则
    * */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length === 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    return{
        init:function () {
            handlerInitValidate();
        }
    }
}();

$(document).ready(function () {
    Validate.init();
});