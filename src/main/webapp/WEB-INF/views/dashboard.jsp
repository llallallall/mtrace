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
<body style="height:100%;padding-bottom:100px;">
	<div class="row p-0 m-0 d-flex justify-content-center align-items-center" >
		<div class="container-fluid d-flex justify-content-center align-items-center">
			<!-- navigation -->
			<jsp:include page="./include/sidebar.jsp"></jsp:include>
			<!-- end of navigation -->
			<input type="hidden" id="reportDate" class="" > <br>	
           	<div id='calendar' class="dashboard-calendar " style="font-size:18px;" ></div>
		            
	
			<!-- footer -->
			<jsp:include page="./include/footer.jsp"></jsp:include>
			<!-- end of footer -->
		</div>
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
	<script src="/assets/js/page/dashboard.js"></script>
	
</html>