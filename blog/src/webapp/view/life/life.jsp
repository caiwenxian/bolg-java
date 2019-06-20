<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset=utf-8>
    <title>生活点滴</title>
</head>
<script src="../../static/js/common/header.js"></script>
<body>
<div class="body">
    <div class="body-main vue-content">
        <!-- <p class="center weight" style="margin-top: 100px">生活点滴开发中...</p> -->
        <div class="layui-carousel" id="carousel" lay-filter="carousel">
            <div carousel-item="" class="carousel-content">
                <div><img src="/blog/static/images/carousel-1.jpg"></div>
                <div><img src="/blog/static/images/carousel-2.jpg"></div>
                <div><img src="/blog/static/images/carousel-3.jpg"></div>
                <div><img src="/blog/static/images/carousel-4.jpg"></div>
                <div><img src="/blog/static/images/carousel-5.jpg"></div>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
            <legend>相册</legend>
        </fieldset>


    </div>
</div>
</body>
<script src="../../static/js/common/footer.js"></script>
</html>
<script src="/static/js/life.js"></script>
<script src="/static/js/common/http.js"></script>
<script>
    life.init();
</script>

<style>
    .carousel-content img {
        width: 100%;
        height: 100%;
    }

    .layui-timeline-axis {
        background-color: #f5f5f5;
    }
</style>