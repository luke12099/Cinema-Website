
        
const forgetBtn = document.addEventListener('click', () => {
	alert("123");
	$.ajax({
            type: 'GET',
            url: `CGA102G1//member/password/forget`,
            dataType: 'json',
            async: false,
            success: function (response) {
                

            },
            error: function (thrownError) {
                console.log(thrownError);
            }
        });
});