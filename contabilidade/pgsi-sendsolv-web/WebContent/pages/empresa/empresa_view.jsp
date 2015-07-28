<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>


    <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<ul class="breadcrumb">
				<li>
					<a href="#">Home</a> <span class="divider">/</span>
				</li>
				<li>
					<a href="#">Library</a> <span class="divider">/</span>
				</li>
				<li class="active">
					Data
				</li>
			</ul>
			<ul class="nav nav-pills">
				<li class="active">
					<a href="#">Home</a>
				</li>
				<li>
					<a href="#">Profile</a>
				</li>
				<li class="disabled">
					<a href="#">Messages</a>
				</li>
				<li class="dropdown pull-right">
					 <a href="#" data-toggle="dropdown" class="dropdown-toggle">Dropdown<strong class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li>
							<a href="#">Action</a>
						</li>
						<li>
							<a href="#">Another action</a>
						</li>
						<li>
							<a href="#">Something else here</a>
						</li>
						<li class="divider">
						</li>
						<li>
							<a href="#">Separated link</a>
						</li>
					</ul>
				</li>
			</ul>
			<div class="page-header">
				<h1>
					LayoutIt! <small>Interface Builder for Bootstrap</small>
				</h1>
			</div>
			<div class="row">
				<div class="col-md-4">
					<span class="label label-default">Label</span>
						<s:message code="pages.business.view.dbaname" text="default text" />
					</span><br>
					<span class="text organization hide" id="dba-field"></span><br>

					<span class="label label-default">Label</span>Nome/Fantasia</span>
					<span class="text" id="nome-field"></span><br>

					<span class="label label-default">Label</span>CNPJ</span>
					<span class="text" id="cnpj-field"></span><br>

					<span class="label label-default">Label</span>Inscricao Municipal</span>
					<span class="text" id="im-field"></span><br>

					<span class="label label-default">Label</span>Inscricao Estadual</span>
					<span class="text" id="IE-field"></span><br>
				</div>
				<div class="col-md-4">
					 <span class="label label-default">Label</span> <span class="label label-default">Label</span> <span class="label label-default">Label</span> <span class="label label-default">Label</span>
				</div>
				<div class="col-md-4">
					 <span class="label label-default">Label</span> <span class="label label-default">Label</span> <span class="label label-default">Label</span> <span class="label label-default">Label</span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-8">
				</div>
				<div class="col-md-4">
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../../scripts/pages/phone/phone_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/address/address_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_view_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/empresa/empresa_view_init.js.jsp" flush="true" />
