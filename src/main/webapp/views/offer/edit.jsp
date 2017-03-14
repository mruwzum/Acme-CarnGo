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


<form:form action="offer/edit.do" modelAttribute="offer">

    <form:hidden path="id"/>
    <form:hidden path="banned"/>
    <form:hidden path="ownerO"/>
    <form:hidden path="applications"/>


    <acme:textbox path="title" code="offer.title"/>
    <br>
    <acme:textbox path="description" code="offer.description"/>
    <br>
    <acme:textbox path="originAddress" code="offer.originAddress"/>
    <br>
    <acme:textbox path="destinationAddress" code="offer.destinationAddress"/>
    <br>
    <acme:textbox path="tripDate" code="offer.tripDate"/>
    <br>
    <acme:textbox path="coordXValue" code="offer.coordXValue"/>
    <acme:textbox path="coordXL" code="offer.coordXL"/>
    <br>
    <acme:textbox path="coordYValue" code="offer.coordYValue"/>
    <acme:textbox path="coordYL" code="offer.coordYL"/>

    <br/>


    <!---------------------------- BOTONES -------------------------->

    <input type="submit" name="save"
           value="<spring:message code="offer.save" />"/>

    <jstl:if test="\$\{offer.id != 0}">
        <input type="submit" name="delete"
               value="<spring:message code="offer.delete" />"
               onclick="return confirm('<spring:message code="offer.confirm.delete"/>')"/>&nbsp;
    </jstl:if>

    <input type="button" name="cancel"
           value="<spring:message code="offer.cancel" />"
           onclick="javascript: window.location.replace('offer/list.do')"/>

</form:form>