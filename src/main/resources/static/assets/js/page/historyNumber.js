/* ========================================================================
		공통 변수 
   ======================================================================== */

/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

		searchHistoryNumbers(1);
		
		let initDate = new Date();   
		let initDateYear = initDate.getFullYear(); // 년도
		let initDateMonth = initDate.getMonth() + 1;  // 월
		let initDateDate = initDate.getDate();  // 날짜
		
		var initDateStr = initDateYear+ '-' + initDateMonth + '-' + initDateDate;
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
		$('#reportDate').val(initDateStr+ '(' + week[initDate.getDay()] + ')' );
		
		
				
});


var calendarEl = document.getElementById('calendar');

let today = new Date();   
let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜

var stdDate = year+ '-' + month + '-' + date;
	
var calendar = new FullCalendar.Calendar(calendarEl, {
		initialDate: stdDate,
      	locale: 'ko',
      	select: function(obj){
			//console.log(obj.start)
			//console.log(typeof obj.start.getMonth())
			
			let selyear = obj.start.getFullYear(); // 년도
			let selmonth = String((obj.start.getMonth() + 1)+'').padStart(2, "0");  // 월
			let seldate = String((obj.start.getDate()+'').padStart(2, "0"));  // 날짜
			
			var selDateStr = selyear+ '-' + selmonth + '-' + seldate;
			var week = new Array('일', '월', '화', '수', '목', '금', '토');
			$('#reportDate').val(selDateStr+ '(' + week[obj.start.getDay()] + ')' );
			
			$('#reportDateHidden').val(selyear+selmonth+seldate);
		},
      	expandRows: true,
      	headerToolbar: {
	        left: 'prevYear,prev, today',
	        center: 'title',
	        right: 'next,nextYear'
	     },
      	navLinks: false, // can click day/week names to navigate views
      	editable: true,
      	selectable: true,
      	nowIndicator: true,
      	dayMaxEvents: false, // allow "more" link when too many events
      	eventDidMount: function(info) {
            tippy(info.el, {
                content:  info.event.extendedProps.description,//이벤트 디스크립션을 툴팁으로 가져옵니다. 
            });
        },
      	events: function(info, successCallback, failureCallback){
				    
				    $.ajax({
				        type : 'GET',
						url : '/callHistoryNumberSucceeded',
						dataType : 'json',
						contentType : "application/json",
				        success: function(data){
							//console.log(data.HistoryList);
				            var events = [];
				            
				            var history = data.HistoryList.history;
				            var packaging = data.HistoryList.packaging;
				            var breeding = data.HistoryList.breeding;
				            var shipment = data.HistoryList.shipment;
				            
				            
				            if ( history !=null && history.length > 0 ) {
								for(var i=0; i < history.length; i++) {
									events.push({
								 		title : history[i].title,
								 		start : history[i].start.substr(0,4)+'-'+history[i].start.substr(4,2)+'-'+history[i].start.substr(6,2),
								 		end : history[i].end.substr(0,4)+'-'+history[i].end.substr(4,2)+'-'+history[i].end.substr(6,2),
								 		description : history[i].description,
								 		backgroundColor: '#2471A3',
								 		borderColor: '#2471A3',
								 		textColor : '#FFF'
								 	});	
								}// for		
							} // if
							
							if (packaging !=null && packaging.length > 0) {
								for(var i=0; i < packaging.length; i++) {
									events.push({
								 		title : packaging[i].title,
								 		start : packaging[i].start.substr(0,4)+'-'+packaging[i].start.substr(4,2)+'-'+packaging[i].start.substr(6,2),
								 		end : packaging[i].end.substr(0,4)+'-'+packaging[i].end.substr(4,2)+'-'+packaging[i].end.substr(6,2),
								 		description : packaging[i].description,
								 		backgroundColor: '#24A388',
								 		borderColor: '#24A388',
								 		textColor : '#FFF'
								 	});	
								}// for		
							} // if
							
							if (breeding !=null && breeding.length > 0) {
								for(var i=0; i < breeding.length; i++) {
									events.push({
								 		title : breeding[i].title,
								 		start : breeding[i].start.substr(0,4)+'-'+breeding[i].start.substr(4,2)+'-'+breeding[i].start.substr(6,2),
								 		end : breeding[i].end.substr(0,4)+'-'+breeding[i].end.substr(4,2)+'-'+breeding[i].end.substr(6,2),
								 		description : breeding[i].description,
								 		backgroundColor: '##28B463',
								 		borderColor: '##28B463',
								 		textColor : '#FFF'
								 	});	
								}// for		
							} // if 
							
							if (shipment !=null && shipment.length > 0) {
								for(var i=0; i < shipment.length; i++) {
									events.push({
								 		title : shipment[i].title,
								 		start : shipment[i].start.substr(0,4)+'-'+shipment[i].start.substr(4,2)+'-'+shipment[i].start.substr(6,2),
								 		end : shipment[i].end.substr(0,4)+'-'+shipment[i].end.substr(4,2)+'-'+shipment[i].end.substr(6,2),
								 		description : shipment[i].description,
								 		backgroundColor: '#A569BD',
								 		borderColor: '#A569BD',
								 		textColor : 'FFF'
								 	});	
								}// for		
							} // if

				        successCallback(events);
				    }
				});	
			}
	 });
	 
document.addEventListener('DOMContentLoaded', function() {

    calendar.render();
  });
  
  
  
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
				if (list.resultCode == "INFO-0000") {
					html+='<tr>';
					
				
					html+='<td class="text-success">'+ list.num +'</td>';
					
					html+='<td class="text-success">'+ formatDateTime(list.reportTime) +'</td>';
					html+='<td class="text-success">'+ list.resultCode +'</td>';
					html+='<td class="text-success">'+ list.resultMsg +'</td>';
					
					html+='<td class="text-success">'+ list.eggHistNo +'</td>';
					html+='<td class="text-success">'+ list.businessNo +'</td>';
					html+='<td class="text-success">'+ list.licenseNo +'</td>';
					html+='<td class="text-success">'+ list.farmIdNo +'</td>';
					
					html+='<td class="text-success">'+ list.eggUsage +'</td>';
					html+='<td class="text-success">'+ list.spawningDate +'</td>';
					html+='<td class="text-success">'+ list.storageMethod +'</td>';
					html+='<td class="text-success">'+ list.washingMethod +'</td>';
					
					
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggXxl) +'</td>';
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggXl) +'</td>';
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggL) +'</td>';
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggM) +'</td>';
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggS) +'</td>';
					html+='<td class="text-success" style="text-align: right;">'+ numberWithCommas(list.eggE) +'</td>';
	
					html+='</tr>';
					
				} else if (list.resultCode.includes("ERROR")) {
					
					html+='<tr>';

					html+='<td class="text-danger">'+ list.num +'</td>';
					
					html+='<td class="text-danger">'+ formatDateTime(list.reportTime) +'</td>';
					html+='<td class="text-danger">'+ list.resultCode +'</td>';
					html+='<td class="text-danger">'+ list.resultMsg +'</td>';
					
					html+='<td class="text-danger">'+ list.eggHistNo +'</td>';
					html+='<td class="text-danger">'+ list.businessNo +'</td>';
					html+='<td class="text-danger">'+ list.licenseNo +'</td>';
					html+='<td class="text-danger">'+ list.farmIdNo +'</td>';
					
					html+='<td class="text-danger">'+ list.eggUsage +'</td>';
					html+='<td class="text-danger">'+ list.spawningDate +'</td>';
					html+='<td class="text-danger">'+ list.storageMethod +'</td>';
					html+='<td class="text-danger">'+ list.washingMethod +'</td>';
					
					
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggXxl) +'</td>';
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggXl) +'</td>';
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggL) +'</td>';
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggM) +'</td>';
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggS) +'</td>';
					html+='<td class="text-danger" style="text-align: right;">'+ numberWithCommas(list.eggE) +'</td>';
	
					html+='</tr>';
					
				} else {
					
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
				pageHtml+='<a class="page-link" onclick="searchHistoryNumbers('+(pageInfo.page-1)+')" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}
			for(var i=pageInfo.startPage;i<=pageInfo.endPage;i++){
				if(pageInfo.page==i){
					pageHtml+='<li class="page-item active">';
					pageHtml+='<a class="page-link" onclick="searchHistoryNumbers('+i+')">'+i+' <span class="sr-only">(current)</span></a>';
					pageHtml+='</li>';
				}else{
					pageHtml+='<li class="page-item">';
					pageHtml+='<a class="page-link" onclick="searchHistoryNumbers('+i+')">'+i+' </a>';
					pageHtml+='</li>';
				}
			}
			if(pageInfo.page>=pageInfo.maxPage){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchHistoryNumbers('+(pageInfo.page+1)+')" href="#" id="nextLang">'+nextLang+'</a>';
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
	var spawningDate = new Date($('#reportDateHidden').val().substr(0,4)+'-'+$('#reportDateHidden').val().substr(4,2)+'-'+$('#reportDateHidden').val().substr(6,2));
	
	//console.log(spawningDate);
	
	let now = new Date();   
	let year = now.getFullYear(); // 년도
	let month = now.getMonth() + 1;  // 월
	let date = now.getDate();  // 날짜
	var today = new Date(year+'-'+month+'-'+date);
	//console.log(today);
	
	if( spawningDate > today) {
		alert("산란일자는 현재일자와 같거나 이전이어야 합니다.")
		return false;
	}
			
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
	
	
	$("#spawningDateHidden").val($("#reportDateHidden").val());
	
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
				calendar.refetchEvents();
				//location.reload();
				
			}
		    ,error: function(response){

		    	alert("에러발생 : " + response.responseText);
		    }
		});
}