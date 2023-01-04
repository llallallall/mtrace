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
    <link rel="stylesheet" href="/assets/bundle/jquery.toast/jquery.toast.min.css">

    <link rel="stylesheet" type="text/css" href="<c:url value="assets/css/custom.css"><c:param name="dt" value="${nowDate}"/></c:url>"/>

</head>
<body>
	<div class="container-fluid p-0">
		<!-- navigation -->
		<jsp:include page="./include/sidebar.jsp"></jsp:include>
		<!-- end of navigation -->
		
		
		<div class="contents">
		<!-- content -->
		
		<div class="row main-content p-4 g-5">
				 
			      
			      <div class="col-md-12 col-lg-12">
				        
				        	
				          <div class="row g-3">
				          		<!-- 좌측 -->
				          		<div class="col-sm-6">
				          		
				          			<div class="row reportDate" >
							              <label for="tradeReportDate" class="form-label">출고 신고일자</label>
							              <input type="text" class="form-control text-center" id="tradeReportDate" >
					              			
							                    
						            </div>
						            <div class="row" >
						            	<div id='calendar'></div>
						            </div>
										
				          		
				          		</div>
				          		
				          		<!-- 우측 -->
				          		<div class="col-sm-6">
				          			<div class="row reportDate" >
							              <label class="form-label text-center">선별포장신고 내역</label>
							              <input type="hidden" class="form-control text-center" >
					              	
							                    
						            </div>
						            <form id="eggTradeForm" >
						            
						            	
						            							            	
						            	<input type="hidden" id="tradeReportDateHidden" name="reportDate">
						            	
						            	<input type="hidden" id="requestDateHidden" name="requestDate">
						            	
				          			<div class="row d-flex flex-row ">
				          				<div class="table-responsive ">
						          			<table class="table table-bordered align-middle">
												<thead id="eggPackingHeader" class="table-light text-center">
													<tr>
														<th>이력번호</th>
														<th>신고일자</th>
														<th>산란일자</th>
														
														<th>왕란</th>
														<th>특란</th>
														<th>대란</th>
														<th>중란</th>
														<th>소란</th>
														<th>기타</th>
														<th>합계</th>
													</tr>
												</thead>
												<tbody id="eggPackingList">
													<tr>
														<td class="text-center ts-18 py-2" colspan="10">선별포장신고 내역을 선택하세요</td>
													</tr>
												</tbody>
											</table>
										</div>
				          				
				          			</div>
				          			</form>
				          			<form id="eggTradeDetailForm" >
				          			<div class="row reportDate" >
							              <label class="form-label text-center ">출고신고 내역</label>
							              <input type="hidden" class="form-control text-center" >
					              	
							                    
						            </div>
				          			<div class="row ">
						          		<div class="table-responsive mt-1">
						          			<table class="table table-striped table-hover table-bordered align-middle">
												<thead id="accountListForTradeHeader" class="table-light text-center">
													<tr>
														<th style="width:5%">번호</th>
														<th style="width:25%">거래처명</th>
														<th style="width:10%">사업자번호</th>
														<th style="width:15%">거래일자</th>
														<th style="width:5%">왕란</th>
														<th style="width:5%">특란</th>
														<th style="width:10%">대란</th>
														<th style="width:5%">중란</th>
														<th style="width:5%">소란</th>
														<th style="width:5%">기타</th>
														<th style="width:10%">합계</th>
													</tr>
												</thead>
												<tbody id="accountListForTrade">
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
										
										<nav aria-label="Page navigation" class="py-3">
										    <ul class="pagination justify-content-center" id="accountPageing"></ul>
										</nav> 
				          			</div>
				          			</form>
				          		</div>
					           
					            
					            
				            
				           <hr class="my-4">
				
				          <button id="submitBtn" class="w-100 btn btn-primary btn-lg" type="button" onclick="submitEggTrade()">거래처별 출고 신고하기</button>
				          
				          </div>

			      		</div>
		        	
			    </div>
			    
			    
			    <hr class="my-4">
			
			          <h4 class="mb-3">등록 내역</h4>
						
			          <div class="row">    
			            <div class="col-lg-12">
			                <div class="card">
			                    <div class="card-body">

			                        <div class="table-responsive">
			                            <table class="table table-bordered table-striped mb-0">
			
			                                <thead  >
			                                    <tr>
			                                        <th >#</th>
			                                        <th >전송시간</th>
			                                        
			                                        <th >응답코드</th>
			                                        <th >응답메시지</th>
			                                        
			                                        <th >이력번호</th>
			                                        <th >이력번호 순번</th>
			                                        <th >산란일자</th>
			                                        <th >이력번호 발급일자</th>
			                                        <th >선별포장 신고일자</th>
			           								<th> 출고내역</th>
			                                    </tr>
			                                    
			                                </thead>
			                                <tbody id="egg-packing-body">
			                                    
			                                </tbody>
			                            </table>
			                        </div>
			
			                    </div>
			                </div>
			                
			                
			                <nav aria-label="Page navigation" class="py-3">
							    <ul class="pagination justify-content-center" id="pageing">
							    	
								 </ul>
							</nav> 
							
							
			            </div>
			        </div>
		
		</div>
		<!-- end of content -->
		
		
		
		<!-- footer -->
		<jsp:include page="./include/footer.jsp"></jsp:include>
		<!-- end of footer -->
	</div>

</body>
	<!-- javascript -->
	<script src="/assets/bundle/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>
	<script src="/assets/bundle/jquery-3.6.0/jquery-3.6.0.min.js"></script>
	<script src="/assets/bundle/jquery.toast/jquery.toast.min.js"></script>

	<!-- full calendar -->
	<script src='/assets/bundle/fullcalendar-6.0.2/dist/index.global.js'></script>
	<script src='/assets/bundle/fullcalendar-6.0.2/packages/bootstrap5/index.global.js'></script>
<!-- 	<script src='/assets/bundle/fullcalendar-6.0.2/packages/moment/index.global.js'></script> -->
<!-- 	<script src='/assets/bundle/fullcalendar-6.0.2/packages/moment-timezone/index.global.js'></script> -->
	<script src='/assets/bundle/fullcalendar-6.0.2/packages/web-component/index.global.js'></script>
	
	<script src="/assets/bundle/popper.2.11.6/js/popper.js"></script>
	<script src="/assets/bundle/tippy.6.3.7/js/tippy.js"></script>
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/page/eggTrade.js"></script>
	
</html>