$()
var webSocket = null;
var host = document.location.host;
//判断当前浏览器是否支持WebSocket

if ('WebSocket' in window) {
    websocket = new WebSocket('ws://'+host+'/mm-dorado/webSocket/'+username);
} else {
    alert('当前浏览器 Not support websocket')
}
//连接发生错误的回调方法
websocket.onerror = function() {
    alert("WebSocket连接发生错误")
    setMessageInnerHTML("WebSocket连接发生错误");
};

webSocket.onmessage = function (event) {
    if(typeof event.data === String) {
        console.log("Received data string");
    }

    if(event.data instanceof ArrayBuffer){
        var buffer = event.data;
        console.log("Received arraybuffer");
    }
}
//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    document.getElementById('message').innerHTML += innerHTML + '<br/>';
}


var sendMsg = function () {
    let msg = document.getElementById("msg");
    webSocket.send(msg);
}