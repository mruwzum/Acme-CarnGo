<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Sample Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="banner/list.do"><spring:message code="master.page.banner.list" /></a></li>
					<li><a href="offer/listAll.do"><spring:message code="master.page.customer.list.offer" /></a></li>
					<li><a href="request/listAll.do"><spring:message code="master.page.customer.list.request" /></a></li>
				</ul>
			</li>
			<li><a href="customer/list.do"><spring:message code="master.page.customer.list.all" /></a></li>
			<li><a href="administrator/list.do"><spring:message code="master.page.administrator.list.all" /></a></li>
			<li><a href="comment/listAll.do"><spring:message code="master.page.administrator.list.allComments" /></a></li>
			<li><a href="admin/dashboard.do"><spring:message
					code="master.page.dashboard.do"/></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">


			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="offer/create.do"><spring:message code="master.page.customer.create.offer" /></a></li>
					<li><a href="request/create.do"><spring:message code="master.page.customer.create.request" /></a></li>
				</ul>
			</li>

			<li><a class="fNiv"><spring:message	code="master.page.trips" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="offer/listMy.do"><spring:message code="master.page.customer.list.my.offer" /></a></li>
					<li><a href="request/listMy.do"><spring:message code="master.page.customer.list.my.request" /></a></li>
				</ul>
			</li>

			<li><a href="offer/list.do"><spring:message code="master.page.customer.list.offer" /></a></li>
			<li><a href="request/list.do"><spring:message code="master.page.customer.list.request" /></a></li>
			<li><a href="customer/applications.do"><spring:message code="master.page.customer.list.applications" /></a></li>

			<li><a href="customer/list.do"><spring:message code="master.page.customer.list.all" /></a></li>
			<li><a href="offer/find.do"><spring:message code="master.page.customer.finder" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="customer/create.do"><spring:message code="master.page.register.customer"/></a>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>

			<li><a href="actor/myMessages.do"><spring:message code="master.page.actor.my.messages" /></a></li>

		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

