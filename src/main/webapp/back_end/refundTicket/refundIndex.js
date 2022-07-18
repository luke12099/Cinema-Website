
// 監聽搜尋列並驗證USER有輸入數字
const searchInput= document.getElementById('searchInput');
searchInput.addEventListener('blur',()=>{
	if(!checkNum(searchInput.value)){
        Swal.fire(
                "請輸入正整數", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
        searchInput.value="";
    }

})
function checkNum(num){
    let regex = new RegExp( "^[0-9]*[1-9][0-9]*$");
    return regex.test(num);
}
/***************************************************************** */

// 監聽搜尋按鈕
const searchBtn = document.getElementById('searchBtn');
searchBtn.addEventListener('click',()=>{
	
	// 獲取USER想要查詢的電影票訂單編號
	let tkOrdID = document.getElementById('searchInput').value;
	if(!tkOrdID.length){
		Swal.fire(
                "請先輸入欲查詢的編號", //標題 
                "",
                "error"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
	};
	const tbody = document.getElementsByTagName('tbody')[0];
	
	// 每次AJAX請求都會清空上一次的搜尋結果
	let child = tbody.lastElementChild;  
        while (child) { 
            tbody.removeChild(child); 
            child = tbody.lastElementChild; 
        } 
	
	$.ajax({
			url: '/CGA102G1//RefundTicketServlet.do',
			type: 'post',                
			dataType:'json',
			data: {
				"action": "getDtByOrd",
				"tkOrdID": tkOrdID,
			},      
			error: function(xhr) { },    
			success: function(response) {
				
				// 獲取java回傳幾個明細物件 , 可知道要迭代幾次
				let totalLength = response.dtVOList.length
				if(!totalLength){
					Swal.fire(
                "查無結果!", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            	);
				}
				// 取出map裡裝著訂單明細VO的list
				let dtVOList = response.dtVOList;
				// 取出裝著每張票的中文票種的list
				let tkNameList = response.tkNameList;
				// 取出裝著每張票的方案名的list
				let actTitleList = response.actTitleList;
				// 取出場次的座位字串
				let seatStr = response.SH_SEAT_STATE;
				
				for(let i =0 ; i < totalLength ; i++){
					
					// 把座位狀態轉換為中文
					let seatState;
					if(dtVOList[i].state ===0)
						seatState = "已付款";
					if(dtVOList[i].state ===1)
						seatState = "已入場";
					if(dtVOList[i].state ===2)
						seatState = "已退票";
						
					// 把座位號碼轉成好讀的中文 O排O號
					let seatNumber;
					let seatIndex = Number(dtVOList[i].seat);
					seatNumber = 
					`${seatStr.slice(seatIndex-4,seatIndex-2)}排${seatStr.slice(seatIndex-2,seatIndex)}號`;
											
					tbody.insertAdjacentHTML('beforeend',`
	            <tr>
	                <td>${dtVOList[i].tkOrdID}</td>
	                <td>${dtVOList[i].tkDtID}</td>
	                <td>${tkNameList[i]}</td>
	                <td>${actTitleList[i]}</td>
	                <td>${seatNumber}</td>
	                <td>${dtVOList[i].sellPrice}</td>
	                <td>${seatState}</td>
	                <td><button class="btn btn-secondary" id="${dtVOList[i].tkDtID}" onclick="refund(this)">退票</button></td>
	            	<input type="hidden" id="seatIndex${dtVOList[i].tkDtID}" value="${seatIndex}">
	            	<input type="hidden" id="tkOrdID${dtVOList[i].tkDtID}" value="${dtVOList[i].tkOrdID}">
	            </tr>`)	
				}
				// 不是在JAVA轉換的資料 使用dt.dtVOlist.屬性名呼叫
				// 有在JAVA轉換的直接在dt.list[i] 用索引值呼叫
				
			}
		});
})

// 監聽退票按鈕
function refund(target){
	let tkDtID = target.id;
	let seatIndex = document.getElementById(`seatIndex${tkDtID}`).value;
	let tkOrdID = document.getElementById(`tkOrdID${tkDtID}`).value;
	// 取回DB內最新的座位字串
	let seatStr;
	$.ajax({
			url: '/CGA102G1//RefundTicketServlet.do',
			type: 'post',                
			dataType:'json',
			async:false,
			data: {
				"action": "getUpdatedDt",
				"tkOrdID": tkOrdID,
			},      
			error: function(xhr) { },    
			success: function(response) {
				
			seatStr = response.SH_SEAT_STATE;
			}
		})
	
	// 將字串切割成陣列
    let seatArr = seatStr.split("",seatStr.length);
    // 更改狀態
    seatArr[seatIndex]=1;
    seatStr="";
    for (const newStr of seatArr){
            seatStr += newStr ;
        };
		// 將改好的字串送回控制器
		$.ajax({
			url: '/CGA102G1//RefundTicketServlet.do',
			type: 'post',                
			dataType:'json',
			async:false,
			data: {
				"action": "updateOneDt",
				"tkDtID": tkDtID,
				"seatIndex": seatIndex,
				"tkOrdID": tkOrdID,
				"seatStr": seatStr,
				"seatState" : 2
			},      
			error: function(xhr) { },    
			success: function(response) {
				
				$(target).parent().prev().text("已退票");
				$(target).attr('disabled','true');
				Swal.fire(
                "訂單狀態已更新!", //標題 
                "",
                "success"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            	);
				// 推播給WebSocket
				sendMessage();
			}
		})
		
		
}

