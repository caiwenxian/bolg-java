var rabbit = {};
rabbit.init = function () {
    rabbit.vm.listRabbitMessage();
};

rabbit.vm = new Vue({
    el: '.vue-content',
    data: {
        list: null,
        search: null,
        page: 1,
        totalSize: 0
    },
    created: function () {
        // this.getrabbitList();
        // this.listHotSong();
    },
    methods: {
        /**获取消息列表*/
        listRabbitMessage: function () {
            var url = '/rabbitmq/listRabbitMessage/' +  this.page;
            http.get(url, null, function (result) {
                rabbit.vm.list = result.data.data;
                rabbit.vm.totalSize = result.data.totalSize;
            });
        },
        /* 搜索 */
        search: function () {
            var url = '/';
            http.get();
        },

    },
    computed: {
        rabbitPage: function () {
            return this.page;
        }
    },
    watch: {
        rabbitPage: function () {
            this.listRabbitMessage();
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

/** 查看消息详细内容 */
rabbit.showMessageDetails = function (index) {
    var message = rabbit.vm.list[index].data;
    try {
        message = JSON.parse(message);
        message = JSON.stringify(message, null, "\t");
        message = message.replace(/\\"/g, "'");
        message = jsonShowFn(message);
    }catch (e) {

    }

    layer.open({
        type: 1,
        area: ['900px', '450px'],
        offset: '70px',
        title: '预览',
        shadeClose: true,
        content: '<div><pre>'+ message +'</pre></div>',
        success: function () {

        }
    });
};


function jsonShowFn(json){
    if (!json.match("^\{(.+:.+,*){1,}\}$")) {
        return json           //判断是否是json数据，不是直接返回
    }

    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function(match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}