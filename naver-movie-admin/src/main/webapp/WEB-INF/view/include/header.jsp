<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>

<div class="header bg-black">
	<ul class="nav">
		<li class="nav-item active">회원관리</li>
		<li class="nav-item">영화관리</li>
		<li class="nav-item"><a href="${context}/mbr/list">시스템관리</a></li>
	</ul>
	<div class="inline profile">
		이름 (logout)
	</div>
</div>