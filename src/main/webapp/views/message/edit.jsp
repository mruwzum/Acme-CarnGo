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


<form:form action="message/edit.do" modelAttribute="message1">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="sentDate"/>
    <form:hidden path="sender"/>
    <form:hidden path="receiver"/>



    <acme:textbox path="subject" code="message.subject"/>
    <acme:textbox path="body" code="message.body"/>
    <acme:textbox path="attachments" code="message.attachments"/>




    <br/>


    <!---------------------------- BOTONES -------------------------->

    <input type="submit" name="save"
           value="<spring:message code="message.save" />"/>

    <jstl:if test="\$\{message1.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="message.delete" />"
               onclick="return confirm('<spring:message code="message.confirm.delete"/>')"/>&nbsp;
    </jstl:if>

    <input type="button" name="cancel"
           value="<spring:message code="message.cancel" />"
           onclick="javascript: window.location.replace('message/list.do')"/>

</form:form>