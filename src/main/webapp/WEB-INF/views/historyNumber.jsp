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
		<form id="historyNumberForm" >
		<div class="row main-content p-4 g-5">
				 
			      <div class="col-md-5 col-lg-4 order-md-last">
				        <h4 class="d-flex justify-content-between align-items-center mb-3">
				          <span class="text-primary">신고인 정보</span>
				        </h4>
				        <ul class="list-group mb-3">
				          <li class="list-group-item d-flex justify-content-between lh-sm">
				            <div>
				              <h6 class="my-0">법인명</h6>
				              <small class="text-muted">대표자명</small>
				            </div>
				            <span class="text-success text-end">
				            	해오름농장<br>
				            	<small class=" inputSmallText">황인기</small>
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between bg-light">
				            <div>
				              <h6 class="my-0">사업자등록번호</h6>
				              <small class="text-muted">영업허가번호</small>
				            </div>
				            <span class="text-success text-end">
				            	<input type="text" class="border-0 text-end bg-light text-success inputNormalText" name="businessNo" value="8609200580"><br>
				            	<input type="text" class="border-0 text-end bg-light inputSmallText" name="licenseNo" value="00000202239" >
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between lh-sm">
				            <div>
				              <h6 class="my-0">농장식별번호</h6>
				              <small class="text-muted">농장고유번호</small>
				            </div>
				            <span class="text-success text-end">
				            	<input type="text" class="border-0 text-end text-success inputNormalText" name="farmIdNo" value="202239"><br>
				            	<input type="text" class="border-0 text-end inputSmallText" name="farmUniqNo" id="farmUniqNo" value="OY2BC">
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between bg-light">
				            <div>
				              <h6 class="my-0">사육방식</h6>
				              <small class="text-muted">사육방식코드</small>
				            </div>
				            <span class="text-success text-end">
					            축사내 평사
					            <br>
				            	<input type="text" class="border-0 text-end  bg-light inputSmallText" name="breedingMethod" id="breedingMethod" value="2">
				            	</span>
				          </li>
				        </ul>
						
						 <hr class="my-4">
						
						<h4 class="d-flex justify-content-between align-items-center mb-3">
				          <span class="text-primary">식용란 정보</span>
				        </h4>
				        <ul class="list-group mb-3">
				          <li class="list-group-item d-flex justify-content-between lh-sm">
				            <div>
				              <h6 class="my-0">용도</h6>
				              <small class="text-muted">용도코드</small>
				            </div>
				            <span class="text-success text-end">
				            	업소용
				            	<br>
				            	<input type="text" class="border-0 text-end  inputSmallText" name="eggUsage" value="314002" >
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between bg-light">
				            <div>
				              <h6 class="my-0">보관방법</h6>
				              <small class="text-muted">보관방법코드</small>
				            </div>
				            <span class="text-success text-end">
				            	상온
				            	<br>
				            	<input type="text" class="border-0 text-end bg-light  inputSmallText" name="storageMethod" value="315002" >
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between lh-sm">
				            <div>
				              <h6 class="my-0">세척방법</h6>
				              <small class="text-muted">세척방법코드</small>
				            </div>
				            <span class="text-success text-end">
				            	브러쉬
				            	<br>
				            	<input type="text" class="border-0 text-end  inputSmallText" name="washingMethod" value="238002" >
				            </span>
				          </li>
				          <li class="list-group-item d-flex justify-content-between bg-light">
				            <div>
				              <h6 class="my-0">크기</h6>
				              <small class="text-muted">수량</small>
				            </div>
				            <span class="text-success text-end">
				            	대란<br>
				            	<small class=" inputSmallText">2,200</small>
				            </span>
				          </li>
				        </ul>
			        
			      </div>
			      <div class="col-md-7 col-lg-8">
				        
				        
				          <div class="row g-3">
				          		<!-- 좌측 -->
				          		<div class="col-sm-8">
				          		
				          			<div class="row reportDate" >
							              <label for="reportDate" class="form-label">산란일자</label>
							              <input type="text" class="form-control text-center" id="reportDate" >
					              	
							                    
						            </div>
						            <div class="row" >
						            	<div id='calendar'></div>
						            	<input type="hidden" id="reportDateHidden" name="spawningDate">
						            </div>
										
				          		
				          		</div>
				          		
				          		<!-- 우측 -->
				          		<div class="col-sm-4">
				          			<div class="row ">
						          		<div class="col-sm-12 eggSize">
							              <label for="size1" class="form-label">왕란</label>
							              <input type="text" class="form-control text-end" id="size1" placeholder="0" value="0" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size1Hidden" name="eggXxl">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size2" class="form-label">특란</label>
							              <input type="text" class="form-control text-end" id="size2" placeholder="0" value="0" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size2Hidden" name="eggXl">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size3" class="form-label">대란</label>
							              <input type="text" class="form-control text-end" id="size3" placeholder="2,200" value="2,200" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size3Hidden" name="eggL">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size4" class="form-label">중란</label>
							              <input type="text" class="form-control text-end" id="size4" placeholder="0" value="0" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size4Hidden" name="eggM">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size5" class="form-label">소란</label>
							              <input type="text" class="form-control text-end" id="size5" placeholder="0" value="0" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size5Hidden" name="eggS">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size6" class="form-label">기타</label>
							              <input type="text" class="form-control text-end" id="size6" placeholder="0" value="0" onKeyup="inputNumberFormat(this)"  >
							              <input type="hidden" id="size6Hidden" name="eggE">
							            </div>
				          			</div>
				          		</div>
					            
							
					            
				            
				           <hr class="my-4">
				
				          <button class="w-100 btn btn-primary btn-lg" type="button" onclick="submitHistoryNumber()">등록하기</button>
				          
				          </div>

			      		</div>
		        	
			    </div>
			    </form>  
			    
			    <hr class="my-4">
			
			          <h4 class="mb-3">등록 내역</h4>
			
			          <div class="row">    
			            <div class="col-lg-12">
			                <div class="card">
			                    <div class="card-body">

			                        <div class="table-responsive">
			                            <table class="table table-striped mb-0">
			
			                                <thead>
			                                    <tr>
			                                        <th>#</th>
			                                        <th>전송시간</th>
			                                        
			                                        <th>응답코드</th>
			                                        <th>응답메시지</th>
			                                        
			                                        <th>이력번호</th>
			                                        
			                                        <th>사업자등록번호</th>
			                                        <th>인허가번호</th>
			                                        <th>농장식별번호</th>
			                                        
			                                        <th>계란의용도</th>
			                                        
			                                        <th>산란일자</th>
			                                        
			                                        <th>보관방법</th>
			                                        <th>세척방법</th>
			                                        
			                                        <th>왕란</th>
			                                        <th>특란</th>
			                                        <th>대란</th>
			                                        <th>중란</th>
			                                        <th>소란</th>
			                                        <th>기타</th>

			                                    </tr>
			                                </thead>
			                                <tbody id="history-number-body">
			                                    
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
	<script src='/assets/bundle/fullcalendar-6.0.1/dist/index.global.js'></script>
	<script src='/assets/bundle/fullcalendar-6.0.1/packages/bootstrap5/index.global.js'></script>
	<script src="/assets/bundle/popper.2.11.6/js/popper.js"></script>
	<script src="/assets/bundle/tippy.6.3.7/js/tippy.js"></script>
	<script src="/assets/js/custom.js"></script>
	<script src="/assets/js/page/historyNumber.js"></script>
	
</html>