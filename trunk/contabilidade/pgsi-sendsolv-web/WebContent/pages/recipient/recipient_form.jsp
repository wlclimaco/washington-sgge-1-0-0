<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>

<div class="person-content">
	<form id="recipient-form" method="post" action="#">
	<input type="hidden" name="locationId" id="location-id" value="0" />
	<input type="hidden" name="recipientId" id="recipient-id" value="0" />
	<input type="hidden" name="memberId" id="member-id" value="0" />
	<input type="hidden" name="documentId" id="document-id" value="" />
	<div class="section-1">
		<div id="infoMember">
			<label class="first"><s:message code="pages.business.view.member" text="default text" /></label>
			<label class="parent nameLabel"></label>
			<a href="" title=""	class="form-link memberName lower edit_link alist">
				<span class="icon-nav icon-search-find"></span>
				<s:message code="commons.pages.view" text="default text" />
			</a>
		</div>

		<hr style="margin-top: 0;">
		<div>
			<label class="first" for="prefix"><s:message code="pages.contacts.form.label.prefix" text="default text" /></label>
			<select name="prefix" id="prefix" style="display: none;">
				<option></option>
			</select>
		</div>

			<div>
			<label class="first"><s:message code="pages.member.form.label.firstName" text="default text" /></label>
			<input id="firstname" name="firstname" class="required width-long" placeholder="*" type="text" minlength="1" maxlength="40" >
		</div>
		<div>
			<label><s:message code="pages.member.form.label.middleName" text="default text" /></label>
			<input id="middlename" class="width-medium" type="text" maxlength="40">
		</div>

		<div>
			<label class="first"><s:message code="pages.member.form.label.lastName" text="default text" /></label>
			<input id="lastname" name="lastname" class="required width-long" placeholder="*" type="text" minlength="1" maxlength="40">
		</div>

		<div>
			<label for="motherMaidenName"><s:message code="pages.member.form.label.mothername" text="Mother's Maiden Name" /></label>
			<input id="motherMaidenName" class="width-long valueOthernames" minlength="1" maxlength="40"  type="text">
		</div>

		<div>
			<label class="first"><s:message code="pages.member.form.label.suffix" text="default text" /></label>
			<select id="suffix" class="width-shortest">
				<option></option>
			</select>
		</div>

		<jsp:include page="../person/other_names_main.jsp" flush="true" />

		<hr>

		<jsp:include page="../address/address_main.jsp" flush="true" />

		<hr>

		<jsp:include page="../phone/phone_main.jsp" flush="true" />

		<hr>

		<jsp:include page="../email/email_main.jsp" flush="true" />

		<hr>

		<div>
			<label class="first">
				<s:message code="pages.recipient.form.label.dateofBirth" text="default text" />
			</label>
			<input type="text" id="dob" name="dob" class="datepicker width-short">
			<hr>
		</div>

		<div id="section-risk">
			<label class="first">
				<s:message code="pages.recipient.form.label.risk" text="default text" />
			</label>
			<select id="risk" class="width-short">
				<option></option>
			</select>

			<label for="risknote">
				<s:message code="pages.recipient.form.label.riskNote" text="default text" />
			</label>

			<textarea id="risknote" class="width-longest" name="risknote"></textarea>
			<hr>
		</div>

		<div id="section-note">
			<label class="first" for="note">
				<s:message code="pages.recipient.form.label.otherNote" text="default text" />
			</label>

			<textarea id="note" style="width: 70%"></textarea>
			<hr>
		</div>

		<div class="buttons-form">
			<div>
				<input id="prev-button" value="<< Back" type="button">
				<input type="button" id="cancel" value="Cancel">
				<input class="hide" id="save-button" value='<s:message code="commons.pages.save" text="default text" />' type="submit">
				<input id="next-button" value="Add Transfer >>" type="submit">
			</div>
		</div>
	</div>
</form>
</div>

<jsp:include page="../../scripts/pages/address/address_init.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_create_main.js.jsp" flush="true" />
<jsp:include page="../../scripts/pages/recipient/recipient_create_init.js.jsp" flush="true" />