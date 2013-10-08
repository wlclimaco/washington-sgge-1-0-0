<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- START Header 2--%>
<div id="hd" class="yui-g">
	<%-- START Main application menu --%>

	<div class="yui-u first">
		<div id="sensus-menu">
			<div class="bd">
				<section id="header" class="container clearfix">
				  <div class="span12">
					<nav class="mainNav">
					  <ul>
						<li class="logo"><a class="page" href="index.html"><img src="thirdparty/jquery/Facebuk/img/logo.png" width="83" height="22" alt="Logo"></a></li>
						<li > <a class="page selected" href="index.html">Home</a> </li>
						<li> <a class="page" href="about.html">About</a> </li>
						<li> <a class="page" href="services.html">Services</a> </li>
						<li> <a class="page" href="portfolio.html">Work</a> </li>
						<li class="last "> <a class="page" href="contact.html">Contact</a> </li>
					  </ul>
					  <ul class="fright">
						<li class="d"> <a class="page" href="#">Like Us</a>
							<label for="groupNameCreate">Usuario<span class="required">*</span></label>
							<input type="hidden" name="id" id="group-id" tabindex="1" value="">
						</li>
					  </ul>
					</nav>
				  </div>
				</section>
			</div>
		</div>
	</div>
	<%-- END Main application menu --%>

</div>
<%-- END Page Content --%>

<div id="customize-filter" class="hide"></div>
<%-- END Main Document --%>