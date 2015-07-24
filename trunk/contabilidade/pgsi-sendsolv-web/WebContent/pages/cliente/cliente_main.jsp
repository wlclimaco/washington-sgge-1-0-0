<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>


    <div id="wrapper">

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




<jsp:include page="../../scripts/pages/pessoa/pessoa_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/cliente/cliente_init.js.jsp" flush="true" />

