<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin login</title>
<link rel="stylesheet" href="${context}/css/common.css">
<script type="text/javascript" src="${context}/js/jquery-3.6.4.min.js" ></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("#login_btn").click(function() {
			var data = {
					mbrId: $("#mbrId").val(),
					pwd: $("#pwd").val()
			};
			$.post("${context}/api/mbr/lgn", data, function(response) {
				// Response Spec
				/* {
					status: "200 OK",
					message: "",
					errorCode: "",
					redirecrURL: ""
				} */
				
				console.log(response)
				if (response.status == "200 OK") {
					location.href = "${context}" + response.redirectURL;
				}
				else if (response.status == "400") {
					// 파라미터 전달을 하지 않은 경우 
					alert("파라미터없깅 " + response.errorCode + " / " +response.message);
				}
				else {
					alert(response.errorCode + " / " + response.message);
					console.log(response)
					if (response.redirectURL != null) {
						//location.href = "${context}" + response.redirectURL; 
					}
				}
			});
		});
		
	})
</script>
</head>
<body>

	<div class="fullscreen item-align-center bg-gray">
	
		<div class="login-group">
			<h1>Ktds Movie System</h1>
			<div class="input-group">
				<label for="mbrId">ID</label>
				<input type="text" id="mbrId" placeholder="ID" name="mbrId" />		
			</div>
			<div class="input-group">
				<label for="pwd">PWD</label>
				<input type="password" id="pwd"  placeholder="Password" name="pwd" />		
			</div>
			<div class="align-right">
				<button class="btn_primary" id="login_btn">로그인</button>
			</div>
		</div>
	
	</div>

</body>
</html>