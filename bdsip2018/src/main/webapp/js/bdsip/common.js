// 当前时间
$(document).ready(function(){
	setInterval(showTime, 1000); 
	function timer(obj,txt){
		obj.text(txt);
	} 
	function showTime(){ 
		var today = new Date();
		var weekday=new Array(7)
		weekday[0]="星期日"
		weekday[1]="星期一"
		weekday[2]="星期二"
		weekday[3]="星期三"
		weekday[4]="星期四"
		weekday[5]="星期五"
		weekday[6]="星期六" 
		var y=today.getFullYear()+"年";
		var month=today.getMonth()+"月";
		var td_keleyi_com=today.getDate()+"日";
		var d=weekday[today.getDay()];
		var h=today.getHours()+":";
		var m=today.getMinutes()+":";
		var s=today.getSeconds(); 
		timer($("#Y"),y);
		timer($("#MH"),month); 
		timer($("#td_kel"+"eyi_com"),td_keleyi_com); 
		timer($("#D"),d);
		timer($("#H"),h);
		timer($("#M"),m);
		timer($("#S"),s);
	} 
})
// 招生咨询         
$(document).ready(function(){

	 $(".suspend").mouseover(function() {
        $(this).stop();
        $(this).animate({width: 160}, 400);
    })
	
    $(".suspend").mouseout(function() {
        $(this).stop();
        $(this).animate({width: 40}, 400);
    });
	
});