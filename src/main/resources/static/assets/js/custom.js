/**
 * 
 */
 
$( document ).ready(function() {
    //현재 날짜 및 시간
    let today = new Date();   

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	let date = today.getDate();  // 날짜
	
	
	$("#todayDate").text(year + '년 ' + month + '월 ' + date + '일 ');
	
	setInterval(getTime, 1000);
	
//	// 메뉴 클릭
//	$(".nav-link").on("click", function() {
//		
//		//현재 메뉴 보이기
//   	 	let currentLink =  document.querySelector('.sidebar-current');
//   	 	currentLink.classList.remove("sidebar-current")
//   	 	currentLink.classList.add("sidebar-link")
//
//   	 	$(this).removeClass('sidebar-link');
//   	 	$(this).addClass('sidebar-current');
//   	 	
//   	 	//페이지 변경 등록
//   	 	$('.menu-title').text(this.dataset.value);
//	});
	var newURL = window.location.pathname;
	
	let currentLink =  document.querySelector('a[href="'+newURL+'"]');
 	currentLink.classList.remove("sidebar-link")
 	currentLink.classList.add("sidebar-current")
 	
 	//페이지 변경 등록
 	$('.menu-title').text(currentLink.dataset.value);
 	
 	
 	
});


function getTime(){
    const time = new Date();
    const hour = time.getHours();
    const minutes = time.getMinutes();
    const seconds = time.getSeconds();
    
    $("#todayTime").text(hour +":" + minutes + ":"+seconds);
}



// 천단위 콤마 찍기, 소수 둘째 자리 반올림 
function numberWithCommas(num) { 
  var roundedNum = 	Math.round(num * 100) / 100
  var parts = roundedNum.toString().split("."); 

  return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
}
  
  
function dateString(date_str)
	{
	    var yyyyMMdd = String(date_str);
	    var sYear = yyyyMMdd.substring(0,4);
	    var sMonth = yyyyMMdd.substring(5,7);
	    var sDate = yyyyMMdd.substring(8,10);
	
	    //alert("sYear :"+sYear +"   sMonth :"+sMonth + "   sDate :"+sDate);
	    return new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
	}  
	
function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();
	
	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
	
	    return [year, month, day].join('-');
	}		
	

function formatDateTime(date) {
	    var d = new Date(date),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();
	        
        var hour = d.getHours();
	    var minutes = d.getMinutes();
	    var seconds = d.getSeconds();
	
	    if (month.length < 2) 
	        month = '0' + month;
	    if (day.length < 2) 
	        day = '0' + day;
	        
	    if (hour < 10) 
	        hour = '0' + hour;
	    if (minutes < 10) 
	        minutes = '0' + minutes;
	    if (seconds < 10) 
	        seconds = '0' + seconds;            
	
	    return ([year, month, day].join('-') +' '+ [hour, minutes, seconds].join(':') );
	}		
	
 function inputNumberFormat(obj) {
     obj.value = comma(uncomma(obj.value));
 }

 function comma(str) {
     str = String(str);
     return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
 }

 function uncomma(str) {
     str = String(str);
     return str.replace(/[^\d]+/g, '');
 }	