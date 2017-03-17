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


<form:form action="comment2/edit.do" modelAttribute="comment">

    <form:hidden path="id"/>
    <form:hidden path="version"/>
    <form:hidden path="postedMoment"/>
    <form:hidden path="objectiveId"/>
    <form:hidden path="owner"/>



    <acme:textbox path="title" code="comment.title"/>
    <acme:textbox path="text" code="comment.text"/>
    <acme:textbox path="numberOfStars" code="comment.stars"/>


    <br/>


    <!---------------------------- BOTONES -------------------------->

    <input type="submit" name="save"
           value="<spring:message code="comment.save" />"/>

    <jstl:if test="\$\{comment.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="comment.delete" />"
               onclick="return confirm('<spring:message code="comment.confirm.delete"/>')"/>&nbsp;
    </jstl:if>

    <input type="button" name="cancel"
           value="<spring:message code="comment.cancel" />"
           onclick="window.location.replace('comment/list.do')"/>

</form:form>