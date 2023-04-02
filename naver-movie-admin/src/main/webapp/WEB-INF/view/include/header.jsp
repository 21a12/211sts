<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<style type="text/css">
#login_info div {
	display: inline-flex;
	margin-left: 5px;
}
#login_info * {
	color: white;
	font-size:14pt;
}
#login_info  {

}

</style>
<div class="header bg-black">
	<ul class="nav">
		<li class="nav-item active">회원관리</li>
		<li class="nav-item"><a href="${context}/gnr/list">영화관리</a></li>
		<li class="nav-item"><a href="${context}/mbr/list">시스템관리</a></li>
	</ul>
	<div id="login_info" class="inline profile">
		<div>
			<span id="userName">${sessionScope.__ADMIN__.mbrNm}(${sessionScope.__ADMIN__.mbrId})</span>님 반갑습니다! 
		</div>
		<div id="logout">
			( <a href="${context}/mbr/logout">logout</a> )
		</div>
	</div>
</div>