<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    layui.use(['layer', 'element', 'table', 'carousel', 'laypage'], function() {
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
        <li class='layui-nav-item'><a href='/blog'><i class='layui-icon'>&#xe68e;</i>首页</a></li>
        <li class='layui-nav-item'><a href='/knowledge'><i class='layui-icon'>&#xe705;</i>知识专栏</a></li>
        <li class='layui-nav-item'><a href='/life'><i class='layui-icon'>&#xe6b2;</i>生活点滴</a></li>
        <li class='layui-nav-item'><a href='/music'><i class='layui-icon'>&#xe6fc;</i>音乐分享</a></li>
        <li class='layui-nav-item'>
          <a href='javascript:;'><i class='layui-icon'>&#xe607;</i>关于本站</a>
          <dl class='layui-nav-child'> <!-- 二级菜单 -->
            <dd><a href='/about/about-author'>关于作者</a></dd>
            <dd><a href='/about/about-blog'>关于blog</a></dd>
            <dd><a href='/about/guest-message'>留言</a></dd>
          </dl>
        </li> 
      </ul>
      </ul>
      </ul>
      </ul>
    </div>
</div>


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
</style>