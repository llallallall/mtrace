/* ========================================================================
		공통 변수 
   ======================================================================== */
var reg = /[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gim;
/* ========================================================================
		초기 로딩
   ======================================================================== */
$(document).ready(function(){

		searchInfoForSchedule(1);
		searchAccountListForSchedule(1);

});


/* ========================================================================
		리스트 로딩 함수 
   ======================================================================== */	


// 농장 출하물량
function searchInfoForSchedule(page){
	

	// 거래처 정보 조회
	$.ajax({
		type : 'GET',
		url : '/callEggInfoForSchedule',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			// 데이터 로딩
			var info = data.infoList;
			
			const farmIdNo = info.farmIdNo;
			const farmUniqNo = info.farmUniqNo;
			
			const packingBusinessNo = info.packingBusinessNo;
			const packingLicenseNo = info.packingLicenseNo;
			
			const tradeBusinessNo = info.tradeBusinessNo;
			const tradeLicenseNo = info.tradeLicenseNo;
			
			const eggUsage = info.eggUsage;
			const storageMethod = info.storageMethod;
			const washingMethod = info.washingMethod;
			
			const tradeTerm = info.tradeTerm;
			
			const eggXxl = info.eggXxl;
			const eggXl = info.eggXl;
			const eggL = info.eggL;
			const eggM = info.eggM;
			const eggS = info.eggS;
			const eggE = info.eggE;
			const totalEgg = info.totalEgg;
			
			$("#farmIdNo").val(farmIdNo);
			$("#farmUniqNo").val(farmUniqNo);
			$("#packingBusinessNo").val(packingBusinessNo);
			$("#packingLicenseNo").val(packingLicenseNo);
			$("#tradeBusinessNo").val(tradeBusinessNo);
			$("#tradeLicenseNo").val(tradeLicenseNo);
//			
//			$("#").val();
//			$("#").val();
//			$("#").val();
//			$("#").val();
//			$("#").val();

			$("#tradeTerm").val(tradeTerm).prop("selected", true); //값이 1인 option 선택
			
			$("#farmEggXxl").val(eggXxl);
			$("#farmEggXl").val(eggXl);
			$("#farmEggL").val(eggL);
			$("#farmEggM").val(eggM);
			$("#farmEggS").val(eggS);
			$("#farmEggE").val(eggE);

			$("#farmTotalEgg").val(eggXxl+eggXl+eggL+eggM+eggS+eggE);
			
			
							
			//$("#accountListForSchedule").html(html);
			
			//sumTotalTrade();
			
		} 
	});
	


}
	
	
// 거래처별 물량 로딩 	
function searchAccountListForSchedule(page){
	

	if(page==null || page==''){
		page=1;
	}

	// 거래처 정보 조회
	$.ajax({
		type : 'GET',
		url : '/callAccountListForSchedule',
		dataType : 'json',
		contentType : "application/json",
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
				html+='<td class="text-center">'+ list.tradeTerm +'</td>';
				html+='<td class="" style="text-align: right;"> <input id="eggXxlDealt'+i+'" type="text" class="w-100 p-0 m-0 py-2 text-end bg-tranparent" name="eggXxlDealtStr" value="'+numberWithCommas(list.eggXxl)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggXxl" id="eggXxl'+i+'" value="'+list.eggXxl+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggXlDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggXlDealtStr" value="'+numberWithCommas(list.eggXl)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggXl" id="eggXl'+i+'" value="'+list.eggXl+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggLDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggLDealtStr" value="'+numberWithCommas(list.eggL)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggL" id="eggL'+i+'" value="'+list.eggL+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggMDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggMDealtStr" value="'+numberWithCommas(list.eggM)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggM" id="eggM'+i+'" value="'+list.eggM+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggSDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggSDealtStr" value="'+numberWithCommas(list.eggS)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggS" id="eggS'+i+'" value="'+list.eggS+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggEDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggEDealtStr" value="'+numberWithCommas(list.eggE)+'"  placeholder="0" onKeyup="sumAccountRow(\''+i+'\');inputNumberFormat(this);setEggVal(this)"></td>';
				html+='		<input type="hidden" name="eggE" id="eggE'+i+'" value="'+list.eggE+'" >';
				html+='<td class="" style="text-align: right;"> <input id="eggTotalDealt'+i+'" type="text" class="w-100 p-0 m-0 text-end bg-tranparent" name="eggTotalDealtStr" value="'+numberWithCommas(list.totalEgg)+'"  value="0" disabled></td>';				
				html+='		<input type="hidden" name="totalEgg" id="totalEgg'+i+'" value="'+list.totalEgg+'" >';
				html+='</tr>';

			} 
							
			$("#accountListForSchedule").html(html);
			
			sumTotalTrade();
			
		} 
	});
	


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
	let nodes = document.querySelector("#accountListForSchedule").querySelectorAll("input[name='accountBusinessNo']")
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
