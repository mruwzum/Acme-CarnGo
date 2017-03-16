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
               name="applications" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="application.requestStatus" var="requestStatus"/>
    <display:column property="requestStatus" title="${requestStatus}" sortable="true"/>
    <spring:message code="application.owner" var="owner"/>
    <display:column property="owner" title="${owner}" sortable="true"/>



</display:table>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="applicationsP" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="application.requestStatus" var="requestStatus"/>
    <display:column property="requestStatus" title="${requestStatus}" sortable="true"/>
    <spring:message code="application.owner" var="owner"/>
    <display:column property="owner" title="${owner}" sortable="true"/>


    <security:authorize access="hasRole('CUSTOMER')">
        <display:column>
            <a href="customer/app/accept.do?applicationId=${row.id}"> <spring:message
                    code="application.Accept"/>
            </a>
        </display:column>
    </security:authorize>

    <security:authorize access="hasRole('CUSTOMER')">
        <display:column>
            <a href="customer/app/deny.do?applicationId=${row.id}"> <spring:message
                    code="application.Deny"/>
            </a>
        </display:column>
    </security:authorize>


</display:table>