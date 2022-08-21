
<%@page import="com.my.dto.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewproduct.js</title>
<link rel="stylesheet" href="./css/layout.css">
<link rel="stylesheet" href="./css/viewproduct.css"> <!-- html은 웹브라우저 주소 기준: jsp가 결과값을 다 계산한 다음에 웹브라우저에서 실행, 웹브라우저 차원에서의 경로기준 -->
</head>
<body>
	<%Product p = (Product) request.getAttribute("p");%>
	<jsp:include page="../html/header.html"></jsp:include> <!-- 서버기준 경로 설정: 서블릿이나 jsp문법은 서버에서 실행하기때문에-->
	
	<section>
		<article>
			<div class="viewproduct">
				<img src="/front/images/<%=p.getProdNo()%>.jpg" alt="<%=p.getProdName()%>">
				<div class="detail">
				<ul>
					<li>상품번호:<%=p.getProdNo()%></li>
					<li>상품명:<%=p.getProdName()%></li>
					<li>상품가격:<%=p.getProdPrice()%></li>
				
					<li>상품제조일자:<%=p.getProdMfd()%></li>
					<li>상품상세:<%=p.getProdInfo()%></li>
					<li>수량: 
					<input type="number" max="9" min="1" value="1">
					</li>
					<li><button>장바구니 넣기</button>
				</ul>
				</div>
			</div>
		</article>
	</section>
	
<jsp:include page="../html/footer.html"></jsp:include>
</body>
</html>