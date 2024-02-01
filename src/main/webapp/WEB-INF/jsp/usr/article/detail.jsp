<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ARTICLE DETAIL</title>
</head>
<body>
	<h1>DETAIL</h1>

	<hr />

	<table border="1">
		<thead>
			<tr>
				<th>��ȣ</th>
				<th>��¥</th>
				<th>���� ��¥</th>
				<th>����</th>
				<th>����</th>
				<th>�ۼ���</th>
			</tr>
		</thead>
		<tbody>
			<c var="article" value="${articleRow }">
			<tr>
				<td>${articleRow.id }</td>
				<td>${articleRow.regDate.substring(0,10) }</td>
				<td>${articleRow.updateDate.substring(0,10) }</td>
				<td>${articleRow.title }</td>
				<td>${articleRow.body }</td>
				<td>${articleRow.nickname }</td>
			</tr>
			</c>

		</tbody>

	</table>

	<div>
		<a href="list">����Ʈ�� ���ư���</a>
	</div>



</body>
</html>