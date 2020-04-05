layui.use(['layer', 'element', 'form'], function () {
    var form = layui.form;

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
                $.ajax({
                    method: 'GET',
                    url: '/api/tts/text2audio?text=' + _this.text
                }).done(function (msg) {
                    _this.audioUrl = msg;
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