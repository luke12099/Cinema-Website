// 權限連動選取
function chooseAll(category){
    const funcs = document.querySelectorAll(`.${category.id}`);
    for(let fun of funcs){
        if(category.checked === true){
            fun.checked = true;
        }else{
            fun.checked = false;
        }
    }
}

// 一鍵清空
const clearAll = document.querySelector('#clearAll');
clearAll.addEventListener('click', function(){
	const checkboxs = document.querySelectorAll('input[type=checkbox]');
	for(let checkbox of checkboxs){
		checkbox.checked = false;
	}
});