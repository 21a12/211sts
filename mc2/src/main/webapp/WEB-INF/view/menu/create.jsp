<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Create</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuCreate.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#do_btn").click(function(event) {
			alert("1")
			
			
		});
	});
</script>
</head>
<body>
	
	<div id="top">생성페이지입</div>
	
	<div>
		<label for="menuName">이름</label>
		<input id="menuName"
			   name="menuName"
			   type="text"
			   maxlength="30"
			   placeholder="메뉴 이름 작성" >
	</div>
	<div>
		<label for="type1">타입1</label>
		<input id="type1"
			   name="type1"
			   type="text"
			   placeholder="TYPE1" >
	</div>
	<div>
		<label for="type2">타입2</label>
		<input id="type2"
			   name="type2"
			   type="text"
			   placeholder="TYPE2" >
	</div>
	<div>
		<label for="price">가격</label>
		<input id="price"
			   type="number"
			   name="price"
			   maxlength="10"
			   placeholder="예) 7000" > 원
	</div>
	
	<div>
		<button id="do_btn">등록</button>
	</div>
	
	
</body>
</html>