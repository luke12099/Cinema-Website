

let allcheckbox = document.getElementById('checkAll');
let checklist = document.getElementsByClassName('checkOne');
let total = document.getElementById('total');
let inputprice = document.getElementsByClassName('inputprice');
let inputcount = document.getElementsByClassName('inputcount');
let totalCount = document.getElementById('totalCount');
allcheckbox.addEventListener('click', function() {
	if (allcheckbox.checked === true) {
		for (let i = 0; i < checklist.length; i++) {
			checklist[i].checked = true;
		}
	} else {
		for (let i = 0; i < checklist.length; i++) {
			checklist[i].checked = false;
		}
	}
	let amount = 0
	for (let k = 0; k < checklist.length; k++) {
		if (checklist[k].checked === true) {
			amount += (parseInt(inputprice[k].textContent)) * (parseInt(inputcount[k].value))
		}
	}
	totalCount.value = amount;
	let text = "總金額" + amount
	total.textContent = text;
	
});
//全選後取消一個 全選會失效 並計算總價格

for (let i = 0; i < checklist.length; i++) {
	checklist[i].addEventListener('click', function() {
		let amount = 0;
		if (checklist[i].checked === false) {
			allcheckbox.checked = false;
		}
		for (let k = 0; k < checklist.length; k++) {
			if (checklist[k].checked === true) {
				amount += (parseInt(inputprice[k].textContent)) * (parseInt(inputcount[k].value))
			}
		}
		totalCount.value = amount;
		let text = "總金額" + amount;
		total.textContent = text;
	})

	inputcount[i].addEventListener('change', function() {
		let amount = 0
		for (let k = 0; k < checklist.length; k++) {
			if (checklist[k].checked === true) {
				amount += (parseInt(inputprice[k].textContent)) * (parseInt(inputcount[k].value))
			}
		}
		totalCount.value = amount;
		let text = "總金額" + amount;
		total.textContent = text;
	})
}