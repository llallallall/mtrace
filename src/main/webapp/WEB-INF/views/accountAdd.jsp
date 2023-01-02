<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>해오름농장 - 축산물이력제 신고</title>
	
	<!--CSS -->
    <link rel="stylesheet" href="/assets/bundle/bootstrap-5.1.3-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/bundle/fontawesome/css/fontawesome.css">
    <link rel="stylesheet" href="/assets/bundle/fontawesome/css/solid.css">


    <link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/custom.css"><c:param name="dt" value="${nowDate}"/></c:url>"/>

</head>
<body>

	

	<div class="container-fluid p-0">
	
		
		
		<!-- navigation -->
		<jsp:include page="./include/sidebar.jsp"></jsp:include>
		<!-- end of navigation -->
		
		<!-- content -->
		<div class="contents">

			<div class="row">
			
				<div class="col mb-3 pt-5 justify-content-center text-center">
					<i class="fa-solid fa-user-plus " style="font-size:100px;opacity:0.7"></i>
				</div>
			</div>	
			
			<div class="row justify-content-center">
				<div class="col-11 ">
					<hr class="mx-4">
				</div>
			</div>
			
			<form id="accountForm">
				<div class="row main-content p-4">
				
										
					<!-- Flexbox container for aligning the toasts -->
					<div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100" >
					  <div class="toast bg-danger rounded-pill" role="alert" aria-live="assertive" aria-atomic="true" data-bs-autohide="true" id="validToast">
					    <div class="toast-body text-white text-center fs-4" id="toastBody"> </div>
					  </div>
					</div>
										
				
					<div class="col-6 px-5">
						<div class="row">
							<div class="col">
								<label for="" class="form-label  my-3">거래처명</label>
			              		<input type="text" class="form-control border-danger" id="accountNm" name="accountNm" placeholder="거래처 이름" required >
							</div>
						</div>
						
						
						<div class="row">
							<div class="col">
								<label for="" class="form-label  my-3">거래처유형</label>
			              		<div class="form-control border-danger" id="">
			              		
					              		<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType1" value="06">
										  <label class="form-check-label" for="accountType1">판매업체(식육판매)</label>
										</div>
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType2" value="15">
										  <label class="form-check-label" for="accountType2">식용란 수집판매업자</label>
										</div>
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType3" value="16">
										  <label class="form-check-label" for="accountType3">식용란 선별포장업자</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType4" value="18">
										  <label class="form-check-label" for="accountType4">축산물 가공업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType5" value="19">
										  <label class="form-check-label" for="accountType5">축산물 보관업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType6" value="20">
										  <label class="form-check-label" for="accountType6">축산물 운반업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType7" value="21">
										  <label class="form-check-label" for="accountType7">축산물유통전문판매업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType8" value="23">
										  <label class="form-check-label" for="accountType8">알가공업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType9" value="24">
										  <label class="form-check-label" for="accountType9">위탁급식영업</label>
										</div>
										
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="accountType" id="accountType10" value="25">
										  <label class="form-check-label" for="accountType10">제과점영업</label>
										</div>
										
										

								</div>
								
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-6">
								<label for="" class="form-label  my-3">사업자등록번호</label>
			              		<input type="number" class="form-control  border-danger" id="businessNo" name="businessNo" placeholder="숫자 10자리" onkeyup="chkword(this, 10)" required >
							</div>
							<div class="col-6">
								<label for="" class="form-label  my-3">인허가번호</label>
			              		<input type="number" class="form-control  border-danger" id="licenseNo" name="licenseNo" placeholder="숫자 11자리" onkeyup="chkword(this, 11)" required >
							</div>
						</div>
						

						<div class="row">
							<div class="col-6">
								<label for="" class="form-label  my-3">대표자명</label>
			              		<input type="text" class="form-control  border-danger" id="ceoNm" name="ceoNm" value="" required >
							</div>
							<div class="col-6">
								<label for="" class="form-label  my-3">핸드폰번호</label>
			              		<input type="number" class="form-control  " id="mobile" name="mobile" placeholder="숫자만" value=""  >
							</div>
						</div>

					
					</div>
				
					<div class="col-6 px-5">
						
						<div class="row">
			              	<div class="col">
			              		<label for="" class="form-label  my-3 ">주소 유형</label>
			              		<div class="form-control " id="">
			              		
					              		<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="addrType" id="addrType1" value="069001">
										  <label class="form-check-label" for="addrType1">우편동주소</label>
										</div>
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="addrType" id="addrType2" value="069002">
										  <label class="form-check-label" for="addrType2">법정동주소</label>
										</div>
										<div class="form-check form-check-inline w-25">
										  <input class="form-check-input" type="radio" name="addrType" id="addrType3" value="069003">
										  <label class="form-check-label" for="addrType3">도로명주소</label>
										</div>

								</div>
			              	</div>
						</div>
						
						
						<div class="row">
							<div class="col-12">
								<label for="" class="form-label  my-3">주소</label>
				              	<input type="text" class="form-control  border-danger" id="address" name="address" value="" required >
							</div>
							<div class="col-12">
								<label for="" class="form-label  my-3">상세주소</label>
				             	<input type="text" class="form-control  border-danger" id="addrDetail" name="addrDetail" value="" required >
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-6">
								<label for="" class="form-label  my-3">우편번호</label>
			              		<input type="number" class="form-control  border-danger" id="postCd" name="postCd" placeholder="숫자 5자리" onkeyup="chkword(this, 5)" required >
							</div>
							<div class="col-6">
								<label for="" class="form-label  my-3">행정구역코드</label>
			              		<input type="number" class="form-control  " id="addrCd" name="addrCd" placeholder="숫자 10자리" onkeyup="chkword(this, 10)"  >
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-6">
								<label for="" class="form-label  my-3">전화번호</label>
			              		<input type="number" class="form-control  " id="telephone" name="telephone" placeholder="숫자만"  >
							</div>
							<div class="col-6">
								<label for="" class="form-label  my-3">팩스번호</label>
			              		<input type="number" class="form-control  " id="fax" name="fax"  placeholder="숫자만"  >
							</div>
						
						</div>
						
						

					</div>
					
					<div class="row">
							<div class="col-12 ps-5 pe-4">
								<label for="" class="form-label  my-3">비고</label>
			              		<input type="text" class="form-control  " id="remark" name="remark" value=""  >
							</div>
					</div>
					
					<div class="row justify-content-center">
						<div class="col-11 my-5">
							<hr class="my-4">
						</div>
					</div>
					
					<div class="row justify-content-center">
						<div class="col-8">
							<button class="w-100 btn btn-primary btn-lg py-3" style="margin:0 auto;" type="button" onclick="submitAdd()">등록하기</button>
						</div>
					</div>
					
			 
				</div>
				
				
			</form>
		
		</div>
		<!-- end of content -->
		
		
		
		<!-- footer -->
		<jsp:include page="./include/footer.jsp"></jsp:include>
		<!-- end of footer -->
	</div>

</body>
	<!-- javascript -->
	<script src="/assets/bundle/jquery-3.6.0/jquery-3.6.0.min.js"></script>
	<script src="/assets/bundle/popper.2.11.6/js/popper.js"></script>
	<script src="/assets/bundle/tippy.6.3.7/js/tippy.js"></script>
	
	<script src="/assets/bundle/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
	


	<!-- full calendar -->
	<script src='/assets/bundle/fullcalendar-6.0.1/dist/index.global.js'></script>
	<script src='/assets/bundle/fullcalendar-6.0.1/packages/bootstrap5/index.global.js'></script>
	
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/page/account.js"></script>
	
</html>