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
            <a href="message/create.do"> <spring:message
                    code="message.create"/>
            </a>
        </H5>
    </div>
</security:authorize>

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="recivedMessages" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="message.subject" var="subject"/>
    <display:column property="subject" title="${subject}" sortable="true"/>
    <spring:message code="message.body" var="body"/>
    <display:column property="body" title="${body}" sortable="true"/>
    <spring:message code="message.sentDate" var="sentDate"/>
    <display:column property="sentDate" title="${sentDate}" sortable="true"/>
    <spring:message code="message.sender" var="sender"/>
    <display:column property="sender" title="${sender}" sortable="true"/>
    <spring:message code="message.recipient" var="recipient"/>
    <display:column property="recipient" title="${recipient}" sortable="true"/>


    <%-- TODO Boton de replay aqui--%>


    <security:authorize access="isAuthenticated()">
        <display:column>
            <a href="message/delete.do?messageId=${row.id}"> <spring:message
                    code="message.delete"/>
            </a>
        </display:column>
    </security:authorize>
</display:table>


<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="sendMessages" requestURI="${requestURI}" id="row">


    <!-- Attributes -->

    <spring:message code="message.subject" var="subject"/>
    <display:column property="subject" title="${subject}" sortable="true"/>
    <spring:message code="message.body" var="body"/>
    <display:column property="body" title="${body}" sortable="true"/>
    <spring:message code="message.sentDate" var="sentDate"/>
    <display:column property="sentDate" title="${sentDate}" sortable="true"/>
    <spring:message code="message.sender" var="sender"/>
    <display:column property="sender" title="${sender}" sortable="true"/>
    <spring:message code="message.recipient" var="recipient"/>
    <display:column property="recipient" title="${recipient}" sortable="true"/>


    <%-- TODO El delete tiene que ser confirmado--%>



    <security:authorize access="isAuthenticated()">
        <display:column>
            <a href="message/delete.do?messageId=${row.id}"> <spring:message
                    code="message.delete"/>
            </a>
        </display:column>
    </security:authorize>
</display:table>