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


<form:form action="administrator/edit.do" modelAttribute="administrator">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="comments"/>
    <form:hidden path="sendMessages"/>
    <form:hidden path="recivedMessages"/>
    <form:hidden path="userAccount"/>
    <form:hidden path="offers"/>
    <form:hidden path="requests"/>


    <acme:textbox path="name" code="customer.name"/>
    <acme:textbox path="surname" code="customer.surname"/>
    <acme:textbox path="email" code="customer.email"/>
    <acme:textbox path="phone" code="customer.phone"/>


    <br/>


    <!---------------------------- BOTONES -------------------------->

    <input type="submit" name="save"
           value="<spring:message code="customer.save" />"/>

    <jstl:if test="\$\{customer.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="customer.delete" />"
               onclick="return confirm('<spring:message code="customer.confirm.delete"/>')"/>&nbsp;
    </jstl:if>

    <input type="button" name="cancel"
           value="<spring:message code="customer.cancel" />"
           onclick="javascript: window.location.replace('customer/list.do')"/>

</form:form>