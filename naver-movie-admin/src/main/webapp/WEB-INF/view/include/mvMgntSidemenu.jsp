<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<div class="side-menu inline bg-black">
	<ul class="menu-list">
		<li class="menu-item active-item">
			<a href="${context}/gnr/list">장르 관리</a>
		</li>
		<li class="menu-item">제작지 관리</li>
		<li class="menu-item">제작사 관리</li>
		<li class="menu-item">
			<a href="${context}/mvppl/list">영화인 관리</a>
		</li>
		<li class="menu-item">
			<a href="${context}/mv/list">영화 관리</a>
		</li>
		<li class="menu-item">사진 관리</li>
		<li class="menu-item">동영상 관리</li>
		<li class="menu-item">댓글 관리</li>
		<li class="menu-item">명대사 관리</li>
		<li class="menu-item">평점 관리</li>
	</ul>
	
</div>