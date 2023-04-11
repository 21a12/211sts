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
<title>영화 상세</title>
<jsp:include page="../include/stylescript.jsp"></jsp:include>
<script type="text/javascript">

// 장르 검색 팝업
var gnr; 
var ppl;

$().ready(function() {

	// $("#scrnStt").val("${mvVO.scrnStt}");
	$("#grd").val("${mvVO.grd}");
	
	$(".del-gnr-item").click(function(event) {
		event.preventDefault();
		var parent = $(this).closest(".gnr-item");
		parent.css("background-color","#f009");
		
		var index = $(this).data("index");
		var deleted = $("<input type='hidden' name='gnrList["+index+"].deleted' />");
		deleted.val($(this).data("gnrid"));
		parent.append(deleted);
		
		$(this).remove();
		
		
		
	})
	
	
	$(".del-ppl-item").click(function(event) {
		event.preventDefault();
		var parent = $(this).closest(".mvppl-item");
		parent.css("background-color","#f009");
		
		var index = $(this).data("index");
		var deleted = $("<input type='hidden' name='pplList["+index+"].deleted' />");
		deleted.val($(this).data("prdcprtcptnpplid"));
		parent.append(deleted);
		
		// 역할명 편집 불가처리
		parent.find("input[type=text]").attr("disabled", "disabled");
		
		$(this).remove();
		
		
	})
	
	
	$(".rspnsbltRolNm").keyup(function() {
		var orgnNm = $(this).data("orgn-nm");
		var nowNm = $(this).val();
		//console.log(orgnNm, nowNm, orgnNm==nowNm);
		var parent = $(this).closest(".mvppl-item");
		var index = $(this).data("index");
		
		if (orgnNm == nowNm) {
			// TODO input type=hidden name="pplList[index].modified" remove
			parent.find("input.mdfy").remove();
		} else {
			var modifiedDom = parent.find("input.mdfy");
			if (modifiedDom.length == 0) {
				var modified = $("<input type='hidden' class='mdfy' name='pplList["+index+"].modified' />");
				modified.val(parent.find("button").data("prdcprtcptnpplid"));
				parent.append(modified);
			}
				
			// TODO input type=hidden name="pplList[index].modified" vlaue=prdcPrtcptnId
			
			
		}
		
	})
	
	
	
	
	
	
	
	
	$("#btn-add-gnr").click(function(event) {
		event.preventDefault();
		
		console.log("장르 등록 버튼")
		gnr = window.open("${context}/gnr/search", "장르검색", "width=500, height=500")
	});
	$("#btn-add-director, #btn-add-scripter, #btn-add-production, #btn-add-producer, #btn-add-mainActor, #btn-add-supportActor, #btn-add-extra").click(function(event) {
		event.preventDefault();
		
		console.log("감독 등록 버튼")
		ppl = window.open("${context}/mvppl/search", "영화인검색", "width=500, height=500")
		var that = this;
		
		console.log("로그찍기")
		console.log(this)
		console.log($(that).attr("id"))
		// /admin/mvppl/search 화면이 브라우저에 모두 로딩이 되었을 때 :: 렌더링이 끝났을 때
		ppl.onload = function() {
			ppl.targetId = $(that).attr("id"); 
		}
	});
	
	
	$("#btn-new").click(function() {
		var ajaxUtil = new AjaxUtil();
		ajaxUtil.upload("#create-form", "${context}/api/mv/update", function(response) {
			if (response.status == "200 OK") {
				location.href = "${context}" + response.redirectURL;
			} else if (response.errorCode == "404" || response.errorCode == "500") {
				alert(response.message);
			} else {
				alert("영화 등록에 실패했습니다.");
			}
		}, {"pstr":"uploadFile"});
	})
	
	
	
	
	
}); // js .ready() 닫는괄호

function addGnrFn(message) {
	
	console.log(message.gnrnm + " / " + message.gnrid);
	console.log(message);
	
	var gnrItems = $("#btn-add-gnr").closest(".create-group").find(".items");
	if (gnrItems.find("." + message.gnrid).length > 0) {
		gnr.alert(message.gnrnm + " : 이미 추가된 장르");
		return;
	}
	
	var len = gnrItems.find(".gnr-item").length;
	var itemDiv = $("<div class='gnr-item " + message.gnrid + "'></div>");
	
	var added = $("<input type='hidden' name='gnrList["+len+"].added'/>");
	added.val("true");
	itemDiv.append(added);
	
	var itemId = $("<input type='hidden' name='gnrList["+len+"].gnrId'/>");
	itemId.val(message.gnrid);
	itemDiv.append(itemId);
	
	var itemRemoveBtn = $("<button>X</button>");
	itemRemoveBtn.click(function() {
		$(this).closest("." + message.gnrid).remove();
		itemDiv.append(itemRemoveBtn)
	})
	
	var itemSpan = $("<span></span>")
	itemSpan.text(message.gnrnm);
	itemDiv.append(itemSpan);
	itemDiv.append(itemRemoveBtn);
	gnrItems.append(itemDiv);
	// gnrItems.before()
	// gnrItems.after()
	// gnrItems.append("<span>" + message.gnrnm + "</span>")
	// gnrItems.prepend()
}
function addPplFn(message) {
	
	// console.log(message.nm + " / " + message.mvpplid);
	// console.log(message.id);
	console.log(message);
	
	var pplItems = $("#" + message.id).closest(".create-group").find(".items");
	if (pplItems.find("." + message.mvpplid).length > 0) {
		ppl.alert(message.nm + " : 이미 추가된 영화인");
		return;
	}
	

	var len = $("#create-form").find(".mvppl-item").length;
	console.log("버튼id:"+message.id+" / mvppl-item 개수:"+len)
	var itemDiv = $("<div class='mvppl-item " + message.mvpplid + "'></div>");
	
	var added = $("<input type='hidden' name='pplList["+len+"].added'/>");
	added.val("true");
	itemDiv.append(added);
	
	var itemId = $("<input type='hidden' name='pplList["+len+"].mvPplId'/>");
	itemId.val(message.mvpplid);
	itemDiv.append(itemId);
	
	var mssnId = "";
	if (message.id == "btn-add-director") {
		mssnId = "DRCTR"; // 감독
	}
	else if (message.id == "btn-add-scripter") {
		mssnId = "SRTPTR"; // 각본
	}
	else if (message.id == "btn-add-production") {
		mssnId = "PRDCTN"; // 연출
	}
	else if (message.id == "btn-add-producer") {
		mssnId = "PRDCR"; // 제작
	}
	else if (message.id == "btn-add-mainActor") {
		mssnId = "MNACTR"; // 주연
	}
	else if (message.id == "btn-add-supportActor") {
		mssnId = "SPRTACTR"; // 조연
	}
	else if (message.id == "btn-add-extra") {
		mssnId = "EXTR"; // 기타
	}
	
	var mssn = $("<input type='hidden' name='pplList["+len+"].mssn' placeholder='임무?'/>");
	mssn.val(mssnId);
	itemDiv.append(mssn);
	
	var rspnsbltRolNm = $("<input type='text' name='pplList["+len+"].rspnsbltRolNm' placeholder='역할명?'/>");
	rspnsbltRolNm.val("");
	itemDiv.append(rspnsbltRolNm);
	
	var itemRemoveBtn = $("<button>X</button>")
	itemRemoveBtn.click(function() {
		$(this).closest("." + message.mvpplid).remove();
		itemDiv.append(itemRemoveBtn)
	})
	
	var itemSpan = $("<span></span>")
	itemSpan.text(message.nm);
	itemDiv.append(itemSpan);
	itemDiv.append(itemRemoveBtn);
	pplItems.append(itemDiv);
	// pplItems.before()
	// pplItems.after()
	// pplItems.append("<span>" + message.pplnm + "</span>")
	// pplItems.prepend()
}



</script>
</head>
<body>

	<div class="main-layout">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<div>
			<jsp:include page="../include/mvMgntSidemenu.jsp"></jsp:include>
			<jsp:include page="../include/content.jsp"></jsp:include>
				
				<div class="path">영화 > 영화 관리 > 조회</div>
				
				<h1>영화 상세</h1>


				<div>
					<form id="create-form" enctype="multipart/form-data">
					<input type="hidden" name="mvId" value="${mvVO.mvId}" />
					
						<div class="create-group">
							<label for="pstr">포스터</label>
							<img class="profile" src="">
							<input type="file" id="pstr" name="pstr">
						</div>
						<div class="create-group">
							<label for="mvTtl">영화제목</label>
							<input type="text" id="mvTtl" name="mvTtl" value="${mvVO.mvTtl}">
						</div>
						<div class="create-group">
							<label for="engTtl">영화제목(영어)</label>
							<input type="text" id="engTtl" name="engTtl" value="${mvVO.engTtl}">
						</div>
						<div class="create-group">
							<label for="scrnStt">상영상태</label>
							<select id="scrnStt" name="scrnStt" >
								<option >선택</option>
								<option value="상영중" ${mvVO.scrnStt eq '상영중' ? 'selected' : ''}>상영중</option>
								<option value="상영예정" ${mvVO.scrnStt eq '상영예정' ? 'selected' : ''}>상영예정</option>
								<option value="상영종료" ${mvVO.scrnStt eq '상영종료' ? 'selected' : ''}>상영종료</option>
							</select>
						</div>
						<div class="create-group">
							<label for="scrnTm">상영시간(분)</label>
							<input type="number" id="scrnTm" name="scrnTm" value="${mvVO.scrnTm}">
						</div>
						<div class="create-group">
							<label for="opngDt">개봉일</label>
							<input type="date" id="opngDt" name="opngDt" value="${mvVO.opngDt}">
						</div>
						<div class="create-group">
							<label for="grd">관람등급</label>
							<select id="grd" name="grd">
								<optgroup label="관람등급">
									<option value="0">전체관람가</option>
									<option value="7">7세이상 관람가</option>
									<option value="12">12세이상 관람가</option>
									<option value="15">15세이상 관람가</option>
								<option value="19">청소년 관람불가</option>
								</optgroup>
							</select>
						</div>
						<div class="create-group">
							<label for="smr">줄거리</label>
							<textarea id="smr" name="smr">${mvVO.smr}</textarea>
						</div>
						<div class="create-group">
							<label for="useYn">게시여부</label>
							<input type="checkbox" id="useYn" name="useYn" value="Y" ${mvVO.useYn eq 'Y' ? 'checked' : ''} >
						</div>
						<div class="create-group">
							<label for="">장르</label>
							<div>
								<button id="btn-add-gnr" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.gnrList}" var="gnr" varStatus="index">
										<div class='gnr-item ${gnr.gnrId}'>
											<input type='hidden' name='gnrList[${index.index}].gnrId' value="${gnr.gnrId}"/>
											<button class="del-gnr-item" 
													data-index="${index.index}"
													data-gnrid="${gnr.gnrId}">X</button>
											<span>${gnr.gnrVO.gnrNm}</span>
										</div>
										
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">감독</label>
							<div>
								<button id="btn-add-director" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'DRCTR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">각본</label>
							<div>
								<button id="btn-add-scripter" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'SRTPTR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명"
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item"
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">연출</label>
							<div>
								<button id="btn-add-production" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'PRDCTN'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">제작</label>
							<div>
								<button id="btn-add-producer" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'PRDCR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">주연</label>
							<div>
								<button id="btn-add-mainActor" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'MNACTR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">조연</label>
							<div>
								<button id="btn-add-supportActor" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'SPRTACTR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="create-group">
							<label for="">기타</label>
							<div>
								<button id="btn-add-extra" class="btn-primary">등록</button>
								<div class="items">
									<c:forEach items="${mvVO.pplList}" var="ppl" varStatus="index">
										<c:if test="${ppl.mssn eq 'EXTR'}">
											<div class='mvppl-item ${ppl.mvPplId}'>
												<input type='hidden' 
														name='pplList[${index.index}].mvPplId' 
														value="${ppl.mvPplId}"/>
												<input type='hidden' 
														name='pplList[${index.index}].mssn' 
														placeholder="임무" 
														value="${ppl.mssn}"/>
												<input type='text'
														name='pplList[${index.index}].rspnsbltRolNm' 
														placeholder="역할명" 
														class="rspnsbltRolNm"
														data-index="${index.index}"
														data-orgn-nm="${ppl.rspnsbltRolNm}" 
														value="${ppl.rspnsbltRolNm}"/>
												<button class="del-ppl-item" 
														data-index="${index.index}"
														data-prdcprtcptnpplid="${ppl.prdcPrtcptnId}">X</button>
												<span>${ppl.mvPplVO.nm}</span>
											</div>
										</c:if>
									</c:forEach>
								</div>
							</div>
						</div>
						
					</form>
				</div>
					
				<div class="align-right grid-btns">
					<button id="btn-new" class="btn-primary">등록</button>
					<button id="btn-cancel" class="btn-primary btn-delete">취소</button>
				</div>
			<jsp:include page="../include/footer.jsp"></jsp:include>
		</div>
	
	</div>
	
	
	
</body>
</html>