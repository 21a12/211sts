<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${topic.id}</title>
</head>
<body>

	<h1>${topic.subject}</h1>
	<div>
		${topic.memberVO.name} (${topic.memberVO.email})
	</div>
	<div>
		작성일 : ${topic.crtDt}
		/ 수정일 : ${topic.mdfyDt}
	</div>
	<div>
		첨부파일 : ${topic.originFileName}
	</div>
	<div>
		${topic.content}
	</div>
	<hr/>
	<ul></ul>
	<a href="/trash/topic">목록</a>
	<a href="/trash/topic/update/${topic.id}">수정</a>
	<a href="/trash/topic/delete/${topic.id}">삭제</a>
	
</body>
</html>