<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! int totalScore=0; //객체의 멤버변수%> 
<%! int totalCnt = 0; %>
<%--요청전달데이터 얻기 --%>
<%   
String score = request.getParameter("score"); //_jspService매서드의 지역변수
totalScore +=Integer.parseInt(score);
totalCnt++;
%>
<%=score %>점을 선택하셨습니다
<hr>
총점은 <%=totalScore %>점입니다 <br>
참여인원은 <%=totalCnt %>명입니다 <br>
평점은<%=(float)totalScore/totalCnt %>점입니다 <br>
<a href= "/front/html/score.html">별점주기</a> 
</body>
</html>