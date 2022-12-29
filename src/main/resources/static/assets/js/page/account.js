/* ========================================================================
		공통 변수 
   ======================================================================== */
var myAlert =document.getElementById('toastNotice');//select id of toast
var bsAlert = new bootstrap.Toast(myAlert);//inizialize it
          
/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

				
});

function submitAdd(){
	

	//input 입력값 검증
	let valList = {"input" : 
					  {"accountNm" : "거래처명을", "businessNo" : "사업자등록번호를", 
					   "licenseNo" : "인허가번호를", "ceoNm":"대표자명을", 
					   "address" : "주소를", "addrDetail" : "상세주소를" , 
					   "postCd" : "우편번호를"},
				   "radio" : 
				   	  {"accountType" : "거래처 유형을"}
				   }
					
	
	
	let inputs = valList["input"];
	let inputIds = Object.keys(inputs)
	
	let radios = valList["radio"];
	let radioIds = Object.keys(radios)
	
	
	// input 값 체크
	for (var i = 0; i < inputIds.length; i++) {	
    	let itemVal = $('#'+inputIds[i]).val();

    	if(itemVal == null || itemVal.length < 1) {
			let id = inputIds[i]
			let nm = inputs[id]
			
			$("#toastBody").html(""+nm+" <br>입력해주세요.");
			
			$("#validToast").show();
 			setTimeout(() => 
 				$("#validToast").fadeOut(2000, function() {
				  	$("#validToast").hide()
				})
 			, 1000);
 			
			$('#'+id).focus();
			
			return false;
		}
	}
	
	// 라디오 체크
	
	for (var i = 0; i < radioIds.length; i++) {	
		let id = radioIds[i]
		let nm = radios[id]
		
		if( !$('input:radio[name='+id+']').is(':checked')) {
			
			$("#toastBody").html(""+nm+" <br>선택해주세요.");
			
			$("#validToast").show();
 			setTimeout(() => 
 				$("#validToast").fadeOut(2000, function() {
				  	$("#validToast").hide()
				})
 			, 1000);
 			
			//$(id)[0].focus();
			document.getElementsByName(id)[0].focus(); 
			
			return false;
		}
	}
//	});

	
	//console.log($("#breedingMethod").val());
	$.ajax({
			 url :"/callRegisterAccount"
			,type:"post"
			,data:$("#accountForm").serialize()
			,dataType:"json"
			,success:function(data){
				
				if(data.resultCode == 'success') {
					setToast("bg-success", "거래처 등록", null, "등록 되었습니다.")
					bsAlert.show();//show it
					
				} else if (data.resultCode == 'duplicate') {
					setToast("bg-warning", "거래처 등록", null, "이미 등록한 내역이 있습니다.")
					bsAlert.show();//show it
					
				} else if  (data.resultCode == 'error') {
					setToast("bg-danger", "거래처 등록", null, "오류가 발생했습니다.")
					bsAlert.show();//show it
				}

//				searchEggPacking(1);
//				calendar.refetchEvents();
				//location.reload();
				
			}
		    ,error: function(response){

		    	alert("에러발생 : " + response.responseText);
		    }
		});

}