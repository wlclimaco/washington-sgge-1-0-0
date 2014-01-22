<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<script type="text/javascript">

	/**
	 * @author QATEmployee
	 */
	$(document).ready(function() {

		//Receives preloaded data
		var	oTagResponse = <c:choose>
							<c:when test="${empty tagResponse}">
								null
							</c:when>
							<c:otherwise>
								${tagResponse}
							</c:otherwise>
						</c:choose>;

		var	oGroupResponse = <c:choose>
						<c:when test="${empty groupResponse}">
							null
						</c:when>
						<c:otherwise>
							${groupResponse}
						</c:otherwise>
					</c:choose>;

		var	oScheduleResponse = <c:choose>
					<c:when test="${empty scheduleResponse}">
						null
					</c:when>
					<c:otherwise>
						${scheduleResponse}
					</c:otherwise>
				</c:choose>;

		var	oLightDetailResponse = <c:choose>
					<c:when test="${empty response}">
						null
					</c:when>
					<c:otherwise>
						${response}
					</c:otherwise>
				</c:choose>;

		var	oResponse = {
				tagResponse   : oTagResponse,
				groupResponse :  oGroupResponse,
				lightDetailResponse : oLightDetailResponse,
				scheduleResponse : oScheduleResponse
		};

		$.moduleController.init(sensus.pages.smartpoint.detail.oDeviceTypeParameters, "#about-device", oResponse);
	});

</script>
