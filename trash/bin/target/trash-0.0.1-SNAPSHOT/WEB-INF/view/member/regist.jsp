<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Member</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
	})
</script>
<style type="text/css">

</style>
</head>
<body>

	<h1>회원가입 합시다</h1>
	<form action="/trash/member/regist" method="post">
	
		<div>
			<label for="email">이메일</label>
			<input type="email" 
				   name="email" 
				   id="email"
				   placeholder="EMAIL을 입력하세요." />
		</div>
		<div>
			<label for="name">이름</label>
			<input type="text" 
				   name="name" 
				   id="name"
				   placeholder="이름을 입력하세요." />
		</div>
		<div>
			<label for="password">비밀번호</label>
			<input type="password" 
				   name="password" 
				   id="password"
				   placeholder="비밀번호를 입력하세요." />
		</div>
		<div>
			<input id="submit" type="submit" value="가입" />
		</div>
	
	</form>
	
	<script>
		$("#submit").click(function(e){
			if($.trim($("#email").val())==''){
				alert("이메일을 입력해주세요.");
				e.preventDefault();
				return ;
			}
			if($.trim($("#name").val())==''){
				alert("이름을 입력해주세요.");
				e.preventDefault();
				return ;
			}
			if($.trim($("#password").val())==''){
				alert("비밀번호를 입력해주세요.");
				e.preventDefault();
				return ;
			}
		});
	</script>
	
	
	
	

</body>
</html>