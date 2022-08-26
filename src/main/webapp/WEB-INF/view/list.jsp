<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach var="pb" items="${list}">
<li onclick="getPhonebook(${pb.idx})">
<img src="https://mblogthumb-phinf.pstatic.net/MjAyMDA1MjFfMTAg/MDAxNTkwMDE1MDcxNjY3.q66gea4AdtMXQ2gZMG11GTy1aFQ5bX0MApov2_Lm5XUg.c0YwODrYXkooTXnhebAc7RsmlPCpGHdrMVgBNzffXVkg.GIF.hope7188/i015786180056.gif?type=w800" style="width: 80px; height: 50px;">
<c:out value="${pb.name}"/><br>
<c:out value="${pb.hp}"/><br>
<c:out value="${pb.memo}"/><br>
</li>
</c:forEach>