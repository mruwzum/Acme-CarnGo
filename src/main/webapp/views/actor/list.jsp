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

<security:authorize access="permitAll">
	<div>
		<H5>
			<a href="actor/create.do"> <spring:message
					code="actor.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="actors" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="actor/edit.do?actorId=${row.id}"> <spring:message
					code="actor.edit" />
			</a>
		</display:column>
	</security:authorize>
	
			<spring:message code="actor.name" var="name" />
			<display:column property="name" title="${name}" sortable="true" />
			<spring:message code="actor.surname" var="surname" />
			<display:column property="surname" title="${surname}" sortable="true" />
			<spring:message code="actor.email" var="email" />
			<display:column property="email" title="${email}" sortable="true" />
			<spring:message code="actor.phone" var="phone" />
			<display:column property="phone" title="${phone}" sortable="true" />
			<spring:message code="actor.picture" var="picture" />
			<display:column property="picture" title="${picture}" sortable="true" />
			<spring:message code="actor.socialidentity" var="socialIdentity" />
			<display:column property="socialIdentity" title="${socialIdentity}" sortable="true" />

</display:table>