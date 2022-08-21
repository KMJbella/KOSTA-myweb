<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
public void jspInit() {
	System.out.println("lifecycle_jsp 객체생성됨");
}
public void jspDestroy() {
	System.out.println("lifecycle_jsp 객체소멸됨");
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lifecycle</title>
</head>
<body>
<%
System.out.println("lifecycle_jsp객체의 _jspService()호출됨");
%>
</body>
</html>




















