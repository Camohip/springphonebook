<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

${phonebook.name}
<br>
${phonebook.hp}
<br>
${phonebook.memo}
<br>
<input type="button" value="수정하기" onclick="updateForm(${phonebook.idx})">
<input type="button" value="삭제하기" onclick="deletelist(${phonebook.idx})">

<script>	
function deletelist(idx) {
	console.log(idx);
	$.ajax({
		url :"/deletelist?idx="+idx, 
				success : function(result) {$("#context").html(result);
				alert("삭제완료");
				document.location.reload();
				}
	});
}
	</script>