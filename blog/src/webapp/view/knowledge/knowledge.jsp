<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <title>知识专栏</title>
</head>
<script src="../../static/js/common/header.js"></script>
<body>
<div class="body">
    <div class="body-main vue-content" id="vue-content">
        <!--<p class="center weight" style="margin-top: 100px">知识专栏开发中...</p>-->
        <fieldset class="layui-elem-field layui-field-title">
            <legend>最新文章</legend>
        </fieldset>
        <div class="div-article-list" v-for="article in article.list">
            <div><a href="javascript:void(0)"><p class="p-article-title" @click="goDetails(article.id)">{{article.title}}</p></a></div>
            <div class="div-article-content" v-text="article.contentText"></div>
            <div class="div-article-bottom">
                <label>张三</label>
                <label>{{article.createTime | date('yyyy-MM-dd')}}</label>
                <label>{{article.type}}</label>
                <div class="div-article-bottom-right">
                    <label>观阅数：12</label>
                    <label>评论数：13</label>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script src="../../static/js/common/footer.js"></script>
</html>
<script src="/static/js/knowledge.js?v=1.1.1"></script>
<script>
    layui.use('layer', function () {

        var layer = layui.layer;
    });
    common.changeNav(1);
    article.init();

    layui.use('code', function () {

        layui.code();
    });

</script>

<style>
    .div-article-list {
        margin-top: 20px;
        background-color: #e5e9ef;
        background-color: #fff;
        box-shadow: 0 0 2px 0 rgba(0, 0, 0, .1);
        padding: 30px;
        position: relative;
        border-bottom: 1px solid #fff;
        transition: all .6s;
        border-bottom: 1px solid #ccc;
    }
    .p-article-title {
        padding: 5px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 18px;
        line-height: 26px;
    }
    .div-article-content {
        padding: 5px 10px;
        margin-right: 10px;
        overflow: hidden;
        text-overflow: ellipsis;
        color: #999;
        font-size: 14px;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
        margin-top: 5px;
        margin-bottom: 5px;
        padding: 10px;
        line-height: 1.5rem;
    }
    .div-article-bottom {
        color: #14b0e6;
        padding: 5px;
    }
    .div-article-bottom label {
        margin-left: 10px;
    }
    .div-article-bottom-right {
        /*position: absolute;*/
        right: 5px;
        float: right;
        /*margin-right: 10px;*/
    }
</style>

