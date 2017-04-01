<%@page import="cn.songm.common.utils.RandomCode"%>
<%@page import="cn.songm.acc.webapi.Browser"%>
<%@page import="cn.songm.sso.service.SongmSSOService"%>
<%@page language="java" contentType="image/jpeg; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
    out.clearBuffer();
    RandomCode rcode = (RandomCode) request.getAttribute("rcode");
    rcode.getRandcode(response.getOutputStream());
%>