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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="actor/edit.do" modelAttribute="actor">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="email" />
	<form:hidden path="phone" />
	<form:hidden path="userAccount" />

	<acme:textbox path="name" code="actor.name"/>
	<br />
	<acme:textbox path="surname" code="actor.surname"/>
	<br />

	<!---------------------------- BOTONES -------------------------->
	<acme:submit name="save" code="actor.save"/>

	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: window.location.replace('/profile.do')" />

</form:form>