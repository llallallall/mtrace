/* ========================================================================
		공통 변수 
   ======================================================================== */
var dateList = [];
let loadedList;
var loadedEls=[];
/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

		searchHistoryNumbers(1);
		

		// 기존 등록 목록 조회

		$.ajax({
			type : 'GET',
			url : '/callHistoryNumberSucceeded',
			dataType : 'json',
			contentType : "application/json",
			success : function(data) {
					// 데이터 로딩
					var hnList = data.HistoryList;
			
					for(var i=0; i<hnList.length; i++){
						var list = hnList[i].substr(0,4)+'-'+hnList[i].substr(4,2)+'-'+hnList[i].substr(6,2);
						dateList.push(list);
					} //for
					
					console.log('makeDateList : ' + dateList);	
					loadedList = dateList;
					for(var i=0; i < dateList.length ; i++) {
					
					var dt = new Date(dateList[i]);
					changeNodeList(dt);
					}
					
					

			} // success 

		});	 //ajax''
		
		$.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd(D)',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년'
	    });
		
		// 목록 조회 후 datepicker 로딩 	
		$('#datepickerReportDate').datepicker({
			
			todayHighlight:true,
//			beforeShowDay:changeNodeListAll
			onSelect:function(dateText, inst) {
//				console.log(dateText);
//				console.log(inst);
				let reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
			    $('#reportDateHidden').val(
			        dateText.substr(0,10).replace(reg, "")
			    );
			    
			    $('#reportDate').val(
			        dateText
			    );
				changeNodeListAll();
			}
		});
	
		// 기존 등록 목록 색상 등록
		function changeNodeList(in_date) {
			if ( in_date ) {
				var in_year = in_date.getFullYear();
				var in_mth = in_date.getMonth();
				var in_day = in_date.getDate();
				var elY = Array.from(document.querySelectorAll('[data-year="'+in_year+'"]'));

				for(var i=0; i < elY.length ;i++) {
					if(elY[i].dataset.month == in_mth) {
						if (elY[i].childNodes[0].dataset.date == in_day) {
							loadedEls.push(elY[i].childNodes[0].parentElement);
							//console.log(loadedEls)
							elY[i].childNodes[0].classList.remove("orange-highlight");
							elY[i].childNodes[0].classList.add("orange-highlight");
						}
					}
				}

			} 
		}	
		
		// 기존 등록 목록 색상 등록
		function changeNodeListAll() {
			
			//console.log("changeNodeListAll " + loadedEls);
			//console.dir(loadedEls);
			for(var i=0; i < loadedEls.length; i++) {
				//console.log(loadedEls[i].querySelector('a'))
				var el = loadedEls[i].querySelector('a')
				el.classList.remove('ui-state-default');
				
				setTimeout(function( ) { 
					console.log(el);
					el.classList.add('orange-highlight');
				} , 1000);
				
				
				//ui-state-default ui-state-highlight ui-state-active orange-highlight ui-state-hover
				//ui-state-default orange-highlight
				//ui-state-default ui-state-highlight ui-state-hover
				//ui-state-default ui-state-hover
			}
//			if ( in_date ) {
//				var in_year = in_date.getFullYear();
//				var in_mth = in_date.getMonth();
//				var in_day = in_date.getDate();
//				var elY = Array.from(document.querySelectorAll('[data-year="'+in_year+'"]'));
//
//				for(var i=0; i < elY.length ;i++) {
//					if(elY[i].dataset.month == in_mth) {
//						if (elY[i].childNodes[0].dataset.date == in_day) {
//							loadedEls.push(elY[i].childNodes[0]);
//							elY[i].childNodes[0].classList.add("orange-highlight");
//						}
//					}
//				}
//
//			} 
		}	
		

		
				
		// 기존 등록 목록 비활성화
		function chkDateList(this_date) {
			this_date = this_date.getFullYear()+ '-'
                + (this_date.getMonth() + 1) + '-' + this_date.getDate() ;
            
            if(loadedList) {
	            console.log(loadedList);
				for(var i=0; i < loadedList.length; i++) {
					var num_dt = new Date(dateList[i]);
					console.log(num_dt*1);
					//1672012800000  26
					//1672099200000  27
					var el = document.querySelector('[data-date="'+num_dt*1+'"]');
					console.log(el);
					el.classList.add("orange-highlight");
					el.style.background = 'rgba(255, 137, 76, 0.5)';
				}   
            };
                   
			if( dateList.indexOf(this_date) > -1 )	 {
				return [false, "notav", 'Not Available'];
			} else {
				return [true, "av", "available"];
			};

		    
		} // function

		
//		$('#datepickerReportDate').on('changeDate', function() {
//			
//		    
//		});
		
		
		let today = new Date();   
	
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		
		var week = new Array('일', '월', '화', '수', '목', '금', '토');
		
		$("#datepickerReportDate").datepicker("setDate", today);
		$('#reportDate').val(year+ '-' + month + '-' + date + '(' + week[today.getDay()] + ')' );

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