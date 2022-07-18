let isNeta = "";
let cmState = 0;
let now;


//評論的星星
$(document).ready(function(){
	$(".cmt_star[value='1']").html('<span class="fa fa-star" ></span>');
	$(".cmt_star[value='2']").html('<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>');
	$(".cmt_star[value='3']").html('<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>');
	$(".cmt_star[value='4']").html('<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>');
	$(".cmt_star[value='5']").html('<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>');
})

//分級轉換
$(document).ready(function(){
	switch($("#movie_level").text()){
		case "0":
		$("#movie_level").html("普遍級");
		break;
		case "1":
		$("#movie_level").html("保護級");
		break;
		case "2":
		$("#movie_level").html("輔導級12歲");
		break;
		case "3":
		$("#movie_level").html("輔導級15歲");
		break;
		case "4":
		$("#movie_level").html("限制級");
		break;
	}
})

//Add Days
Date.prototype.addDays = function (days) {
    const date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
};
// ingect date
let today = new Date();
const formatDate = (current_datetime)=>{
    let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
    return formatted_date;
}

for(let i = 0; i < 7; i++){
    let date = today.addDays(i).toLocaleDateString('sv');
    let day = today.addDays(i).getDay();
    let week;
    switch (day){
        case 1:
            week = "星期一";
            break;
        case 2:
            week = "星期二";
            break;
        case 3:
            week = "星期三";
            break;
        case 4:
            week = "星期四";
            break;
        case 5:
            week = "星期五";
            break;
        case 6:
            week = "星期六";
            break;
        case 0:
            week = "星期日";
            break;
    }
    $("#dateSelector").append(`<option value="${date}">${date} ${week}</option>`)
}



// inject comment
$(".cmtBtnArea").click(function(){
        if(($("textarea").val() == "") || (ratingValue == undefined)){
            alert("請輸入評論及評星!");
        }else{
            now = formatDate(new Date());
            $("input[name='CM_DATE']").prop("value", `${now}`)
            let star = "";
            if(ratingValue == 1){
                star = '<span class="fa fa-star" ></span>';
            }else if(ratingValue == 2){
                star = '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>';
            }else if(ratingValue == 3){
                star = '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>';
            }else if(ratingValue == 4){
                star = '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>';
            }else if(ratingValue == 5){
                star = '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>' + '<span class="fa fa-star" ></span>';
            }
            $("#comment").append(`
            <div class="cmt">
                <div class="member">
                <div class="member_pic">
                    <img src="./images/${$("#thisMemId").text()}.jpg" alt="">
                </div>
                <div class="member_name">
                    <div class="member_nickname">
                    <span>${$("#thisMemNickname").text()}</span>
                    </div>
                    <div class="member_id">
                    <span>(test5678)</span>
                </div>
                </div>
                </div>
                <div class="cmt_detail">
                <div class="cmt_text whiteBg ${isNeta}" value="${cmState}">
                    ${$("textarea").val()}
                </div>
                <hr class="hr_cmt">
                <div class="cmt_like">
                    <span class="fa fa-heart like" >0</span>
                 </div>
                <div class="cmt_date">
                    <span>${formatDate(now)}</span>
                </div>
                </div>
                <div class="cmt_star" value="${ratingValue}">
                ${star}
                </div>
            </div>
            `)
            
            $("textarea").val("");
        }
    })

// CM_STATE
// 正常0(預設)/暴雷隱藏1(點擊可看)/刪除隱藏2(員工可看)
// 劇透馬賽克
$(document).ready(function() {
	$(".cmt_text[value='1']").prop("class", "cmt_text whiteBg neta");
    $("#comment").on("click",".cmt_text[value='1']", function(){
        if($(this).prop("class") == "cmt_text whiteBg neta"){
            $(this).prop("class", "cmt_text whiteBg");
        }else if($(this).prop("class") == "cmt_text whiteBg"){
            $(this).prop("class", "cmt_text whiteBg neta");
        }
    })
});
// $("div[value='1']").blur(function(){
//     console.log("blur")
// })
// $(document).ready(function() {
//     $("#comment").on("blur","[value='1']", function(){
//         if($(this).prop("class") == "cmt_text whiteBg neta"){
//             $(this).prop("class", "cmt_text whiteBg");
//         }else if($(this).prop("class") == "cmt_text whiteBg"){
//             $(this).prop("class", "cmt_text whiteBg neta");
//         }
//     })
//   });
// 被刪除的評論
$(document).ready(function(){
    $(".cmt_text[value='2']").html("評論已被刪除");
    $(".cmt_text[value='2'] ~ .cmt_like > span").prop("class", "fa fa-heart deleted");
});


// 評論點讚計數
//$(document).ready(function(){
//    $("#comment").on("click",".like", function(e){
//        $(this).html(parseInt($(this).html()) + 1);
//    })
//});

// 劇透checkbox
$(document).ready(function(){
    $("#netabare").click(function(){
        if($("#netabare").is(":checked") == true){
            isNeta = "neta";
            cmState = 1;
            $("input[name='CM_STATE']").prop("value", `${cmState}`)
        }else{
            isNeta = "";
            cmState = 0;
            $("input[name='CM_STATE']").prop("value", `${cmState}`)
        }
    })
});

// 展開評論
$(document).ready(function(){
    $(".cmt").hide();
    $("#newCmt").hide();
    $("#cmt_info").prop("value", "0");
    $("#cmt_info").click(function(){
        if($("#cmt_info").prop("value") == "0"){
            $("#cmt_open span").prop("class", "fa fa-minus")
            $(".cmt").show(500);
            $("#newCmt").show(500);
            $("#cmt_info").prop("value", "1");
        }else if($("#cmt_info").prop("value") == "1"){
            $("#cmt_open span").prop("class", "fa fa-plus")
            $(".cmt").hide(500);
            $("#newCmt").hide(500);
            $("#cmt_info").prop("value", "0");
        }
    })
})

// 未登入時無法評論
$(document).ready(function(){
	if($('input[name="MEMBER_ID"]').prop('value') == ''){
		$("#newCmt").remove();
	}
});

// 未登入時無法檢舉
$(document).ready(function(){
	if($('input[name="MEMBER_ID"]').prop('value') == ''){
		$(".reportBtn").remove();
	}
});



//檢舉後轉跳最上
$(".reportBtn").click(function(){
	$('html,body').animate({ scrollTop: 0 }, 333);
})

