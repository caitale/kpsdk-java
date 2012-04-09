<%@ page contentType="text/html;charset=utf-8" %>
<%@ page language="java" import="org.scribe.model.Token" %>  
<%@ page language="java" import="kp.client.core.KuaipanApi" %>
<%@ page language="java" import="kp.client.core.KuaipanApiClient" %>
<%@ page language="java" import="org.scribe.builder.ServiceBuilder" %>
<%@ page language="java" import="org.scribe.model.Verifier" %>
<%@ page language="java" import="org.json.JSONObject" %>
<%@ page language="java" import="kp.client.model.AccountInfo" %>

<%
	String verifierString=request.getParameter("oauth_verifier");
	String token = null;
	String secret = null;
	if(verifierString !=null){
	    System.out.println("oauth_verifier:" + verifierString);
	    Verifier verifier = new Verifier(verifierString);
	    
	    KuaipanApiClient client = new ServiceBuilder()
	                               .provider(KuaipanApi.class)
	                               .build();
	    Token requestToken = (Token)session.getAttribute("requestToken");
	    if( requestToken == null){
	    	out.write("requestToken is null");
	    }
	    Token accessToken = client.getAccessToken(requestToken, verifier);
	    if(accessToken!=null)
	    {
	    	token = accessToken.getToken();
	    	secret = accessToken.getSecret();
	    	System.out.println("accessToken: " + token);
	    	System.out.println("tokenSecret: " + secret);
	    }else{
	    	out.write("access token request error");
		}	
	}
	else{
		out.println("verifier String error");
	}
%>   
Access Token: <%=token%>
Token Secret: <%=secret%>