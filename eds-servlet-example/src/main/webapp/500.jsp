<%-- Set the content type header with the JSP directive --%>
<%@ page contentType="application/json" %>

<%-- Set the content disposition header --%>
<%
   // Returns all employees (active and terminated) as json.
   response.setContentType("application/json");
   response.setHeader("Content-Disposition", "inline");
%>
{
	"userMessage": "${requestScope['javax.servlet.error.message']}"
}