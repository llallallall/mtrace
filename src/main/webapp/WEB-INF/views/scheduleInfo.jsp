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
					<i class="fa-solid fa-user-clock" style="font-size:100px;opacity:0.7"></i>
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
								<label for="" class="form-label  my-3">전송 주기</label>
			              		<div class="form-control " id="">
			              		
			              		
			              			<ul class="nav nav-tabs" id="myTab" role="tablist">
										  <li class="nav-item" role="presentation">
										    <button class="nav-link active" id="daily-tab" data-bs-toggle="tab" data-bs-target="#daily" type="button" role="tab" aria-controls="daily" aria-selected="true">일별</button>
										  </li>
										  <li class="nav-item" role="presentation">
										    <button class="nav-link" id="weekly-tab" data-bs-toggle="tab" data-bs-target="#weekly" type="button" role="tab" aria-controls="weekly" aria-selected="false">주별</button>
										  </li>
										  <li class="nav-item" role="presentation">
										    <button class="nav-link" id="monthly-tab" data-bs-toggle="tab" data-bs-target="#monthly" type="button" role="tab" aria-controls="monthly" aria-selected="false">월별</button>
										  </li>
									</ul>
									<div class="tab-content py-3" id="myTabContent">
									  <div class="tab-pane fade show active" id="daily" role="tabpanel" aria-labelledby="daily-tab">
									  
									  		<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType1" value="06">
											  <label class="form-check-label" for="accountType1">7시</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType2" value="15">
											  <label class="form-check-label" for="accountType2">12시</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">17시</label>
											</div>
									  
									  </div>
									  <div class="tab-pane fade" id="weekly" role="tabpanel" aria-labelledby="weekly-tab">
									  
									  		<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType1" value="06">
											  <label class="form-check-label" for="accountType1">월요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType2" value="15">
											  <label class="form-check-label" for="accountType2">화요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">수요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">목요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">금요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">토요일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">일요일</label>
											</div>
											
											
									  
									  
									  </div>
									  <div class="tab-pane fade" id="monthly" role="tabpanel" aria-labelledby="monthly-tab">
									  		<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType1" value="06">
											  <label class="form-check-label" for="accountType1">1일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType2" value="15">
											  <label class="form-check-label" for="accountType2">11일</label>
											</div>
											<div class="form-check form-check-inline w-25">
											  <input class="form-check-input" type="checkbox" name="accountType" id="accountType3" value="16">
											  <label class="form-check-label" for="accountType3">21일</label>
											</div>
									  
									  
									  </div>
									</div>
			              		
					              		
										
										
										

								</div>
								
							</div>
						</div>
						
						<div class="row">
							<div class="col-4">
								<label for="" class="form-label bold my-3">농장 식별번호</label>
			              		<input type="text" class="form-control  " id="farmIdNo" name="farmIdNo" onkeyup="chkword(this, 6)"  >
							</div>
							
							<div class="col-4">
								<label for="" class="form-label bold my-3">농장 고유번호</label>
			              		<input type="text" class="form-control  " id="farmUniqNo" name="farmUniqNo" onkeyup="chkword(this, 5)"  >
							</div>
							
							<div class="col-4">
								<label for="tradeTerm" class="form-label bold my-3">농장출하 주기</label>
								<select class="form-select" id="tradeTerm" aria-label="tradeTermSelect">
								  <option value="D">일별</option>
								  <option value="W">주별</option>
								  <option value="M">월별</option>
								</select>
							</div>
						</div>
						
						
						<div class="row">
							<div class="col-12">
								<label for="" class="form-label  mt-3">선별포장 수량(농장출하 + 폐기)</label>
								<div class="row">
				              		<div class="col-2">
										<label for="" class="form-label  mb-3">왕란</label>
					              		<input type="number" id="farmEggXxl" class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);" required >
									</div>
									<div class="col-2">
										<label for="" class="form-label  mb-3">특란</label>
					              		<input type="number" id="farmEggXl" class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);"  required >
									</div>
									<div class="col-2">
										<label for="" class="form-label  mb-3">대란</label>
					              		<input type="number" id="farmEggL" class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);"  required >
									</div>
									<div class="col-2">
										<label for="" class="form-label  mb-3">중란</label>
					              		<input type="number" id="farmEggM"  class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);"  required >
									</div>
									<div class="col-2">
										<label for="" class="form-label  mb-3">소란</label>
					              		<input type="number" id="farmEggS"  class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);"  required >
									</div>
									<div class="col-2">
										<label for="" class="form-label  mb-3">기타</label>
					              		<input type="number" id="farmEggE"   class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);" required >
									</div>
								</div>
							</div>
							
						</div>
						
						<div class="row">
							<div class="col-4">
								<label for="" class="form-label bold my-3">농장출하 총수량</label>
			              		<input type="number" id="farmTotalEgg" class="form-control w-100 p-0 m-0 py-2 text-end "  value="" onKeyup="inputNumberFormat(this);"  disabled >
							</div>
							
							<div class="col-4">
								<label for="" class="form-label bold my-3">선별포장 폐기수량</label>
			              		<input type="number" id="farmTotalDispose" class="form-control w-100 p-0 m-0 py-2 text-end "  value="0" onKeyup="inputNumberFormat(this);"  required >
							</div>
							
							
							
							<div class="col-4">
								<label for="" class="form-label bold my-3">입출고 총수량</label>
			              		<input type="number" id="totlaDealt" class="form-control w-100 p-0 m-0 py-2 text-end " onKeyup="inputNumberFormat(this);" disabled >
							</div>
						</div>
						
						<div class="row">
							<div class="col-6">
								<label for="" class="form-label bold my-3">선별포업 사업자등록번호</label>
			              		<input type="text" class="form-control  " id="packingBusinessNo"  onkeyup="chkword(this, 11)" disabled >
							</div>
							
							<div class="col-6">
								<label for="" class="form-label bold my-3">선별포장업 인허가번호</label>
			              		<input type="text" class="form-control  " id="packingLicenseNo"  onkeyup="chkword(this, 11)" required >
							</div>
						</div>
						
						<div class="row">
							<div class="col-6">
								<label for="" class="form-label bold my-3">수집판매업 사업자등록번호</label>
			              		<input type="text" class="form-control  " id="tradeBusinessNo"   onkeyup="chkword(this, 11)" disabled >
							</div>
							
							<div class="col-6">
								<label for="" class="form-label bold my-3">수집판매업 인허가번호</label>
			              		<input type="text" class="form-control  " id="tradeLicenseNo" onkeyup="chkword(this, 11)" required >
							</div>
						</div>
						
						
					
					</div>
				
					<div class="col-6 px-5">
						
						<div class="row">
			              	<div class="col">
			              		<label for="" class="form-label  my-3 ">거래처별 수량</label>
			              		<div class="table-responsive mt-1">
						          			<table class="table table-striped table-hover table-bordered align-middle">
												<thead id="accountListForTradeHeader" class="table-light text-center">
													<tr>
														<th style="width:5%">번호</th>
														<th style="width:25%">거래처명</th>
														<th style="width:15%">사업자번호</th>
														<th style="width:10%">거래주기</th>
														<th style="width:5%">왕란</th>
														<th style="width:5%">특란</th>
														<th style="width:10%">대란</th>
														<th style="width:5%">중란</th>
														<th style="width:5%">소란</th>
														<th style="width:5%">기타</th>
														<th style="width:10%">합계</th>
													</tr>
												</thead>
												<tbody id="accountListForSchedule">
												</tbody>
												<tfoot>
												   <tr>
														<td colspan="4" class="text-center">합계</td>
														
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggXxlDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggXlDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggLDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggMDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggSDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggEDealt" value="0" disabled></td>
														<td ><input type="text" class="w-100 p-0 m-0 text-end bg-tranparent" id="sumEggTotalDealt" value="0" disabled></td>
													</tr>
												</tfoot>
																								
											
											</table>
										</div>
			              	</div>
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
	
	
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/page/schedule.js"></script>
	
</html>