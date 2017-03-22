<%--
  Created by IntelliJ IDEA.
  User: mruwzum
  Date: 19/12/16
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<br>
<spring:message code="dashboard.q1" var="q1b"/>
<jstl:out value="${q1b}"/>:
<jstl:out value="${q1}"/>
<br/>
<br>
<spring:message code="dashboard.q2" var="q2b"/>
<jstl:out value="${q2b}"/>:
<jstl:out value="${q2}"/>
<br>
<br/>
<spring:message code="dashboard.q3" var="q3b"/>
<jstl:out value="${q3b}"/>:
<jstl:out value="${q3}"/>
<br>
<br/>
<spring:message code="dashboard.q4" var="q4b"/>
<jstl:out value="${q4b}"/>:
<jstl:out value="${q4}"/>
<br>
<br/>


<spring:message code="dashboard.q5" var="q5b"/>
<h3><jstl:out value="${q5b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q5" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>



<spring:message code="dashboard.q6" var="q6b"/>
<h3><jstl:out value="${q6b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q6" requestURI="/admin/dashboard.do" id="row">
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

</display:table>
<br>
<br/>


<spring:message code="dashboard.q7" var="q7b"/>
<h3><jstl:out value="${q7b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q7" requestURI="/admin/dashboard.do" id="row">
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

</display:table>
<br>
<br/>



<spring:message code="dashboard.q8" var="q8b"/>
<h3><jstl:out value="${q8b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q8" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>
<spring:message code="dashboard.q9" var="q9b"/>
<h3><jstl:out value="${q9b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q9" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>
<spring:message code="dashboard.q10" var="q10b"/>
<h3><jstl:out value="${q10b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q10" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>



<spring:message code="dashboard.q11" var="q11Persb"/>
<jstl:out value="${q11Persb}"/>:
<jstl:out value="${q11Pers}"/>
<spring:message code="dashboard.q11" var="q11Ratb"/>
<jstl:out value="${q11Ratb}"/>:
<jstl:out value="${q11Rat}"/>
<br>
<br/>
<spring:message code="dashboard.q12" var="q12Persb"/>
<jstl:out value="${q12Persb}"/>:
<jstl:out value="${q12Pers}"/>
<spring:message code="dashboard.q12" var="q12Ratb"/>
<jstl:out value="${q12Ratb}"/>:
<jstl:out value="${q12Rat}"/>
<br>
<br/>



<spring:message code="dashboard.q13" var="q13b"/>
<jstl:out value="${q13b}"/>:
<jstl:out value="${q13}"/>
<br>
<br/>
<spring:message code="dashboard.q14" var="q14b"/>
<jstl:out value="${q14b}"/>:
<jstl:out value="${q14}"/>
<br>
<br/>
<spring:message code="dashboard.q15" var="q15b"/>
<jstl:out value="${q15b}"/>:
<jstl:out value="${q15}"/>
<br>
<br/>

<%--DASHBOARD B--%>


<spring:message code="dashboard.q16" var="q16b"/>
<jstl:out value="${q16b}"/>:
<jstl:out value="${q16}"/>
<br>
<br/>
<spring:message code="dashboard.q17" var="q17b"/>
<jstl:out value="${q17b}"/>:
<jstl:out value="${q17}"/>
<br>
<br/>
<spring:message code="dashboard.q18" var="q18b"/>
<jstl:out value="${q18b}"/>:
<jstl:out value="${q18}"/>
<br>
<br/>

<%--CollectionInteger--%>
<spring:message code="dashboard.q19" var="q19b"/>
<jstl:out value="${q19b}"/>:
<jstl:out value="${q19}"/>
<br>
<br/>

<spring:message code="dashboard.q20" var="q20b"/>
<h3><jstl:out value="${q20b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q20" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>

<spring:message code="dashboard.q21" var="q21b"/>
<h3><jstl:out value="${q21b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q21" requestURI="/admin/dashboard.do" id="row">
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
</display:table>
<br>
<br/>

<spring:message code="dashboard.q22" var="q22b"/>
<h3><jstl:out value="${q22b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q22" requestURI="/admin/dashboard.do" id="row">
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

</display:table>
<br>
<br/>

<spring:message code="dashboard.q23" var="q23b"/>
<h3><jstl:out value="${q23b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q23" requestURI="/admin/dashboard.do" id="row">
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
    <spring:message code="lessor.creditcard" var="creditCard" />
    <display:column property="creditCard" title="${creditCard}" sortable="true" />
</display:table>
<br>
<br/>
<spring:message code="dashboard.q24" var="q24b"/>
<h3><jstl:out value="${q24b}"/></h3>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="q24" requestURI="/admin/dashboard.do" id="row">
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

</display:table>
<br>
<br/>

<%--DASHBOARD A --%>

<spring:message code="dashboard.q25" var="q25b"/>
<jstl:out value="${q25b}"/>:
<jstl:out value="${q25}"/>
<br>
<br/>
<spring:message code="dashboard.q26" var="q26b"/>
<jstl:out value="${q26b}"/>:
<jstl:out value="${q26}"/>
<br>
<br/>
<spring:message code="dashboard.q27" var="q27b"/>
<jstl:out value="${q27b}"/>:
<jstl:out value="${q27}"/>
<br>
<br/>
<spring:message code="dashboard.q28" var="q28b"/>
<jstl:out value="${q28b}"/>:
<jstl:out value="${q28}"/>
<br>
<br/>
<spring:message code="dashboard.q29" var="q29b"/>
<jstl:out value="${q29b}"/>:
<jstl:out value="${q29}"/>
<br>
<br/>
<spring:message code="dashboard.q30" var="q30b"/>
<jstl:out value="${q30b}"/>
<jstl:out value="${q30}"/>
<br>
<br/>
<spring:message code="dashboard.q31" var="q31b"/>
<jstl:out value="${q31b}"/>:
<jstl:out value="${q31}"/>
<br>
<br/>
<spring:message code="dashboard.q32" var="q32b"/>
<jstl:out value="${q32b}"/>:
<jstl:out value="${q32}"/>
<br>
<br/>