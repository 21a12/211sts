<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화인 검색</title>
<jsp:include page="../include/stylescript.jsp"></jsp:include>
<script type="text/javascript">

var targetId;

$().ready(function() {
	
	targetId = targetId || "${targetId}";
	console.log(targetId);
	$("#targetId").val(targetId || "${targetId}");
	
	
	$("#all-check").change(function(){
		$(".check-idx").prop("checked",$(this).prop("checked"));
	})
	$(".check-idx").change(function(){
		var count = $(".check-idx").length;
		var checkCount = $(".check-idx:checked").length;
		console.log(count +"/"+ checkCount)
		$("#all-check").prop("checked", count == checkCount);
	})
	
	
	$("#btn-cancel").click(function(){
		window.close();
		
	})
	
	$("#btn-regist").click(function() {
		
		var checkbox = $(".check-idx:checked");
		if (checkbox.length == 0) {
			alert("영화인을 선택하세요");
			return;
		}
		
		checkbox.each(function() {
			var each = $(this).closest("tr").data();
			each.id = targetId;
			opener.addPplFn(each);
			console.log(each);
		})
		
	})
	
	
	
});
</script>
</head>
<body>

	<div class="search-popup content">
		<h1>영화인 검색</h1>
		
		<form>
			<div class="search-group">
				<label for="">영화인 이름</label>
				<input type="text" name="nm" class="grow-1 mr-10" value="${nm}">
				<input type="hidden" name="targetId" id="targetId">
				<button class="btn-search" id="btn-search">검색</button>
			</div>
		</form>
		
		<div class="grid">
			<div class="grid-count align-right">
						총 ${mvPplList.size() > 0 ? mvPplList.size() : 0}건
			</div>
			<table>
				<thead>
					<tr>
						<th>
							<input type="checkbox" id="all-check">
						</th>
						<th>영화인명</th>
						<th>등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty mvPplList}">
							<c:forEach items="${mvPplList}" var="mvPpl">
								<tr data-mvpplid="${mvPpl.mvPplId}"
								    data-nm="${mvPpl.nm}">
									<td class="align-center">
										<input type="checkbox" class="check-idx" value="${mvPpl.mvPplId}">
									</td>
									<td>${mvPpl.nm}</td>
									<td>${mvPpl.crtDt}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
									<tr>
										<td colspan="3" class="no-items">
											검색된 영화인이 없습니다.
										</td>
									</tr>
								</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		
		<div class="align-right grid-btns">
			<button id="btn-regist" class="btn-primary">등록</button>
			<button id="btn-cancel" class="btn-primary btn-delete">취소</button>
		</div>
		
		
	</div>
	
	
	
</body>
</html>