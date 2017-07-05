$(function () {
    loginSystem();
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            $("#loginForm").submit();
        }
    });
});

function loginSystem() {
    $.ajaxSetup({cache: false});//清除缓存
    $("#loginForm").validate({
        submitHandler: function (form) {
            //验证通过后可提交表单
            /*	form.submit();*/
            $(form).ajaxSubmit({
                beforeSend: function () {
                    //在表单提交后后端未作出响应时，禁用提交按钮，提高用户体验。
                    $(".cardioadd-submit").attr({
                        disabled: "disabled"
                    });
                },
                complete: function () {
                    //后端响应后放开按钮
                    $(".cardioadd-submit").removeAttr("disabled");
                },
                success: function (result) {
                    if (result.success) {
                        window.location.href = basePath + "/loginHandler/index";
                    } else {
                        if (result.tryLoginNum > 2) {
                            $('#vcode').removeClass("verify-code");
                        }
                        layer.alert(result.message);
                    }
                },
                dataType: 'json'
            });
        },
        rules: {
            loginName: {
                required: true,
            },
            password: {
                required: true,
            },
            identifyingCode: {
                required: true,
            }
        },
        messages: {
            loginName: {
                required: "请输入用户名！",
            },
            password: {
                required: "请输入密码！",
            },
            identifyingCode: {
                required: "请输入验证码！",
            }
        },
        onfocusout: function (element) { //失去焦点时执行验证
            $(element).valid();
        },
        errorPlacement: function (error, element) { //错误提示，错误对象
            console.log($(element).attr("id"));
            if ("identifyingCode" == $(element).attr("id")) {
                layer.tips(error[0].innerText, $("#checkCode"), {
                    tipsMore: true
                });
            } else {
                layer.tips(error[0].innerText, element, { //1.错误信息，2提示位置，3同时提示多个错误
                    tipsMore: true //错误信息可以同时提示多个，...
                });
            }
        }
    });
}
function saveAccount() {
    var url = basePath + "/loginHandler/signAccout";
    var data = $("#useInfo").serializeObject();
    $.post(url, data, function (result) {
        if (result.success) {
            window.location.href=basePath+"/loginHandler/forwardLogin";
        } else {
            layer.alert(result.message);
        }
    }, "json");

}
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};