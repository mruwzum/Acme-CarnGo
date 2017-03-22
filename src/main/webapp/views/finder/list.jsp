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
			<a href="finder/create.do"> <spring:message
					code="finder.create" />
			</a>
		</H5>
	</div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="finders" requestURI="${requestURI}" id="row">


	<!-- Attributes -->

	<security:authorize access="permitAll">
		<display:column>
			<a href="finder/edit.do?finderId=${row.id}"> <spring:message
					code="finder.edit" />
			</a>
		</display:column>
	</security:authorize>
	
			<spring:message code="finder.destinationcity" var="destinationCity" />
			<display:column property="destinationCity" title="${destinationCity}" sortable="true" />
			<spring:message code="finder.minimumpay" var="minimumPay" />
			<display:column property="minimumPay" title="${minimumPay}" sortable="true" />
			<spring:message code="finder.maximumpay" var="maximumPay" />
			<display:column property="maximumPay" title="${maximumPay}" sortable="true" />
			<spring:message code="finder.keyword" var="keyword" />
			<display:column property="keyword" title="${keyword}" sortable="true" />

</display:table>