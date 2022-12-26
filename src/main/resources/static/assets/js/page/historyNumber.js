

/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

		searchHistoryNumbers(1);

		$('#datepickerReportDate').datepicker({
				language:'ko',
				todayHighlight:true
			}
		);
		$('#datepickerReportDate').on('changeDate', function() {
			let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
		    $('#reportDateHidden').val(
		        $('#datepickerReportDate').datepicker('getFormattedDate').substr(0,10).replace(reg, "")
		    );
		    
		    $('#reportDate').val(
		        $('#datepickerReportDate').datepicker('getFormattedDate')
		    );
		});
		
		
		let today = new Date();   
	
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
		
		$("#datepickerReportDate").datepicker("setDate", today);
		$('#reportDate').val(year+ '-' + month + '-' + date + '(' + week[today.getDay()] + ')' );
		
		$('#datepickerReportDate').css('font-size', '40px');
		
		var dateList = ['2022-12-19', 
			'2022-12-20',
			'2022-12-21', 
			'2022-12-22', 
			'2022-12-23', '2022-12-24'];
		
		function available(date, current) {  
		  dmy = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();  
		  if ($.inArray(dmy, dateList) > -1) {  
		    return [true, "vio-highlight","예약가능"];  
		  } 
		  else {  
		    return [false,"","unAvailable"];  
		  }  
		}  	
		
		$('#datepickerReportDate').datepicker({ beforeShowDay: available,  
                        onSelect: function(dateText, inst) {  
		        window.location = 'http://www.amerigotour.com/goods_airtel.calendar.asp?g_cd=' + dateText;  
		    }  
		 });  
		  
			
//		$('#datepickerReportDate').datepicker().on("show", function(e) {
//			const fixDate = new Date("2022", "12", "22"), fixDateData = '1671667200000', fixMarchLastDay = '31', fixDayData = '86400000';
//			var split, date, dayGap, result, temp;
//		
//			for(var index = 0; index < dateList.length; index++){
//			    split = dateList[index].split("-");
//			    date = new Date(split[0], split[1], split[2]);
//			    dayGap = fixDayData * (parseInt(fixMarchLastDay) - lastDay(split[0], split[1]));
//		
//			    result = date - fixDate + dayGap;
//					
//			    temp = parseInt(fixDateData) + parseInt(result);
//					
//			    $(".day[data-date='" + temp + "']").addClass("active");
//			}
//    });

});


//월별 마지막 일자
function lastDay(year, month){
	var m = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
	m[1] = isLeapYear(year) ? 29 : 28;
	
	return m[parseInt(month) - 1];	
}

//윤년 여부
function isLeapYear(year){
	var boolean;
	boolean = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	return boolean;
}

/* ========================================================================
		리스트 로딩 함수 
   ======================================================================== */	
	
	
// 페이지 로딩 	
function searchHistoryNumbers(page){


	if(page==null || page==''){
		page=1;
	}
	
	
			
	$.ajax({
		type : 'GET',
		url : '/callHistoryNumber',
		dataType : 'json',
		contentType : "application/json",
		data : {
            "page" : page
        },
		success : function(data) {
			// 데이터 로딩
			var dataList = data.totPropertiesList;
			
			//console.dir(dataList);
			var html="";
			
			for(var i=0; i<dataList.length; i++){
				var list = dataList[i];

				////console.log(list);
				html+='<tr>';
					
				
				html+='<td>'+ list.num +'</td>';
				
				html+='<td>'+ formatDateTime(list.reportTime) +'</td>';
				html+='<td>'+ list.resultCode +'</td>';
				html+='<td>'+ list.resultMsg +'</td>';
				
				html+='<td>'+ list.eggHistNo +'</td>';
				html+='<td>'+ list.businessNo +'</td>';
				html+='<td>'+ list.licenseNo +'</td>';
				html+='<td>'+ list.farmIdNo +'</td>';
				
				html+='<td>'+ list.eggUsage +'</td>';
				html+='<td>'+ list.spawningDate +'</td>';
				html+='<td>'+ list.storageMethod +'</td>';
				html+='<td>'+ list.washingMethod +'</td>';
				
				
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggXxl) +'</td>';
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggXl) +'</td>';
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggL) +'</td>';
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggM) +'</td>';
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggS) +'</td>';
				html+='<td style="text-align: right;">'+ numberWithCommas(list.eggE) +'</td>';

				html+='</tr>';
				
			
                
                
			}
							
			$("#history-number-body").html(html);
			
			// 페이지 로딩
			
			var pageInfo = data.pageInfo;
			
			var pageHtml="";
			
			var preLang = "이전";
			var nextLang= "다음";
			
			
			
			if(pageInfo.page<=1){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchProperties('+(pageInfo.page-1)+')" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}
			for(var i=pageInfo.startPage;i<=pageInfo.endPage;i++){
				if(pageInfo.page==i){
					pageHtml+='<li class="page-item active">';
					pageHtml+='<a class="page-link" onclick="searchProperties('+i+')">'+i+' <span class="sr-only">(current)</span></a>';
					pageHtml+='</li>';
				}else{
					pageHtml+='<li class="page-item">';
					pageHtml+='<a class="page-link" onclick="searchProperties('+i+')">'+i+' </a>';
					pageHtml+='</li>';
				}
			}
			if(pageInfo.page>=pageInfo.maxPage){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchProperties('+(pageInfo.page+1)+')" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}

			$("#pageing").html(pageHtml);
			
		} 
	});

}

/* ========================================================================
		등록 버튼 클릭 
   ======================================================================== */	

function submitHistoryNumber() {
	let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
	
	let eggSize1 =  $("#size1").val().replace(reg, "")*1;
	$("#size1Hidden").val(eggSize1);
	
	let eggSize2 =  $("#size2").val().replace(reg, "")*1;
	$("#size2Hidden").val(eggSize2);
	
	let eggSize3 =  $("#size3").val().replace(reg, "")*1;
	$("#size3Hidden").val(eggSize3);
	
	let eggSize4 =  $("#size4").val().replace(reg, "")*1;
	$("#size4Hidden").val(eggSize4);
	
	let eggSize5 =  $("#size5").val().replace(reg, "")*1;
	$("#size5Hidden").val(eggSize5);
	
	let eggSize6 =  $("#size6").val().replace(reg, "")*1;
	$("#size6Hidden").val(eggSize6);
	
	console.log($("#breedingMethod").val());
	$.ajax({
			 url :"/registerHistoryNumber"
			,type:"post"
			,data:$("#historyNumberForm").serialize()
			,dataType:"json"
			,success:function(data){
				
				if(data.resultCode == 'success') {
					
					$.toast({ 
					  heading: 'Success',
					  text : "<h2>성공적으로 등록되었습니다.</h2>", 
					  showHideTransition : 'slide',  // It can be plain, fade or slide
					  icon: 'success',
					  allowToastClose : false,       // Show the close button or not
					  hideAfter : 2000,              // `false` to make it sticky or time in miliseconds to hide after
					  stack : 5,                     // `fakse` to show one stack at a time count showing the number of toasts that can be shown at once
					  textAlign : 'left',            // Alignment of text i.e. left, right, center
					  position : 'top-center'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
					})
					
				} else if (data.resultCode == 'duplicate') {
					
					$.toast({ 
					  heading: 'Warning',
					  text : "<h2>이미 등록한 내역이 있습니다.</h2>", 
					  showHideTransition : 'slide',  // It can be plain, fade or slide
					  icon: 'warning',
					  allowToastClose : false,       // Show the close button or not
					  hideAfter : 2000,              // `false` to make it sticky or time in miliseconds to hide after
					  stack : 5,                     // `fakse` to show one stack at a time count showing the number of toasts that can be shown at once
					  textAlign : 'left',            // Alignment of text i.e. left, right, center
					  position : 'top-center'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
					})
					
				} else if  (data.resultCode == 'error') {
					$.toast({ 
					  heading: 'Error',
					  text : "<h2>오류가 발생했습니다.</h2>", 
					  showHideTransition : 'slide',  // It can be plain, fade or slide
					  icon: 'danger',
					  allowToastClose : false,       // Show the close button or not
					  hideAfter : 2000,              // `false` to make it sticky or time in miliseconds to hide after
					  stack : 5,                     // `fakse` to show one stack at a time count showing the number of toasts that can be shown at once
					  textAlign : 'left',            // Alignment of text i.e. left, right, center
					  position : 'top-center'       // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values to position the toast on page
					})
					
				}

				searchHistoryNumbers(1);
				
			}
		    ,error: function(response){

		    	alert("에러발생 : " + response.responseText);
		    }
		});
}