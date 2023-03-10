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
		<form id="eggPackngForm" >
		<div class="row main-content p-4 g-5">
				 
			      
			      <div class="col-md-12 col-lg-12">
				        
				        
				          <div class="row g-3">
				          		<!-- 좌측 -->
				          		<div class="col-sm-6">
				          		
				          			<div class="row reportDate" >
				          				<div class="col-6">
				          					<label for="reportDate" class="form-label">이력번호 발급일자</label>
							              	<input type="text" class="form-control text-center" id="reportDate" >
				          				</div>
				          				
				          				<div class="col-6">
				          					<label for="eggHistNo" class="form-label">이력번호</label>
							              	<input type="text" class="form-control text-center" id="eggHistNoHidden" name="eggHistNo">
				          				</div> 
						            </div>
						            <div class="row" >
						            	<div id='calendar'></div>
<!-- 						            	<input type="hidden" id="spawningDateHidden" name="spawningDate"> -->
						            	<input type="hidden" id="reportDateHidden" name="reportDate">
						            	<input type="hidden" id="requestDateHidden" name="requestDate">  <!-- 오늘 날짜 -->
<!-- 						            	<input type="hidden" id="clientBusinessNoHidden" name="clientBusinessNo" value="8609200580">	의뢰인 사업자 번호 : 해오름농장 -->
<!-- 						            	<input type="hidden" id="clientLicenseNoHidden" name="clientLicenseNo" value="00000202239"> 의뢰인 허가번호 : 해오름농장 -->
						            	
						            </div>
										
				          		
				          		</div>
				          		
				          		<!-- 우측 -->
				          		<div class="col-sm-2">
				          			<div class="row ">
						          		<div class="col-sm-12 eggSize">
							              <label for="size1" class="form-label">왕란 - 입고</label>
							              <input type="text" class="form-control text-end " id="size1" placeholder="0" value="" disabled >
							              <input type="hidden" id="size1Hidden" name="eggXxl">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size2" class="form-label">특란 - 입고</label>
							              <input type="text" class="form-control text-end " id="size2" placeholder="0" value="" disabled>
							              <input type="hidden" id="size2Hidden" name="eggXl">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size3" class="form-label">대란 - 입고</label>
							              <input type="text" class="form-control text-end " id="size3" placeholder="0" value="" disabled >
							              <input type="hidden" id="size3Hidden" name="eggL">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size4" class="form-label">중란 - 입고</label>
							              <input type="text" class="form-control text-end " id="size4" placeholder="0" value="" disabled >
							              <input type="hidden" id="size4Hidden" name="eggM">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size5" class="form-label">소란 - 입고</label>
							              <input type="text" class="form-control text-end " id="size5" placeholder="0" value="" disabled>
							              <input type="hidden" id="size5Hidden" name="eggS">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size6" class="form-label">기타 - 입고</label>
							              <input type="text" class="form-control text-end " id="size6" placeholder="0" value="" disabled>
							              <input type="hidden" id="size6Hidden" name="eggE">
							            </div>
				          			</div>
				          		</div>
					            <input type="hidden" id="totalEggHidden" name="totalEgg">
					            
					            <div class="col-sm-2">
				          			<div class="row ">
						          		<div class="col-sm-12 eggSize">
							              <label for="size1" class="form-label">왕란 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size1Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size1','dealt');inputNumberFormat(this)"  >
							              <input type="hidden" id="size1DealtHidden" name="eggXxlDealt">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size2" class="form-label">특란 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size2Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size2','dealt');inputNumberFormat(this)"  >
							              <input type="hidden" id="size2DealtHidden" name="eggXlDealt">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size3" class="form-label">대란 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size3Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size3','dealt');inputNumberFormat(this);" >
							              <input type="hidden" id="size3DealtHidden" name="eggLDealt">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size4" class="form-label">중란 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size4Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size4','dealt');inputNumberFormat(this)"  >
							              <input type="hidden" id="size4DealtHidden" name="eggMDealt">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size5" class="form-label">소란 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size5Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size5','dealt');inputNumberFormat(this)"  >
							              <input type="hidden" id="size5DealtHidden" name="eggSDealt">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size6" class="form-label">기타 - 처리</label>
							              <input type="text" class="form-control text-end border-success" id="size6Dealt" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size6','dealt');inputNumberFormat(this)"  >
							              <input type="hidden" id="size6DealtHidden" name="eggEDealt">
							            </div>
				          			</div>
				          		</div>
					            <input type="hidden" id="totalDealtHidden" name="totalDealt">
					            
					            <!-- 우측 폐기 -->
					            <div class="col-sm-2">
				          			<div class="row ">
						          		<div class="col-sm-12 eggSize">
							              <label for="size1Dispose" class="form-label">왕란 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size1Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size1','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size1DisposeHidden" name="eggXxlDispose">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size2Dispose" class="form-label">특란 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size2Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size2','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size2DisposeHidden" name="eggXlDispose">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size3Dispose" class="form-label">대란 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size3Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size3','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size3DisposeHidden" name="eggLDispose">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size4Dispose" class="form-label">중란 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size4Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size4','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size4DisposeHidden" name="eggMDispose">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size5Dispose" class="form-label">소란 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size5Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size5','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size5DisposeHidden" name="eggSDispose">
							            </div>
							            
							            <div class="col-sm-12 eggSize">
							              <label for="size6Dispose" class="form-label">기타 - 폐기</label>
							              <input type="text" class="form-control text-end border-warning" id="size6Dispose" placeholder="0" value="" onClick="chkHistNo()" onKeyDown="chkHistNo()" onKeyup="validSum('size6','dispose');inputNumberFormat(this)"  >
							              <input type="hidden" id="size6DisposeHidden" name="eggEDispose">
							            </div>
				          			</div>
				          		</div>
								<input type="hidden" id="totalDisposeHidden" name="totalDispose">
					            
				            
				           <hr class="my-4">
				
				          <button class="w-100 btn btn-primary btn-lg" type="button" onclick="submitEggPackng()">신고하기</button>
				          
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
			                                        <th rowspan="2">#</th>
			                                        <th rowspan="2">전송시간</th>
			                                        
			                                        <th rowspan="2">응답코드</th>
			                                        <th rowspan="2">응답메시지</th>
			                                        
			                                        <th rowspan="2">이력번호</th>
			                                        
			                                        <th rowspan="2">발급일자</th>
			                                        
			                                        <th rowspan="2">의뢰일자</th>
			                                        
			                                       
			                                        
<!-- 			                                        <th rowspan="2">사업자등록번호</th> -->
<!-- 			                                        <th rowspan="2">인허가번호</th> -->
<!-- 			                                        <th rowspan="2">농장식별번호</th> -->
			                                        
<!-- 			                                        <th rowspan="2">계란의용도</th> -->
			                                        
<!-- 			                                        <th rowspan="2">산란일자</th> -->
			                                        
<!-- 			                                        <th rowspan="2">보관방법</th> -->
<!-- 			                                        <th rowspan="2">세척방법</th> -->
			                                        
			                                        <th colspan="6" class="text-center">처리실적</th>
<!-- 			                                        <th>특란</th> -->
<!-- 			                                        <th>대란</th> -->
<!-- 			                                        <th>중란</th> -->
<!-- 			                                        <th>소란</th> -->
<!-- 			                                        <th>기타</th> -->
			                                        
			                                        <th colspan="6" class="text-center">폐기내역</th>
<!-- 			                                        <th>특란</th> -->
<!-- 			                                        <th>대란</th> -->
<!-- 			                                        <th>중란</th> -->
<!-- 			                                        <th>소란</th> -->
<!-- 			                                        <th>기타</th> -->

			                                    </tr>
			                                    <tr>
<!-- 			                                        <th colspan="2">#</th> -->
<!-- 			                                        <th colspan="2">전송시간</th> -->
			                                        
<!-- 			                                        <th colspan="2">응답코드</th> -->
<!-- 			                                        <th colspan="2">응답메시지</th> -->
			                                        
<!-- 			                                        <th colspan="2">이력번호</th> -->
			                                        
<!-- 			                                        <th colspan="2">사업자등록번호</th> -->
<!-- 			                                        <th colspan="2">인허가번호</th> -->
<!-- 			                                        <th colspan="2">농장식별번호</th> -->
			                                        
<!-- 			                                        <th colspan="2">계란의용도</th> -->
			                                        
<!-- 			                                        <th colspan="2">산란일자</th> -->
			                                        
<!-- 			                                        <th colspan="2">보관방법</th> -->
<!-- 			                                        <th colspan="2">세척방법</th> -->
			                                        
			                                        <th>왕란</th>
			                                        <th>특란</th>
			                                        <th>대란</th>
			                                        <th>중란</th>
			                                        <th>소란</th>
			                                        <th>기타</th>
			                                        
			                                        <th>왕란</th>
			                                        <th>특란</th>
			                                        <th>대란</th>
			                                        <th>중란</th>
			                                        <th>소란</th>
			                                        <th>기타</th>

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
	<script src="/assets/js/page/eggPacking.js"></script>
	
</html>