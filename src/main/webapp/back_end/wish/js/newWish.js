const moviesBlock = document.querySelector('#movies');

function colorChange(e) {
    console.log(e.parentElement);
    if (e.parentElement.classList.contains('checked')) {
        e.parentElement.classList.remove('checked');
    } else {
        e.parentElement.classList.add('checked');
    }
}