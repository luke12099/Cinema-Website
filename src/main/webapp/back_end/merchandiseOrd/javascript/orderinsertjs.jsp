<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
let merchID = [];
let merchName = [];
<c:forEach var="merchVo" items="${merchSvc.all}">
    merchID.push('${merchVo.merchID}');
    merchName.push('${merchVo.merchName}');
</c:forEach>
let table = document.getElementById('table');
let select = document.getElementById('select');
select.addEventListener('change', f1);


/**/
let k = 0;
function f1(e) {
	/*過濾掉被選擇的商品*/
    merchID = merchID.filter(function (item) {
        return item !== e.target.value;
    })
    /*製作下一個table的row*/
    let input = document.createElement('input');
    input.type = "number";
    input.name = "merchCount";
    input.className = "totalCount";
    input.setAttribute('min',0);
    input.placeholder = "0";
    input.value = "${orderDetailVo.ordCount==''?0:orderDetailVo.ordCount}";
    let selectes = document.createElement('select');
    selectes.name = "merchID";
    selectes.style.width = "300px";
    let tr1 = document.createElement('tr');
    let td4 = document.createElement('td');
    let td5 = document.createElement('td');
    let td6 = document.createElement('td');
    td6.className = "merchPrice";
    table.append(tr1);
    tr1.append(td4);
    tr1.append(td5);
    tr1.append(td6);
//     td4.textContent = '商品名稱:';
    td4.append(selectes);
//     td5.textContent = "商品數量:";
    td5.append(input);
    /*第一個選單*/
    let option1 = document.createElement('option');
    option1.textContent = "請選擇";
    option1.value = "0";
    selectes.append(option1);
    /*製作沒有被選到商品的選單*/
    for (let i = 0; i < merchID.length; i++) {
        let option = document.createElement('option');
        option.value = merchID[i];
        let merchID1 = merchID[i];
        /*傳沒被選到的商品編號回去取得剩下的所有商品名稱*/
        let url = "${pageContext.request.contextPath}/merchOrd/merchOrd.do?action=getmerchNameByID&merchID=" + merchID1;
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            async: false,
            timeout: 15000,
            success: function (data) {
                option.textContent = data.merchName;
            }
        })
        selectes.append(option);
    }
    /*傳被選取的商品編號回去取得被選取的商品的價格*/
    let url1 = "${pageContext.request.contextPath}/merchOrd/merchOrd.do?action=getmerchNameByID&merchID=" + e.target.value;
    $.ajax({
        url: url1,
        type: 'post',
        dataType: 'json',
        async: false,
        timeout: 15000,
        success: function (data) {
            let td = document.getElementsByClassName('merchPrice')[k++];
            td.textContent = data.merchPrice;
        }
    })
    
	
    e.target.removeEventListener('change', f1);
    e.target.addEventListener('change', () => {
        location.reload();
    })
    selectes.addEventListener('change', f1)
}


/*監聽TABLE取得總金額*/
table.addEventListener('change',f2);

function f2(){
	let total = 0;
	let merchPrice = document.getElementsByClassName('merchPrice');
	let totalCount = document.getElementsByClassName('totalCount');
	for(let i = 0; i < merchPrice.length - 1; i++){
		if(totalCount[i].value === ""){
		}else{
		let x = parseInt(merchPrice[i].textContent);
		let y = parseInt(totalCount[i].value);
		total += x * y;
		}
		
	}	
	merchPrice[merchPrice.length-1].textContent = "總金額:" + total;
	document.getElementById('totalCount').value = total;
}	
</script>