// Title
const main = document.querySelector('#main');
main.innerHTML = '<h1>帳號與權限</h1>';
// 新增按鈕
const button = document.createElement('button');
button.className = 'add'
button.textContent = '新增';
button.onclick = addAccount; // 點擊後執行addAccount function
main.append(button);


// emp array
let empList = [];
// emp objs
let emp1 = {
    'id': 1,
    'name': '黃小美',
    'status': '在職',
    'password': '123456'
};
let emp2 = {
    'id': 2,
    'name': '黃大為',
    'status': '在職',
    'password': '654321'
};
let emp3 = {
    'id': 3,
    'name': '黃XXX',
    'status': '在職',
    'password': '222333'
};
empList.push(emp1); empList.push(emp2); empList.push(emp3); 

// init
showEmp();
let newNum = 4;

// show emp
function showEmp(){
    // 如果 #empData 表格已存在就刪除
    const tableIfExist = document.querySelector('#empData');
    if(tableIfExist){
        tableIfExist.remove();
    }
    // table and thead
    let empThead = ['編號', '姓名', '狀態', '密碼', '功能'];
    const table = document.createElement('table');
    table.id = 'empData';
    main.append(table);
    const tr = document.createElement('tr');
    let k = 0;
    for(let j of empThead){
        const th = document.createElement('th');
        th.innerHTML = j;
        th.className = 'tb' + k;
        k++;
        tr.append(th);
    }
    table.append(tr);
    // td
    if(empList.length === 0){ // if no emp
        const tr = document.createElement('tr');
        const td = document.createElement('td');
        td.innerHTML = '無符合資料';
        td.style.backgroundColor = 'transparent';
        tr.append(td);
        table.append(tr);
    } else{ 
        for(let i = 0; i < empList.length; i++){
            const tr = document.createElement('tr');
            for(let j in empList[i]){
                const td = document.createElement('td');
                td.innerHTML = empList[i][j];
                tr.append(td);
            }
            const td = document.createElement('td');
            td.innerHTML = `
            <button id="edit" onclick="check(empList[` + i + `].id)">查看詳情/修改</button>
            <button id="resetPw" onclick="resetPw(empList[` + i + `].id)">重設密碼</button>
            <button id="delete" onclick="deleteEmp(empList[` + i + `].id)">刪除</button>
            `;
            tr.append(td);
            table.append(tr);
        }
    }
}

// 新增帳戶
function addAccount(){
    const newAccount = document.querySelector('#newAccount');
    if(newAccount){
        newAccount.remove();
    }
    // 區分前後區域
    const divNew = document.createElement('div');
    divNew.id = 'newAccount';
    divNew.onclick = close;
    // 新增帳戶的視窗
    const divInput = document.createElement('div');
    divInput.id = 'dataInput';
    // 輸入名稱
    let inner = `
    <h1>新增員工資料</h1>
    <table id="newInfo">
        <tr>
            <td><label for="number">編號：</label></td>
            <td><input value="${newNum++}" id="number" readonly></td>
            <td><label for="password">密碼：</label></td>
            <td><input type="password" id="password"></td>
        <tr>
            <td><label for="ename">姓名：</label></td>
            <td><input id="ename"></td>
            <td><label for="status">狀態：</label></td>
            <td>
                <select name="" id="status">
                    <option value="1">在職</option>
                    <option value="0">離職</option>
                    <option value="2">留職停薪</option>
                </select>
            </td>
        </tr>
    </table>
    <h1>權限功能</h1>
    <div id="auth">`;
    
    // 動態生成權限的checkbox
    let funcs = {
        '票務相關': ['買票', '退票'], 
        '管理員相關': ['新增員工', '刪除員工']
    };
    let i = 0;
    for(let func in funcs){
        let j = i++;
        inner += `<br>
        <input type="checkbox" id="${'funcs' + j}" onclick="chooseAll(this)">
        <label for="${'funcs' + j}" class="big">${func}</label>
        <br>`;
        for(let f of funcs[func]){
            inner += `
            <input type="checkbox" value="${f}" class="${'funcs' + j}">
            <label for="${'funcs' + funcs[func].indexOf(f)}">${f}</label>
            `;
        }
    }
    
    inner += `</div><div>
        <button id="cancel">取消</button>
        <button id="enter">確認</button>
    </div>
    `;
    divInput.innerHTML = inner;
    main.append(divNew);
    main.append(divInput);
    document.querySelector('#password').focus();
    const cancel = document.querySelector('#cancel');
    const enter = document.querySelector('#enter');
    cancel.onclick = close;
    enter.onclick = addEmp;
}

// 關閉新增帳戶的視窗
function close(){
    const newAccount = document.querySelector('#newAccount');
    const dataInput = document.querySelector('#dataInput');
    if(newAccount !== null){
        newAccount.remove();
        dataInput.remove();
    }
}

// 新增員工
function addEmp(){
    const number = parseInt(document.querySelector('#number').value);
    const password = document.querySelector('#password').value;
    const ename = document.querySelector('#ename').value;
    const status = document.querySelector('#status').value;
    const newEmp = {
        'id': number,
        'name': ename,
        'status': status,
        'password': password
    }
    empList.push(newEmp);
    showEmp();
    close();
}

// 刪除員工
function deleteEmp(id){
    let result = confirm('刪除後無法復原，確認刪除該員工?');
    if(!result){
        return;
    }
    empList = empList.filter(emp => emp.id !== id);
    showEmp();
}

// 修改密碼
function resetPw(id){
    const newAccount = document.querySelector('#newAccount');
    if(newAccount){
        newAccount.remove();
    }
    // 區分前後區域
    const divNew = document.createElement('div');
    divNew.id = 'newAccount';
    divNew.onclick = close;
    // 新增帳戶的視窗
    const divInput = document.createElement('div');
    divInput.id = 'dataInput';
    // 輸入名稱
    let inner = `
    <h1>重設密碼</h1>
    <label for="empNumber">編號：</label>
    <input value="${id}" id="empNumber" readonly>
    <br>
    <label for="password">密碼：</label>
    <input type="password" id="newPassword">
    <div>`;

    inner += `</div><div>
        <button id="cancel">取消</button>
        <button id="reset">重設</button>
    </div>
    `;
    divInput.innerHTML = inner;
    main.append(divNew);
    main.append(divInput);
    document.querySelector('#newPassword').focus();
    const cancel = document.querySelector('#cancel');
    const reset = document.querySelector('#reset');
    cancel.onclick = close;
    reset.addEventListener('click', function(){
        const emp = empList.filter(emp => emp.id === id)[0];
        const newPw = document.querySelector('#newPassword').value;
        emp.password = newPw;
        close();
        showEmp();
    });
}

// 權限連動選取
function chooseAll(category){
    const funcs = document.querySelectorAll(`.${category.id}`);
    // console.log(category);
    // console.log(funcs);
    for(let fun of funcs){
        if(category.checked === true){
            fun.checked = true;
        }else{
            fun.checked = false;
        }
        console.log(fun.checked);
    }
}

function check(id){
    alert(id);
}