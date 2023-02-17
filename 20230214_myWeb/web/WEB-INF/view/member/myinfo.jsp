<%@page import="kh.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내정보보기</title>
<script src="https://code.jquery.com/jquery-3.6.3.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<h1>내정보보기</h1>
<h4>EL request attribute</h4>
<c:if test="${empty myinfo }">
<div>
	<h4>로그인 되지 않았습니다. 정보가 없습니다.</h4>
	<a href="<%=request.getContextPath()%>/login">로그인페이지로 이동</a>
</div>
</c:if>
<c:if test="${not empty myinfo }">
<div>
	id : ${myinfo.id }
	<br>
	name : ${myinfo.name }
	<br>
	email : ${myinfo.email }	
</div>
</c:if>
<hr>
<h4>EL session attribute</h4>
<div>
	id : ${lgnss.id }
	<br>
	name : ${lgnss.name }
	<br>
	email : ${lgnss.email }	
</div>
<hr>
 
<h4>JSP request attribute</h4>
<%
	Object obj = request.getAttribute("myinfo");
	MemberVo vo = null;
	if(obj == null){
		// 로그아웃 상태
%>
			<h4>로그인 되지 않았습니다. 정보가 없습니다.</h4>
			<a href="<%=request.getContextPath()%>/login">로그인페이지로 이동</a>
<%		
	} else {
		if(obj instanceof MemberVo){
			vo = (MemberVo)obj;
		}
		if(vo == null){
			// 로그아웃 상태
%>
			<h4>로그인 되지 않았습니다. 정보가 없습니다.</h4>
			<a href="<%=request.getContextPath()%>/login">로그인페이지로 이동</a>
<%
		} else {
%>
			id : <%=vo.getId() %>
			<br>
			name : <%=vo.getName() %>
			<br>
			email : <%=vo.getEmail() %>	
<% 
		}
	}
%>

</body>
</html>