$(document).ready(function(){
	// 取得DB內的原始座位字串
    let seat=document.getElementById('hlSeat').value;
    // 備份給一個const
    const seatBc = seat;
    // 取得DB內的原始長寬
    let inputRow =document.getElementById('hlRow').value;
    let inputCol =document.getElementById('hlCol').value;
    // 取得DB內的原始座位數
    let hlSeatCount = document.getElementById('hlSeatCount').value;
    document.getElementById('showCount').value='目前可賣座位:'+hlSeatCount+'個';
    // 獲取預覽區塊
    let prBox = document.getElementById("prBox");
    // 配置索引值
    let seq =0;
    // 第一排第一個位置的狀態索引值必為4
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
            btn.id=seq;
            // btn.id = `${row}排${col}號`;
            btn.style.fontSize = '10px';
            btn.style.color='white';
            btn.style.width='55px';
            btn.style.padding='5px 0px';
            btn.style.margin ='3px'
            btn.innerText=`${row}排${col}號`;

            btn.addEventListener('click',selectedBtn);
            
            // debug專用:檢查按鈕的底層索引值
            // btn.innerText=seq;
            
            prBox.append(btn);
            seatIndex += 5;
            seq++
           }
        const br = document.createElement("br");
        prBox.append(br);
    }
    // 創建一陣列蒐集選中的座位物件
    const seatSelected = [];
    
    function selectedBtn(){
        // 獲取座位的資訊
        let text =this.innerText;
        // 加進 目前已選
        if(seatSelected.indexOf(this)===-1){
            document.getElementById('numberBox').innerText += '\n'+text  ;
            this.setAttribute('class','btn btn-outline-danger');
            // 利用bootstrap樣式改變 達到選取效果
            seatSelected.push(this);  
        }
    }

    document.getElementById('colorsample1').addEventListener('click',changeStatus)
    document.getElementById('colorsample2').addEventListener('click',changeStatus)
    document.getElementById('colorsample3').addEventListener('click',changeStatus)
    document.getElementById('colorsample4').addEventListener('click',changeStatus)
    
    // 改變狀態
    function changeStatus({target}){
        // 清空 目前已選div內的文字
        document.querySelector('#numberBox').innerHTML = "目前已選:";
        
        // 將字串切割成陣列
        let seatArr = seat.split("",seat.length);
        for(let i =0 ; i < seatSelected.length ; i++){
            let index = 4 + Number(seatSelected[i].id)*5

            if(Number(target.value) ===0){
                seatArr[index]=0;
                seatSelected[i].setAttribute('class',"btn btn-secondary");
            }
            if(Number(target.value) ===1){
                seatArr[index]=1;
                seatSelected[i].setAttribute('class',"btn btn-success");
            }
            if(Number(target.value) ===3){
                seatArr[index]=3;
                seatSelected[i].setAttribute('class',"btn btn-warning");
            }
            if(Number(target.value) ===4){
                seatArr[index]=4;
                seatSelected[i].setAttribute('class',"btn btn-danger");
            }

        }
        seat="";
        for (const newStr of seatArr){
            seat += newStr ;
        }
        // 更改結束,清空記錄用的陣列
        seatSelected.length=0;
		// 更新座位數
		seatCounter();
		// 把新的字串指定回hidden input
		document.getElementById('hlSeat').value=seat;
        return seat;

    }

    // 計算座位數
    function seatCounter(){
        let seatCount =0;
        let seatArr =seat.split("",seat.length);
        // 座位狀態的索引值由4開始跳, 每次+5
        let index = 4;
        
        // row*col 總共有幾個格子 控制迴圈要遍歷幾次
        for(let i =0 ; i < (inputRow*inputCol) ; i++){
            // 若狀態 為1和3 進入計次
            if((seatArr[index]-1===0) || (seatArr[index]-3===0)){
                seatCount++;
            }
            index += 5;
        }
        if(seatCount==0){
			Swal.fire(
                "沒有位置可以賣了!", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
		}
        document.getElementById('hlSeatCount').value=seatCount;
        document.getElementById('showCount').value='目前可賣座位:'+seatCount+'個';

    }

    // 重設回USER尚未更改前的配置
    // document.getElementById('re').addEventListener('click',re);

    // function re (){
    //     let bArr = document.getElementsByName('seatBtn');
    //     let seatArr = seatBc.split("",seatBc.length);
    //     let i =0;
    //     for(let row= 1 ; row <= inputRow ; row++){
    //         for(let col= 1 ; col <= inputCol ; col++){
                
    //             console.log(bArr);
    //             if(Number(seatArr[seatIndex]) ===0)
    //             bArr[i].setAttribute('class',"btn btn-secondary");
    //             if(Number(seatArr[seatIndex]) ===1)
    //             bArr[i].setAttribute('class',"btn btn-success");
    //             if(Number(seatArr[seatIndex]) ===3)
    //             bArr[i].setAttribute('class',"btn btn-warning");
    //             if(Number(seatArr[seatIndex]) ===4)
    //             bArr[i].setAttribute('class',"btn btn-danger");
    
    //             seatIndex += 5;
    //             i++;
    //            }
    //     }
    // }
})


