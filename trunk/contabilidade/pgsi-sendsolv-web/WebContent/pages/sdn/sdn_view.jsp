<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
	<input type="hidden" name="sdnHistoryId" id="sdnHistoryId" value="0" />
	<nav class="secondary">
		<a class="alist" href="sdn"> <span><s:message
					code="commons.pages.compliance" /></span>
		</a><span class="icon-nav icon-angle-right"></span> <a class="alist"
			href="sdn"> <span><s:message
					code="pages.sdn.dashboard.match.SDN" /></span>
		</a> <span class="icon-nav icon-angle-right"></span> <span id="sdn-name"></span>
	</nav>

	<h2 id="sdn-title"></h2>
	<div id="tabs-sdn">
		<div id="person_info">
			<div id="status-template">
				<div class="newline label"></div>
				<div class="value" id="status-id"></div>
				<div class="label"><s:message code='pages.member.view.status' text='default text' /></div>
				<div class="value status" id="status" ></div>
				<a class="security high hide" id="status-risk" href="javascript:;"><span class="icon-security icon-shield84"></span><s:message code='commons.pages.sdn.positive' text='default text' /></a>
				<a class="security medium hide" id="medium-risk" href="javascript:;"><span class="icon-security icon-flag"></span><s:message code='commons.pages.mediumrisk' text='default text' /></a>
				<a class="security high hide" id="high-risk" href="javascript:;"><span class="icon-security icon-flag"></span><s:message code='commons.pages.highrisk' text='default text' /></a>
				<a class="security high hide" id="status-pep" href="javascript:;"><span class="icon-security icon-torso"></span><s:message code='commons.pages.pep' text='default text' /></a>
				<span class="divider">|</span>
				<a href="javascript:;" class="view"><span class=" alist icon-small-button active icon-nav icon-search-find"></span><s:message code='commons.pages.view' text='default text' /></a>
				<a href="javascript:;" class="active"><span class="alist icon-small-button active icon-nav icon-check-mark"></span><s:message code='pages.view.activate' text='default text' /></a>
				<a href="javascript:;" class="deactivate"><span class="alist icon-small-button deactivate icon-nav icon-minus-circle"></span><s:message code='pages.view.deactivate' text='default text' /></a>
			</div>
			<hr>
			<jsp:include page="../sdn_detail/sdn_detail_main.jsp" flush="true" />
			<hr>
				<h3><s:message code='pages.sdn.table.status.history' text='default text' /></h3>
				<div class="col-all sdn-history">
					<div class="content list">
						<div class="data">
							<div id="data_list_wrapper" class="dataTables_wrapper no-footer">
								<div class="list_header"></div>
								<div>
									<table id="data_list" class="dataTable no-footer" role="grid">
										<thead>
											<tr role="row">
											<th class="status-sdn sorting" tabindex="0" aria-controls="data_list" rowspan="1" colspan="1" style="width: 130px;" aria-label="Status: activate to sort column ascending">Status</th>
											<th class="sdn-date   sorting" tabindex="0" aria-controls="data_list" rowspan="1" colspan="1" style="width: 98px;"  aria-label="Date: activate to sort column ascending">Date</th>
											<th class="sdn-author sorting" tabindex="0" aria-controls="data_list" rowspan="1" colspan="1" style="width: 172px;" aria-label="By: activate to sort column ascending">By</th>
											<th class="sdn-reason sorting" tabindex="0" aria-controls="data_list" rowspan="1" colspan="1" style="width: 579px;" aria-label="Reason: activate to sort column ascending">Reason</th>
											</tr>
										</thead>
										<tbody>

										</tbody>
									</table>
								</div>
								<div class="list_footer"></div>
							</div>
						</div>
						</div>
				</div>
			<hr>
			<h3><s:message code='pages.sdn.table.status.update' text='default text' /></h3>
			<br>
			<form id="sdn-form" method="post" action="#">
				<div class="col1 first">
					<label class="first"><s:message code='commons.pages.sdn.status' text='default text' /></label>
					<div><select id="field-sdn-status" class="width-medium required" placeholder="*" name="field-sdn-status">
						<option></option>
					</select>
					</div>
					<label class="first"><s:message code='pages.sdn.view.reason' text='default text' /></label>
					<div><textarea id="field-sdn-note" class="width-longest required" placeholder="*" name="field-sdn-note" ></textarea></div>
				</div>
				<div id="batches" class="col1 first" style="padding-top: 16px;"></div>
				<div class="col1 first">
					<input id="reset-button" class="btn first spacer ui-button ui-widget ui-state-default ui-corner-all" type="reset" value="Reset" role="button">
					<input id="save-button" class="btn spacer ui-button ui-widget ui-state-default ui-corner-all" type="submit" value="Update SDN Status" role="button">
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="../../scripts/pages/sdn_detail/sdn_detail_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/sdn/sdn_view_main.js.jsp" flush="true" />
	<jsp:include page="../../scripts/pages/sdn/sdn_view_init.js.jsp" flush="true" />
</sec:authorize>