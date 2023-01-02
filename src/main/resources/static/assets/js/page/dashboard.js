/* ========================================================================
		공통 변수 
   ======================================================================== */
var reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){


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

var calendar = new FullCalendar.Calendar(calendarEl, {
 		initialDate: stdDate,
 		//initialDate : '2023-01-02',
 		height: "calc(100vh - 140px)",
      	locale: 'ko',
		eventOrder : 'displayOrder', 
      	select: function(obj){

		},
      	expandRows: true,
      	headerToolbar: {
	        left: 'prevYear,prev, today',
	        center: 'title',
	        right: 'next,nextYear'
	     },
	     titleFormat: { // will produce something like "Tuesday, September 18, 2018"
		    month: 'long',
		    year: 'numeric',
		    day: 'numeric',
		    weekday: 'long'
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

  });
  
