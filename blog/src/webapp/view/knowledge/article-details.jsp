<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <title>文章详情</title>
</head>
<script src="/static/js/common/header.js"></script>
<body>
<div class="body" id="vue-content">
    <div class="body-main vue-content" >
        <!--<fieldset class="layui-elem-field layui-field-title">
            <legend>文章详情</legend>
        </fieldset>-->
        <div class="div-article-details">
            <div class="title" v-text="article.title"></div>
            <div class="div-article-top">
                <label>{{article.userName}}</label>
                <label>{{article.createTime | date('yyyy-MM-dd')}}</label>
                <label>{{article.type}}</label>
                <!--<div class="div-article-top-right">-->
                    <label>观阅数：{{article.browseAmount}}</label>
                    <label>评论数：{{article.commentAmount}}</label>
                <!--</div>-->
            </div>
            <hr style="border: 1px whitesmoke dashed;">
            <div v-html="article.content">

            </div>
        </div>
    </div>
    <!--评论-->
    <div class="div-article-comment">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>评论</legend>
        </fieldset>
        <div class="layui-input-block">
            <textarea name="desc" placeholder="请输入内容" class="layui-textarea" style="resize: none;"></textarea>
        </div>
        <button class="layui-btn" lay-submit lay-filter="formDemo" @click="commitComment()">提交评论</button>
    </div>
</div>
</body>
<script src="/static/js/common/footer.js"></script>
</html>
<script src="/static/js/article-details.js?v=1.1.1"></script>
<script>
    layui.use('layer', function () {

        var layer = layui.layer;
    });
    common.changeNav(1);
    details.vm.article.id = '${id}';
    details.init();

    layui.use('code', function () {

        layui.code();
    });


</script>
<style>
    .div-article-details .title{
        font-size: 24px;
        font-weight: 500;
        line-height: 150%;
        margin: 10px;
        padding: 0px;
        text-align: center;
    }

    .div-article-top {
        color: #14b0e6;
        padding: 5px;
    }
    .div-article-top label {
        margin-left: 10px;
    }
    /*.div-article-top-right {
        !*position: absolute;*!
        right: 5px;
        float: right;
        !*margin-right: 10px;*!
    }*/

    .div-article-comment {
        height: 220px;
        margin-bottom: 100px;
        position: relative;
        width: 1250px;
        left: 19%;
        background-color: white;
        padding: 10px 10px;
        margin-top: 20px;
        box-shadow: 0 2px 10px 0 rgba(0,0,0,.1);
        margin-top: -70px;
    }

    .layui-input-block{
        margin-bottom: 10px;
        margin-left: 0px !important;

    }
</style>