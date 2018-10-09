<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
	<!-- 공통 HEADER -->
	<%@ include file="/WEB-INF/jsp/com/include/header.jspf"%>

	<script>
	$(function() {

		$("#id").val($.cookie("id"));
		$("#password").val($.cookie("pw"));

		$("#btn_login").click(function() {
			$.ajax({
				type : "POST",
				url : "<c:url value='/login.do'/>",
				data : {id : $("#id").val(), password : $("#password").val()},
				success : function(jsonData) {
					
					$.cookie('id', $("#id").val()      , {expires : 7, path : '/'});
					$.cookie('pw', $("#password").val(), {expires : 7, path : '/'});

					try {
						alert(jsonData.msg);
					} catch (e) {
						alert("ERROR")
					}
				},
				error : function() {}
			});
		});

		$("#btn_mail").click(function() {
			$.ajax({
				type : "POST",
				url : "<c:url value='/sendMail.do'/>",
				data : {},
				success : function(jsonData) {
					try {
						alert(jsonData.msg);
					} catch (e) {
						alert("ERROR")
					}
				},
				error : function() {}
			});
		});
	});
	</script>
</head>
<body>
	<section id="page1" data-role="page">
		<header data-role="header">
			<h1>Login Mobile</h1>
			<button id="btn_mail" class="ui-btn-right ui-btn ui-btn-b ui-btn-inline ui-mini ui-corner-all ui-btn-icon-right ui-icon-check">MAIL</button>
		</header>
		<div class="content">
			<label for="userid">id</label> <input type="text" class="form-control" name="id" id="id" value="">
			<label for="userid">pw</label> <input type="text" class="form-control" name="password" id="password" value="">
			<button id="btn_login">LOGIN</button>
		</div>
		<footer data-role="footer">
			<h1>footer</h1>
		</footer>
	</section>
</body>
</html>