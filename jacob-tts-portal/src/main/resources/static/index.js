layui.use(['layer', 'element', 'form'], function () {
    var form = layui.form;
    var layer = layui.layer;

    //监听提交
    form.on('submit(formDemo)', function (data) {
        vm.text2audio();
        return false;
    });

    var vm = new Vue({
        el: '#app',
        data: {
            text: '',
            audioUrl: ''
        },
        methods: {
            text2audio: function () {
                var _this = this;
                _this.audioUrl = '';
                var index = layer.load();
                $.ajax({
                    method: 'GET',
                    url: '/api/tts/text2audio?text=' + _this.text
                }).done(function (res) {
                    if (res && res.code === '8888') {
                        _this.audioUrl = res.data;
                    }
                }).always(function () {
                    //关闭
                    layer.close(index);
                })
            },
            play: function () {
                if (this.audioUrl) {
                    var x = document.createElement("AUDIO");
                    x.setAttribute("src", this.audioUrl);
                    x.play();
                }
            }
        }
    });
});