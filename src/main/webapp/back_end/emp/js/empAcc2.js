// 新增帳戶
function addAccount(){
    // 區分前後區域
    const divNew = document.querySelector('#newAccount');
    // 新增帳戶的視窗
    const divInput = document.querySelector('#dataInput');
   	divInput.style.display = "block";
   	divNew.style.display = "block";
}

// 關閉新增帳戶的視窗
function windowClose(){
	const divNew = document.querySelector('#newAccount');
    // 新增帳戶的視窗
    const divInput = document.querySelector('#dataInput');
    const statusInput = document.querySelector('#statusInput');
   	statusInput.style.display = "none";
   	divInput.style.display = "none";
   	divNew.style.display = "none";
}

// 選擇顯示 所有人/在職/離職/留職停薪
const statuss = document.querySelectorAll('#empStatus');
document.querySelector('#showStatus').addEventListener('change', function(){
//	console.log(this.value);
	switch(this.value){
		case '-1':
			for(let status of statuss){
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
			}
			break;
		case '0':
			for(let status of statuss){
					status.parentElement.style.display = 'table-row';
					status.parentElement.className = 'showTable';
				if(status.innerText !== '離職'){
					status.parentElement.style.display = 'none'; 
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
		case '1':
			for(let status of statuss){
					status.parentElement.style.display = 'table-row';
					status.parentElement.className = 'showTable';
				if(status.innerText !== '在職'){
					status.parentElement.style.display = 'none';
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
		case '2':
			for(let status of statuss){
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
				if(status.innerText !== '留職停薪'){
					status.parentElement.style.display = 'none'; 
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
	}
	const showTables = document.querySelectorAll('.showTable');
	const last = showTables[showTables.length - 1];
	last.classList.add('lastTr');
	for(let i = 0; i < showTables.length; i++){
		if(i % 2 === 0){
			showTables[i].classList.add('oddTr');
		}else{
			showTables[i].classList.add('evenTr');
		}
	}
//	console.log(document.querySelectorAll('#empStatus')[0].innerText);
});

// 權限連動選取
function chooseAll(category){
    const funcs = document.querySelectorAll(`.${category.id}`);
//     console.log(category);
//     console.log(funcs);
    for(let fun of funcs){
        if(category.checked === true){
            fun.checked = true;
        }else{
            fun.checked = false;
        }
//        console.log(fun.checked);
    }
}
const buttonInStatus = document.querySelectorAll('#statusInput>form>button');
// 修改員工狀態
function reviseStatus(e){
	const memberStatus = e.parentElement.parentElement.firstElementChild.nextElementSibling.nextElementSibling.innerText;
	for(let btn of buttonInStatus){
		console.log(btn.innerText);
		console.log(memberStatus);
		if(btn.innerText === memberStatus){
			btn.style.display = 'none';
		} else {
			btn.style.display = 'inline-block';
		}
	}
	const memberId = e.parentElement.parentElement.firstElementChild.innerText;
    // 區分前後區域
    const divNew = document.querySelector('#newAccount');
    // 新增帳戶的視窗
    const statusInput = document.querySelector('#statusInput');
   	statusInput.style.display = "block";
   	divNew.style.display = "block";
   	// 將員工編號傳進 input
   	document.querySelector('#statusInputEmpNo').value = memberId;
}
// 將員工編號狀態傳進 input
function setStatus(e){
	let status;
	switch(e.innerText){
		case '離職':status = 0;break;
		case '在職':status = 1;break;
		case '留職停薪':status = 2;break;
	}
	document.querySelector('#statusInputEmpStatus').value = status;
}