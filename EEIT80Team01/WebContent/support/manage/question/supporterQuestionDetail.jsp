<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/include/include"%>
<%@include file="/include/datatables.file"%>
<title>編號：${questionDetail.qno}來自${questionDetail.member}的問題</title>
<style>
.navbar {
	margin-bottom: 0px;
}

#carousel1 {
	margin-bottom: 20px;
}

body {
	padding-top: 50px;
}
</style>
</head>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<body>
	<header><%@include file="/include/header-support"%></header>
	<article>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-3">
					<%@include file="/support/toolbar"%>
				</div>
				<div class="col-md-9">
					<div class="panel panel-default"
						style="margin: auto; margin-top: 20px; width: 70%">
						<div class="panel-heading">
							<h3 class="panel-title">問題編號${questionDetail.qno}</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<label>會員</label>
								<div class="form-control">${questionDetail.member}</div>
							</div>
							<div class="form-group">
								<label>標題</label>
								<pre>${questionDetail.title}</pre>
							</div>
							<div class="form-group">
								<label>發問時間</label>
								<div class="form-control">
									<jsp:useBean id="dateObject" class="java.util.Date" />
									<jsp:setProperty name="dateObject" property="time" value="${questionDetail.qt}" />
									<fmt:formatDate value="${dateObject}" pattern="yyyy/MM/dd HH:mm:ss" />
								</div>
							</div>
							<div class="form-group">
								<label>問題內容：</label>
								<pre>${questionDetail.qmsg}</pre>
							</div>
							<hr/>
							<div class="form-group">
								<label>回答：</label>
								<form method="post" action="supporterAnswerQuestion.do" id="answerform">
									<textarea id="editor1" class="form-control" rows="10" name="supporter-answer"></textarea>
									<script>CKEDITOR.replace('editor1')</script>
									<div style="margin:20px">
										<input class="btn btn-default" type="submit" value="送出">
										<input class="btn btn-danger" type="reset" value="清除">
									</div>
									<input type="hidden" name="hidden-qno" value="${questionDetail.qno}">
									<input type="hidden" name="hidden-member" value="${questionDetail.member}">
									<input type="hidden" name="hidden-title" value="${questionDetail.title}">
									<input type="hidden" name="hidden-qmsg" value="${questionDetail.qmsg}">
									<input type="hidden" name="hidden-qt" value="${questionDetail.qt}">
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</article>
</body>
</html>