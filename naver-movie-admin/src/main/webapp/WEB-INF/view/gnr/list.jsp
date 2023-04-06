<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Random"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	var originNm = ""
	
	
	$(".grid > table > tbody > tr").click(function() {
		/*
		var cnt = $(".check-idx:checked").length;
		console.log("체크박스 checked 개수 : " + cnt);
		if (cnt) {
			 $(".check-idx:checked").each(function() {
				console.log("체크박스.each ~ 할당된 value : " + $(this).val() + $(this).data());
			}) 
			var gnrIdList = new Array();
				var list = $(".check-idx:checked");
				for (var i=0 ; i<cnt ; i++) {
					gnrIdList.push(list[i].value);
				}
				console.log(gnrIdList);
		}
		*/
		
		var data = $(this).data();
		console.log(data);
		$("#isModify").val("true"); // 수정모드

		$("#gnrId").val(data.gnrid);
		$("#gnrNm").val(data.gnrnm);
		originNm = data.gnrnm; // 수정 시 이름 중복 체크에 활용
		$("#crtr").val(data.crtr);
		$("#crtDt").val(data.crtdt);
		$("#mdfyr").val(data.mdfyr);
		$("#mdfyDt").val(data.mdfydt);
		
		$("#useYn").prop("checked", data.useyn == "Y");
	})
	
	
	
 	/* $("#gnrNm").keyup(function() {
		
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
					$(that).css("backgroundColor","#FFF");
				}
				else if (response.status == "500") {
					$(that).css("backgroundColor","#F00");
				}
			})
		}
	})  */
	
	
	
	
	
	$("#btn-new").click(function() {
		originNm = ""; // 수정 시 이름 중복 체크에 활용
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
	
	
	
	/* 
	$("#all-check").click(function(){
		var checked = $("#all-check").is(":checked");
		$(".check-idx").prop("checked",checked);
	});
	*/
	$("#all-check").change(function(){
		$(".check-idx").prop("checked",$(this).prop("checked"));
	})
	$(".check-idx").change(function(){
		var count = $(".check-idx").length;
		var checkCount = $(".check-idx:checked").length;
		console.log(count +"/"+ checkCount)
		$("#all-check").prop("checked", count == checkCount);
	})
	
	
	$("#btn-delete-all").click(function() {
		var checkLen = $(".check-idx:checked").length;
		if (checkLen == 0) {
			alert("삭제할 장르가 없습니다.");
			return;
		}
		if (!confirm("체크한 항목이 일괄 삭제됩니다.\n정말 삭제하시겠습니까?")) {
			return;
		}
		
		var form = $("<form></form>")
		
		$(".check-idx:checked").each(function() {
			console.log($(this).val());
			form.append("<input type='hiedden' name='gnrId' value='" + $(this).val() + "'>");
		});
		
		$.post("${context}/api/gnr/delete", form.serialize(), function(response) {
			if (response.status == "200 OK") {
				location.reload(); //새로고침
			}
			else {
				alert(response.errorCode + " / " + response.message);
			}
		});
	})
	
	
	$("#btn-delete").click(function() {
		/* 
		var cnt = $(".check-idx:checked").length;
		console.log(cnt);
		var gnrIdList = new Array();
		var list = $(".check-idx:checked");
		for (var i=0 ; i<cnt ; i++) {
			gnrIdList.push(list[i].value);
		}
		console.log(gnrIdList);
		
		if (cnt) {
			if (confirm("체크한 항목이 일괄 삭제됩니다.\n정말 삭제하시겠습니까?")) {
				
				$(".check-idx:checked").each(function() {
					$.get("${context}/api/gnr/delete/" + $(this).val());
				})
				setTimeout(() => location.reload(), 100);
				return;
				
			} else {
				
			}
			console.log("일괄삭제 알림이 지나감")
		} else {
			console.log("일괄삭제 실행안됨")
		}
		 */
		var gnrId = $("#gnrId").val();
		if (gnrId == "") {
			alert("선택된 장르가 없습니다.")
			return;
		}
		if (!confirm("장르ID [ " + gnrId + " ] 정말 삭제하시겠습니까?")) {
			return;
		}
		$.get("${context}/api/gnr/delete/" + gnrId, function(response) {
			location.reload(); //새로고침
		})
	})
	
	
	
	
	
	$("#btn-save").click(function() {
		
		var gnrNm = $("#gnrNm").val();
		console.log(originNm +" / "+ gnrNm)
		
		if (originNm != gnrNm) {
			
		}
		/* 
		function dup(oroginNm, gnrNm) {
			$.get("${context}/api/gnr/dup/" + gnrNm, function(response) {
				if (response.status == "200 OK") {
					console.log("중복체크 통과")
					return true;
				}
				else if (response.status == "500") {
					console.log("중복체크 걸림")
					return false;
				}
			}) 
			
		}
		 */
		
		 
		if ($("#isModify").val() == "false") { //신규
			//console.log(dup(oroginNm, gnrNm))
			/* if (dup(oroginNm, gnrNm=='false')) {
				
				if(!confirm("중복임 계쏙진행?")) {
					console.log("취소하기 됐나??")
					return;
				}
				
			} */
			
			$.post("${context}/api/gnr/create", {gnrNm:$("#gnrNm").val()
												, useYn:$("#useYn:checked").val()}
												, function(response) {
				if (response.status == "200 OK") {
					console.log("신규 성공됨")
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
					console.log("수정 성공됨")
					location.reload(); //새로고침
				}
				else {
					alert(response.errorCode + " / " + response.message);
				}
			});
		}
		
	});
	
	
	$("#btn-search").click(function() {
		movePage(0)
	});
	
});

function movePage(pageNo) {
	var gnrNm = $("#search-keyword").val(); // 입력값.
	location.href = "${context}/gnr/list?gnrNm=" + gnrNm + "&pageNo=" + pageNo; // URL 요청
} 

</script>
</head>
<body>

	<div class="main-layout">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<div>
			<jsp:include page="../include/mvMgntSidemenu.jsp"></jsp:include>
			<jsp:include page="../include/content.jsp"></jsp:include>
				
				<div class="path">영화 > 장르 관리</div>
				<div class="search-group">
					<label for="search-keyword">장르명</label>
					<input type="text" id="search-keyword" class="search-input" value="${gnrVO.gnrNm}">
					<button class="btn-search" id="btn-search">검색</button>
				</div>
				
				
				<div class="grid">
					
					<div class="grid-count align-right">
						총 ${gnrList.size() > 0 ? gnrList.get(0).totalCount : 0}건
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
											<td class="align-center">
												<input type="checkbox" class="check-idx" value="${gnr.gnrId}" />
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
					
					<div class="align-right mt-10">
						<button id="btn-delete-all" class="btn-primary btn-delete">삭제</button>
					</div>
					
					<div class="pagenate">
						<ul>
							<c:set value="${gnrList.size() > 0 ? gnrList.get(0).lastPage : 0}" var="lastPage"></c:set>
							<c:set value="${gnrList.size() > 0 ? gnrList.get(0).lastGroup : 0}" var="lastGroup"></c:set>
							
							<fmt:parseNumber var="nowGroup" value="${Math.floor(gnrVO.pageNo / 10)}" integerOnly="true" />
							<c:set value="${nowGroup * 10}" var="groupStartPageNo"></c:set>
							<c:set value="${groupStartPageNo + 10}" var="groupEndPageNo"></c:set>
							<c:set value="${groupEndPageNo > lastPage ? lastPage : groupEndPageNo - 1}" var="groupEndPageNo"></c:set>
							
							<c:set value="${(nowGroup - 1) * 10}" var="prevGroupStartPageNo"></c:set>
							<c:set value="${(nowGroup + 1) * 10}" var="nextGroupStartPageNo"></c:set>
							
							 
							<c:if test="${nowGroup > 0}">
								<li><a href="javascript:movePage(0)">처음</a></li>
								<li><a href="javascript:movePage(${prevGroupStartPageNo})">이전</a></li>
							</c:if>
							
							<c:forEach begin="${groupStartPageNo}" end="${groupEndPageNo}" step="1"	var="pageNo">
								<li><a class="${pageNo eq gnrVO.pageNo ? 'on' : ''}"  href="javascript:movePage(${pageNo})">${pageNo+1}</a></li>
							</c:forEach>
							
							<c:if test="${lastGroup > nowGroup}">
								<li><a href="javascript:movePage(${nextGroupStartPageNo})">다음</a></li>
								<li><a href="javascript:movePage(${lastPage})">끝</a></li>
							</c:if>
						</ul>
					</div>
					<div>
						lastPage : ${lastPage}
						lastGroup : ${lastGroup}
						nowGroup : ${nowGroup}
						groupStartPageNo : ${groupStartPageNo}
						groupEndPageNo : ${groupEndPageNo}
						prevGroupStartPageNo : ${prevGroupStartPageNo}
						nextGroupStartPageNo : ${nextGroupStartPageNo}
					</div>
					
				</div>
				
				<div class="grid-detail">
					<form id="form-detail">
						<!-- 
						isModify == true -> 수정(update)
						isModify == false -> 등록(insert)
						 -->
						<input type="hidden" id="isModify" value="false" />
						<div class="input-group inline">
							<label for="gnrId" style="width:150px;">장르 ID</label><input type="text" id="gnrId" class="readonly" name="gnrId" readonly value=""/>
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