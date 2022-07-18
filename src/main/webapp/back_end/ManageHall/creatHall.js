$(document).ready(function(){
	
    // 獲取預覽區塊
    let prBox = document.getElementById("prBox");
    // 獲取USER輸入區塊
    let inputCol = document.querySelector("#inputCol");
    let inputRow = document.querySelector("#inputRow");
    // 獲取預覽按鈕
    let previewBtn = document.getElementById("previewBtn");
    let total;
    
    // 展示座位
    function previewSeat (){
		document.getElementById("submitBtn").disabled=false;
		// 清空prbox
     	let child = prBox.lastElementChild;  
        while (child) { 
            prBox.removeChild(child); 
            child = prBox.lastElementChild; 
        } 
	
        // 獲取全部button數量
        total = inputCol.value * inputRow.value;
        // 設定索引值
        let seq = 0;
        // 讓更改狀態的input 顯示
        document.getElementsByClassName('statusinfo')[0].style.display="block";
        document.getElementsByClassName('statusinfo')[1].style.display="block";
        // 讓螢幕圖標顯示
        const screen = document.getElementById('screen');
        screen.style.display='block';
        //讓顏色範例圖標顯示
        document.getElementById('colorsample1').style.display='block';
        document.getElementById('colorsample2').style.display='block';
        document.getElementById('colorsample3').style.display='block';
        
        for(let row= 1 ; row <= inputRow.value ; row++){
            for(let col= 1 ; col <= inputCol.value&seq<=total ; col++){
                const btn = document.createElement('button');
                
                btn.id =seq;
                btn.setAttribute('class',"btn btn-success") ;
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
                seq++;
               }
            const br = document.createElement("br");
            prBox.append(br);
        }

        
    }

    // 預覽按鈕綁定click監聽
    previewBtn.addEventListener('click',previewSeat);
    
    document.getElementById('changeRow1').addEventListener('click',changeRow);
    // 變更某排狀態
    function changeRow({target}){
        // 每個row 索引值會相差的值為 5 * inputCol
        let rowIndexGap =Number(inputCol.value);
        const {id} = target ;
        const seq = id.substring(id.indexOf('changeRow') + 9);
        const nextSeq = Number(seq) + 1;
        // 獲取USER指定的座位排數
        let setRow =Number(document.querySelector('#setRow'+seq).value);
        let setIndex = (inputCol.value*(setRow-1));
        if(setRow > inputRow.value){
            alert('超過原先設置的排數!請重新輸入');
            document.querySelector('#setRow'+seq).value="";
            return;
        }
        // 獲取USER選擇的狀態
        let selectRow=document.querySelector('#selectRow'+seq);
        let selectValue = Number(selectRow.value);
        // 獲取座位按鈕陣列
        let seatarr =document.getElementsByName('seatBtn');
        // 判斷 0走道1可出售3保留位4維修中
        for(let i =0 ; i< inputCol.value ; i++){
            if(selectValue ===0)
            seatarr[setIndex].setAttribute('class',"btn btn-secondary");
            if(selectValue ===1)
            seatarr[setIndex].setAttribute('class',"btn btn-success");
            if(selectValue ===3)
            seatarr[setIndex].setAttribute('class',"btn btn-warning");
            
            setIndex += 1;
        }
        if (!document.querySelector('#changeRow' + nextSeq)) {   // 如果找不到 就呼叫add傳入nextSeq
            addRow(nextSeq);
        }

    }
    // for 更改某排使用
    function addRow(nextSeq){
        const div = document.createElement('div');
        div.setAttribute('class','statusinfo');
        div.id='rowStatus'+nextSeq;
        div.style.display='block';

        const input =  document.createElement('input');
        input.type='text';
        input.id ='setRow'+nextSeq;
        input.style.width='100px';
        input.style.margin='4.5px 4.5px 4.5px 0px';
        input.placeholder='請輸入排號';

        const select = document.createElement('select');
        select.id = 'selectRow'+nextSeq;
        select.style.marginRight='4.5px';
        select.innerHTML=
        '<option value="0">設為走道</option>'+
        '<option value="1">設為座位</option>'+
        '<option value="3">設為保留</option>';

        const button = document.createElement('button');
        button.type = 'button';
        button.id = 'changeRow'+nextSeq;
        button.textContent='更改整排';
        // 更改配置按鈕 綁定更改預覽顏色&更改底層字串
        button.addEventListener('click',changeRow);
        button.addEventListener('click',setSeatRow);
        div.append(input);
        div.append(select);
        div.append(button);
        document.querySelector('#rowStatus'+(nextSeq-1)).after(div);

    }

    document.getElementById('changeCol1').addEventListener('click',changeCol);
    // 變更某列狀態
    function changeCol({target}){
        // 每個row 索引值會相差的值為 5 * inputCol
        let rowIndexGap = Number(inputCol.value);
        const {id} = target ;
        const seq = id.substring(id.indexOf('changeCol') + 9);
        const nextSeq = Number(seq) + 1;
        // 獲取座位陣列
        let seatarr =document.getElementsByName('seatBtn');
        // 獲取USER指定的座位列數
        let setCol =Number(document.querySelector('#setCol'+seq).value);
        if(setCol > inputCol.value){
            alert('超過原先設置的列數!請重新輸入');
            document.querySelector('#setCol'+seq).value="";
            return
        }
        let setIndex = setCol-1;
        // 獲取USER選取的值
        let selectCol=document.querySelector('#selectCol'+seq);
        let selectValue = Number(selectCol.value);

       for(let i=0; i< inputRow.value ; i++){
           if(selectValue ===0)
           seatarr[setIndex].setAttribute('class',"btn btn-secondary");
           if(selectValue ===1)
           seatarr[setIndex].setAttribute('class',"btn btn-success");
           if(selectValue ===3)
           seatarr[setIndex].setAttribute('class',"btn btn-warning");
          
           setIndex += rowIndexGap;
       }  
       if(!document.querySelector('#changeCol'+nextSeq)){
           addCol(nextSeq);
       }
    }
    // for 更改某列使用
    function addCol(nextSeq){
        const div = document.createElement('div');
        div.setAttribute('class','statusinfo');
        div.id='colStatus'+nextSeq;
        div.style.display='block';

        const input =  document.createElement('input');
        input.type='text';
        input.id ='setCol'+nextSeq;
        input.style.width='100px';
        input.style.marginRight='4.5px'
        input.placeholder='請輸入列號';

        const select = document.createElement('select');
        select.id = 'selectCol'+nextSeq;
        select.style.marginRight='4.5px';
        select.innerHTML=
        '<option value="0">設為走道</option>'+
        '<option value="1">設為座位</option>'+
        '<option value="3">設為保留</option>';

        const button = document.createElement('button');
        button.type = 'button';
        button.id = 'changeCol'+nextSeq;
        button.textContent='更改整列';
        button.addEventListener('click',changeCol);
        button.addEventListener('click',setSeatCol);

        div.append(input);
        div.append(select);
        div.append(button);

        document.querySelector('#colStatus'+(nextSeq-1)).after(div);

    }
    
    // ******************************************座位字串處理區*********************************************************
    const seatDBStr = document.getElementById('seatDBStr');
    const seatDBCount =document.getElementById('seatDBCount')
    let seat="";
    
    const colIndexGap = 5; // 每一個座位的 同位置索引值相差5
    
    
    previewBtn.addEventListener('click',setSeatOringinal);
    // 預覽時抓取初始化的排列參數 , 生成初始座位字串
    function setSeatOringinal(){
        let status1 = "1";
        for(let i =1 ; i <= inputRow.value ; i++ ){
            for(let j =1 ; j <= inputCol.value ; j++){
                let rowNum="";
                let colNum="";
                if(i<10){
                    rowNum = "0"+i;
                }else{
                    rowNum += i;
                }
                if(j<10){
                    colNum = "0"+j;
                }else{
                    colNum += j;
                }
                        
                seat += rowNum+colNum+status1;
            }
        }
        // console.log('原始字串:'+seat);
        return seat;
    }

    // 綁定變更排的 更改配置按鈕
    document.getElementById('changeRow1').addEventListener('click',setSeatRow);
    function setSeatRow({target}){
        // 每列 同位置 索引值會相差的值為 5 * inputCol.value
        let rowIndexGap =Number(inputCol.value)*5;
        const {id} = target ;
        const seq = id.substring(id.indexOf('changeRow') + 9);
        // 獲取USER指定的排數
        let setRow =Number(document.querySelector('#setRow'+seq).value);
        let setIndex = 4 + (rowIndexGap * (setRow-1));
        // 獲取USER選擇的狀態
        let selectRow=document.querySelector('#selectRow'+seq);
        let selectValue = Number(selectRow.value);
        // 將字串切割成陣列
        let seatArr = seat.split("",seat.length);

        for(let i=0 ; i < inputCol.value ; i++ ){
            if(selectValue ===0)
            seatArr[setIndex]=0;
            if(selectValue ===1)
            seatArr[setIndex]=1;
            if(selectValue ===3)
            seatArr[setIndex]=3;

            setIndex += colIndexGap;
        }
        seat="";
        for (const newStr of seatArr){
            seat += newStr ;
        }
        // console.log(`新${seq}字串:`+seat);
        return seat;
    }

    // 綁定變更列的 更改配置按鈕
    document.getElementById('changeCol1').addEventListener('click',setSeatCol);
    function setSeatCol({target}){
        // 每個row 索引值會相差的值為 5 * inputCol
        let rowIndexGap = Number(inputCol.value)*5;
        const {id} = target ;
        const seq = id.substring(id.indexOf('changeCol') + 9);
        // 獲取USER指定的座位列數
        let setCol =Number(document.querySelector('#setCol'+seq).value);
        let setIndex = 4+(5*(setCol-1));
        // 獲取USER選取的值
        let selectCol=document.querySelector('#selectCol'+seq);
        let selectValue = Number(selectCol.value);
        // 將字串切割成陣列
        let seatArr = seat.split("",seat.length);
        for(let i=0 ; i < inputRow.value ; i++ ){
            if(selectValue ===0)
            seatArr[setIndex]=0;
            if(selectValue ===1)
            seatArr[setIndex]=1;
            if(selectValue ===3)
            seatArr[setIndex]=3;

            setIndex += rowIndexGap;
        }
        seat="";
        for (const newStr of seatArr){
            seat += newStr ;
        }
        // console.log(`新${seq}字串:`+seat);
        return seat;
        
    }

    // 暫時綁定送出 -> 測試可以抓到最後修改完的字串
    document.getElementById("submitBtn").addEventListener('click',seatCounter);

    function seatCounter(){
        let seatArr =seat.split("",seat.length);
        // 座位狀態的索引值由4開始跳, 每次+5
        let index = 4;
        let seatCount=0;
        // row*col 總共有幾個格子 控制迴圈要遍歷幾次
        for(let i =0 ; i < (inputRow.value*inputCol.value) ; i++){
            // 若狀態 為1和3 進入計次
            if((seatArr[index]-1===0) || (seatArr[index]-3===0)){
                seatCount++;
            }
            index += 5;
        }
        seatDBCount.value = seatCount;
        seatDBStr.value = seat;
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

