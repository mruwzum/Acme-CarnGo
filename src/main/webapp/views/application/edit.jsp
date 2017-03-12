<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="application/edit.do" modelAttribute="application">

    <form:hidden path="id"/>
    <form:hidden path="version"/>


    <acme:textbox path="requestStatus" code="application.name"/>
    <br/>


    <!---------------------------- BOTONES -------------------------->

    <input type="submit" name="save"
           value="<spring:message code="application.save" />"/>

    <jstl:if test="\$\{application.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="application.delete" />"
               onclick="return confirm('<spring:message code="application.confirm.delete"/>')"/>&nbsp;
    </jstl:if>

    <input type="button" name="cancel"
           value="<spring:message code="application.cancel" />"
           onclick="javascript: window.location.replace('application/list.do')"/>

</form:form>