<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath}"></c:set>
<c:set var="randomValue" value="<%= new Random().nextInt() %>"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장르 관리</title>
<jsp:include page="../include/stylescript.jsp"></jsp:include>
<script type="text/javascript">
	$().ready(function() {

	console.log($("#isModify").val());
	
	$(".grid > table > tbody > tr").not(".grid > table > tbody > tr > td:first-child").click(function() {
		
		var data = $(this).data();
		console.log(data);
		$("#isModify").val("true"); // 수정모드

		$("#gnrId").val(data.gnrid);
		$("#gnrNm").val(data.gnrnm);
		$("#crtr").val(data.crtr);
		$("#crtDt").val(data.crtdt);
		$("#mdfyr").val(data.mdfyr);
		$("#mdfyDt").val(data.mdfydt);
		
		$("#useYn").prop("checked", data.useyn == "Y");
	})
	
 	$("#gnrNm").keyup(function() {
		
		var that = this;
		var value = $(that).val();
		value = $.trim(value);
		
		if (value == "") {
			$(that).css("backgroundColor","#FFF");
			return;
		}
		else {
			$.get("${context}/api/gnr/dup/" + value, function(response) {
				if (response.status == "200 OK") {
					$(that).css("backgroundColor","#000");
				}
				else if (response.status == "500") {
					$(that).css("backgroundColor","#F00");
				}
			})
			
		}
		
	}) 
	
	
	$("#btn-new").click(function() {
		$("#gnrNm").css("backgroundColor","#FFF");
		$("#isModify").val("false"); // 등록모드
		$("#gnrId").val("");
		$("#gnrNm").val("");
		$("#crtr").val("");
		$("#crtDt").val("");
		$("#mdfyr").val("");
		$("#mdfyDt").val("");
		
		$("#useYn").prop("checked", false);
	})
	
	$("#btn-delete").click(function() {
		var gnrId = $("#gnrId").val();
		if (gnrId == "") {
			alert("선택된 장르가 없습니다.")
			return;
		}
		if (!confirm("정말 삭제하시겠습니까?")) {
			return;
		}
		$.get("${context}/api/gnr/delete/" + gnrId, function(response) {
			location.reload(); //새로고침
		})
	})
	
	$("#btn-save").click(function() {
		
		if ($("#isModify").val() == "false") {
			
			 // 신규등록할때만 중복체크
			var bgColor = $("#gnrNm").css("backgroundColor");
			console.log(bgColor)
			if(bgColor == "rbg(255,0,0)") {
				alert("bgColor이미 사용중인 ID입니다");
				return;
			} 
			
			$.post("${context}/api/gnr/create", {gnrNm:$("#gnrNm").val()
												, useYn:$("#useYn:checked").val()}
												, function(response) {
				if (response.status == "200 OK") {
					location.reload(); //새로고침
				}
				else {
					alert(response.errorCode + " / " + response.message);
				}
			});
		}
		else { //수정
			$.post("${context}/api/gnr/update", {gnrId:$("#gnrId").val()
												, gnrNm:$("#gnrNm").val()
												, useYn:$("#useYn:checked").val()}
												, function(response) {
				if (response.status == "200 OK") {
					location.reload(); //새로고침
				}
				else {
					alert(response.errorCode + " / " + response.message);
				}
			});
		}
		
	});
	
	
	});
</script>
</head>
<body>

	<div class="main-layout">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<div>
			<jsp:include page="../include/mvMgntSidemenu.jsp"></jsp:include>
			<jsp:include page="../include/content.jsp"></jsp:include>
				
				<div class="path">영화 > 장르 관리</div>
				
				<div class="grid">
					
					<div class="grid-count align-right">
						총 ${gnrList.size()}건
					</div>
					<table>
						<thead>
							<tr>
								<th><input type="checkbox" id="all-check"/></th>
								<th>순번</th>
								<th>장르ID</th>
								<th>장르명</th>
								<th>사용여부</th>
								<th>등록자</th>
								<th>등록일</th>
								<th>수정자</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty gnrList}">
									<c:forEach items="${gnrList}"
												var="gnr"
												varStatus="index">
										<tr data-gnrid="${gnr.gnrId}" 
											data-gnrnm="${gnr.gnrNm}" 
											data-useyn="${gnr.useYn}" 
											data-crtr="${gnr.crtr}" 
											data-crtdt="${gnr.crtDt}" 
											data-mdfyr="${gnr.mdfyr}" 
											data-mdfydt="${gnr.mdfyDt}" >
											<td>
												<input type="checkbox" class="check_idx" value="${gnr.gnrId}" />
											</td>
											<td>${index.index}</td>											
											<td>${gnr.gnrId}</td>											
											<td>${gnr.gnrNm}</td>											
											<td>${gnr.useYn}</td>											
											<td>${gnr.crtr}</td>											
											<td>${gnr.crtDt}</td>											
											<td>${gnr.mdfyr}</td>											
											<td>${gnr.mdfyDt}</td>											
										</tr>
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="9" class="no-items">
											등록된 장르가 없습니다.
										</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				
				<div class="grid-detail">
					<form id="form-detail">
						<!-- 
						isModify == true -> 수정(update)
						isModify == false -> 등록(insert)
						 -->
						<input type="hidden" id="isModify" value="false" />
						<div class="input-group inline">
							<label for="gnrId" style="width:150px;">장르 ID</label><input type="text" id="gnrId" name="gnrId" readonly value=""/>
						</div>
						<div class="input-group inline">
							<label for="gnrNm" style="width:150px;">장르명</label><input type="text" id="gnrNm"  name="gnrNm" value=""/>
						</div>
						<div class="input-group inline">
							<label for="useYn" style="width:150px;">사용여부</label><input type="checkbox" id="useYn" name="useYn" value="Y"/>
						</div>
						<div class="input-group inline">
							<label for="crtr" style="width:150px;">등록자</label><input type="text" id="crtr" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="crtDt" style="width:150px;">등록일</label><input type="text" id="crtDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="mdfyr" style="width:150px;">수정자</label><input type="text" id="mdfyr" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="mdfyDt" style="width:150px;">수정일</label><input type="text" id="mdfyDt" disabled value=""/>
						</div>
						
						
					</form>
					
				</div>
				<div class="align-right grid-btns">
					<button id="btn-new" class="btn-primary">신규</button>
					<button id="btn-save" class="btn-primary">저장</button>
					<button id="btn-delete" class="btn-primary btn-delete">삭제</button>
				</div>
			<jsp:include page="../include/footer.jsp"></jsp:include>
		</div>
	
	</div>
	
	
	
</body>
</html>