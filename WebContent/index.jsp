<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="org.scribe.model.Token" %>  
<%@ page language="java" import="kp.client.core.KuaipanApi" %>
<%@ page language="java" import="kp.client.core.KuaipanApiClient" %>
<%@ page language="java" import="org.scribe.builder.ServiceBuilder" %>

<%
	if("1".equals(request.getParameter("opt"))){
	KuaipanApiClient client = new ServiceBuilder()
	                           .provider(KuaipanApi.class)
	                           .callback("http://localhost:8080/KPsdk/callback.jsp")
	                           .build();
	Token requestToken = client.getRequestToken();
	session.setAttribute("requestToken", requestToken);
	if( requestToken.getToken() != null){
		System.out.println("requestToken: " + requestToken.getToken());
		response.sendRedirect(client.getAuthorizationUrl(requestToken));
	}
	else{
		out.println("request error");
	}
}else{
%>
<a href="index.jsp?opt=1">请点击进行OAuth认证</a>   
<%}%>