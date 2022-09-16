<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>엑셀 다운로드 할 옵션 선택창</title>
<style>
</style>
<script type="text/javascript">

// 창 크기 조절
function reSize(){
	window.resizeTo(1000,700);
}

// 전체선택
function selectTotal(){
	if(document.forms[0].selectTotal.checked){
		for(i=0; i<document.forms[0].checkList.length; i++) {
			if(!document.forms[0].checkList[i].disabled) {
				document.forms[0].checkList[i].checked = true;
			}
		}
	} else {
		for(i=0; i<document.forms[0].checkList.length; i++) {
			if(!document.forms[0].checkList[i].disabled) {
				document.forms[0].checkList[i].checked = false;
			}
		}
	}
}

// 엑셀 다운로드
function realExcelDownload(){
	
	// checkbox 값 없으면 alert
	var count = 0;
	var length = excelForm2.checkList.length;
	var i = 0;
	while (i < length){
		if (excelForm2.checkList[i].checked){
			count = count+1;
		}
		i++;
	}
	if (count=0){
		alert("최소 한 개 이상 선택해주세요.");
		return false;
	}
	
	frm = document.excelForm2;
	frm.action = '/excelDownload.do';
	frm.method = 'post';
	frm.submit();
	
}

// 팝업창 닫기
function close(){
	self.close();
}

</script>
</head>
<body onload="reSize();">

<div id="wrap">
	<div>
		<h3>다운로드할 옵션을 선택하세요.</h3>
	</div>
	<form name="excelForm2" id="excelForm2">
		<div>
			<table>
				<colgroup>
					<col width="300px;"/>
					<col width="300px;"/>
				</colgroup>
				<tbody>
					<tr>
						<th>선택</th>
						<td>
							<input type="checkbox" name="checkList" id="checkList1" value="love" checked><label for="checkList1">love</label>
							<input type="checkbox" name="checkList" id="checkList2" value="happy" checked><label for="checkList2">happy</label>
							<input type="checkbox" name="checkList" id="checkList3" value="wish"><label for="checkList3">wish</label>
							<input type="checkbox" name="checkList" id="checkList4" value="amaze"><label for="checkList4">amaze</label>
							<input type="checkbox" name="checkList" id="checkList5" value="friendship"><label for="checkList5">friendship</label>
							<br/>
							<input type="checkbox" name="checkList" id="checkList6" value="smile"><label for="checkList6">smile</label>
							<input type="checkbox" name="checkList" id="checkList7" value="hope"><label for="checkList7">hope</label>
							<input type="checkbox" name="checkList" id="checkList8" value="world"><label for="checkList8">world</label>
							<input type="checkbox" name="checkList" id="checkList9" value="touch"><label for="checkList9">touch</label>
							<input type="checkbox" name="checkList" id="checkList10" value="surprised"><label for="checkList10">surprised</label>
							<br/>
							<input type="checkbox" name="checkList" id="checkList11" value="kind"><label for="checkList11">kind</label>
							<input type="checkbox" name="checkList" id="checkList12" value="try"><label for="checkList12">try</label>
							<input type="checkbox" name="checkList" id="checkList13" value="excited"><label for="checkList13">excited</label>
							<input type="checkbox" name="checkList" id="checkList14" value="fruits"><label for="checkList14">fruits</label>
							<input type="checkbox" name="checkList" id="checkList15" value="interested"><label for="checkList15">interested</label>
							<br/>
							<input type="checkbox" name="checkList" id="checkList16" value="relieved"><label for="checkList16">relieved</label>
							<input type="checkbox" name="checkList" id="checkList17" value="peaceful"><label for="checkList17">peaceful</label>
							<input type="checkbox" name="checkList" id="checkList18" value="grateful"><label for="checkList18">grateful</label>
							<input type="checkbox" name="checkList" id="checkList19" value="joyful"><label for="checkList19">joyful</label>
							<input type="checkbox" name="checkList" id="checkList20" value="calm"><label for="checkList20">calm</label>
						</td>
					</tr>
					<tr>
						<th><input type="checkbox" id="selectTotal" onClick="javascript:selectTotal()"><label for="selectTotal">전체선택</label></th>
						<td>5초 정도 기다려주시면 다운로드 됩니다.</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
	<div>
		<a href="#none" id="realExcelDownload" onClick="javascript:realExcelDownload()">엑셀 다운로드</a>
		<a href="#none" id="close" onClick="javascript:close()">창 닫기</a>
	</div>
</div>

</body>
</html>