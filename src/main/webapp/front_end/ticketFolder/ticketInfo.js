
/************************************* 退餐 取餐 區域 *************************************************** */

const tkOrdID = document.getElementById('tkOrdID').value;

function cancelMeal(){
    Swal.fire({
        title: '您確定要取消餐飲訂單嗎?',
        text: "",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText: '返回',
        confirmButtonText: '取消這筆訂單'
      }).then((result) => {
        if (result.isConfirmed) {
          	
          	$.ajax({
			url: '/CGA102G1/TkFolderServlet.do',
			type: 'post',                
			dataType:'json',
			async:false,
			data: {
				"action": "changeMealState",
				"fdState" :2,
				"tkOrdID": tkOrdID,
			},      
			error: function(xhr) { 
			},    
			success: function(response) {
			
			// 每次AJAX請求都會清空上一次的搜尋結果
			const tbody = document.getElementsByTagName('tbody')[1];
			let child = tbody.lastElementChild;  
		        while (child) { 
		            tbody.removeChild(child); 
		            child = tbody.lastElementChild; 
		        }
		         
		    let foodOrdList = response.foodOrdList;
		    let foodNameList = response.foodNameList;
		    for(let i =0 ; i < foodOrdList.length ; i++){
				
				let ordState;
				if (foodOrdList[i].fdState === 0)
					ordState = "已付款";
				if (foodOrdList[i].fdState === 1)
					ordState = "已取餐";
				if (foodOrdList[i].fdState === 2)
					ordState = "已退餐";
				
				
				tbody.insertAdjacentHTML('beforeend',`
	            <tr>
	                <td>${foodNameList[i]}</td>
	                <td>${foodOrdList[i].fdCount}</td>
	                <td>${foodOrdList[i].sellPrice}</td>
	                <td>${ordState}</td>
	            </tr>`)	
			
			}
			// 成功退餐後 禁用按鈕
			document.getElementById('takeMeal').setAttribute('disabled','true');
			
			// 顯示通知
			 Swal.fire(
            '取消成功!',
            '您的餐飲訂單已取消',
            'success'
          	)
			}
		})
        }
      })
}
 //領餐驗證
function takeMeal(){
	Swal.fire({
		title: '請輸入員工驗證碼',
		text:'請將畫面交由領餐櫃檯員工',
		input: 'text',
		inputAttributes: {
			autocapitalize: 'off'
		},
		showCancelButton: true,
		confirmButtonText: '送出',
		cancelButtonText: '返回',
		showLoaderOnConfirm: true,
		preConfirm: (code) => {
			
			$.ajax({
			url:'/CGA102G1/TkFolderServlet.do',
			type:'post',
			dataType:'json',
			async:false,
			data: {
				"action": "verifyCode",
				"code" :code,
			},   
			error: function(xhr) { 
			},    
			success: function(response) {
				if(response===0){
					Swal.fire(
						'驗證失敗!',
						'請重新輸入驗證碼',
						'error'
					)

				}else{
					$.ajax({
						url: '/CGA102G1/TkFolderServlet.do',
						type: 'post',
						dataType: 'json',
						async: false,
						data: {
							"action": "changeMealState",
							"fdState": 1,
							"tkOrdID": tkOrdID,
						},
						error: function(xhr) {
						},
						success: function(response) {

							// 每次AJAX請求都會清空上一次的搜尋結果
							const tbody = document.getElementsByTagName('tbody')[1];
							let child = tbody.lastElementChild;
							while (child) {
								tbody.removeChild(child);
								child = tbody.lastElementChild;
							}

							let foodOrdList = response.foodOrdList;
							let foodNameList = response.foodNameList;
							for (let i = 0; i < foodOrdList.length; i++) {

								let ordState;
								if (foodOrdList[i].fdState === 0)
									ordState = "已付款";
								if (foodOrdList[i].fdState === 1)
									ordState = "已取餐";
								if (foodOrdList[i].fdState === 2)
									ordState = "已退餐";


								tbody.insertAdjacentHTML('beforeend', `
					            <tr>
					                <td>${foodNameList[i]}</td>
					                <td>${foodOrdList[i].fdCount}</td>
					                <td>${foodOrdList[i].sellPrice}</td>
					                <td>${ordState}</td>
					            </tr>`)

							}
							// 成功取餐後 禁用按鈕
							document.getElementById('takeMeal').setAttribute('disabled', 'true');
							document.getElementById('cancelMeal').setAttribute('disabled', 'true');

							// 顯示通知
							Swal.fire(
								'兌換成功!',
								'請享受美好的食物和電影',
								'success'
							)
						}
					})

					}
				}
			})

		}
	})
}
/*********************************************退票 驗票 區域******************************************* */

// 監聽退票按鈕
function refund(target){
	
	Swal.fire({
		title: '您確定要退票嗎?',
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		cancelButtonText: '取消',
		confirmButtonText: '是的，我確定要退票'
	}).then((result) => {
		if (result.isConfirmed) {
			
			let tkDtID = target.id;
			let seatIndex = document.getElementById(`seatIndex${tkDtID}`).value;
			let tkOrdID = document.getElementById('tkOrdID').value;
			// 取回DB內最新的座位字串
			let seatStr;
			$.ajax({
				url: '/CGA102G1//RefundTicketServlet.do',
				type: 'post',
				dataType: 'json',
				async: false,
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
			let seatArr = seatStr.split("", seatStr.length);
			// 更改狀態
			seatArr[seatIndex] = 1;
			seatStr = "";
			for (const newStr of seatArr) {
				seatStr += newStr;
			};
			// 將改好的字串送回控制器
			$.ajax({
				url: '/CGA102G1//RefundTicketServlet.do',
				type: 'post',
				dataType: 'json',
				async: false,
				data: {
					"action": "updateOneDt",
					"tkDtID": tkDtID,
					"tkOrdID": tkOrdID,
					"seatStr": seatStr,
					"seatState": 2
				},
				error: function(xhr) { },
				success: function(response) {

					// 改變前端欄位狀態 鎖定退票按鈕
					$(target).parent().prev().text("已退票");
					$(target).attr('disabled', 'true');
					// 改變modal內的狀態和 鎖定入場按鈕
					$(`#getin${tkDtID}`).attr('disabled', 'true');
					$(`#getin${tkDtID}`).parent().prev().text("已退票");

					// 通知USER已完成
					Swal.fire(
						"訂單狀態已更新!", //標題 
						"",
						"success"
						//圖示(可省略) success/info/warning/error/question
						//圖示範例：https://sweetalert2.github.io/#icons
					);

				}
			})





		}
	})
	
}
// 驗票
function tkChecking(){
		
		Swal.fire({
		title: '請輸入員工驗證碼',
		text:'請將畫面交由驗票員工',
		input: 'text',
		inputAttributes: {
			autocapitalize: 'off'
		},
		showCancelButton: true,
		confirmButtonText: '送出',
		cancelButtonText: '返回',
		showLoaderOnConfirm: true,
		preConfirm: (code) => {
			$.ajax({
			url:'/CGA102G1/TkFolderServlet.do',
			type:'post',
			dataType:'json',
			async:false,
			data: {
				"action": "verifyCode",
				"code" :code,
			},   
			error: function(xhr) { 
			},
			success: function(response){
				
				if(response===1){
					// 顯示 入場modal
					$('#exampleModal').modal('show');
					
				}else{
					Swal.fire(
	                "驗證碼錯誤!", //標題 
	                "",
	                "error"
	                //圖示(可省略) success/info/warning/error/question
	                //圖示範例：https://sweetalert2.github.io/#icons
            	);
				}
			}
			
			})    

			}
		})
}
// 入場
function admission(target){
	let tkDtID =document.getElementById(`tkDtId_${target.id}`).value;
	$.ajax({
		url: '/CGA102G1/TkFolderServlet.do',
		type: 'post',
		dataType: 'json',
		async: false,
		data: {
			"action": "updateAdmission",
			"tkDtID": tkDtID,
			"seatState": 1
		},
		error: function(xhr) { },
		success: function(response) {
			// 改變前端欄位狀態 鎖定退票按鈕
			$(`#${tkDtID}`).parent().prev().text("已入場");
			$(`#${tkDtID}`).attr('disabled', 'true');
			// 改變modal內的狀態和 鎖定入場按鈕
			$(target).attr('disabled', 'true');
			$(target).parent().prev().text("已入場");

			// 通知USER已完成
			Swal.fire(
				'驗票完成!',
				'HireMe影城祝您觀影愉快!',
				'success'
			)

		}
	})
	
}
