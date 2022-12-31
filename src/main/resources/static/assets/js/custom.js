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


/* ========================================================================
		시계 달기
   ======================================================================== */

function getTime(){
    const time = new Date();
    const hour = time.getHours();
    const minutes = time.getMinutes();
    const seconds = time.getSeconds();
    
    $("#todayTime").text(hour +":" + minutes + ":"+seconds);
}




  
/* ========================================================================
		날짜 포멧
   ======================================================================== */	
// String(12345678)  ->  String(0000-00-00)
function dateFormat(date_str, sperator)
	{
		
	    var yyyyMMdd = String(date_str);
	    //console.log(yyyyMMdd)
	    var sYear = yyyyMMdd.substring(0,4);
	    //console.log(sYear)
	    var sMonth = yyyyMMdd.substring(4,6);
	    //console.log(sMonth)
	    var sDate = yyyyMMdd.substring(6,8);
	    //console.log(sDate)
	
	    //alert("sYear :"+sYear +"   sMonth :"+sMonth + "   sDate :"+sDate);
	    return sYear+sperator+sMonth+sperator+sDate;
	}     
   
// String(yyyy-mm-dd)  ->  Date
function dateString(date_str)
	{
	    var yyyyMMdd = String(date_str);
	    var sYear = yyyyMMdd.substring(0,4);
	    var sMonth = yyyyMMdd.substring(5,7);
	    var sDate = yyyyMMdd.substring(8,10);
	
	    //alert("sYear :"+sYear +"   sMonth :"+sMonth + "   sDate :"+sDate);
	    return new Date(Number(sYear), Number(sMonth)-1, Number(sDate));
	}  
//   Date -> String (0000-00-00)
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
	
// DateTime -> String (0000-00-00 hh:mm:ss)
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
	
// 입력시 날짜형태로
function inputDateFormat(obj, sperator) {
     // @see DELETE 키버튼이 눌리지 않은 경우에만 실행
        if(event.keyCode != 8) {

            // @see 숫자와 하이픈(-)기호의 값만 존재하는 경우 실행
            if(obj.value.replace(/[0-9 \-]/g, "").length == 0) {

                // @see 하이픈(-)기호를 제거한다.
                let number = obj.value.replace(/[^0-9]/g,"");
                let ymd = "";

                // @see 문자열의 길이에 따라 Year, Month, Day 앞에 하이픈(-)기호를 삽입한다.
                if(number.length < 4) {
                    return number;
                } else if(number.length < 6){
                    ymd += number.substr(0, 4);
                    ymd += sperator;
                    ymd += number.substr(4);
                } else {
                    ymd += number.substr(0, 4);
                    ymd += sperator;
                    ymd += number.substr(4, 2);
                    ymd += sperator;
                    ymd += number.substr(6, 2);
                }

                // @see 입력 가능 날짜 제한 기능 - 선택
                // if(ymd.length == 10) {
                //
                //     const birthDay = new Date(number.substr(0,4)+"/"+number.substr(4,2)+"/"+number.substr(6)+" 00:00:00");
                //     const limitDay = new Date("2000/10/04 23:59:59");
                //
                //     if(birthDay > limitDay) {
                //         alert("2000년 10월 04일 이후의 날짜는\n선택할 수 없습니다.");
                //         obj.value = "";
                //         obj.focus();
                //         return false;
                //     }
                // }

                obj.value = ymd;

            } else {
                //alert("숫자 이외의 값은 입력하실 수 없습니다.");

                //@see 숫자와 하이픈(-)기호 이외의 모든 값은 삭제한다.
                obj.value = obj.value.replace(/[^0-9 ^\-]/g,"");
                return false;
            }
        } else {
            return false;
        }
 }		
/* ========================================================================
		숫자 포멧(천자리 콤마)
   ======================================================================== */	
// 입력시 천단위마다 콤마
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
 
 // 천단위 콤마 찍기, 정수만(소수점 제외)
function numberWithCommas(num) { 
  var roundedNum = 	Math.round(num * 100) / 100
  var parts = roundedNum.toString().split("."); 

  return parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ","); 
}

/* ========================================================================
		null 포멧(공백으로)
   ======================================================================== */	
 function nullFormat(obj) {
	
	return ( obj == null || obj == undefined || obj == "null") ? "" : obj;
//     if( obj == null || obj == undefined) {
//		return "";
//	} else {
//		return obj;
//	}

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
		첫글자만 대문자로 변경
   ======================================================================== */
function capitalize(str) {
	return str.charAt(0).toUpperCase() + str.slice(1);
}


/* ========================================================================
		글자 웨이브 효과(class)
   ======================================================================== */
function waveAnimation(cls, str) {
	let objs = document.getElementsByClassName(cls);
	for( var i = 0; i < objs.length; i++ ){

		let html ="";
		for(var j=0; j < str.length; j++) {
			let letter = str.charAt(j);
			html += '<span>'
			html += letter;
			html += '</span>'
		};

		objs[i].innerHTML = html;
		
		let els = objs[i].querySelectorAll('span');

		for(var j=0; j < els.length; j++) {
			els[j].style.animation = "jumb 2s infinite";
			els[j].style.animationDelay = "0."+j+"s";
		};
  }	
} 

/* ========================================================================
		토스트
   ======================================================================== */
    
function setToast(bg, title, info, content) {
	$("#toastNotice").addClass(bg)
	$("#toastNoticeTitle").html(title)
	$("#toastNoticeInfo").html(info)
	$("#toastNoticeContent").html(content)
} 

function clearToast() {
	$("#toastNotice").removeAttr('class');
	$("#toastNotice").addClass('toast fade animate bg-solid-white')
	$("#toastNotice").removeClass("bg-*")
	$("#toastNoticeTitle").html(null)
	$("#toastNoticeInfo").html(null)
	$("#toastNoticeContent").html(null)
}
/* ========================================================================
		테두리
   ======================================================================== */

function changeBorderColor(obj, color){
	//console.log(color);
	obj.style.borderRadius = "5px";
	obj.style.borderStyle = "solid";
	obj.style.borderWidth = "1px";
	obj.style.borderColor=color;
};


/* ========================================================================
		Json 데이터 변환
   ======================================================================== */

$.fn.serializeObject = function() {
  "use strict"
  var result = {}
  var extend = function(i, element) {
    var node = result[element.name]
    if ("undefined" !== typeof node && node !== null) {
      if ($.isArray(node)) {
        node.push(element.value)
      } else {
        result[element.name] = [node, element.value]
      }
    } else {
      result[element.name] = element.value
    }
  }

  $.each(this.serializeArray(), extend)
  return result
}



$.fn.serializeArrayObject = function() {
  "use strict"
  var jsonObjArray = []
  
  var jsonObj  = this.serializeArray();
  var objLength = Object.keys(jsonObj).length;	// item 전체 개수  90
  var nameKey = jsonObj[0].name;
  var groupLength = 0;							// json 묶음 개수  5
  
  jsonObj.forEach( function(el) {
		if( el.name == nameKey ){ groupLength++}	
		return groupLength;
	}
  ) 
  var itemsLength = parseInt(objLength / groupLength); // 묶음 내 item 개수  18
  
  
  for(var i=0; i < objLength; i++) {
	
	if (parseInt( i % itemsLength ) == 0 ) {	// item개수 의 배수마다 객체 초기화
		//console.log(i + "new turn");
		var data = new Object() ;	
	} 

	var key = jsonObj[i].name;
	var value = jsonObj[i].value;
	//console.log(i+' '+key+ ' ' + value);
	data[key] = value;
	//console.log(data)
	
	if (parseInt( i % itemsLength ) == 0 ) {	// item개수 의 배수마다 객체 초기화
		//console.log(i + "add array");
		jsonObjArray.push(data);	
	 	//console.log(jsonObjArray);
	} 

  }

  return jsonObjArray
}

