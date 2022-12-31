/* ========================================================================
		공통 변수 
   ======================================================================== */

/* ========================================================================
		초기 로딩
   ======================================================================== */
   
   
$(document).ready(function(){

	searchAccount(1);
				
});


function searchAccount(page) {
	
	if (page == null|| page == undefined) {
		page = 1;
	}
	
	$.ajax({
		
		type : 'GET',
		url : '/callAccountList',
		dataType : 'json',
		contentType : "application/json",
		data : {
            "page" : page
        },
		success : function(data) {
			//console.log(data.accountList);
			let accountList = data.accountList;
			
			let html ="";
			var arryAccountTypeData = new Array();
			
			accountList.forEach( (account, index) => {
				//console.dir(index +' : '+ account);
				//console.log()
				html +='<tr>';
				html +='	<td>';
				html +='		'+nullFormat(account.num)+'	';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="accountNm" class="bg-tranparent" value="'+nullFormat(account.accountNm)+'" onChange="changeBorderColor(this,\'red\')"> ';
				html +='	</td>';
				html +='	<td>';
				html +='		<select data-index="'+index+'" name="accountType" class="select-tranparent" id="accountTypeSelect'+index+'">';
				html +='			<option value="06">판매업체(식육판매)</option>';
				html +='			<option value="15">식용란 수집판매업자</option>';
				html +='			<option value="16">식용란 선별포장업자</option>';
				html +='			<option value="18">축산물 가공업</option>';
				html +='			<option value="19">축산물 보관업</option>';
				html +='			<option value="20">축산물 운반업</option>';
				html +='			<option value="21">축산물유통전문판매업</option>';
				html +='			<option value="23">알가공업</option>';
				html +='			<option value="24">위탁급식영업</option>';
				html +='			<option value="25">제과점영업</option>';
				html +='		</select>';	
				
				var accountTypeData = new Object();
				accountTypeData.id = 'accountTypeSelect'+index;
				accountTypeData.cd = nullFormat(account.accountType);
				accountTypeData.nm = nullFormat(account.accountTypeNm);
				arryAccountTypeData.push(accountTypeData);
				
//				html +='		<input data-index="'+index+'" type="text" name="accountTypeNm" class="bg-tranparent" value="'+nullFormat(account.accountTypeNm)+'"> <input data-index="'+index+'" type="hidden" name="accountType" class="bg-tranparent" value="'+nullFormat(account.accountType)+'">	';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="businessNoNext" class="bg-tranparent" onkeyup="chkword(this, 10)" onChange="changeBorderColor(this,\'red\')" value="'+nullFormat(account.businessNo)+'"> ';
				html +='		<input data-index="'+index+'" type="hidden" name="businessNo" value="'+nullFormat(account.businessNo)+'"> ';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="licenseNoNext" class="bg-tranparent" onkeyup="chkword(this, 11)" onChange="changeBorderColor(this,\'red\')" value="'+nullFormat(account.licenseNo)+'"> ';
				html +='		<input data-index="'+index+'" type="hidden" name="licenseNo" value="'+nullFormat(account.licenseNo)+'"> ';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="ceoNm" class="bg-tranparent" onChange="changeBorderColor(this,\'red\')" value="'+nullFormat(account.ceoNm)+'"> ';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="address" class="bg-tranparent" onChange="changeBorderColor(this,\'red\')" value="'+nullFormat(account.address)+'"> <input data-index="'+index+'" type="hidden" name="addrDetail" class="bg-tranparent" value="'+nullFormat(account.addrDetail)+'"> ';
				html +='	</td>';
				html +='	<td>';
				html +='		<input data-index="'+index+'" type="text" name="telephone" class="bg-tranparent" onChange="changeBorderColor(this,\'red\')" value="'+nullFormat(account.telephone)+'"> ';
				html +='	</td>';
				html +='	<td class="text-center">';
				
				if(account.tradeUse == "Y"){
					html +='		'+'<button type="button" class="btn btn-sm btn-success ts-11" onClick="toggleTradeUse('+index+',\'N\')">사용중</button>'+'	';
				} else {
					html +='		'+'<button type="button" class="btn btn-sm btn-warning ts-11" onClick="toggleTradeUse('+index+',\'Y\')">미사용</button>'+'	';
				}
				
				html +='	</td>';
				html +='	<td class="text-center">';
				html +='		'+'<button type="button" class="btn btn-sm btn-primary ts-11" onClick="updateAddr('+index+')" >수정</button>'+'	';
				html +='	</td>';
				html +='	<td class="text-center">';
				html +='		'+'<button type="button" class="btn btn-sm btn-danger ts-11" onClick="deleteAddr('+index+')">삭제</button>'+'	';
				html +='	</td>';
				html +='</tr>';
				
				
			});
			
			$("#accountList").html(html);

			arryAccountTypeData.forEach( (data) => {
				$("#"+data.id).val(data.cd).prop("selected", true);
			});
			
			
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
				pageHtml+='<a class="page-link" onclick="searchAccount('+(pageInfo.page-1)+')" tabindex="-1" id="preLang">'+preLang+'</a>';
				pageHtml+='</li>';
			}
			for(var i=pageInfo.startPage;i<=pageInfo.endPage;i++){
				if(pageInfo.page==i){
					pageHtml+='<li class="page-item active">';
					pageHtml+='<a class="page-link" onclick="searchAccount('+i+')">'+i+' <span class="sr-only">(current)</span></a>';
					pageHtml+='</li>';
				}else{
					pageHtml+='<li class="page-item">';
					pageHtml+='<a class="page-link" onclick="searchAccount('+i+')">'+i+' </a>';
					pageHtml+='</li>';
				}
			}
			if(pageInfo.page>=pageInfo.maxPage){
				pageHtml+='<li class="page-item disabled">';
				pageHtml+='<a class="page-link" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}else{
				pageHtml+='<li class="page-item">';
				pageHtml+='<a class="page-link" onclick="searchAccount('+(pageInfo.page+1)+')" href="#" id="nextLang">'+nextLang+'</a>';
				pageHtml+='</li>';
			}

			$("#pageing").html(pageHtml);
			
			
			
		}, //success
		 error: function(response){
			alert("에러발생 : " + response.responseText);
	    } //error	

		
	});
};


function deleteAddr(idx) {
	let els = document.querySelectorAll('[data-index="'+idx+'"]');
	let formData = new FormData();
	
	els.forEach( (obj) => {
		//console.dir(obj);
		if ( obj.tagName == "INPUT") {
			formData.append(obj.name, nullFormat(obj.value));
		} else if (obj.tagName == "SELECT") {
			let option = $("#"+obj.id+" option:selected").val();
			formData.append(obj.name, nullFormat(option));
		}
	});
	
	fetch('/callDeleteAccount', {
	    method: 'POST',
	    cache: 'no-cache',
	    body: formData // body 부분에 폼데이터 변수를 할당
	})
	.then((response) => response.json())
	.then((data) => {
	    //console.log(data.resultCode);
	    if(data.resultCode == 'success') {
			setToast("bg-success", "거래처 삭제", null, "성공적으로 삭제 되었습니다.")
			bsAlert.show();//show it
			
		} else if (data.resultCode == 'error') {
			setToast("bg-danger", "거래처 삭제", null, "오류가 발생했습니다.<br>"+data.resultCd+"<br>"+data.resultStr)
			bsAlert.show();//show it
		}
	    searchAccount(1);
	});
	
} 

function updateAddr(idx) {
	let els = document.querySelectorAll('[data-index="'+idx+'"]');
	let formData = new FormData();
	
	els.forEach( (obj) => {
		//console.dir(obj);
		if ( obj.tagName == "INPUT") {
			formData.append(obj.name, nullFormat(obj.value));
		} else if (obj.tagName == "SELECT") {
			let option = $("#"+obj.id+" option:selected").val();
			formData.append(obj.name, nullFormat(option));
		}
	});
	
	fetch('/callUpdateAccount', {
	    method: 'POST',
	    cache: 'no-cache',
	    body: formData // body 부분에 폼데이터 변수를 할당
	})
	.then((response) => response.json())
	.then((data) => {
	    //console.log(data.resultCode);
	    if(data.resultCode == 'success') {
			setToast("bg-success", "거래처 수정", null, "성공적으로 변경 되었습니다.")
			bsAlert.show();//show it
			
		} else if (data.resultCode == 'error') {
			setToast("bg-danger", "거래처 수정", null, "오류가 발생했습니다.<br>"+data.resultCd+"<br>"+data.resultStr)
			bsAlert.show();//show it
		}
	    searchAccount(1);
	});
	
}

function toggleTradeUse(idx,nextVal){
	let els = document.querySelectorAll('[data-index="'+idx+'"]');
	let formData = new FormData();
	
	els.forEach( (obj) => {
		//console.dir(obj);
		if ( obj.tagName == "INPUT") {
			formData.append(obj.name, nullFormat(obj.value));
		} else if (obj.tagName == "SELECT") {
			let option = $("#"+obj.id+" option:selected").val();
			formData.append(obj.name, nullFormat(option));
		}
	});
	formData.append("tradeUse", nextVal);
	
	let entries = formData.entries();
	for (const pair of entries) {
    //console.log(pair[0]+ ', ' + pair[1]); 
	}
	
	fetch('/callUpdateTradeUse', {
	    method: 'POST',
	    cache: 'no-cache',
	    body: formData // body 부분에 폼데이터 변수를 할당
	})
	.then((response) => response.json())
	.then((data) => {
	    //console.log(data.resultCode);
	    if(data.resultCode == 'success') {
			setToast("bg-success", "거래처 출고사용", null, "성공적으로 변경 되었습니다.")
			bsAlert.show();//show it
			
		} else if (data.resultCode == 'error') {
			setToast("bg-danger", "거래처 출고사용", null, "오류가 발생했습니다.<br>"+data.resultCd+"<br>"+data.resultStr)
			bsAlert.show();//show it
		}
	    searchAccount(1);
	});
	
	
}