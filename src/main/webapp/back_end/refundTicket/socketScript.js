let MyPoint = "/WebSocket_server/refundTicket_user";
let host = window.location.host;
let path = window.location.pathname;
let webCtx = path.substring(0, path.indexOf('/', 1));
let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
						//串接起來=> ws://localhost:8081/CGA102G1/WebSocket_server/backend_user

	let webSocket;

	function connect() {
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
		};
		// 此處收到來自server的session.getAsyncRemote().sendText(message);
		webSocket.onmessage = function(event) {
			console.log("退票頁面-收到推播!")
		};

		webSocket.onclose = function(event) {
		};
	}
	connect();
	
	function sendMessage() {
		
		let message ="退票頁面發出推播了!";
		let jsonObj = {
			"message" : message
		};
		// JSON API 轉成文字 送到SERVER 清空欄位 focus
		webSocket.send(JSON.stringify(jsonObj));
			
	}

	function disconnect() {
		webSocket.close();
	}