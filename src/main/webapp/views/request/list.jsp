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
            <a href="request/create.do"> <spring:message
                    code="request.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="requests" requestURI="${requestURI}" id="row">

    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="request/edit.do?requestId=${row.id}"> <spring:message
                    code="request.edit"/></a>
        </display:column>
    </security:authorize>

    <!-- Attributes -->

    <spring:message code="request.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="request.description" var="description"/>
    <display:column property="description" title="${description}" sortable="true"/>
    <spring:message code="request.originAddress" var="originAddress"/>
    <display:column property="originAddress" title="${originAddress}" sortable="true"/>
    <spring:message code="request.destinationAddress" var="destinationAddress"/>
    <display:column property="destinationAddress" title="${destinationAddress}" sortable="true"/>
    <spring:message code="request.tripDate" var="tripDate"/>
    <display:column property="tripDate" title="${tripDate}" sortable="true"/>
    <spring:message code="request.coordXValue" var="coordXValue"/>
    <display:column property="coordXValue" title="${coordXValue}" sortable="true"/>
    <spring:message code="request.coordXL" var="coordXL"/>
    <display:column property="coordXL" title="${coordXL}" sortable="true"/>
    <spring:message code="request.coordYValue" var="coordYValue"/>
    <display:column property="coordYValue" title="${coordYValue}" sortable="true"/>
    <spring:message code="request.coordYL" var="coordYL"/>
    <display:column property="coordYL" title="${coordYL}" sortable="true"/>


    <security:authorize access="isAuthenticated()">
        <display:column>
            <a href="request/view.do?requestId=${row.id}"> <spring:message
                    code="request.view"/></a>
        </display:column>
    </security:authorize>
<%--TODO check if its already banned or not --%>
    <security:authorize access="hasRole('ADMINISTRATOR')">
        <display:column>
            <a href="request/ban.do?requestId=${row.id}"> <spring:message
                    code="request.ban"/></a>
        </display:column>
    </security:authorize>





    <%--<security:authorize access="isAuthenticated()">--%>
        <%--<display:column>--%>
            <%--<a href="request/delete.do?requestId=${row.id}"> <spring:message--%>
                    <%--code="request.delete"/>--%>
            <%--</a>--%>
        <%--</display:column>--%>
    <%--</security:authorize>--%>
</display:table>