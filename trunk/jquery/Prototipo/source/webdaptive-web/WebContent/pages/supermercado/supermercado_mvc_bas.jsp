<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%
	response.setContentType("text/html; charset=iso-8859-1");
	response.setHeader("Cache-Control", "no-cache, private, must-revalidate, max-stale=0, post-check=0, pre-check=0 no-store"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" href="../styles/common_grid.css" rel="stylesheet" />
<link type="text/css" href="../styles/slick.pager.css" rel="stylesheet" />
</head>
<body>
	<input type="button" name="button" id="insertButon" value="Inserir Novo Supermercado" />
	<div style="width:100%;height:100%;float:left;">
		<div class="grid-header" style="width:100%">
		    <span id="listsup" style="float:left; width: 16px; height: 16px; background-image:url('../images/text_list.png')" title="List Procedures"></span>
			<sec:authorize access="hasAnyRole('ROLE_DOMAIN USERS', 'ROLE_DOMAIN ADMINS')">
		    <span id="refreshsup" style="float:right" class="ui-icon ui-icon-refresh" title="Rebuild Procedures"></span>
			</sec:authorize>
		</div>
		<div id="supGrid" style="width:100%; height:95%;" class="wdgrid" ></div>
		<div id="pager" style="width:100%; height:3%;"></div>
	</div>

<!-- Add Tag Dialog -->
<div id="action-tag-dialog" class="action-dialog">
	<form id="add-tag-form" name="create-tag-form" method="post" >
    <!-- Messaging -->
    <div id="add-tag-messaging" class="messaging"><span class="message"></span></div>
        <fieldset class="two-line">
                <ul>
                    <li class="ui-widget">
                        <label for="tag-add-select">Select or create new Tag <span class="required">*</span></label> <br/>
                        <select id="tag-add-select" class="combobox">
                            <option value="" selected>Type to search...</option>
                                <option value="c">Beaverton</option>
                                <option value="c">Buckman </option>
                                <option value="c">Concordia</option>
                                <option value="c">Cully</option>
                                <option value="c">Downtown</option>
                                <option value="c">Eliot </option>
                                <option value="c">Forest Park </option>
                                <option value="c">Healy Heights </option>
                                <option value="c">Hillsdale </option>
                                <option value="c">I5 </option>
                                <option value="c">Irvington </option>
                                <option value="c">Kully </option>
                                <option value="c">Lloyd </option>
                                <option value="c">Mount Tabor </option>
                                <option value="c">Old Town </option>
                                <option value="c">Oregon Health & Science University</option>
                                <option value="c">Overlook </option>
                                <option value="c">PDX Airport </option>
                                <option value="c">PDX NE </option>
                                <option value="c">PDX NW </option>
                                <option value="c">PDX SE </option>
                                <option value="c">PDX SW </option>
                                <option value="c">Pearl District </option>
                                <option value="c">Reed College </option>
                                <option value="c">Richmoned </option>
                                <option value="c">TEMP: Fox </option>
                                <option value="c">TEST: Conservation-rural </option>
                                <option value="c">Southwest Hills </option>
                                <option value="c">University Park </option>
                                <option value="c">Woodstock</option>
                        </select>
                    </li>
                </ul>
   		 </fieldset>
	    <div class="highlight">Tag will be added to the 15 currently selected SmartPoints.</div>

      </form>
</div>
<script type="text/javascript" src="../commons/scripts/model/user_context.js"></script>
<script type="text/javascript" src="../commons/scripts/model/request_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/model/domain_objects.js"></script>
<script type="text/javascript" src="../commons/scripts/widget/slick.pager.js"></script>
<jsp:include page="../../commons/scripts/util/app_reuse_functions.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/supermercado/supermercado_mvc_bas_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/supermercado/supermercado_mvc_bas_init.js.jsp" flush="true"/>
</body>
</html>