<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <title>音乐分享</title>
</head>
<script src="../../static/js/common/header.js"></script>
<body>
<div class="body">
    <div class="body-main vue-content">
        <!--<p class="center weight" style="margin-top: 100px">音乐分享开发中...</p>-->
        <div>
            <div style="height: 10px;"></div>
            <blockquote class="layui-elem-quote background-color-fff">音乐推荐</blockquote>
            <div>
                <table class="layui-table layui-table-template" lay-size="sm" lay-skin="line">
                    <thead>
                    <tr style="height: 31px;">
                        <th >播放</th>
                        <th style="width: 70%;">歌名</th>
                        <th >大小</th>
                        <th >收藏</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(d, index) in list">
                        <td>
                            <i class="layui-icon cursor-pointer">&#xe652;</i>
                        </td>
                        <td style="width:70%;">
                            {{d.name}}
                        </td>
                        <td>
                            03:41
                        </td>
                        <td>
                            <i class="layui-icon cursor-pointer">&#xe658;</i>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="music-player">
            <audio preload="auto" controls>
                <source src="/blog/database/test.mp3">
            </audio>
        </div>
    </div>
</div>
</body>
<script src="../../static/js/common/footer.js"></script>
</html>
<script src="/static/js/music.js"></script>
<script src="/static/plugins/audioplayer/js/audioplayer.min.js"></script>
<script>
    music.init();
</script>


<style>
    .music-player {
        position: fixed;
        bottom: 80px;
        left: 120px;
    }
</style>