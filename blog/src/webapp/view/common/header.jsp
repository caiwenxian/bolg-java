<%@ page contentType='text/html;charset=UTF-8' language='java' %>
<script src='/static/plugins/jquery/jquery.min.js'></script>
<script src='/static/plugins/layui/layui.js'></script>
<script src='/static/plugins/vue/vue.min.js'></script>
<script src='/static/js/common/http.js'></script>
<script src='/static/js/common/common.js'></script>
<link rel='stylesheet' href='/static/plugins/layui/css/layui.css'>
<link rel='stylesheet' href='/static/css/common.css'>
<script>
    var element = null;
    var table = null;
    var carousel = null;
    var laypage = null;
    layui.use(['layer', 'element', 'table', 'carousel', 'laypage'], function () {
        carousel = layui.carousel;
        laypage = layui.laypage;
        element = layui.element;
        table = layui.table;
    });
</script>

<div class='header'>
    <div>
        <a href='#' class='log'>BLOG</a>
        <ul class='layui-nav nav-ul' lay-filter=''>
            <li class='layui-nav-item'><a href='/index'><i class='layui-icon'>&#xe68e;</i>首页</a></li>
            //
            <li class='layui-nav-item'><a href='/knowledge'><i class='layui-icon'>&#xe705;</i>知识专栏</a></li>

            <li class='layui-nav-item'>
                <a href='/knowledge'><i class='layui-icon'>&#xe705;</i>知识专栏</a>
                <dl class='layui-nav-child'> <!-- 二级菜单 -->
                    <dd><a href='/knowledge/article/publish'>发布文章</a></dd>
                </dl>
            </li>

            <li class='layui-nav-item'><a href='/life'><i class='layui-icon'>&#xe6b2;</i>生活点滴</a></li>
            <li class='layui-nav-item'><a href='/music'><i class='layui-icon'>&#xe6fc;</i>音乐分享</a></li>
            <li class='layui-nav-item'>
                <a href="javascript:"><i class='layui-icon'></i>关于本站</a>
                <dl class='layui-nav-child'> <!-- 二级菜单 -->
                    <dd><a href='/about/about-author'>关于作者</a></dd>
                    <dd><a href='/about/about-blog'>关于blog</a></dd>
                    <dd><a href='/about/guest-message'>留言</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="ul-login">
            <img src="/static/images/login.png" alt="" onclick="goLogin()" class="img-photo">
            <li onclick="goLogin()" class="li-login none">登录</li>
            <li onclick="goRegister()" class="li-register none">注册</li>
            <li onclick="logout()" class="li-logout none">退出</li>
        </ul>
    </div>
</div>

<script>
    var goLogin = function () {
        window.location.href = '/login';
    };

    var goRegister = function () {
        window.location.href = '/register';
    };

    var logout = function () {
        var url = '/login/logout';
        var data = {
            name: null,
            password: null
        };
        http.post(url, data, function (result) {

            if (result.code !== 0) {
                layer.msg("退出登录失败");
                return;
            }
            layer.msg("退出登录成功");
            checkStatus();
        });
    };

    var checkStatus = function () {
        var url = '/account/checkStatus';
        http.get(url, null, function (result) {
            $('.img-photo').css('margin-top', '0px');
            if (result.code === 401) {  //未登录
                $('.li-login').removeClass('none');
                $('.li-register').removeClass('none');
                $('.li-logout').addClass('none');
                return;
            }
            $('.li-logout').removeClass('none');


        })

        /*$('.img-photo').hover(function () {
        })*/
    };

    $(function () {
        checkStatus();

    })
</script>


<style>
    .header {
        width: 100%;
        height: 61px;
        position: fixed;
        top: 0;
        background-color: #393D49;
        z-index: 9999;
    }

    .log {
        position: absolute;
        left: 24%;
        padding: 20px;
        font-family: -webkit-pictograph;
        font-weight: 600;
        font-size: 20px;
        color: antiquewhite;
    }

    .nav-ul {
        position: absolute;
        left: 30%;
    }

    .layui-icon {
        font-size: 16px;
        margin-right: 5px
    }

    .ul-login {
        display: inline-block;
        color: rgba(255, 255, 255, .7);
        position: absolute;
        left: 75%;
        cursor: pointer;
    }

    .ul-login li {
        display: inline-block;
        vertical-align: middle;
        line-height: 60px;
        margin-right: 10px;
    }

    .ul-login img {
        display: inline-block;
        border: none;
        height: 36px;
        margin-right: 10px;
        margin-top: 12px;
    }
</style>