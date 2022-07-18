// 各自商品頁的顯示與隱藏
$(".pg").hide();

$("#pg1-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg1").show(500);
});
$("#pg2-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg2").show(500);
});
$("#pg3-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg3").show(500);
});
$("#pg4-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg4").show(500);
});
$("#pg5-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg5").show(500);
});
$("#pg6-home").click(function () {
  $(".mytabs").hide();
  $(".pg").hide();
  $("#pg6").show(500);
});

$(".home").click(function () {
  $(".mytabs").show(500);
  $(".pg").hide(500);
});

// 價格篩選器

//$(".js-range-slider").ionRangeSlider({
//  onFinish: function (price) {
//    console.log(price.from); //下區間
//    console.log(price.to); //上區間
//  },
//  type: "double",
//  min: 0, //最小值
//  max: 100000, //最大值
//  from: 0, //預設下區間
//  to: 10000, //預設上區間
//  grid: true, 
//  step: 100, //步進
//});
