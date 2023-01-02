/* ========================================================================
		공통 변수 
   ======================================================================== */
var reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

		searchEggTrade(1);
		
		let initDate = new Date();   
		let initDateYear = initDate.getFullYear(); // 년도
		let initDateMonth = initDate.getMonth() + 1;  // 월
		let initDateDate = initDate.getDate();  // 날짜
		//console.log(initDate.getUTCFullYear())
		var initDateStr = initDateYear+ '-' + initDateMonth.toString().padStart(2, '0') + '-' + initDateDate.toString().padStart(2, '0');
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
		$('#reportDate').val(initDateStr+ '(' + week[initDate.getDay()] + ')' );

});


var calendarEl = document.getElementById('calendar');

let today = new Date();   
let year = today.getFullYear(); // 년도
let month = today.getMonth() + 1;  // 월
let date = today.getDate();  // 날짜


var stdDate = year+ '-' + month.toString().padStart(2, '0') + '-' + date.toString().padStart(2, '0');
//console.log(stdDate);	
var calendar = new FullCalendar.Calendar(calendarEl, {
 		initialDate: stdDate,
      	locale: 'ko',
      	//dateFormat: 'YYYY-MM-DD',
//      	selectAllow: function(info) {
//	        if (!info.event.extendedProps.eggHistNo) {
//	            return false;
//	        }
//	    },
		//initialDate: '2023-01-01',
		eventOrder : 'displayOrder', 
      	select: function(obj){
			//console.log(obj)
			//console.log(typeof obj.start.getMonth())
			
			let selyear = obj.start.getFullYear(); // 년도
			let selmonth = String((obj.start.getMonth() + 1)+'').padStart(2, "0");  // 월
			let seldate = String((obj.start.getDate()+'').padStart(2, "0"));  // 날짜
			
			var selDateStr = selyear+ '-' + selmonth + '-' + seldate;
			var week = new Array('일', '월', '화', '수', '목', '금', '토');
			$('#reportDate').val(selDateStr+ '(' + week[obj.start.getDay()] + ')' );
			
			$('#reportDateHidden').val(selyear+selmonth+seldate);
			
			//입력정보 초기화 
			resetEggCount();
			
			
			//입고등록번호 초기화
			//$("#eggHistNoHidden").val(null);
			
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
                placement : 'bottom',
                theme: 'yellow',
                arrow : true,
                allowHTML : true
            });
        },
        eventClick: function(info) {
	
			//입력정보 초기화 
			resetEggCount();
			
			var clickStart = info.event._instance.range.start;
			
			let clickYear = clickStart.getFullYear(); // 년도
			let clickMonth = String((clickStart.getMonth() + 1)+'').padStart(2, "0");  // 월
			let clickDate = String((clickStart.getDate()+'').padStart(2, "0"));  // 날짜
			
			var clickDateStr = clickYear+ '-' + clickMonth + '-' + clickDate;
			var week = new Array('일', '월', '화', '수', '목', '금', '토');
			
			$('#reportDate').val(clickDateStr+ '(' + week[clickStart.getDay()] + ')' );
			//$('#reportDateHidden').val(clickYear+clickMonth+clickDate);
			
			
			let eggHistNo  		= info.event.extendedProps.eggHistNo ;				//이력번호
			let issueDate  		= info.event.extendedProps.issueDate ;				//이력번호 발급일자
			let spawningDate  	= info.event.extendedProps.spawningDate ;			//산란일자
			let requestDate  	= info.event.extendedProps.requestDate ;			//선별포장신고 일자
			
			let eggUsage  				= info.event.extendedProps.eggUsage ;				//계란용도
			let reporterBusinessNo  	= info.event.extendedProps.reporterBusinessNo ;			//사업자번호	
			let reporterLicenseNo  		= info.event.extendedProps.reporterLicenseNo ;			//인허가번호
			
			//$("#eggHistNoHidden").val(eggHistNo);
			
			let eggXxlDealt  	=  info.event.extendedProps.eggXxlDealt ;
			let eggXlDealt  	=  info.event.extendedProps.eggXlDealt ;
			let eggLDealt  		=  info.event.extendedProps.eggLDealt ;
			let eggMDealt  		=  info.event.extendedProps.eggMDealt ;
			let eggSDealt  		=  info.event.extendedProps.eggSDealt ;
			let eggEDealt  		=  info.event.extendedProps.eggEDealt ;
			let totalDealt 	 	=  info.event.extendedProps.totalDealt ;
			
			let html = "";
			if(info.event.groupId =="packing") {

				html +="<tr>";
				
				html +="	<td><input type='text' id='selEggHistNo' name='eggHistNo' class='w-100 p-0 m-0 text-end bg-tranparent' value='"+eggHistNo+"'/></td>"; 		//이력번호
				html +="	<td><input type='text' id='selIssueDate' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+dateFormat(issueDate,'-')+"'/></td>";			//신고일자
				html +="	<td><input type='text' id='selSpawningDate' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+dateFormat(spawningDate,'-')+"'/></td>";		//산란일자
				
				html +=' 		<input type="hidden" name="packingReportDate" id="packingReportDate" value="'+requestDate+'" >';
				html +=' 		<input type="hidden" name="eggUsage" id="eggUsage" value="'+eggUsage+'" >';
				html +=' 		<input type="hidden" name="reporterBusinessNo" id="reporterBusinessNo" value="'+reporterBusinessNo+'" >';
				html +=' 		<input type="hidden" name="reporterLicenseNo" id="packingReportDate" value="'+reporterLicenseNo+'" >';

				html +="	<td><input type='text' id='selEggXxlDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggXxlDealt+"'/></td>";		//왕란
				html +="	<td><input type='text' id='selEggXlDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggXlDealt+"'/></td>";		//특란
				html +="	<td><input type='text' id='selEggLDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggLDealt+"'/></td>";		//대란
				html +="	<td><input type='text' id='selEggMDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggMDealt+"'/></td>";		//중란
				html +="	<td><input type='text' id='selEggSDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggSDealt+"'/></td>";		//소란
				html +="	<td><input type='text' id='selEggEDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+eggEDealt+"'/></td>";		//기타
				html +="	<td><input type='text' id='selEggTotalDealt' class='w-100 p-0 m-0 text-end bg-tranparent'  value='"+totalDealt+"'/></td>";		//합계
				
				html +="</tr>";
				
				$("#eggPackingList").html(html);
				
				$("#eggPackingHeader").removeAttr("class");
				$("#eggPackingHeader").addClass("table-info text-center");	
				
				
				$("#accountListForTradeHeader").removeAttr("class");
				$("#accountListForTradeHeader").addClass("table-success text-center");	
				
				
				//거래일자를 선별포장 신고일자로 변경(초기 오늘날짜)
				let tds = document.querySelectorAll('.transDate');
				tds.forEach(td => td.value = dateFormat(requestDate,'-'));
				
				let tdHiddens = document.querySelectorAll('input[name="transDate"]');
				tdHiddens.forEach(td => td.value = requestDate);
					 //console.log(tdHiddens);
					 
				
				//이력번호 발급일자 입력
				$("#histNoIssueDate").val(issueDate);
				
				//산란일자 입력
				$("#spawningDate").val(spawningDate);
				
			}
			
			// 거래처별 자동 배분 ( 왕란,특란,대란,중란,소란,기타,합계)
			distributeByAccount(eggXxlDealt,eggXlDealt,eggLDealt,eggMDealt,eggSDealt,eggEDealt,totalDealt);
			
			// 모달
			setToast("bg-success", "선별포장신고 내역", null, "선별포장 신고내역이 업데이트 되었습니다.<br><br>거래처별 출고 수량을 수정하세요")
			bsAlert.show();//show it
			
		},

      	events: function(info, successCallback, failureCallback){
				    
				    $.ajax({
				        type : 'GET',
						url : '/callEggTradeSucceeded',
						dataType : 'json',
						contentType : "application/json",
				        success: function(data){
							//console.log(data.HistoryList);
				            var events = [];
				            
				            var history = data.eventList.history;
				            var packaging = data.eventList.packaging;
				            var breeding = data.eventList.breeding;
				            var shipment = data.eventList.shipment;
				            
				            
				            if ( history !=null && history.length > 0 ) {
								for(var i=0; i < history.length; i++) {
									events.push({
								 		title : history[i].title,
								 		start : history[i].start.substr(0,4)+'-'+history[i].start.substr(4,2)+'-'+history[i].start.substr(6,2),
								 		end : history[i].end.substr(0,4)+'-'+history[i].end.substr(4,2)+'-'+history[i].end.substr(6,2),
								 		groupId : history[i].groupId,
								 		extendedProps: {
									
											eggHistNo	: history[i].eggHistNo,
											reportDate	: history[i].reportDate,
											spawningDate	: history[i].spawningDate,
											
									        eggXxl	: history[i].eggXxl,
									        eggXl	: history[i].eggXl,
									        eggL	: history[i].eggL,
									        eggM	: history[i].eggM,
									        eggS	: history[i].eggS,
									        eggE	: history[i].eggE
									      },
								 		description : history[i].description,
								 		backgroundColor: 'rgba(36, 113, 163, 0.4)',
								 		borderColor: 'rgba(36, 113, 163, 0.4)',
								 		textColor : 'rgba(0, 0, 0, 0.2)'
								 	});	
								}// for		
							} // if
							
							if (packaging !=null && packaging.length > 0) {
								for(var i=0; i < packaging.length; i++) {
									events.push({
								 		title : packaging[i].title,
								 		start : packaging[i].start.substr(0,4)+'-'+packaging[i].start.substr(4,2)+'-'+packaging[i].start.substr(6,2),
								 		end : packaging[i].end.substr(0,4)+'-'+packaging[i].end.substr(4,2)+'-'+packaging[i].end.substr(6,2),
								 		groupId : packaging[i].groupId,
								 		extendedProps: {
									
											eggHistNo		: packaging[i].eggHistNo,
											issueDate		: packaging[i].issueDate,
											spawningDate	: packaging[i].spawningDate,
											requestDate		: packaging[i].requestDate,
											
											eggUsage		: packaging[i].eggUsage,
											reporterBusinessNo		: packaging[i].reporterBusinessNo,
											reporterLicenseNo		: packaging[i].reporterLicenseNo,

									        eggXxl			: packaging[i].eggXxl,
									        eggXl			: packaging[i].eggXl,
									        eggL			: packaging[i].eggL,
									        eggM			: packaging[i].eggM,
									        eggS			: packaging[i].eggS,
									        eggE			: packaging[i].eggE,
									        
									        eggXxlDealt		: packaging[i].eggXxlDealt,
									        eggXlDealt		: packaging[i].eggXlDealt,
									        eggLDealt		: packaging[i].eggLDealt,
									        eggMDealt		: packaging[i].eggMDealt,
									        eggSDealt		: packaging[i].eggSDealt,
									        eggEDealt		: packaging[i].eggEDealt,
									        
									        eggXxlDispose	: packaging[i].eggXxlDispose,
									        eggXlDispose	: packaging[i].eggXlDispose,
									        eggLDispose		: packaging[i].eggLDispose,
									        eggMDispose		: packaging[i].eggMDispose,
									        eggSDispose		: packaging[i].eggSDispose,
									        eggEDispose		: packaging[i].eggEDispose,
									        
									        totalEgg		: packaging[i].totalEgg,
									        totalDealt		: packaging[i].totalDealt,
									        totalDispose	: packaging[i].totalDispose
									      },
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
									console.log(shipment[i].description);
									events.push({
								 		title : shipment[i].title,
								 		start : shipment[i].start.substr(0,4)+'-'+shipment[i].start.substr(4,2)+'-'+shipment[i].start.substr(6,2),
								 		end : shipment[i].end.substr(0,4)+'-'+shipment[i].end.substr(4,2)+'-'+shipment[i].end.substr(6,2),
								 		groupId : shipment[i].groupId,
								 		extendedProps: {
									
											eggHistNo			: shipment[i].eggHistNo,
											histNoIssueDate		: shipment[i].histNoIssueDate,
											packingReportDate	: shipment[i].packingReportDate,
											spawningDate		: shipment[i].spawningDate,
											
											eggUsage				: shipment[i].eggUsage,
											reporterBusinessNo		: shipment[i].reporterBusinessNo,
											reporterLicenseNo		: shipment[i].reporterLicenseNo
											
									      },
								 		
								 		description : shipment[i].description,
								 		backgroundColor: '#A569BD',
								 		borderColor: '#A569BD',
								 		textColor : 'FFF'
								 	});	
								}// for		
							} // if

				        successCallback(events)
				        
				        
				    }
				});	
			}
	 });
	 
document.addEventListener('DOMContentLoaded', function() {

    calendar.render();
   
    //글자 웨이브 효과
  });
  
  

//  waveAnimation("wave-title", "신고하기")

/* ========================================================================
		초기화 함수 
   ======================================================================== */
function resetEggCount() {
	$("#eggPackingHeader").removeAttr("class");
	$("#eggPackingHeader").addClass("table-light text-center");
	
	$("#accountListForTradeHeader").removeAttr("class");
	$("#accountListForTradeHeader").addClass("table-light text-center");
	
	
	let html ='<td class="text-center ts-18 py-2" colspan="10">선별포장신고 내역을 선택하세요</td>';
	$("#eggPackingList").html(html);
	
}

/* ========================================================================
		이력등록번호 확인 함수 
   ======================================================================== */
function chkHistNo () {
	//입고등록 확인
	if($("#selEggHistNo").val() == null || $("#selEggHistNo").val().length <1 ) {
		alert("출고 신고할 일자의 [선별포장신고]를 클릭하세요");
		return false;
	
	}
}
 	
/* ========================================================================
		입고내역 자동 배분
   ======================================================================== */
function distributeByAccount(eggXxlDealt,eggXlDealt,eggLDealt,eggMDealt,eggSDealt,eggEDealt,totalDealt){
	
	let nodes = document.querySelector("#accountListForTrade").querySelectorAll("input[name='accountBusinessNo']")

	// 객체 생성
	var sizeData = new Object() ;

	sizeData.eggXxlDealt 	= eggXxlDealt.replace(reg, "")*1;
	sizeData.eggXlDealt 	= eggXlDealt.replace(reg, "")*1;
	sizeData.eggLDealt 		= eggLDealt.replace(reg, "")*1;
	sizeData.eggMDealt 		= eggMDealt.replace(reg, "")*1;
	sizeData.eggSDealt 		= eggSDealt.replace(reg, "")*1;
	sizeData.eggEDealt 		= eggEDealt.replace(reg, "")*1;
	sizeData.totalDealt 	= totalDealt.replace(reg, "")*1;

	//console.log(sizeData);
	
	$.each(sizeData, function(key, value){
		makeAccountArry(key, value ,nodes.length)
		}
	);
	
}

function makeAccountArry(id, cnt ,dataLength){
//	let inputId = 'arr'+capitalize(id);
//	console.log(inputId);
	//console.log(id+' '+cnt+' '+dataLength)
	for(let index=0; index < dataLength; index++){
		let val = ( cnt / dataLength).toFixed();
		let valLast = cnt - (val*(dataLength -1));
		if ( index < dataLength -1) {
			// 텍스트
			$("#"+id+index).val(numberWithCommas(val)) ;
			// Hidden num
			$("#"+id.replace("Dealt","")+index).val((val)) ;
		} else if (index == dataLength -1) {
			// 텍스트
			$("#"+id+index).val(numberWithCommas(valLast)) ;
			// Hidden num
			$("#"+id.replace("Dealt","")+index).val((valLast)) ;
		}
		sumAccountRow(index)
//		console.log("#"+id+index);
	}

}

/* ========================================================================
		리스트 로딩 함수 
   ======================================================================== */	
	
	
// 페이지 로딩 	
function searchEggTrade(page){
	

	if(page==null || page==''){
		page=1;
	}

	// 거래처 정보 조회
	$.ajax({
		type : 'GET',
		url : '/callAccountListForEggTrade',
		dataType : 'json',
		contentType : "application/json",
		data : {
            "page" : page
        },
		success : function(data) {
			// 데이터 로딩
			var dataList = data.accountList;
			
			//console.dir(dataList);
			var html="";

			for(var i=0; i<dataList.length; i++){
				var list = dataList[i];


				html+='<tr class="">';
				
				html+='<td class="text-center">'+ list.num +'</td>';
				html+='<td class="">'+ list.accountNm +'</td>';
				html+='<td class="text-center">'+ list.businessNo +'</td>';
				html+='		<input type="hidden" name="accountNm" id="accountNm" value="'+list.accountNm+'" >';
				html+='		<input type="hidden" name="accountBusinessNo" id="businessNo" value="'+list.businessNo+'" >';
				html+='		<input type="hidden" name="accountLicenseNo" id="licenseNo" value="'+list.licenseNo+'" >';
				html+='		<input type="hidden" name="transType" id="transType" value="309002" >';	//거래유형 : 출고
				
				html+='<td class="text-center " ><input type="text" class="transDate w-100 p-0 m-0 py-2 text-center bg-tranparent" oninput="inputDateFormat(this,\'-\')" onKeyUp="setTransDate(this.value,\'transDate'+i+'\')" value="'+dateFormat(year+''+month.toString().padStart(2, '0')+''+date.toString().padStart(2, '0'),'-')+'" > </td>';		//거래일자	
				html+='		<input type="hidden" name="transDate" id="transDate'+i+'" value="'+dateFormat(year+''+month.toString().padStart(2, '0')+''+date.toString().padStart(2, '0'),'')+'"  >';
				
				html+='<td class="" style="text-align: right;"> <input id="eggXxlDealt'+i+'" type="text" class="w-100 p-0 m-0 py-2 text-end bg-tranparent" name="eggXxlDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggXxl" id="eggXxl'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggXlDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggXlDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggXl" id="eggXl'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggLDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggLDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggL" id="eggL'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggMDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggMDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggM" id="eggM'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggSDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggSDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggS" id="eggS'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggEDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggEDealtStr" placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggE" id="eggE'+i+'" value="" >';
				html+='<td class="" style="text-align: right;"> <input id="eggTotalDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggTotalDealtStr" value="0" disabled></td>';				
				html+='		<input type="hidden" name="totalEgg" id="totalEgg'+i+'" value="" >';
				html+='</tr>';

			} 
							
			$("#accountListForTrade").html(html);
			
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
				pageHtml+='<a class="page-link" onclick="searchEggTrade('+(pageInfo.page-1)+')" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}
			for(var i=pageInfo.startPage;i<=pageInfo.endPage;i++){
				if(pageInfo.page==i){
					pageHtml+='<li class="page-item active">';
					pageHtml+='<a class="page-link" onclick="searchEggTrade('+i+')">'+i+' <span class="sr-only">(current)</span></a>';
					pageHtml+='</li>';
				}else{
					pageHtml+='<li class="page-item">';
					pageHtml+='<a class="page-link" onclick="searchEggTrade('+i+')">'+i+' </a>';
					pageHtml+='</li>';
				}
			}
			if(pageInfo.page>=pageInfo.maxPage){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchEggTrade('+(pageInfo.page+1)+')" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}

			$("#accountPageing").html(pageHtml);
			
		} 
	});
	
	
	
	// 등록내역 조회
	$.ajax({
		type : 'GET',
		url : '/callEggTrade',
		dataType : 'json',
		contentType : "application/json",
		data : {
            "page" : page
        },
		success : function(data) {
			// 데이터 로딩
			var dataList = data.eventList;
			
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
					html+='<td class="text-success">'+ list.eggHistIdx +'</td>';
					
					html+='<td class="text-success">'+ list.spawningDate +'</td>';
					html+='<td class="text-success">'+ list.histNoIssueDate +'</td>';
					html+='<td class="text-success">'+ list.packingReportDate +'</td>';
					
					html+='<td class="text-success">'+ list.transInfo +'</td>';
					
					
	
					html+='</tr>';
					
				} else if (list.resultCode.includes("ERROR")) {
					
					html+='<tr>';
					
					html+='<td class="text-danger">'+ list.num +'</td>';
					
					html+='<td class="text-danger">'+ formatDateTime(list.reportTime) +'</td>';
					html+='<td class="text-danger">'+ list.resultCode +'</td>';
					html+='<td class="text-danger">'+ list.resultMsg +'</td>';
					
					html+='<td class="text-danger">'+ list.eggHistNo +'</td>';
					html+='<td class="text-danger">'+ list.eggHistIdx +'</td>';
					
					html+='<td class="text-danger">'+ list.spawningDate +'</td>';
					html+='<td class="text-danger">'+ list.histNoIssueDate +'</td>';
					html+='<td class="text-danger">'+ list.packingReportDate +'</td>';
					
					html+='<td class="text-danger">'+ list.transInfo +'</td>';
	
					
					html+='</tr>';
					
				} else {
					
					html+='<tr>';

					html+='<td class="">'+ list.num +'</td>';
					
					html+='<td class="">'+ formatDateTime(list.reportTime) +'</td>';
					html+='<td class="">'+ list.resultCode +'</td>';
					html+='<td class="">'+ list.resultMsg +'</td>';
					
					html+='<td class="">'+ list.eggHistNo +'</td>';
					html+='<td class="">'+ list.eggHistIdx +'</td>';
					
					html+='<td class="">'+ list.spawningDate +'</td>';
					html+='<td class="">'+ list.histNoIssueDate +'</td>';
					html+='<td class="">'+ list.packingReportDate +'</td>';
					
					html+='<td class="">'+ list.transInfo +'</td>';
	
					html+='</tr>';
					
				}
				
				
			
                
                
			}
							
			$("#egg-packing-body").html(html);
			
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
				pageHtml+='<a class="page-link" onclick="searchEggPacking('+(pageInfo.page-1)+')" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}
			for(var i=pageInfo.startPage;i<=pageInfo.endPage;i++){
				if(pageInfo.page==i){
					pageHtml+='<li class="page-item active">';
					pageHtml+='<a class="page-link" onclick="searchEggPacking('+i+')">'+i+' <span class="sr-only">(current)</span></a>';
					pageHtml+='</li>';
				}else{
					pageHtml+='<li class="page-item">';
					pageHtml+='<a class="page-link" onclick="searchEggPacking('+i+')">'+i+' </a>';
					pageHtml+='</li>';
				}
			}
			if(pageInfo.page>=pageInfo.maxPage){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchEggPacking('+(pageInfo.page+1)+')" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}

			$("#pageing").html(pageHtml);
			
		} 
	});


}

/* ========================================================================
		거래일자 입력
   ======================================================================== */
function setTransDate(val,target){
	var parts = val.toString().split("-"); 
	
	if ( parts.length == 3 ) {
		$("#"+target).val(parts[0]+parts[1].padStart(2, '0')+parts[2].padStart(2, '0'));
	}
	
}   

/* ========================================================================
		거래수량 입력
   ======================================================================== */
function setEggVal(obj){
	//console.log(obj);
	//console.log(obj.id.replace("Dealt",""));
	//console.log(obj.value);
	
	$("#"+obj.id.replace("Dealt","")).val(obj.value);
}      

/* ========================================================================
		거래처별 합계 
   ======================================================================== */	
function sumAccountRow(index) {
	
	let eggXxlDealt = $("#eggXxlDealt"+index).val().replace(reg, "")*1;
	let eggXlDealt = $("#eggXlDealt"+index).val().replace(reg, "")*1;
	let eggLDealt = $("#eggLDealt"+index).val().replace(reg, "")*1;
	let eggMDealt = $("#eggMDealt"+index).val().replace(reg, "")*1;
	let eggSDealt = $("#eggSDealt"+index).val().replace(reg, "")*1;
	let eggEDealt = $("#eggEDealt"+index).val().replace(reg, "")*1;
	let eggTotalDealt = $("#eggTotalDealt"+index);
	let eggTotal = $("#totalEgg"+index);
	
	eggTotalDealt.val(numberWithCommas(eggXxlDealt+eggXlDealt+eggLDealt+eggMDealt+eggSDealt+eggEDealt));
	eggTotal.val(eggXxlDealt+eggXlDealt+eggLDealt+eggMDealt+eggSDealt+eggEDealt);
	sumTotalTrade();
	
}

function sumTotalTrade() {
	let nodes = document.querySelector("#accountListForTrade").querySelectorAll("input[name='accountBusinessNo']")
	//console.log(nodes.length);
//	nodes.forEach((node,index) => {
//		console.log(node);
//	});
	
	
	let sumEggXxlDealt=0;
	let sumEggXlDealt=0;
	let sumEggLDealt=0;
	let sumEggMDealt=0;
	let sumEggSDealt=0;
	let sumEggEDealt=0;
	let sumEggTotalDealt=0;

	for(let index=0; index < nodes.length; index++){
		let eggXxlDealt = $("#eggXxlDealt"+index).val().replace(reg, "")*1;
		let eggXlDealt = $("#eggXlDealt"+index).val().replace(reg, "")*1;
		let eggLDealt = $("#eggLDealt"+index).val().replace(reg, "")*1;
		let eggMDealt = $("#eggMDealt"+index).val().replace(reg, "")*1;
		let eggSDealt = $("#eggSDealt"+index).val().replace(reg, "")*1;
		let eggEDealt = $("#eggEDealt"+index).val().replace(reg, "")*1;
		let eggTotalDealt = $("#eggTotalDealt"+index).val().replace(reg, "")*1;
		
		sumEggXxlDealt 	+=eggXxlDealt;
		sumEggXlDealt 	+=eggXlDealt;
	 	sumEggLDealt 		+=eggLDealt;
	 	sumEggMDealt 		+=eggMDealt;
	 	sumEggSDealt 		+=eggSDealt;
	 	sumEggEDealt 		+=eggEDealt;
	 	sumEggTotalDealt 	+=eggTotalDealt;
	}
	
	$("#sumEggXxlDealt").val(numberWithCommas(sumEggXxlDealt));
	$("#sumEggXlDealt").val(numberWithCommas(sumEggXlDealt));
	$("#sumEggLDealt").val(numberWithCommas(sumEggLDealt));
	$("#sumEggMDealt").val(numberWithCommas(sumEggMDealt));
	$("#sumEggSDealt").val(numberWithCommas(sumEggSDealt));
	$("#sumEggEDealt").val(numberWithCommas(sumEggEDealt));
	$("#sumEggTotalDealt").val(numberWithCommas(sumEggTotalDealt));
	
}
/* ========================================================================
		등록 버튼 클릭 
   ======================================================================== */	
   
   

function submitEggTrade() {
		
	//입고등록 확인
	if ( chkHistNo() == false){
		return false;
	}
	
	//합계 검증
	let size1 = $("#selEggXxlDealt").val().replace(/,/g, "")*1;
	let size2 = $("#selEggXlDealt").val().replace(/,/g, "")*1;
	let size3 = $("#selEggLDealt").val().replace(/,/g, "")*1;
	let size4 = $("#selEggMDealt").val().replace(/,/g, "")*1;
	let size5 = $("#selEggSDealt").val().replace(/,/g, "")*1;
	let size6 = $("#selEggEDealt").val().replace(/,/g, "")*1;
	let size7 = $("#selEggTotalDealt").val().replace(/,/g, "")*1;
			
	let calc1 = $("#sumEggXxlDealt").val().replace(/,/g, "")*1;
	let calc2 = $("#sumEggXlDealt").val().replace(/,/g, "")*1;
	let calc3 = $("#sumEggLDealt").val().replace(/,/g, "")*1;
	let calc4 = $("#sumEggMDealt").val().replace(/,/g, "")*1;
	let calc5 = $("#sumEggSDealt").val().replace(/,/g, "")*1;
	let calc6 = $("#sumEggEDealt").val().replace(/,/g, "")*1;
	let calc7 = $("#sumEggTotalDealt").val().replace(/,/g, "")*1;
	
	for (var i =1; i < 8 ; i++ ) {
		if( eval("size"+i) != eval("calc"+i) ){
			alert(i+"번째 합계가 불일치 합니다.");
			return false;
		}
	}

	
	var reportDate = new Date($('#reportDateHidden').val().substr(0,4)+'-'+$('#reportDateHidden').val().substr(4,2)+'-'+$('#reportDateHidden').val().substr(6,2));
	
	
	let now = new Date();   
	let year = now.getFullYear(); // 년도
	let month = now.getMonth() + 1;  // 월
	let date = now.getDate();  // 날짜
	var today = new Date(year+'-'+month.toString().padStart(2, '0')+'-'+date.toString().padStart(2, '0'));
	//console.log(today);
	
	// 입력기간 검증
	if( reportDate > today) {
		alert("출고일자는 현재일자와 같거나 이전이어야 합니다.")
		return false;
	}

	
	
	let formData = $("#eggTradeForm").serializeObject()
	let formDetailData = $("#eggTradeDetailForm").serializeArrayObject()
	formData.eggTradeList = formDetailData;

	
	$.ajax({
			 url :"/registerEggTrade"
			,type:"post"
			,data: JSON.stringify(formData)
			,dataType:"json"
			,contentType: 'application/json'
			,success:function(data){
				
				if(data.resultCode == 'success') {
					
					setToast("bg-success", "출고 등록", null, "성공적으로 등록되었습니다.")
					bsAlert.show();//show it
					
					
					
				} else if (data.resultCode == 'duplicate') {
					
					setToast("bg-warning", "출고 등록", null, "이미 등록한 내역이 있습니다.")
					bsAlert.show();//show it
					
					
					
				} else if  (data.resultCode == 'error') {
					
					setToast("bg-danger", "출고 등록", null, "오류가 발생했습니다.<br>"+data.resultCode+"<br>"+data.resultMsg)
					bsAlert.show();//show it
	
				}
				resetEggCount();
				searchEggTrade(1);
				calendar.refetchEvents();
				//location.reload();
				
			}
		    ,error: function(response){

		    	alert("에러발생 : " + response.responseText);
		    }
		});
}