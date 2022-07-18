$(document).ready(function(){
    //全選
    $("#checkAll").on("click", function(){
        if(this.checked){
            $(":checkbox").each(function() {
                this.checked = true;                        
            });
        } else {
            $(":checkbox").each(function() {
                this.checked = false;                       
            });
        }     
    });

    //將選中CM_ID加入陣列
    let cmtArr = [];
    $(".checkOne").on("click", function(){
        console.log($(this).val())

    });
    
    
    $('input[type="checkbox"]').click(function(){
	console.log($('.checkOne:checked').length)
		$("#total").html("已選擇: " + $('.checkOne:checked').length + " 筆評論");
})


});