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

<security:authorize access="permitAll">
    <div>
        <H5>
            <a href="offer/create.do"> <spring:message
                    code="offer.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="offers" requestURI="${requestURI}" id="row">

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="offer/edit.do?offerId=${row.id}"> <spring:message
                    code="offer.edit"/></a>
        </display:column>
    </security:authorize>

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


    <security:authorize access="isAuthenticated()">
        <display:column>
            <a href="offer/view.do?offerId=${row.id}"> <spring:message
                    code="request.view"/></a>
        </display:column>
    </security:authorize>


    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="offer/ban.do?offerId=${row.id}"> <spring:message
                    code="offer.ban"/></a>
        </display:column>
    </security:authorize>




    <security:authorize access="hasRole('CUSTOMER')">
        <display:column>
            <a href="offer/apply.do?offerId=${row.id}"> <spring:message
                    code="request.apply"/></a>
        </display:column>
    </security:authorize>

    <%--<security:authorize access="isAuthenticated()">--%>
        <%--<display:column>--%>
            <%--<a href="offer/delete.do?offerId=${row.id}"> <spring:message--%>
                    <%--code="offer.delete"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
</display:table>