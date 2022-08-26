<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h3 style="font-style: inherit; font-family: monospace;">전화번호부 수정</h3>
<form action="/updateProc" method="post">

<input type="text" id="idx" name="idx" value="${list.idx}" hidden="hidden" > <br> 
<label for="name">이름:</label>
<input type="text" id="name" name="name" value="${list.name}"> <br>

<label for="hp">전화번호:</label>
<input type="text" id="hp" name="hp" value="${list.hp}"> <br>

<label for="memo">메모:</label>
<input type="text" id="memo" name="memo" value="${list.memo}"> <br>

<!-- <input type="submit" value="전화번호입력"> <br> --> 

<input type="submit" value="전화번호수정" onclick="update()"> <br>
</form>