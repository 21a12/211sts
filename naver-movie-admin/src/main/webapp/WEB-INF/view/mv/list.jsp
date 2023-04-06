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
<title>영화 목록</title>
<jsp:include page="../include/stylescript.jsp"></jsp:include>
<script type="text/javascript">
$().ready(function() {
		
		$("#btn-new").click(function() {
			location.href = "${context}/mv/create"
			
		})
		
	
}); // js .ready() 닫는괄호

</script>
</head>
<body>

	<div class="main-layout">
		<jsp:include page="../include/header.jsp"></jsp:include>
		<div>
			<jsp:include page="../include/mvMgntSidemenu.jsp"></jsp:include>
			<jsp:include page="../include/content.jsp"></jsp:include>
				
				<div class="path">영화 > 영화 관리</div>
				<div class="search-row-group">
					<div class="search-group">
						<label for="search-keyword-nm">이름</label>
						<input type="text" id="search-keyword-nm" class="search-input" value="${mvPplVO.nm}">
						
						<label for="search-keyword-rlnm">본명</label>
						<input type="text" id="search-keyword-rlnm" class="search-input" value="${mvPplVO.rlNm}">
					</div>
					<div class="search-group">
						<label for="search-keyword-startdt">조회기간</label>
						<input type="date" id="search-keyword-startdt" class="search-input" value="${mvPplVO.startDt}">
						<input type="date" id="search-keyword-enddt" class="search-input" value="${mvPplVO.endDt}">
						<button class="btn-search" id="btn-search">검색</button>
					</div>
				</div>
				
				
				<div class="grid">
					
					<div class="grid-count align-right">
						총 ${mvPplList.size() > 0 ? mvPplList.get(0).totalCount : 0}건
					</div>
					<table>
						<thead>
							<tr>
								<th><input type="checkbox" id="all-check"/></th>
								<th>영화인ID</th>
								<th>사진유무</th>
								<th>이름</th>
								<th>본명</th>
								<th>등록자</th>
								<th>등록일</th>
								<th>수정자</th>
								<th>수정일</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty mvPplList}">
									<c:forEach items="${mvPplList}"
												var="mvPpl"
												varStatus="index">
										<tr data-mvpplid="${mvPpl.mvPplId}" 
											data-prflpctr="${mvPpl.prflPctr}" 
											data-nm="${mvPpl.nm}" 
											data-rlnm="${mvPpl.rlNm}" 
											data-crtr="${mvPpl.crtr}" 
											data-crtdt="${mvPpl.crtDt}" 
											data-mdfyr="${mvPpl.mdfyr}" 
											data-mdfydt="${mvPpl.mdfyDt}"
											data-useyn="${mvPpl.useYn}"
											data-crtrnm="${mvPpl.crtrMbrVO.mbrNm}"
											data-mdfyrnm="${mvPpl.mdfyrMbrVO.mbrNm}"> 
											<td class="align-center">
												<input type="checkbox" class="check-idx" value="${mvPpl.mvPplId}" />
											</td>
											<td>${mvPpl.mvPplId}</td>
											<td>
												<c:choose>
													<c:when test="${not empty mvPpl.prflPctr}">Y
													</c:when>
													<c:otherwise>N</c:otherwise>
												</c:choose>
											</td>
											<td>${mvPpl.nm}</td>
											<td>${mvPpl.rlNm}</td>
											<td>${mvPpl.crtr}(${mvPpl.crtrMbrVO.mbrNm})</td>
											<td>${mvPpl.crtDt}</td>
											<td>${mvPpl.mdfyr}(${mvPpl.mdfyrMbrVO.mbrNm})</td>
											<td>${mvPpl.mdfyDt}</td>
										</tr>
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="9" class="no-items">
											등록된 영화인이 없습니다.
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
							<c:set value="${mvPplList.size() > 0 ? mvPplList.get(0).lastPage : 0}" var="lastPage"></c:set>
							<c:set value="${mvPplList.size() > 0 ? mvPplList.get(0).lastGroup : 0}" var="lastGroup"></c:set>
							
							<fmt:parseNumber var="nowGroup" value="${Math.floor(mvPplVO.pageNo / 10)}" integerOnly="true" />
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
								<li><a class="${pageNo eq mvPplVO.pageNo ? 'on' : ''}"  href="javascript:movePage(${pageNo})">${pageNo+1}</a></li>
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
					<form id="form-detail" enctype="multipart/form-data">
						<!-- 
						isModify == true -> 수정(update)
						isModify == false -> 등록(insert)
						 -->
						<input type="hidden" id="isModify" value="false" />
						<div class="input-group inline">
							<label for="mvPplId" style="width:150px;">영화인ID</label>
							<input type="text" id="mvPplId" class="readonly" name="mvPplId" readonly value=""/>
						</div>
						<div class="input-group inline">
							<div style="position:relative;">
								<label for="prflPctr" style="width:150px;">프로필사진</label>
								<input type="file" id="prflPctr"  name="prflPctr" value="" style="display:none;"/>
								<img src="${context}/img/base_profile.jpg" id="previewPrfl" class="profile">
								<button id="del-pctr" style="position: absolute; right:10px; bottom:10px;">X</button>
								<input type="hidden" id="isDeletePctr" name="isDeletePctr" value="N">
							</div>
						</div>
						<div class="input-group inline">
							<label for="nm" style="width:150px;">이름</label>
							<input type="text" id="nm"  name="nm" value=""/>
						</div>
						<div class="input-group inline">
							<label for="rlNm" style="width:150px;">본명</label>
							<input type="text" id="rlNm" name="rlNm" value=""/>
						</div>
						<div class="input-group inline">
							<label for="crtr" style="width:150px;">등록자</label>
							<input type="text" id="crtr" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="crtDt" style="width:150px;">등록일</label>
							<input type="text" id="crtDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="mdfyr" style="width:150px;">수정자</label>
							<input type="text" id="mdfyr" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="mdfyDt" style="width:150px;">수정일</label>
							<input type="text" id="mdfyDt" disabled value=""/>
						</div>
						<div class="input-group inline">
							<label for="mdfyDt" style="width:150px;">사용여부</label>
							<input type="checkbox" id="useYn"  name="useYn" value="Y"/>
						</div>
						
						
					</form>
					
				</div>
				<div class="align-right grid-btns">
					<button id="btn-new" class="btn-primary">등록</button>
					<button id="btn-delete" class="btn-primary btn-delete">삭제</button>
				</div>
			<jsp:include page="../include/footer.jsp"></jsp:include>
		</div>
	
	</div>
	
	
	
</body>
</html>