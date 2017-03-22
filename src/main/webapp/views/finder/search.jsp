<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 22/11/16
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="acme" uri="http://www.springframework.org/tags/form" %>

<form:form action="finder/search.do" modelAttribute="finder">

    <form:hidden path="id"/>
    <form:hidden path="version"/>

    <acme:textarea path="destinationCity"/>

    <acme:textarea path="minimumPay"/>

    <acme:textarea path="maximumPay"/>

    <acme:textarea path="keyword"/>

        <spring:url value="/finder/search.do" var="url" />







</form:form>