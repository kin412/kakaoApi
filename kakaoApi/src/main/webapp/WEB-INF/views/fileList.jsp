<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
업로드 된 이미지 목록<br>
(이미지 클릭 시 다운로드)
<table>
	<c:forEach var="item" items="${listFile}" varStatus="fstStatus">
		<tr>
			<c:forEach var="item2" items="${item}" varStatus="status">
				<c:choose>
					<c:when test="${status.index eq 0}">
						<td>${item2}</td>
						
						<!-- tomcat 내  server.xml host 문구 추가. 브라우저 로컬 파일 접근 금지정책 -->
						<!-- <img src="/upload/mountain.jpg"/> -->
						<c:choose>
							<c:when test="${fn:indexOf(item2, 'xlsx') gt -1 }">
								<td><a href="fileDown?fileName=${item2}">엑셀 다운로드 ${fstStatus.index} </a></td>
							</c:when>
							<c:otherwise>
								<td><a href="fileDown?fileName=${item2}"><img src="/upload/${item2}"/></a></td>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>
			</c:forEach>
		</tr>
	</c:forEach>
</table>

<div>이미지 업로드</div>
<form id="frm" name="frm" method="post" action="fileUpload" enctype="multipart/form-data">
	<input type="file" name="file1"/><br>
	<input type="file" name="file2"/><br>
	<input type="submit" value="이미지 업로드">
</form><br>

<div>엑셀 업로드</div>
<form id="frmEx" name="frmEx" method="post" action="fileUploadEx" enctype="multipart/form-data" >
	<input type="file" id="fileEx" name="fileEx"/><br>
	<input type="submit" value="엑셀 업로드 및 poi 재조립">
</form>

</body>
</html>