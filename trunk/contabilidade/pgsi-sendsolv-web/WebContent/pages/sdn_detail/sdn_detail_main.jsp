
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec'
	uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN')">
	<div id="detail-section" class="row sdn-detail" style="display: none">
		<h3>
			<s:message code='pages.sdn.view.sdnDetail' text='default text' />
		</h3>
		<table class="sdn-detail">
			<thead>
				<tr>
					<th class="section">&nbsp;</th>
					<th class="sdn"><s:message
							code='pages.sdn.view.detail.sdnRecord'></s:message></th>
					<th class="pgsi"><s:message
							code='pages.sdn.view.detail.pgsiRecord'></s:message></th>
					<th class="pgsi"><s:message
							code='pages.sdn.view.detail.matchPercent'></s:message></th>
				</tr>
			</thead>
			<tbody>
				<tr class="spacer"></tr>
				<tr class="name first last">
					<td class="section" colspan=""><s:message
							code='pages.sdn.view.match.name'></s:message></td>
					<td class="sdn"><div class="col person" id="primaryname">
							<div class="header">
								<s:message code='pages.sdn.view.detail.primaryname'></s:message>
							</div>
							<ul>
							</ul>
						</div>
						<div class="col  person" id="strongaka">
							<div class="header">
								<s:message code='pages.sdn.view.detail.strongaka'></s:message>
							</div>
							<ul>
							</ul>
						</div>
						<div class="col  person" id="weakaka">
							<div class="header">
								<s:message code='pages.sdn.view.detail.weakaka'></s:message>
							</div>
							<ul>
							</ul>
						</div>
						<div class="col" id="name-match">
							<div class="header">
								<s:message code='pages.sdn.view.detail.match'></s:message>
							</div>
							<ul>
							</ul>
						</div></td>
					<td class="pgsi"><div class="col">
							<div class="header">
								<s:message code='pages.sdn.view.match.name'></s:message>
							</div>
							<span id="pgsi-name"></span>
						</div>
						<div class="col">
							<div class="header">
								<s:message code='pages.sdn.view.detail.match'></s:message>
							</div>
							<span class="match" id="pgsi-match-name"></span>
						</div></td>
					<td class="match"><span class="matchpercent"></span><a
						href="#" id="link-name-match" title="View Name Matching Details"><span
							class="icon-nav icon-search-find"></span></a></td>
				</tr>
				<tr class="spacer"></tr>
				<tr class="address first last">
					<td class="section" colspan=""><s:message
							code='pages.sdn.view.match.address'></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="city matchpercent"></span><span
						class="divide">/</span><span class="country matchpercent"></span></td>
				</tr>
				<tr class="spacer person"></tr>
				<tr class="iddoc person first last">
					<td class="section" colspan=""><s:message
							code='pages.sdn.view.match.iddoc'></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="matchpercent"></span></td>
				</tr>
				<tr class="spacer person"></tr>
				<tr class="pob person first last">
					<td class="section" colspan=""><s:message
							code="pages.sdn.view.match.birthplace" htmlEscape="false"></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="matchpercent"></span></td>
				</tr>
				<tr class="spacer person"></tr>
				<tr class="yob first last">
					<td class="section"><s:message
							code="pages.sdn.view.match.birthdate"></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="matchpercent"></span></td>
				</tr>
				<!--  WAS WORKING ON THIS LAST -->
				<tr class="spacer person"></tr>
				<tr class="citizen first last">
					<td class="section"><s:message
							code="pages.sdn.view.match.citizen"></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="matchpercent"></span></td>
				</tr>
				<tr class="spacer person"></tr>
				<tr class="nationality first last">
					<td class="section"><s:message
							code="pages.sdn.view.match.nationality"></s:message></td>
					<td class="sdn"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="pgsi"><span class="empty"><s:message
								code="pages.sdn.view.detail.nodata"></s:message></span></td>
					<td class="match"><span class="matchpercent"></span></td>
				</tr>
				<tr class="spacer person"></tr>
				<tr class="other first last">
					<td class="section">Other Details</td>
					<td class="sdn"></td>
					<td class="pgsi">&nbsp;</td>
					<td class="match"></td>
				</tr>

			</tbody>
		</table>
	</div>

	<%-- This is the match detail table that needs to pop up when the name match details are clicked --%>
	<div id="name-match-detail" style="display: none" title="Match Details - TODO: i18n">
		<table>
			<thead>
				<tr>
					<th><s:message code="pages.sdn.view.detail.sdnvalue"></s:message></th>
					<th><s:message code="pages.sdn.view.detail.pgsivalue"></s:message></th>
					<th><s:message code="pages.sdn.view.detail.matchPercent"></s:message></th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
</sec:authorize>