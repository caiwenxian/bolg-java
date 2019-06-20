<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <link rel='stylesheet' href='/static/css/knowledge/article-details.css'>
    <title>文章详情</title>
</head>
<script src="/static/js/common/header.js"></script>
<body>
<div class="body" id="vue-content" v-cloak>
    <div class="body-main vue-content">
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
            <textarea name="desc" placeholder="请输入内容" class="layui-textarea" style="resize: none;"
                      v-model="comment.comment"></textarea>
            <div class="div-no-login">未登录，请先登录</div>
        </div>
        <button class="layui-btn" lay-submit lay-filter="formDemo" @click="commitComment()">提交评论</button>
        <hr style="background-color: #fbf6f6; margin-top: 30px;">
        <div>
            <ul>
                <li class="li-comment" v-for="(c, index) in comment.list">
                    <div style="padding: 0 0 0 15px; background: none;">
                        <img src="/static/images/comment-photo.jpg" alt="" style="display: block; width: 40px; height: 40px;
                         margin: 0; border-radius: 2px; display: inline-block; border-radius: 50%;">
                        <div style="margin-left: 50px; margin-top: -40px;">
                            <span style="color: gray">{{c.createTime | date('yyyy-MM-dd hh:mm')}}</span><br>
                            <span style="color: #009688;">{{c.user.name}}</span>
                        </div>
                    </div>
                    <div style="margin: 25px 20px 20px; min-height: 0; line-height: 24px; font-size: 14px;">
                        {{c.comment}}
                    </div>
                    <div style="margin-left: 20px; color: gray" class="comment-bottom">
                        <span class="cursor-pointer">赞</span>

                        <span class="cursor-pointer reply-btn" style="margin-left: 20px"
                              @click="c.childs != null ? hideComment(index, c.childs != null) : popReply(index, c.id)">
                            <template v-if="c.childs != null">
                                收起回复
                            </template>
                            <template v-else>
                                回复
                            </template>

                        </span>
                    </div>
                    <%--回复详情div--%>
                    <div class="comment-reply-details" v-if="c.childs != null">
                        <ul>
                            <li v-for="(d, index) in c.childs">
                                <img src="/static/images/comment-photo-2.jpg" alt="" style="display: block; width: 40px; height: 40px;
                         margin: 0; border-radius: 2px; display: inline-block; border-radius: 50%;">
                                <span style="color: #009688;">{{d.user.name}}:</span>
                                <span>{{d.comment}}</span>
                                <br>
                                <span style="color: gray; float: right;">{{d.createTime | date('yyyy-MM-dd hh:mm')}}</span>
                            </li>
                        </ul>
                        <div style="padding-top: 15px;">
                            <span style="position: relative; margin-left: 90.5%; padding: 2px;
                             border: 1px solid #e6e1e1; background-color: #f3f2f1; cursor: pointer"
                                  @click="popReply(index, c.id)">回复层主</span>
                        </div>
                    </div>
                    <%--回复框--%>
                    <div class="comment-reply">
                    </div>
                </li>
            </ul>
        </div>
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

</style>