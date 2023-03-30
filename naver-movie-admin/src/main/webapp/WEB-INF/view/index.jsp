<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<c:set var="randomValue" value="<%= new Random().nextInt() %>"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INDEX PAGE</title>
<jsp:include page="./include/stylescript.jsp"></jsp:include>
</head>
<body>

	<div class="main-layout">
		<jsp:include page="./include/header.jsp"></jsp:include>
		<div>
			<jsp:include page="./include/sidemenu.jsp"></jsp:include>
			<jsp:include page="./include/content.jsp"></jsp:include>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				안녕하세요!<br/>
				${context}
				안녕하세요!<br/>
			<jsp:include page="./include/footer.jsp"></jsp:include>
		</div>
	
	</div>
	
	
	
</body>
</html>