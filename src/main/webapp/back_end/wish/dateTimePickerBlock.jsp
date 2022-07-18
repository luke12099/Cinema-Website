<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link   rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back_end/wish/datetimepicker/jquery.datetimepicker.css" />
<script src="${pageContext.request.contextPath}/back_end/wish/datetimepicker/jquery.js"></script>
<script src="${pageContext.request.contextPath}/back_end/wish/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  minDate:'-1969-12-31', // 從明天開始
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false,
	  scrollMonth : false,
	  scrollInput : false,
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false,
	  scrollMonth : false,
	  scrollInput : false,
	 });
});
</script>