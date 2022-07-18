//// 新增帳戶
//function addAccount(){
//    // 區分前後區域
//    const divNew = document.querySelector('#newAccount');
//    // 新增帳戶的視窗
//    const divInput = document.querySelector('#dataInput');
//   	divInput.style.display = "block";
//   	divNew.style.display = "block";
//}
//
//// 關閉新增帳戶的視窗
//function windowClose(){
//	const divNew = document.querySelector('#newAccount');
//    // 新增帳戶的視窗
//    const divInput = document.querySelector('#dataInput');
//   	divInput.style.display = "none";
//   	divNew.style.display = "none";
//}

const statuss = document.querySelectorAll('#faq_class');
document.querySelector('#showfaqclass').addEventListener('change', function() {
	console.log(this.value);
	switch (this.value) {
		case '-1':
			for (let status of statuss) {
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
			}
			break;
		case '0':
			for (let status of statuss) {
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
				if (status.innerText !== '會員相關問題') {
					status.parentElement.style.display = 'none';
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
		case '1':
			for (let status of statuss) {
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
				if (status.innerText !== '影城相關問題') {
					status.parentElement.style.display = 'none';
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
		case '2':
			for (let status of statuss) {
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
				if (status.innerText !== '電影上映相關問題') {
					status.parentElement.style.display = 'none';
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
		case '3':
			for (let status of statuss) {
				status.parentElement.style.display = 'table-row';
				status.parentElement.className = 'showTable';
				if (status.innerText !== '其他問題') {
					status.parentElement.style.display = 'none';
					status.parentElement.className = 'noShowTable';
				}
			}
			break;
	}
	const showTables = document.querySelectorAll('.showTable');
	const last = showTables[showTables.length - 1];
	last.classList.add('lastTr');
	for (let i = 0; i < showTables.length; i++) {
		if (i % 2 === 0) {
			showTables[i].classList.add('oddTr');
		} else {
			showTables[i].classList.add('evenTr');
		}
	}
	console.log(document.querySelectorAll('#faq_class')[0].innerText);
});
//
//// 新增員工
//function addEmp(){
////    const number = parseInt(document.querySelector('#number').value);
////    const password = document.querySelector('#password').value;
////    const ename = document.querySelector('#ename').value;
////    const status = document.querySelector('#status').value;
////    const newEmp = {
////        'id': number,
////        'name': ename,
////        'status': status,
////        'password': password
////    }
////    empList.push(newEmp);
////    close();
//}
//
//// 刪除員工
//function deleteEmp(id){
//	alert('刪除: ' + id);
////    let result = confirm('刪除後無法復原，確認刪除該員工?');
////    if(!result){
////        return;
////    }
////    empList = empList.filter(emp => emp.id !== id);
////    showEmp();
//}
//
//// 修改密碼
//function resetPw(id){
//	alert('修改密碼: ' + id);
////    const newAccount = document.querySelector('#newAccount');
////    if(newAccount){
////        newAccount.remove();
////    }
////    // 區分前後區域
////    const divNew = document.createElement('div');
////    divNew.id = 'newAccount';
////    divNew.onclick = close;
////    // 新增帳戶的視窗
////    const divInput = document.createElement('div');
////    divInput.id = 'dataInput';
////    // 輸入名稱
////    let inner = `
////    <h1>重設密碼</h1>
////    <label for="empNumber">編號：</label>
////    <input value="${id}" id="empNumber" readonly>
////    <br>
////    <label for="password">密碼：</label>
////    <input type="password" id="newPassword">
////    <div>`;
////
////    inner += `</div><div>
////        <button id="cancel">取消</button>
////        <button id="reset">重設</button>
////    </div>
////    `;
////    divInput.innerHTML = inner;
////    main.append(divNew);
////    main.append(divInput);
////    document.querySelector('#newPassword').focus();
////    const cancel = document.querySelector('#cancel');
////    const reset = document.querySelector('#reset');
////    cancel.onclick = close;
////    reset.addEventListener('click', function(){
////        const emp = empList.filter(emp => emp.id === id)[0];
////        const newPw = document.querySelector('#newPassword').value;
////        emp.password = newPw;
////        close();
////        showEmp();
////    });
//}
//
//// 權限連動選取
//function chooseAll(category){
//    const funcs = document.querySelectorAll(`.${category.id}`);
////     console.log(category);
////     console.log(funcs);
//    for(let fun of funcs){
//        if(category.checked === true){
//            fun.checked = true;
//        }else{
//            fun.checked = false;
//        }
////        console.log(fun.checked);
//    }
//}
//
////function check(id){
////    alert('查看： ' +　id);
////}