var details = {};

details.init = function () {

    details.vm.getArticle();

    common.checkStatus1(function (result) {
        if (result.code === 0) {
            $('.div-no-login').addClass("none");
        }
    });
};

details.vm = new Vue({
    el: '#vue-content',
    data: {
        article: {
            id: null
        },
        comment: {
            comment: null

        }

    },
    methods: {
        getArticle: function () {
            var vm = details.vm;
            var url = '/knowledge/article/' + vm.article.id;
            var data = null;
            http.get(url, data, function (result) {

                if (result.code !== 0) {
                    layer.msg("获取文章失败");
                    return;
                }
                vm.article = result.data;

            });
        },
        /** 评论 */
        commitComment: function () {
            // layer.msg('暂不可评论', {offset: '200px'});
            var vm = details.vm;
            var url = '/knowledge/article/comment';
            vm.comment.articleId = vm.article.id;
            var data = vm.comment;
            http.post(url, data, function (result) {

                if (result.code !== 0) {
                    layer.msg("评论失败");
                    return;
                }
                layer.msg("评论成功");

            });
            return;
        }
    },
    filters: {
        date: function (value, fmt) {
            if (null === value) {
                return null;
            }
            return new Date(value).Format(fmt);
        }
    }
});