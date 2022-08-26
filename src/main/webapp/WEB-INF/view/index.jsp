<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
* {
	list-style: none;
}

header {
	width: 100%;
	height: 200px;
	border: 1px solid red;
	background-color: aqua;
}

main {
	width: 100%;
	height: 600px;
	border: 2px solid orange;
	display: flex;
	background-color: fuchsia;
}

aside {
	width: 22%;
	height: 600px;
	border: 1px solid green;
	overflow: scroll;
	background-color: orange;
}

#content {
	width: 78%;
	height: 600px;
	border: 1px solid yellow;
}

footer {
	width: 100%;
	height: 100px;
	border: 1px solid black;
	background-color: olive;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	function getList() {
		// $.ajax(); -> $.ajax({}); -> $.ajax({url,성공했을 때 함수 구현}) 
		//$.ajax({url:"/list",sucess:function(result){$("#list").html(reslut);}
		//});

		$.ajax({
			url :"/list", success : function(result) {$("#list").html(result);}
		});
	}
	function search(search) {
		//$("#content").html(search);
		$.ajax({
			url :"/find?search=" + search, success : function(result) {$("#list").html(result);}
		});
	}
	function insert() {

		var name = document.getElementById("name").value;
		var hp = document.getElementById("hp").value;
		var memo = document.getElementById("memo").value;
		console.log(name);
		console.log(hp);
		console.log(memo);
		/*
		$.ajax({
			url :"/insert?name="+name+"&hp="+hp+"&memo="+memo,
			success : function(result) {$("#list").html(result);}
		});
		*/
		
		$.ajax({
			url :"/insert",
			type :"post",
			dataType :"text",
			data : {"name" : name, "hp" : hp, "memo" : memo},
			success : function(result) {$("#list").html(result);}
		});
		document.querySelector("#name").value = "";
		document.querySelector("#hp").value = "";
		document.querySelector("#memo").value = "";
	}
	function getPhonebook(idx) {
		console.log(idx);
		$.ajax({
			url :"/findOne?idx=" + idx, 
					success : function(result) {$("#content").html(result);}
		});
	}
	function updateForm(idx) {
		$.ajax({
			url : "/updateForm?idx="+idx,
			success : function(result) {
				$("#content").html(result);
			}
		});
	}
	function update(idx) {
		//alert('test');
		
	 	//alert(idx);
	 	/* var name = document.getElementById("name").value;
		var hp = document.getElementById("hp").value;
		var memo = document.getElementById("memo").value; */
		 $.ajax({
			url : "/updateProc?idx="+idx,
			success : function(result) {
				$("#content").html(result);	}
		});  
	}
		//alert(name);
		/* console.log(name);
		console.log(hp);
		console.log(memo);  */
 
		//document.querySelector("#name").value = "";
		//document.querySelector("#hp").value = "";
		//document.querySelector("#memo").value = "";

 </script>

</head>
<body onload="getList()">

	<header>
		<h3 style="font-style: inherit; font-family: monospace;">전화번호부 입력</h3>
		<form action="/insertPrco" method="post">
			
			<label for="name">이름:</label> 
			<input type="text" id="name" name="name"> <br> 
			
			<label for="hp">전화번호:</label>
			 <input	type="text" id="hp" name="hp"> <br> 
			 
			 <label for="memo">메모:</label>
			<input type="text" id="memo" name="memo"> <br>
			
			<!-- <input type="submit" value="전화번호입력"> <br> -->

		</form>
		<input type="button" value="전화번호입력" onclick="insert()"> <br>
	</header>

	<main>
		<aside>
			<div id="search">
				<input type="text" name="search" onkeyup="search(this.value)">
			</div>
			<div id="list">전체 리스트 or 검색 리스트</div>
		</aside>

		<div id="content">선택 결과</div>
	</main>

	<footer></footer>

</body>
</html>