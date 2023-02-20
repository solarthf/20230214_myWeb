<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section>
	<h1>게시글 목록</h1>
	
	<table>
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성시간</td>
				<td>조회수</td>
			</tr>
		</thead>
		
		<c:forEach items="${boardlist }" var="vo" varStatus="s">
		<%-- <c:set target="${vo }" property="boardTitle" value="aaa" /> --%>
		
		<c:if test="${s.count % 2 == 1}">
			<tr style="{background:#ccc}">
		</c:if>
		<c:if test="${s.count % 2 == 0}">
			<tr>
		</c:if>
			<!-- <tr> -->
				<td>&nbsp;${vo.boardNum }: ${s.count }: ${s.index }</td>
				<td>${vo.boardTitle }</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${vo.boardWriter }</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${vo.boardDate }</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;${vo.boardReadcount }</td>
			</tr>
		</c:forEach>
		<tbody></tbody>
	</table>
	<div>
		<h3>페이지처리</h3>
		<div>
		<c:if test="${startPageNum > 1}">
			<span><a href="<%=request.getContextPath()%>/?aaa=${startPageNum }">&nbsp;&nbsp;이전&nbsp;&nbsp;</a></span>
		</c:if>
			<c:forEach begin="${startPageNum }" end="${endPageNum }" step="1" var="page">
				<c:choose>
					<c:when test="${page == currnetPage }">
					<span><b>&nbsp;&nbsp;${page }&nbsp;&nbsp;</b></span>
					</c:when>
				<c:otherwise>
					<span><a href="<%=request.getContextPath()%>/?aaa=${page }">&nbsp;&nbsp;${page }&nbsp;&nbsp;</a></span>
				</c:otherwise>
				</c:choose>
			</c:forEach>
		<c:if test="${endPageNum < pageCnt}">
			<span><a href="<%=request.getContextPath()%>/?aaa=${endPageNum }">&nbsp;&nbsp;다음&nbsp;&nbsp;</a></span>
		</c:if>
		</div>
	</div>
	
	 
	<hr>

</section>
