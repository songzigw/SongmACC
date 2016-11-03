<%@page import="songm.account.utils.RandomCode"%>
<%@page import="songm.account.web.SSOAuthUtil"%>
<%@page import="songm.account.service.SSOAuthServer"%>
<%@page language="java" contentType="image/jpeg; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
    out.clearBuffer();
    SSOAuthServer ssoServer = (SSOAuthServer) request.getAttribute("ssoAuthServer");
    SSOAuthUtil ssoAuthUtil = new SSOAuthUtil(request, response, ssoServer);
    RandomCode rcode = new RandomCode();
    ssoServer.getSSOClient().setAttribute(ssoAuthUtil.getSessionId(), "V_CODE",
            rcode.getCode());
    rcode.getRandcode(response.getOutputStream());
%>