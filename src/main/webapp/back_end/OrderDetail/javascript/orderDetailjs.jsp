<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>


//checkbox checkbox1 checkbox2連動

let checkbox = document.getElementsByClassName('forcheckbox');
let inputnone = document.getElementsByClassName('inputnone')[0];

for(let i = 0; i < checkbox.length; i++){
	let checkbox1 = document.createElement('input');
	let checkbox2 = document.createElement('input');
    checkbox1.value = checkbox[i].value;
    checkbox2.value = checkbox[i].value;
    checkbox1.setAttribute("form","checkbox1");
    checkbox2.setAttribute("form","checkbox2");
    checkbox1.name = "item";
    checkbox2.name = "item";
    checkbox1.style.display = "none";
    checkbox2.style.display = "none";
    checkbox1.type = "checkbox";
    checkbox2.type = "checkbox";
    checkbox1.className = "checkbox1 forcheckbox1";
    checkbox2.className = "checkbox2 forcheckbox1";
    checkbox[i].addEventListener('change',function(){
        if(checkbox[i].checked === true){
            checkbox1.checked = true;
            checkbox2.checked = true;
        }else if (checkbox[i].checked === false){
            checkbox1.checked = false;
            checkbox2.checked = false;
        }
    })
    inputnone.append(checkbox1);
    inputnone.append(checkbox2);
}

//清單全選全取消
let allcheckbox = document.querySelector('#allcheckbox');
let contentlist = document.querySelectorAll('.forcheckbox1');
allcheckbox.addEventListener('click',function(){
    if(allcheckbox.checked === true){
        for(content of contentlist){
            content.checked = true;
        }
    }else{
        for(content of contentlist){
            content.checked = false;
        }
    }
});
//全選後取消一個 全選會失效
for(content of contentlist){
    content.addEventListener('click',function(){
        if(this.checked === false){
            allcheckbox.checked = false;
        }
    })
}


//新增視窗
let main = document.getElementsByClassName('main')[0];
let add =  document.getElementById('add');
add.onclick = addAccount;	// 點擊後執行addAccount function

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
    <h1>新增明細資料</h1>
    <form action="${pageContext.request.contextPath}/OrderDetail/OrderDetail.do">
    <table id="newInfo">
        <tr>
            <td><label for="number">訂單編號：</label></td>
            <td><input value="${merchOrdVo.merchOrdID}" name="merchOrdID" id="number" readonly></td>
            <td><label for="merchID">商品名稱：</label></td>
            <td><select name="merchID" id="merchID">
			<c:forEach var="merchVo" items="${insertlist}">
				<option value="${merchVo.merchID}">${merchVo.merchName}</option>
			</c:forEach>
			</select>
            </td>
        <tr>
            <td><label for="ename">數量：</label></td>
            <td><input id="ename" type="number" name="ordCount" required></td>
            <td><label for="status">產品狀態：</label></td>
            <td>
            <select name="ordStatus" id="status">
			<option value="0"${orderDetail.ordStatus == 0? 'selected':''}>備貨中</option>
			<option value="1"${orderDetail.ordStatus == 1? 'selected':''}>可取貨</option>
			<option value="2"${orderDetail.ordStatus == 2? 'selected':''}>已取貨</option>
			<option value="3"${orderDetail.ordStatus == 3? 'selected':''}>已取消</option>
			</select>
            </td>
        </tr>
    </table>`;
    inner += `<div>
        <button id="cancel" class="tablebt">取消</button>
        <button id="enter" name="action" value="insert" type="submit" class="tablebt">確認</button>
    </div></form>`;
    divInput.innerHTML = inner;
    main.append(divNew);
    main.append(divInput);
    const cancel = document.querySelector('#cancel');
    const enter = document.querySelector('#enter');
    cancel.onclick = close;
};

//關閉新增帳戶的視窗
function close(){
    const newAccount = document.querySelector('#newAccount');
    const dataInput = document.querySelector('#dataInput');
    if(newAccount !== null){
        newAccount.remove();
        dataInput.remove();
    }
}
</script>