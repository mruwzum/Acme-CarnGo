<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
          uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="request.title" var="title1"/>
<h3><jstl:out value="${title1}"/></h3>
<jstl:out value="${title}"/>

<spring:message code="request.description" var="description1"/>
<h3><jstl:out value="${description1}"/></h3>
<jstl:out value="${description}"/>

<spring:message code="request.originAddress" var="originAddress1"/>
<h3><jstl:out value="${originAddress1}"/></h3>
<jstl:out value="${originAddress}"/>

<spring:message code="request.destinationAddress" var="destinationAddress1"/>
<h3><jstl:out value="${destinationAddress1}"/></h3>
<jstl:out value="${destinationAddress}"/>

<spring:message code="request.tripDate" var="tripDate1"/>
<h3><jstl:out value="${tripDate1}"/></h3>
<jstl:out value="${tripDate}"/>
<spring:message code="offer.banned" var="banned1"/>
<h3><jstl:out value="${banned1}"/></h3>
<jstl:out value="${banned}"/>

<spring:message code="request.comments" var="comments"/>
<h3><jstl:out value="${comments}"/></h3>

<!-- Listing grid comments -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="comments" requestURI="${requestURI}" id="row">


    <!-- Attributes -->


    <spring:message code="comment.title" var="title"/>
    <display:column property="title" title="${title}" sortable="true"/>
    <spring:message code="comment.postedMoment" var="postedMoment"/>
    <display:column property="postedMoment" title="${postedMoment}" sortable="true"/>
    <spring:message code="comment.text" var="text"/>
    <display:column property="text" title="${text}" sortable="true"/>
    <spring:message code="comment.stars" var="numberOfStars"/>
    <display:column property="numberOfStars" title="${numberOfStars}" sortable="true"/>

</display:table>
<security:authorize access="hasRole('CUSTOMER')">
    <div>
        <H5>
            <a href="comment3/createReqCom.do?id=${id}"> <spring:message
                    code="comment.create"/>
            </a>
        </H5>
    </div>
</security:authorize>
<security:authorize access="hasRole('ADMINISTRATOR')">
    <div>
        <H5>
            <a href="comment3/createReqCom.do?id=${id}"> <spring:message
                    code="comment.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<spring:message code="request.applications" var="applications"/>
<h3><jstl:out value="${applications}"/></h3>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="applications" requestURI="${requestURI}" id="row">


    <!-- Attributes -->


    <spring:message code="application.requestStatus" var="requestStatus" />
    <display:column property="requestStatus" title="${requestStatus}" sortable="true" />
    <spring:message code="application.owner" var="owner"/>
    <display:column property="owner" title="${owner}" sortable="owner"/>


</display:table>