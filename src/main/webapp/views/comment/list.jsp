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


<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="comments" requestURI="${requestURI}" id="row">

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="comment/ban.do?commentId=${row.id}"> <spring:message
                    code="comment.ban"/></a>
        </display:column>
    </security:authorize>

    <!-- Attributes -->

    <spring:message code="comment.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="comment.text" var="text"/>
    <display:column property="text" title="${text}" sortable="true"/>
    <spring:message code="comment.postedMoment" var="postedMoment"/>
    <display:column property="postedMoment" title="${postedMoment}" sortable="true"/>
    <spring:message code="comment.stars" var="numberOfStars"/>
    <display:column property="numberOfStars" title="${numberOfStars}" sortable="true"/>
    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <spring:message code="comment.banned" var="banned"/>
            <display:column property="banned" title="${banned}" sortable="true"/>
        </display:column>
    </security:authorize>


</display:table>