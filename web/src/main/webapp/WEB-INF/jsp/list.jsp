<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<!-- 공통 HEADER -->
	<%@ include file="/WEB-INF/jsp/com/include/header.jspf"%>
	
	<script>
	$(function() {
		$('#pagination').twbsPagination({
			totalPages : Number("${rParam.totalPageCount}"),
			startPage : Number("${rParam.currentPageNo}"),
			visiblePages : Number("${rParam.visiblePages}"),
			initiateStartPageClick : false,
			onPageClick : function(event, page) {
				$("#currentPageNo").val(page);
				$("#frm").attr("action", "<c:url value='/list.do'/>").submit();
			}
		});
	});
	</script>
</head>
<body>
	<form method="post" name="frm" id="frm">
		<input type="hidden" name="currentPageNo" id="currentPageNo" value="${rParam.currentPageNo}" />
	</form>

	<section id="page1" data-role="page">
		<header data-role="header">
			<h1>List</h1>
		</header>
		<div>
			<c:forEach items="${list}" var="paramVO">
			    <ul>
					<li>${paramVO.id} | ${paramVO.password}</li>
				</ul>
			</c:forEach>
		</div>
		<div class="content">
			<ui id="pagination" class="pagination-sm"></ui>
		</div>
		<footer data-role="footer">
			<h1>footer</h1>
		</footer>
	</section>
</body>
</html>