<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>rankPage.jsp</h1>
<!-- private int rank;
	private String methodName;
	private int callCount; -->
	<table>
		<tr>
			<td>rank</td>
			<td>method name</td>
			<td>call count</td>
	
		</tr>
		<c:forEach items="${RankList }" var="rank">
			<tr>
				<td>${rank.rank }</td>
				<td>${rank.methodName }</td>
				<td>${rank.callCount }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
