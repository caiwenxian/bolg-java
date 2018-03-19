<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <title>文章发布</title>
</head>
<script src="/static/js/common/header.js"></script>
<body>
<div class="body-main " id="vue-content">

    <fieldset class="layui-elem-field layui-field-title">
        <legend>发布文章</legend>
    </fieldset>
    <form class="layui-form" action="">
        <label class="layui-form-label">文章类型：</label>
        <div class="layui-form-item select-article-type">
            <select name="type" v-model="articleType">
                <option value="TECHNOLOGY" selected="">技术</option>
                <option value="LIFE">生活</option>
            </select>
        </div>
        <p></p>
        <label class="layui-form-label">文章标题：</label>
        <div class="layui-input-block" style="margin-bottom: 15px;">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off"
                   class="layui-input" v-model="articleTitle">
        </div>
        <p></p>
        <label class="layui-form-label">文章内容：</label>
        <div id="editor" style="display: grid">
            <p>hello</p>
        </div>

        <p></p>
        <label class="layui-form-label"></label>
        <div class="div-publish" style="display: inline-table;">
            <input type="button" class="btn-outline-danger color-ganger cursor-pointer publish" value="发布"
                   @click="publish()">
            <input type="button" class="btn-outline-danger color-ganger cursor-pointer publish" value="预览"
                   @click="preview()">
            <input type="button" class="btn-outline-danger color-ganger cursor-pointer publish" value="保存草稿"
                   @click="saveDraft()">
        </div>
    </form>





    <div id="preview" class="div-preview none" v-html="articleContent">

    </div>
</div>

<!--<pre class="layui-code">
    Lay.fn.event = function(modName, events, params){
      var that = this, result = null, filter = events.match(/\(.*\)$/)||[];
      var set = (events = modName + '.'+ events).replace(filter, '');
    };

</pre>-->


<!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
<script type="text/javascript" src="/static/plugins/wangEditor-3.1.0/wangEditor.min.js"></script>
<script type="text/javascript" src="/static/plugins/vue/vue.min.js"></script>
<script src="/static/js/knowledge-publish.js"></script>
<script type="text/javascript">
    layui.use(['code', 'form'], function () {

        layui.code();
        var form = layui.form;
    });

    var E = window.wangEditor;
    var editor = new E('#editor');
    // 或者 var editor = new E( document.getElementById('editor') )

    editor.customConfig.uploadImgServer = '/upload';  // 上传图片到服务器
    editor.create();

    /* document.getElementById('btn1').addEventListener('click', function () {
         // 读取 html
         console.log(editor.txt.html());

     }, false);
 */


</script>
</body>
<script src="/static/js/common/footer.js"></script>
<style>
    .div-publish {
        margin-top: 20px;
    }

    .div-preview {
        min-width: 1170px;
        min-height: 400px;
    }

    pre code {
        position: relative;
        padding: 15px;
        line-height: 20px;
        border: 1px solid #ddd;
        border-left-width: 6px;
        background-color: #F2F2F2;
        color: #333;
        font-family: Courier New;
        font-size: 12px;
    }

    .select-article-type {
        display: inline-block;
    }

    .w-e-text-container {
        height: 500px !important;
        /*max-height: 500px;*/
    }
    #editor div {
        z-index: 100 !important;
    }
</style>
</html>