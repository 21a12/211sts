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
<title>INDEX PAGE</title>
<jsp:include page="../include/stylescript.jsp"></jsp:include>
<script type="text/javascript">
	$().ready(function() {
		console.log($("#isModify").val());
		
		$(".grid > table > tbody > tr").click(function() {
			var data = $(this).data();
			console.log(data);
			$("#isModify").val("true"); // 수정모드
			$("#mbrId").attr("readonly",true); //입력제한
			$("#mbrId").click(function() {
				alert("ID는 수정 불가임")
			})
			$("#mbrId").val(data.mbrid);
			$("#mbrNm").val(data.mbrnm);
			$("#crtDt").val(data.crtdt);
			$("#lstLgnDt").val(data.lstlgndt);
			$("#lstLgnIp").val(data.lstlgnip);
			$("#lgnTryCnt").val(data.lgntrycnt);
			$("#lstLgnFailDt").val(data.lstlgnfaildt);
			$("#lstPwdChngDt").val(data.lstpwdchngdt);
			
			$("#useYn").prop("checked", data.useyn == "Y");
			$("#lgnBlockYn").prop("checked", data.lgnblockyn == "Y");
			$("#admYn").prop("checked", data.admyn == "Y");
		})
		
		$("#btn-new").click(function() {
			
			$("#isModify").val("false"); // 등록모드
			$("#mbrId").removeAttr("readonly"); //입력제한해제
			$("#mbrId").val("");
			$("#mbrNm").val("");
			$("#crtDt").val("");
			// $("#useYn").val(data.useyn);
			$("#lstLgnDt").val("");
			$("#lstLgnIp").val("");
			$("#lgnTryCnt").val(0);
			// $("#lgnBlockYn").val(data.lgnblockyn);
			$("#lstLgnFailDt").val("");
			$("#lstPwdChngDt").val("");
			// $("#admYn").val(data.admyn);
			
			$("#useYn").prop("checked", false);
			$("#lgnBlockYn").prop("checked", false);
			$("#admYn").prop("checked", false);
		})
		
		$("#btn-delete").click(function() {
			var mbrId = $("#mbrId").val();
			if (mbrId == "") {
				alert("선택된 관리자가 없습니다.")
				return;
			}
			if (!confirm("정말 삭제하시겠습니까?")) {
				return;
			}
			$.get("${context}/api/mbr/delete/" + mbrId, function(response) {
				location.reload(); //새로고침
			})
		})
		
		$("#btn-save").click(function() {
			
			if ($("#lgnTryCnt").val() =="") {
				$("#lgnTryCnt").val(0)
			}
			
			if ($("#isModify").val() == "false") {
				$.post("${context}/api/mbr/create", $("#form-detail").serialize(), function(response) {
					if (response.status == "200 OK") {
						location.reload(); //새로고침
					}
					else {
						alert(response.errorCode + " / " + response.message);
					}
				});
			}
			else { //수정
				$.post("${context}/api/mbr/update", $("#form-detail").serialize(), function(response) {
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
			<jsp:include page="../include/sysMgntSidemenu.jsp"></jsp:include>
			<jsp:include page="../include/content.jsp"></jsp:include>
				
				<div class="path">시스템관리 > 관리자관리 > 관리자목록</div>
				
				<div class="grid">
					
					<div class="grid-count align-right">
						총 ${mbrList.size()}건
					</div>
					<table>
						<thead>
							<tr>
								<th>순번</th>
								<th>ID</th>
								<th>이름</th>
								<th>가입일</th>
								<th>사용여부</th>
								<th>최근 로그인 날짜</th>
								<th>최근 로그인 IP</th>
								<th>로그인 제한 여부</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty mbrList}">
									<c:forEach items="${mbrList}"
												var="mbr"
												varStatus="index">
										<tr data-mbrId="${mbr.mbrId}" 
											data-mbrNm="${mbr.mbrNm}" 
											data-crtDt="${mbr.crtDt}" 
											data-useYn="${mbr.useYn}" 
											data-lstLgnDt="${mbr.lstLgnDt}" 
											data-lstLgnIp="${mbr.lstLgnIp}" 
											data-lgnTryCnt="${mbr.lgnTryCnt}" 
											data-lgnBlockYn="${mbr.lgnBlockYn}" 
											data-lgnFailDt="${mbr.lstLgnFailDt}" 
											data-lstPwdChngDt="${mbr.lstPwdChngDt}" 
											data-admYn="${mbr.admYn}">
											<td>${index.index}</td>											
											<td>${mbr.mbrId}</td>											
											<td>${mbr.mbrNm}</td>											
											<td>${mbr.crtDt}</td>											
											<td>${mbr.useYn}</td>											
											<td>${mbr.lstLgnDt}</td>											
											<td>${mbr.lstLgnIp}</td>											
											<td>${mbr.lgnBlockYn}</td>											
										</tr>
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="8" class="no-items">
											등록된 관리자가 없습니다.
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
							<label for="mbrId" style="width:150px;">ID</label><input type="text" id="mbrId" name="mbrId" value=""/>
						</div>
						<div class="input-group inline">
							<label for="mbrNm" style="width:150px;">이름</label><input type="text" id="mbrNm"  name="mbrNm" value=""/>
						</div>
						<div class="input-group inline">
							<label for="crtDt" style="width:150px;">등록일</label><input type="text" id="crtDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="useYn" style="width:150px;">사용여부</label><input type="checkbox" id="useYn" name="useYn" value="Y"/>
						</div>
						<div class="input-group inline">
							<label for="lstLgnDt" style="width:150px;">마지막 로그인 날짜</label><input type="text" id="lstLgnDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="lstLgnIp" style="width:150px;">마지막 로그인 IP</label><input type="text" id="lstLgnIp" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="lgnTryCnt" style="width:150px;">로그인 실패 횟수</label><input type="text" id="lgnTryCnt" name="lgnTryCnt" value="0"/>
						</div>
						<div class="input-group inline">
							<label for="lgnBlockYn" style="width:150px;">접속 제한 여부</label><input type="checkbox" id="lgnBlockYn"  name="lgnBlockYn" value="Y"/>
						</div>
						<div class="input-group inline">
							<label for="lstLgnFailDt" style="width:150px;">로그인 실패날짜</label><input type="text" id="lstLgnFailDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="lstPwdChngDt" style="width:150px;">비밀번호 변경날짜</label><input type="text" id="lstPwdChngDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="admYn" style="width:150px;">관리자 여부</label><input type="checkbox" id="admYn" name="admYn" value="Y"/>
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