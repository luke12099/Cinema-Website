/***********************片名不可空驗證******************************/
const mvName = document.getElementById('mvName');
mvName.addEventListener("blur",()=>{
        if(mvName.value.length===0){
            mvName.setAttribute('class',"form-control is-invalid")
            Swal.fire(
                "此欄位不可為空", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
        }else
        mvName.setAttribute('class',"form-control is-valid")
    })
/***********************英文片名不可空驗證******************************/
const mvEName = document.getElementById('mvEName');
mvEName.addEventListener("blur",()=>{
    let regex = new RegExp("^[A-Za-z0-9\u4e00-\u9fa5] $");
    
    if(mvEName.value.length===0){
            mvEName.setAttribute('class',"form-control is-invalid");
            Swal.fire(
                "此欄位不可為空", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
        }else if(/[\u4E00-\u9FA5]/g.test(mvEName.value)){
			mvEName.setAttribute('class',"form-control is-invalid");
			mvEName.value="";
			 Swal.fire(
                "此欄位不可填入中文", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
    	}else {
	
        	mvEName.setAttribute('class',"form-control is-valid");
		}

   
    })
/*******************上映日驗證:上映日只能大於等於當天******************************/
const stDate = document.getElementById('stDate');

stDate.addEventListener('change',()=>{
	let nowDate= new Date().toISOString().split('T')[0];
	if(stDate.value >= nowDate){
		stDate.setAttribute('class',"form-control is-valid");
		document.getElementById('edDate').removeAttribute('disabled');
	}else{
		stDate.setAttribute('class',"form-control is-invalid");
		Swal.fire(
                "上映日不得小於今日", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
		stDate.value="";
	}
})
/*******************下檔日驗證:下檔日只能大於等於上映日******************************/    
const edDate = document.getElementById('edDate');
edDate.addEventListener('change',()=>{
	if(edDate.value >= stDate.value){
		edDate.setAttribute('class',"form-control is-valid");
	}else{
		edDate.setAttribute('class',"form-control is-invalid");
		Swal.fire(
                "下檔日不可小於上檔日", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
		edDate.value="";
	}
})
/***********************導演不可空驗證******************************/
const mvDrt = document.getElementById('mvDrt');
mvDrt.addEventListener("blur",()=>{
        if(mvDrt.value.length===0){
            mvDrt.setAttribute('class',"form-control is-invalid")
        }else
        mvDrt.setAttribute('class',"form-control is-valid")
    })
/***********************演員不可空驗證******************************/
const mvCast = document.getElementById('mvCast');
mvCast.addEventListener("blur",()=>{
        if(mvCast.value.length===0){
            mvCast.setAttribute('class',"form-control is-invalid")
        }else
        mvCast.setAttribute('class',"form-control is-valid")
    })
/***********************片長僅能輸入正整數驗證******************************/
const mvLong = document.getElementById('mvLong');
mvLong.addEventListener('blur',()=>{
	if(!checkNum(mvLong.value)){
        Swal.fire(
                "請輸入正整數", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
        mvLong.value="";
        mvLong.setAttribute('class',"form-control is-invalid");
    }else
    mvLong.setAttribute('class',"form-control is-valid");

})
function checkNum(num){
    let regex = new RegExp( "^[0-9]*[1-9][0-9]*$");
    return regex.test(num);
}


/***********************上傳圖片預覽******************************/
document.getElementById('mvPc').addEventListener('change',uploadListner);
function uploadListner({target}){
            // 取得target的files物件
            const tFile = target.files[0];
            const img =document.querySelector("#pvImg");
            img.src = URL.createObjectURL(tFile);
            
        }
        

/* ***********************電影細節不可超過500字********************************* */



    