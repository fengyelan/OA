<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>    
    <title>My JSP 'editUI.jsp' starting page</title>
  </head>
  
  <body>
   	<s:form action="role_edit">
   		<s:hidden name="id"></s:hidden>
   		岗位名称<s:textfield name="name"></s:textfield><br/>
   		岗位描述<s:textarea name="description" cols="50" rows="10"></s:textarea><br/>
   		<s:submit value="提交"></s:submit>
   	</s:form>
  </body>
</html>
