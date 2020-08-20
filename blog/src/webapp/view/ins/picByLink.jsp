
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ins图片</title>
    <script src="${pageContext.request.contextPath}/static/plugins/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/vue/vue.min.js"></script>
    <script src="${pageContext.request.contextPath}${pageContext.request.contextPath}/static/js/common/http.js?v=1.0"></script>
    <script src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/static/plugins/layui/css/layui.css'>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/static/css/common.css'>
    <style>
        .string { color: green; }
        .number { color: darkorange; }
        .boolean { color: blue; }
        .null { color: magenta; }
        .key { color: red; }
        #link {
            width: 80%;
            height: 100px;
            line-height: 1.3;
            line-height: 38px\9;
            border: 1px solid #e6e6e6;
            background-color: #fff;
            border-radius: 2px;
            font-size: 30px;
        }
        #select {
            display: inline-block;
            height: 100px;
            line-height: 38px;
            padding: 0 18px;
            background-color: #009688;
            color: #fff;
            white-space: nowrap;
            text-align: center;
            border: none;
            border-radius: 2px;
            cursor: pointer;
            width: 150px;
            font-size: 30px;
            margin-top: -12px;
        }
    </style>
</head>
<body>
<div class="body">
    <div class="body-main vue-content" id="vue-content" style="width: 100% !important; left: 0 !important; margin-top: 0 !important; height: 100%">

        <div style="margin-top: 25%">
            <input id="link" type="text" name="title" autocomplete="off" placeholder="请输入链接" value="">
            <button id="select" type="button" class="" ontouchstart="ins.search()">查询</button>
        </div>

        <div style="text-align: center; margin-top: 10px; margin-bottom: 50px;">
            <div v-for="item in list" :key="item.displayUrl">
                <img :src="item.displayUrl" style="width: 100%;"/>
                <br><br>
            </div>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/ins.js"></script>
<script type="text/javascript">
    ins.init();
    layui.use(['layer'], function () {
        var layer = layui.layer;
    });

</script>

<script>

</script>
</html>
