<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="#{board.code } ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>
<%
int cPage = (int) request.getAttribute("page");
int totalPage = (int) request.getAttribute("totalPage");
int pageSize = (int) request.getAttribute("pageSize");
int pageGroup = (int) request.getAttribute("pageGroup");
int from = (int) request.getAttribute("from");
int end = (int) request.getAttribute("end");
String searchKeyword = (String) request.getAttribute("searchKeyword");
%>


<section class="mt-8 text-xl px-4">
	<div class="mx-auto overflow-x-auto">
		<div class="badge badge-outline">${articlesCount }개</div>
		<table class="table-box-1 table" border="1">
			<colgroup>
				<col style="width: 10%" />
				<col style="width: 20%" />
				<col style="width: 60%" />
				<col style="width: 10%" />
			</colgroup>
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="article" items="${articles }">
					<tr class="hover">
						<td>${article.id }</td>
						<td>${article.regDate.substring(0,10) }</td>
						<td><a href="detail?id=${article.id }">${article.title }</a></td>
						<td>${article.extra__writer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="pagination flex justify-center mt-3">
		<div class="btn-group">
			<%
			if (pageGroup * pageSize > totalPage) {
				end = totalPage;
			}

			if (from < 1) {
				from = 1;
			}

			if (end > totalPage) {
				end = totalPage;
			}
			int beforeBtn = cPage - pageSize;

			if (beforeBtn < 1) {
				beforeBtn = 1;
			}

			int afterBtn = pageGroup * pageSize + 1;

			if (cPage > 1) {
			%>
			<a href="?boardId=${board.id }&page=1">◀◀</a>
			<%
			}
			%>
			<a href="?boardId=${board.id }&page=<%=beforeBtn%>">◁</a>
			<c:forEach begin="<%=from%>" end="<%=end%>" var="i">
				<a class="btn btn-sm ${param.page == i ? 'btn-active' : '' }"
					href="?boardId=${param.boardId }&page=${i }&searchKeyword=${param.searchKeyword } ">${i }</a>
			</c:forEach>
			<%
			if (afterBtn < totalPage) {
			%>
			<a href="?boardId=${board.id }&page=<%=afterBtn%>">▷</a>
			<%
			}
			if (cPage < totalPage) {
			%>
			<a href="?boardId=${board.id }&page=<%=totalPage%>">▶▶</a>
			<%
			}
			%>
		</div>
	</div>
</section>

<div class="container">
	<div class="row">
		<form method="get" name="search" action="../article/list">

			<div>
				<input type="hidden" name="boardId" value="${param.boardId }" /> <input type="hidden" name="page" value="1" />
			</div>
			<div>
				<input type="text" class="form-control" placeholder="검색어 입력" name="searchKeyword" maxlength="100">
			</div>

			<button type="submit" class="btn btn-success">검색</button>

		</form>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>