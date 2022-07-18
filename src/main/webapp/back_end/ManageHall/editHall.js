$(document).ready(function(){
    const seat= document.getElementById('hlSeat').value;
    const inputRow = document.getElementById('hlRow').value
    const inputCol = document.getElementById('hlCol').value;
    // 獲取預覽區塊
    let prBox = document.getElementById("prBox");

    let seatIndex = 4;
    // 原始按鈕生成
    for(let row= 1 ; row <= inputRow ; row++){
        for(let col= 1 ; col <= inputCol ; col++){

            const btn = document.createElement('button');

            if(Number(seat[seatIndex]) ===0)
            btn.setAttribute('class',"btn btn-secondary");
            if(Number(seat[seatIndex]) ===1)
            btn.setAttribute('class',"btn btn-success");
            if(Number(seat[seatIndex]) ===3)
            btn.setAttribute('class',"btn btn-warning");
            if(Number(seat[seatIndex]) ===4)
            btn.setAttribute('class',"btn btn-danger");

            btn.name ='seatBtn';
            btn.style.fontSize = '10px';
            btn.style.color='white';
            btn.style.width='55px';
            btn.style.padding='5px 0px';
            btn.style.margin ='3px'
            btn.innerText=`${row}排${col}號`;
            
            // debug專用:檢查按鈕的底層索引值
            // btn.innerText=seq;
            
            prBox.append(btn);
            seatIndex += 5;
           }
        const br = document.createElement("br");
        prBox.append(br);
    }
        // 驗證影廳名稱不可為空白
    const nameId = document.getElementById('nameId');
    nameId.addEventListener('blur',()=>{
		if(nameId.value.trim().length === 0){
			Swal.fire(
		  '請輸入影廳名稱',
		  '',
		  'warning'
		)
			nameId.value ="";
		}
	
})
})


