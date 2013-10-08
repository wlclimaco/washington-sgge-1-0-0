<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR', 'ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<%-- START Footer --%>
	<div id="ft" class="yui-g clearfix">
			<section id="copyright" class="container clearfix headerFooter">
			  <div class="span12">
				<p>© Faceboock 2012. All Rights Reserved. </p>
				<ul class="social">
				  <li class="facebook"><a href="#">facebook</a></li>
				  <li class="twitter"><a href="#">twitter</a></li>
				  <li class="skype"><a href="#">Skype</a></li>
				  <li class="googleplus"><a href="#">googleplus</a></li>
				  <li class="flickr"><a href="#">flickr</a></li>
				  <li class="youtube"><a href="#">youtube</a></li>
				  <li class="rss"><a href="#">rss</a></li>
				  <li class="email"><a href="#">flickr</a></li>
				</ul>
			  </div>
			</section>
		</div>
		<%-- END Footer --%>

</sec:authorize>