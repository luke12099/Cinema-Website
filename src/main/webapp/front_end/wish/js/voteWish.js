const voteMvName = document.querySelector('#voteMvName');
const voteMvId = document.querySelector('#voteMvId');

// 新增帳戶
function windowOpen(e){
	// 電影標題
	voteMvName.innerText = e.parentElement.parentElement.firstElementChild.innerText;
	// 電影編號
	voteMvId.value = e.parentElement.parentElement.parentElement.previousElementSibling.value;
    // 區分前後區域
    const divPlate = document.querySelector('#divPlate');
    // 新增帳戶的視窗
    const dbConfirm = document.querySelector('#dbConfirm');
   	divPlate.style.display = "block";
   	dbConfirm.style.display = "block";
}

// 關閉新增帳戶的視窗
function windowClose(){
	const divPlate = document.querySelector('#divPlate');
    // 新增帳戶的視窗
    const dbConfirm = document.querySelector('#dbConfirm');
   	dbConfirm.style.display = "none";
   	divPlate.style.display = "none";
}


//const clickImgs = document.querySelectorAll('.clickImg');
//for(let clickImg of clickImgs){
//	addEventListener('click', function(e){
//		console.log(e.target.parentElement.parentElement.parentElement);
//	});
//}


