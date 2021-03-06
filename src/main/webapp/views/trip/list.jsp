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
               name="trips" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="offer.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="offer.description" var="description"/>
    <display:column property="description" title="${description}" sortable="true"/>
    <spring:message code="offer.originAddress" var="originAddress"/>
    <display:column property="originAddress" title="${originAddress}" sortable="true"/>
    <spring:message code="offer.destinationAddress" var="destinationAddress"/>
    <display:column property="destinationAddress" title="${destinationAddress}" sortable="true"/>
    <spring:message code="offer.tripDate" var="tripDate"/>
    <display:column property="tripDate" title="${tripDate}" sortable="true"/>
    <spring:message code="offer.coordXValue" var="coordXValue"/>
    <display:column property="coordXValue" title="${coordXValue}" sortable="true"/>
    <spring:message code="offer.coordXL" var="coordXL"/>
    <display:column property="coordXL" title="${coordXL}" sortable="true"/>
    <spring:message code="offer.coordYValue" var="coordYValue"/>
    <display:column property="coordYValue" title="${coordYValue}" sortable="true"/>
    <spring:message code="offer.coordYL" var="coordYL"/>
    <display:column property="coordYL" title="${coordYL}" sortable="true"/>

</display:table>