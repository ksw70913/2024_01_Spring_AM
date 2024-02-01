<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>


<table border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>수정 날짜</th>
			<th>제목</th>
			<th>내용</th>
			<th>작성자</th>
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
	<a href="list">리스트로 돌아가기</a>
</div>



<%@ include file="../common/foot.jspf"%>