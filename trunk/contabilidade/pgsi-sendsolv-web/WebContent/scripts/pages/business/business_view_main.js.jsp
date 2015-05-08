<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<sec:authorize access="hasAnyRole('ROLE_DOMAIN ADMIN', 'ROLE_ADMIN', 'ROLE_CSR')">

<script type="text/javascript">

<c:choose>
<c:when test="${empty response}">
	var oPreLoadResponse = null;
</c:when>
<c:otherwise>
	var oPreLoadResponse = ${response};
</c:otherwise>
</c:choose>

pgsi.pages.business.dialog ={

	fnLoadDialog : function (nBusinessType)
	{
		if (nBusinessType === 1) {
			pgsi.version.versionBusiness = oPreLoadResponse.organizationList[0].version;
			pgsi.pages.organization.form.fnInitForm();
			pgsi.pages.organization.form.fnFillOrganization(oPreLoadResponse);
			pgsi.pages.organization.form.displayOrganizationFields();
			pgsi.pages.organization.form.setFieldSizes();
		}

		else {
			pgsi.version.versionBusiness = oPreLoadResponse.locationList[0].version;
			pgsi.pages.location.form.fnInitForm();
			pgsi.pages.location.form.fnFillLocation(oPreLoadResponse);
			pgsi.pages.location.form.displayLocationFields();
			pgsi.pages.location.form.setFieldSizes();

		}
	},
	fnFetchBusiness : function (sUrl,oRequest,fnCallBack){

		$.pgsi.ajax.post({
			 sUrl       : sUrl,
			 oRequest   : oRequest,
			 fnCallback : fnCallBack
		});

	}

}

</script>

</sec:authorize>