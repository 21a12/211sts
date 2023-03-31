<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topic Home</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		alert("1");
		$("title").on('mouseenter','li', (function(event) {
			$(".contents").slideDown(500);
		}));
		$("title").on('mouseleave','li', (function(event) {
			$(".contents").slideUp(500);
		}));
	})
</script>
</head>
<style type="text/css">
	body, ul, li {
	margin:0;
	padding: 0;
	font-size: 12px;
	}
	ul {
		list-style-type: none;
	}
	li {
		margin: 3px 0;			
		padding: 3px;
		display: inline-block;
		border: 1px solid #000;
		text-align: center;
	}
	.bold {
		font-weight: bold;
	}
	.id {
		width: 30px;
	}
	.title {
		width: 150px;
	}
	.email {
		width: 100px;
	}
	.memberName {
		width: 50px;
	}
	.crtDt {
		width: 50px;
	}
	.id, .title:hover, .memberName:hover {
		opacity: 50%;
		animation: transbg 0.2s linear infinite;
	}
	@keyframes transbg {
	    0% {
	        background-color: cyan;
	    }
	    33% {
	        background-color: magenta;
	    }
	    66% {
	        background-color: yellow;
	    }
	    100% {
	        background-color: cyan;
	    }
	}
	.content {
		margin-left: 70px;
		display: none;'
	}
</style>
<body>

	<h1>Topic Home</h1>
	<hr>
	
	<c:choose>
		<c:when test="${not empty topicList}">
			<ul>
				<li class="id bold">번호</li>
				<li class="title bold">제목</li>
				<li class="memberName bold">작성자</li>
				<li class="crtDt bold">작성일</li>
			</ul>
		
			<c:forEach items="${topicList}" var="topic">

				<ul>
					<li class="id">${topic.id}</li>
					<li class="title">${topic.subject}</li>
					<li class="memberName">${topic.memberVO.name}</li>
					<li class="crtDt">
						<script type="text/javascript">
							var date = "${topic.crtDt}"
							var crtDt = date.substring(5,10);
							document.write(crtDt);
						</script>
					</li>
					<%-- <li class="mdfyDt">${topic.mdfyDt}</li>
					<li class="fileName">${topic.fileName} fileName</li>
					<li class="originFile">${topic.originFileName}</li> --%>
				</ul>	
				<div class="content">${topic.content}</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
			TOPIC EMPTY
		</c:otherwise>
	</c:choose>
	<hr>
	<div>
		<a href="/trash/topic/write">토픽작성</a>
		<a href="/trash/member/regist">회원가입</a>
	</div>

</body>
</html>