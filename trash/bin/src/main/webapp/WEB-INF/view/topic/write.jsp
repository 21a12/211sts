<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Topic</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script type="text/javascript">
	$().ready(function() {
		
	})
</script>
</head>
<body>
	
	<form action="/trash/topic/write" method="post">
		
		<div>
			<label for="subject">제목</label>
			<input type="text"
				   name="subject"
				   id="subject" 
				   placeholder="제목입력" />
		</div>
		<div>
			<label for="email">이메일</label>
			<input type="email"
				   name="email"
				   id="email" 
				   placeholder="email@sample.com" />
		</div>
		<div>
			<label for="content">내용</label>
			<textarea name="content"
					  id="content" 
					  placeholder="내용입력" ></textarea>
		</div>
		<div>
			<input id="submit" type="submit" value="등록" />
		</div>
		
	</form>
	
	<script>
		$("#submit").click(function(e){
			if($.trim($("#subject").val())==''){
				alert("제목을 입력해주세요.");
				e.preventDefault();
				return ;
			}
			if($.trim($("#email").val())==''){
				alert("이메일을 입력해주세요.");
				e.preventDefault();
				return ;
			}
			if($.trim($("#content").val())==''){
				alert("내용을 입력해주세요.");
				e.preventDefault();
				return ;
			}
		});
	</script>
	
</body>
</html>