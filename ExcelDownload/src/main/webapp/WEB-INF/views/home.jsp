<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
<style>
</style>
<script type="text/javascript">
// 엑셀 다운로드
function selectExcelOption(){
	frm = document.getElementById('excelForm');
	openWin = window.open('','viewer','resizable=yes, scrollbars=no, location=no, toolbars=no, status=no, left=0, top=0'+'width='+screen.width+', height='+screen.height+',fullscreen=yes');
	frm.action = '/selectExcelOption.do';
	frm.target = 'viewer';
	frm.method = 'post';
	frm.submit();
}
</script>
</head>
<body>
<h1>
	Excel Download (셀 병합 ver)
</h1>

<form id="excelForm">
	<div class="contents">
		<a href="#none" onClick="javascript:selectExcelOption();">엑셀 다운로드 할 옵션 선택창</a>
	</div>
</form>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
