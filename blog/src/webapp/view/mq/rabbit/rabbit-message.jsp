<%--
  Created by IntelliJ IDEA.
  User: wenxianm
  Date: 2019/11/4
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>rabbitmq消息</title>
    <script src="${pageContext.request.contextPath}/static/plugins/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/plugins/vue/vue.min.js"></script>
    <script src="${pageContext.request.contextPath}${pageContext.request.contextPath}/static/js/common/http.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/common/common.js"></script>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/static/plugins/layui/css/layui.css'>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/static/css/common.css'>
    <style>
        .string { color: green; }
        .number { color: darkorange; }
        .boolean { color: blue; }
        .null { color: magenta; }
        .key { color: red; }
    </style>
</head>
<body>
<div class="body">
    <div class="body-main vue-content" id="vue-content" style="width: 95% !important; left: 0 !important; margin-top: 0 !important;">
        <fieldset class="layui-elem-field layui-field-title">
            <legend>消息列表</legend>
        </fieldset>
        <div>
            <table class="layui-table layui-table-template"  >
                <thead>
                <tr style="height: 31px;">
                    <th>消息ID</th>
                    <th style="width: 20%;">消息状态</th>
                    <th>消息内容</th>
                    <th>发送时间</th>
                    <th>消费时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="(d, index) in list">
                    <td>
                        {{d.id}}
                    </td>
                    <td style="width:20%;">
                        <span v-if="d.status == 0" class="color-orange">{{d.statusStr}}</span>
                        <span v-if="d.status == 1" class="color-green">{{d.statusStr}}</span>
                        <span v-if="d.status == -1" class="color-red">{{d.statusStr}}</span>
                    </td>
                    <td>
                        <span @click="rabbit.showMessageDetails(index)" class="cursor-pointer">查看</span>
                    </td>
                    <td>
                        {{d.sendTime}}
                    </td>
                    <td>
                        {{d.consumeTime}}
                    </td>
                    <td>
                        重新发送
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="page" style="text-align: center; margin-top: 10px;"></div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/static/js/rabbit.js"></script>
<script>
    rabbit.init();

    layui.use(['layer', 'laypage', 'table'], function () {

        var layer = layui.layer;
        var laypage = layui.laypage;
        var table = layui.table;

        laypage.render({
            elem: 'page'
            , count: rabbit.vm.totalSize
            ,limit: 15
            , theme: '#1E9FFF'
            , jump: function (obj, first) {
                if (!first) {
                    rabbit.vm.page = obj.curr;

                }
            }
        });
    });


    layui.use('code', function () {

        layui.code();
    });

</script>

<script>

</script>
</html>
