const loginbtn = document.querySelector('#loginbtn');
loginbtn.addEventListener('click', logincheck);
function logincheck(){
    const username = document.querySelector('#empNameLG').value;
    const password = document.querySelector('#empPasswordLG').value;
    alert(username + ' ' + password);
}