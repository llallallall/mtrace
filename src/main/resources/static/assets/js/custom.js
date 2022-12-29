/* ========================================================================
		공통 변수 
   ======================================================================== */
var myAlert =document.getElementById('toastNotice');//select id of toast
var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
	myAlert.addEventListener('hidden.bs.toast', function () {
		console.log("toast closed");
		clearToast();
	})
/* ========================================================================
		초기 로딩
   ======================================================================== */
    
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

/* ========================================================================
		글자수 체크 (object, 자리수)
   ======================================================================== */ 
function chkword(obj, maxByte) {
 
		var strValue = obj.value;
		var strLen = strValue.length;
		var totalByte = 0;
		var len = 0;
		var oneChar = "";
		var str2 = "";
 
		for (var i = 0; i < strLen; i++) {
			oneChar = strValue.charAt(i);
			if (escape(oneChar).length > 4) {
				totalByte += 2;
			} else {
				totalByte++;
			}
 
			// 입력한 문자 길이보다 넘치면 잘라내기 위해 저장
			if (totalByte <= maxByte) {
				len = i + 1;
			}
		}
 
		// 넘어가는 글자는 자른다.
		if (totalByte > maxByte) {
			alert(maxByte + "자를 초과 입력 할 수 없습니다.");
			str2 = strValue.substr(0, len);
			obj.value = str2;
			chkword(obj, 4000);
		}
	} 

/* ========================================================================
		토스트
   ======================================================================== */
    
function setToast(bg, title, info, content) {
	$("#toastNotice").addClass(bg)
	$("#toastNoticeTitle").text(title)
	$("#toastNoticeInfo").text(info)
	$("#toastNoticeContent").text(content)
} 

function clearToast() {
	$("#toastNotice").removeClass("bg-*")
	$("#toastNoticeTitle").text(null)
	$("#toastNoticeInfo").text(null)
	$("#toastNoticeContent").text(null)
}