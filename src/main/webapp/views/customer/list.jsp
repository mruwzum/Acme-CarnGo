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
               name="customers" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="customer.name" var="name"/>
    <display:column property="name" title="${name}" sortable="true"/>
    <spring:message code="customer.surname" var="surname"/>
    <display:column property="surname" title="${surname}" sortable="true"/>
    <spring:message code="customer.email" var="email"/>
    <display:column property="email" title="${email}" sortable="true"/>
    <spring:message code="customer.phone" var="phone"/>
    <display:column property="phone" title="${phone}" sortable="true"/>



    <security:authorize access="hasAnyRole('CUSTOMER','ADMINISTRATOR')">
        <display:column>
            <a href="customer/view.do?customerId=${row.id}"> <spring:message
                    code="customer.profile"/>
            </a>
        </display:column>
    </security:authorize>
</display:table>