<%@page import="cn.songm.common.utils.RandomCode"%>
<%@page import="cn.songm.acc.webapi.Browser"%>
<%@page import="cn.songm.sso.service.SongmSSOService"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="cn.songm.common.utils.CodeUtils"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", -10);
    out.clearBuffer();
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    RandomCode rcode = (RandomCode) request.getAttribute("rcode");
    rcode.getRandcode(output);
    out.print(CodeUtils.encode64(output.toByteArray()));
%>