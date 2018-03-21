<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录界面</title>
    <link rel='stylesheet' href='/static/css/login.css'>
</head>
<script src="/static/js/common/header.js"></script>
<body>
<div class="body" style="min-height: 750px;">
    <%--<div class="body-main vue-content" id="vue-content">
        <h1>登录界面</h1>
        <input type="button" value="登录" onclick="login()">
        <input type="button" value="退出登录" onclick="logout()">
    </div>--%>

    <div class="login_boder">

        <div class="login_padding" id="login_model">

            <h2>用户名</h2>
            <label>
                <input type="text" id="username" class="txt_input txt_input2 username"
                       <%--onfocus="if (value =='请输入用户名'){value =''}" onblur="if (value ==''){value='请输入用户名'}"--%>
                       placeholder="请输入用户名">
            </label>
            <h2>密码</h2>
            <label>
                <input type="password" name="textfield2" id="userpwd" class="txt_input password"
                       <%--onfocus="if (value =='******'){value =''}" onblur="if (value ==''){value='******'}"--%>
                       placeholder="******">
            </label>

            <p class="forgot">
                <a id="register" href="javascript:void(0);" style="float: left" onclick="goRegister()">注册帐号</a>
                <a id="iforget" href="javascript:void(0);" onclick="goForgetPwd()">忘记密码?</a>
            </p>
            <div class="rem_sub">
                <div class="rem_sub_l">
                    <input type="checkbox" name="checkbox" id="save_me" class="rememberMe">
                    <label for="save_me">Remember me</label>
                </div>
                <label>
                    <input type="submit" class="sub_button input-login" name="button" id="button" value="登录"
                           style="opacity: 0.7; background-color: cornflowerblue;" onclick="login()">
                </label>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="/static/js/common/footer.js"></script>
<script>
    layui.use('layer', function () {
        var layer = layui.layer;
    })


    var goRegister = function () {
        window.location.href = '/register';
    }

    var goForgetPwd = function () {
        window.location.href = '/account/forgetPwd';
    }

    var login = function () {

        var name = $.trim($('.username').val());
        var password = $.trim($('.password').val());
        var rememberMe = $('.rememberMe').prop('checked');

        if (name === '') {
            layer.msg('请输入用户名');
            return;
        }
        if (password === '') {
            layer.msg('请输入密码');
            return;
        }

        var url = '/login';
        var data = {
            name: name,
            password: password,
            rememebrMe: rememberMe,
            <%--refUrl: '${refUrl}'--%>
        }
        http.post(url, data, function (result) {

            if (result.code !== 0) {
                layer.msg("登录失败");
                return;
            }
            layer.msg("登录成功");
            if ('${refUrl}' !== '') {
                window.location.href = '${refUrl}';
            }
            window.location.href = '/index';

        });
    }


</script>

<style>
    .input-login:hover {
        opacity: 0.3 !important;
    }

    .input-login:active {
        opacity: 0.3 !important;
    }
</style>
