/***********************片名英文數字驗證******************************/
const mvEName = document.getElementById('mvEName');
mvEName.addEventListener("blur",()=>{
    
     if (mvEName.value.replace(/\p{sc=Han}/gu,'')){
    }
    else{
        Swal.fire(
                "請勿輸入中文", //標題 
                "",
                "warning"
                //圖示(可省略) success/info/warning/error/question
                //圖示範例：https://sweetalert2.github.io/#icons
            );
        mvEName.value="";
    }
    })