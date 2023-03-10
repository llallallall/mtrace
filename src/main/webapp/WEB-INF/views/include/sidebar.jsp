<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Toast -->
	
<div id="toast-container" class="fixed-top d-flex justify-content-end mt-5 p-3" style="z-index:100000000">

	<div id="toastNotice" class="toast" role="alert" aria-live="assertive" aria-atomic="true" >
	  <div class="toast-header" style="display:none">
	    <strong class="me-auto" id="toastNoticeTitle"></strong>
	    <small id="toastNoticeInfo"></small>
	    <button type="button" class="ms-2 mb-1 close" data-dismiss="toast" >
	      <span >&times;</span>
	    </button>
	  </div>
	  <div class="toast-body text-white text-center" id="toastNoticeContent" onclick="$(this).hide()"></div>
	</div>
</div>
			    
<!-- navbar --> 
<nav class="navbar navbar-expand-md navbar-light" >
	<button class="navbar-toggler ms-auto mb-2 bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#sidebar" aria-controls="sidebar" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="sidebar">
		<div class="container-fluid">
			<div class="row">
			
				<!-- sidebar -->
				<div class="col-xxl-2 col-xl-3 col-lg-4 col-md-4 sidebar fixed-top">
					<a href="" class="navbar-brand text-white text-center d-block mx-auto py-3 mb-4 bottom-border">축산물이력제 신고현황</a>
					
					<!-- 사용자 -->
					<div class="bottom-border pb-3">
						<img src="/assets/images/users/user1.png" alt="" width="50" class="rounded-circle me-3 ms-3"/>
						<a href="#" class="text-white">해오름농장</a>
					</div>
					
					<!-- 메뉴 -->
					<ul class="navbar-nav flex-column mt-4">

						<li class="nav-item">
							<a href="/dashboard" class="nav-link text-white p-2 mb-1" data-value="이력제신고 내역">
								<i class="fa-solid fa-calendar-days text-white fa-lg me-3"></i>이력제신고 내역
							</a>
							
						</li>
						
						<li class="nav-item">
							<a href="#" class="nav-link text-white p-3 mb-1">
								<i class="fa-solid fa-boxes-packing text-white fa-lg me-3"></i>식용란 선별포장업
							</a>
							<ul class="navbar-nav flex-column mt-0 ms-3">
								<li class="nav-item">
									<a href="/historyNumber" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="이력번호 등록">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>이력번호 등록
									</a>
								</li>
								
								<li class="nav-item">
									<a href="/packagingPerformance" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="선별포장실적 신고">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>선별포장실적 신고
									</a>
								</li>
								
								<li class="nav-item">
									<a href="/breedingStatus" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="사육현황 신고">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>사육현황 신고
									</a>
								</li>
							</ul>
						</li>
						
						<li class="nav-item">
							<a href="#" class="nav-link text-white p-3 mb-1 mt-4" >
								<i class="fa-sharp fa-solid fa-truck text-white fa-lg me-3"></i>식용란 수집판매업
							</a>
							<ul class="navbar-nav flex-column mt-0 ms-3">
								<li class="nav-item">
									<a href="/shipmentReport" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="출고신고">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>출고신고
									</a>
								</li>
							</ul>
						</li>
						
						<li class="nav-item">
							<a href="#" class="nav-link text-white p-3 mb-1 mt-4" >
								<i class="fa-solid fa-user-tie text-white fa-lg me-3"></i>거래처 
							</a>
							<ul class="navbar-nav flex-column mt-0 ms-3">
								<li class="nav-item">
									<a href="/accountAdd" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="거래처 추가">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>거래처 추가
									</a>
								</li>
								<li class="nav-item">
									<a href="/accountMgmt" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="거래처 관리">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>거래처 관리
									</a>
								</li>
							</ul>
						</li>
						
						
						<li class="nav-item">
							<a href="#" class="nav-link text-white p-3 mb-1 mt-4" >
								<i class="fa-solid fa-user-tie text-white fa-lg me-3"></i>신고 예약 
							</a>
							<ul class="navbar-nav flex-column mt-0 ms-3">
								<li class="nav-item">
									<a href="/scheduleInfo" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="예약 정보">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>예약 정보
									</a>
								</li>
								<li class="nav-item">
									<a href="/scheduleMgmt" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="예약 관리">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>예약 관리
									</a>
								</li>
								<li class="nav-item">
									<a href="/scheduleHist" class="nav-link text-white p-1 mb-1 sidebar-link" data-value="처리 내역">
										<i class="fa-solid fa-angle-right text-white fa-sm me-3"></i>처리 내역
									</a>
								</li>
								
							</ul>
						</li>
						
	
					
					</ul>
				</div>
				<!-- end of sidebar -->
				
				<!-- top navbar -->
				<div class="col-xxl-10 col-xl-9 col-lg-8 col-md-8 ms-auto fixed-top">
					<div class="row align-items-center top-navbar bg-dark">
						<div class="col-md-4">
							<h4 class="menu-title text-light text-uppercase mb-0 ms-3"></h4>
						</div>
						<div class="col-md-5">
							<form action=""> 
								<div class="input-group">
									<input type="text" class="form-control search-input" placeholder="Search...">
									<button type="button" class="btn btn-light search-button">
										<i class="fa-solid fa-magnifying-glass"></i>
									</button>
								</div>
							</form>
						</div>
						<div class="col-md-3">
							<ul class="navbar-nav justify-content-end">
								<li class="nav-info-date"><a href="#" ><i class="fa-solid fa-calendar-days fa-sm text-white"></i><span class="today-text" id="todayDate"></span></a></li>
								<li class="nav-info-time"><a href="#" ><i class="fa-solid fa-clock fa-sm text-white"></i><span class="today-text" id="todayTime"></span></a></li>
								<li class="nav-info-logout"><a href="#" ><i class="fa-solid fa-right-from-bracket fa-lg text-danger"></i></a></li>
							</ul>
						</div>
					</div>
					
					
