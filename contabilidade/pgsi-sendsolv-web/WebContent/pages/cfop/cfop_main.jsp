<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>

<div id="wrapper">

<nav class="secondary">
	<a class="alist" href="organization" title='<s:message code="commons.pages.customers" text="default text" />'>
		<span><s:message code="commons.pages.customers" text="default text" /></span>
	</a>
	<span class="icon-nav icon-angle-right add-business">
		<span>
			<s:message code="commons.pages.organizationfind" text="default text" />
		</span>
	</span>
	<span id="company-name"></span>
</nav>

<h2 class="list"><s:message code="commons.pages.organizationfind" text="default text" /></h2>

<a href="organization/add" class="add-business alist" title='<s:message code="commons.pages.organizationadd" text="default text" />'>
	<span class="icon-nav icon-plus icon-small-button"></span>
	<s:message code="commons.pages.organizationadd" text="default text" />
</a>
            <div class="navbar-default sidebar" role="navigation">
                <div class="filter">
					<form id="filterForm"></form>
				</div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">

                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="table-responsive table-bordered">
                                <table class="table" id="data_list">

                                </table>
                            </div>

                        </div>

                    </div>

                </div>

            </div>
            <!-- /.row -->
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>

<jsp:include page="../../scripts/pages/cfop/cfop_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/cfop/cfop_actions.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/cfop/cfop_init.js.jsp" flush="true" />

