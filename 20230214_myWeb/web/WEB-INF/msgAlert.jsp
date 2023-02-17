<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	var m = "${msg}";
	alert(m);
	location.replace("<%=request.getContextPath()%>/");	
</script>
<!-- href="" 대신 replace()를 쓰는 이유는 새로고침이나 뒤로가기를 해도 양식의 재제출 않게 하기 위해서 -->
</body>
</html>