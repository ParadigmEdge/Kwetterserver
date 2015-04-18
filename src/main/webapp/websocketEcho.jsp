<%-- 
    Document   : websocketEcho
    Created on : Apr 18, 2015, 1:39:51 PM
    Author     : RY Jin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8" />  
<title>WebSocket page</title> 
<script language="javascript" type="text/javascript">
    //references #output
    var output;
    var wsUri = "ws://localhost:23761/Kwetter_Server/websocket";
    //connects the websocket and inits output
    function init() {
        output = document.getElementById("output");
        websocket = new WebSocket(wsUri);
        websocket.onopen = function(evt){
            onOpen(evt);
        };
        websocket.onclose = function(evt){
            onClose(evt);
        };
        websocket.onmessage = function(evt){
            onMessage(evt);
        };
        websocket.onerror = function(evt){
            onError(evt);
        };
    }
    
    //define event handlers
    function onOpen(evt) {
        writeToScreen("CONNECTED");
    }
    function onWindowClose(evt){
        websocket.close();
    }
    function onClose(evt){
        writeToScreen("DISCONNECTED");
    }
    function onMessage(evt){
        //convert json to javascript object
        var message = JSON.parse(evt.data);
        //write message.text to screen
        writeToScreen('<span style="color: green;">RESPONSE: ' + message.text + '</span>');
    }
    
    function onError(event){
        writeToScreen('<span style="color: red;">ERROR:</span> ' + event.data);
    }
    
    function doSend(message){
        writeToScreen("SENT: " + message);
        var txt = message;
        websocket.send(txt);
    }
    
    //appends text to #output
    function writeToScreen(text) {
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = text;
        output.appendChild(pre);
    }
    
    //invoke init() on load
    window.addEventListener("load", init, false);
    
    //handles [Enter] in #textforws/clicks on #sendButton
    function keyPressed(event){
        if(event.keyCode === 13){
            document.getElementById("sendButton").click();
            document.getElementById("textforws").value='';
        }
    }
</script>  
    <h2>websocket</h2>
    Enter text:<input id="textforws" type="text" autofocus="true" onkeypress="return keyPressed(event);"> 
    <button id="sendButton" onclick='doSend(document.getElementById("textforws").value);'>send</button>
    <div id="output"/>
</html>
