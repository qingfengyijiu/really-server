<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
<style type="text/css">
	.ui-page {
		display:block;
	}
</style>
<script src="<c:url value='/js/jquery-1.11.0.js'/>"></script>
<script>
	<% 
		String schema = request.getScheme();
		String redirectUrl = "http://" + request.getServerName() + ":8080" + request.getContextPath() + "/test/welcome";
	%>
	$(function() {
		var schema = "<%=schema%>";
		if(schema != "http") {
			alert("<%=redirectUrl%>");
			//window.location.href = "<%=redirectUrl%>";
		}
		$("#submit").on("click", function() {
			$("form")[0].submit();
		});
	});
</script>
</head>
<body>
	<div class="ui-page">
		<form action="<sp:url value='/test/login'/>" method="post">
			<input type="text" name="username"/>
			<input type="text" name="password"/>
		</form>
		<button id="submit">welcome</button>
	</div>
	
</body>
</html>
