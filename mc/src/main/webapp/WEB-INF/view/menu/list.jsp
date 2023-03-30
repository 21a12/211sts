<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/menuHome.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#create_btn").click(function(){
			location = "${pageContext.request.contextPath}/menu/create";
		})
	})
</script>
</head>
<body>
	
	<div id="top">본문입니다</div>
	
    <table>
        <thead>
            <tr>
                <th>MENU_ID</th>
                <th>MENU_NAME</th>
                <th>PRICE</th>
                <th>TYPE1</th>
                <th>TYPE2</th>
                <th>IMAGE</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        	<c:choose>
       			<c:when test="${not empty menuList}">
       				<c:forEach items="${menuList}" var="menu">
	       				<tr>
	       					<td>${menu.menuId}</td>
	       					<td>${menu.menuName}</td>
	       					<td>${menu.price}</td>
	       					<td>${menu.type1}</td>
	       					<td>${menu.type2}</td>
	       					<td>${menu.image}</td>
	       					<td class="td_btn"><button class="update">수정</button></td>
	       					<td class="td_btn"><button class="delete">삭제</button></td>
	       				</tr>
       				</c:forEach>
       			</c:when>
       			<c:otherwise>
					<tr>
						<td colspan="6">
							목록이 비었음.
						</td>
					</tr>
       			</c:otherwise>
        	</c:choose>
        </tbody>
    </table>
	
	<button id="create_btn">create</button>
	
</body>
</html>