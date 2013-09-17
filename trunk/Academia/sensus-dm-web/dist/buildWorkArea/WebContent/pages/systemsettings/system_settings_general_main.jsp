<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<sec:authorize access="hasAnyRole('ROLE_EPM_SERVICE_ELECTRIC', 'ROLE_EPM_SERVICE_WATER', 'ROLE_EPM_SERVICE_GAS') and 
	hasAnyRole('ROLE_EPM_ADMIN', 'ROLE_EPM_SYSTEM_OPERATOR') and !hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER')">

	<div class="settings-context">
		<fieldset>
			<ul>
				<li>
					<label><spring:message code="profile.page.languagepreference" />: </label>
					<select id="language" name="LANGUAGE">
						<option value="en_US">
							<spring:message code="profile.page.englishEN" />
						</option>
						<option value="pt_BR">
							<spring:message code="profile.page.portuguesePT" />
						</option>
						<option value="es_MX">
							<spring:message code="profile.page.spanishES" />
						</option>
					</select>
				</li>

				<li class="chzn-row select-group">
					<label><spring:message code="profile.page.timezone" />: </label>

					<select id="timezone" name="TIME_ZONE" class="chzn-select" data-placeholder="<spring:message code="commons.pages.selectTimeZone" />">
						<optgroup label="US">
							<option value="US/Alaska">Alaska</option>
							<option value="US/Aleutian">Aleutian</option>
							<option value="US/Arizona">Arizona</option>
							<option value="CST">Central</option>
							<option value="US/East-Indiana">East Indiana</option>
							<option value="US/Eastern">Eastern</option>
							<option value="US/Hawaii">Hawaii</option>
							<option value="US/Indiana-Starke">Indiana Starke</option>
							<option value="US/Michigan">Michigan</option>
							<option value="MST">Mountain</option>
							<option value="SystemV/PST8">Pacific</option>
							<option value="US/Pacific-New">Pacific New</option>
							<option value="Pacific/Samoa">Samoa</option>
						</optgroup>
						<optgroup label="Canada">
							<option value="America/Toronto">Atlantic</option>
							<option value="Canada/Central">Central</option>
							<option value="Canada/East-Saskatchewan">East Saskatchewan</option>
							<option value="Canada/Eastern">Eastern</option>
							<option value="Canada/Mountain">Mountain</option>
							<option value="Canada/Newfoundland">Newfoundland</option>
							<option value="Canada/Pacific">Pacific</option>
							<option value="Canada/Saskatchewan">Saskatchewan</option>
							<option value="Canada/Yukon">Yukon</option>
						</optgroup>
						<optgroup label="Europe">
							<option value="Europe/Amsterdam">Amsterdam</option>
							<option value="Europe/Andorra">Andorra</option>
							<option value="Europe/Athens">Athens</option>
							<option value="Europe/Belfast">Belfast</option>
							<option value="Europe/Belgrade">Belgrade</option>
							<option value="Europe/Berlin">Berlin</option>
							<option value="Europe/Bratislava">Bratislava</option>
							<option value="Europe/Brussels">Brussels</option>
							<option value="Europe/Bucharest">Bucharest</option>
							<option value="Europe/Budapest">Budapest</option>
							<option value="Europe/Chisinau">Chisinau</option>
							<option value="Europe/Copenhagen">Copenhagen</option>
							<option value="Europe/Dublin">Dublin</option>
							<option value="Europe/Gibraltar">Gibraltar</option>
							<option value="Europe/Guernsey">Guernsey</option>
							<option value="Europe/Helsinki">Helsinki</option>
							<option value="Europe/Isle_of_Man">Isle of Man</option>
							<option value="Europe/Istanbul">Istanbul</option>
							<option value="Europe/Jersey">Jersey</option>
							<option value="Europe/Kaliningrad">Kaliningrad</option>
							<option value="Europe/Kiev">Kiev</option>
							<option value="Europe/Lisbon">Lisbon</option>
							<option value="Europe/Ljubljana">Ljubljana</option>
							<option value="Europe/London">London</option>
							<option value="Europe/Luxembourg">Luxembourg</option>
							<option value="Europe/Madrid">Madrid</option>
							<option value="Europe/Malta">Malta</option>
							<option value="Europe/Mariehamn">Mariehamn</option>
							<option value="Europe/Minsk">Minsk</option>
							<option value="Europe/Monaco">Monaco</option>
							<option value="Europe/Moscow">Moscow</option>
							<option value="Europe/Nicosia">Nicosia</option>
							<option value="Europe/Oslo">Oslo</option>
							<option value="Europe/Paris">Paris</option>
							<option value="Europe/Podgorica">Podgorica</option>
							<option value="Europe/Prague">Prague</option>
							<option value="Europe/Riga">Riga</option>
							<option value="Europe/Rome">Rome</option>
							<option value="Europe/Samara">Samara</option>
							<option value="Europe/San_Marino">San Marino</option>
							<option value="Europe/Sarajevo">Sarajevo</option>
							<option value="Europe/Simferopol">Simferopol</option>
							<option value="Europe/Skopje">Skopje</option>
							<option value="Europe/Sofia">Sofia</option>
							<option value="Europe/Stockholm">Stockholm</option>
							<option value="Europe/Tallinn">Tallinn</option>
							<option value="Europe/Tirane">Tirane</option>
							<option value="Europe/Tiraspol">Tiraspol</option>
							<option value="Europe/Uzhgorod">Uzhgorod</option>
							<option value="Europe/Vaduz">Vaduz</option>
							<option value="Europe/Vatican">Vatican</option>
							<option value="Europe/Vienna">Vienna</option>
							<option value="Europe/Vilnius">Vilnius</option>
							<option value="Europe/Volgograd">Volgograd</option>
							<option value="Europe/Warsaw">Warsaw</option>
							<option value="Europe/Zagreb">Zagreb</option>
							<option value="Europe/Zaporozhye">Zaporozhye</option>
							<option value="Europe/Zurich">Zurich</option>
						</optgroup>
						<optgroup label="Australia">
							<option value="Australia/ACT">ACT</option>
							<option value="Australia/Adelaide">Adelaide</option>
							<option value="Australia/Brisbane">Brisbane</option>
							<option value="Australia/Broken_Hill">Broken Hill</option>
							<option value="Australia/Canberra">Canberra</option>
							<option value="Australia/Currie">Currie</option>
							<option value="Australia/Darwin">Darwin</option>
							<option value="Australia/Eucla">Eucla</option>
							<option value="Australia/Hobart">Hobart</option>
							<option value="Australia/LHI">LHI</option>
							<option value="Australia/Lindeman">Lindeman</option>
							<option value="Australia/Lord_Howe">Lord Howe</option>
							<option value="Australia/Melbourne">Melbourne</option>
							<option value="Australia/NSW">NSW</option>
							<option value="Australia/North">North</option>
							<option value="Australia/Perth">Perth</option>
							<option value="Australia/Queensland">Queensland</option>
							<option value="Australia/South">South</option>
							<option value="Australia/Sydney">Sydney</option>
							<option value="Australia/Tasmania">Tasmania</option>
							<option value="Australia/Victoria">Victoria</option>
							<option value="Australia/West">West</option>
							<option value="Australia/Yancowinna">Yancowinna</option>
						</optgroup>
					</select>
				</li>

				<li>
					<label><spring:message code="systemsettings.page.temperature.format"/>: </label>
					<select id="temperature" name="TEMPERATURE">
						<option value="FAHRENHEIT"><spring:message code="systemsettings.page.temperature.fahrenheit"/></option>
						<option value="CELSIUS"><spring:message code="systemsettings.page.temperature.celsius"/></option>
					</select>
				</li>

				<li>
					<label><spring:message code="profile.page.dateformat" />: </label>
					<select name="DATE_FORMAT" id="dateformat">
						<option value="MM/dd/yyyy">
							<spring:message code="profile.page.endataformat" />
						</option>
						<option value="dd/MM/yyyy">
							<spring:message code="profile.page.ptdataformat" />
						</option>
					</select>
				</li>

				<!-- li>
					<label for="select_start_page"><spring:message code="systemsettings.page.startPage"/></label>
					<select id="startpage" name="START_PAGE">
						<option value="DASHBOARD"><spring:message code="commons.pages.dashboard"/></option>
						<option value="DEVICE_LIST"><spring:message code="commons.pages.SmartPointList"/></option>
						<option value="SYSTEM_INTELLIGENCE"><spring:message code="commons.pages.systemintelligence"/></option>
					</select>
				</li-->

				<li>
					<label><spring:message code="profile.page.perPage" />: </label>
					<select name="PAGE_SIZE" id="pagesize">
						<option value="25">
							<spring:message code="profile.page.convertUnit.twentyFive" />
						</option>
						<option value="50">
							<spring:message code="profile.page.convertUnit.fifty" />
						</option>
						<option value="100">
							<spring:message code="profile.page.convertUnit.hundred" />
						</option>
					</select>
				</li>

				<sec:authorize access="!hasAnyRole('ROLE_EPM_CUSTOMER_SUPPORT', 'ROLE_EPM_BILLING_MANAGER', 'ROLE_EPM_SYSTEM_OPERATOR')">
					<li class="submit-row">
						<a href="#" id="button-save" class="button save-settings"> <spring:message code="commons.pages.save" /></a>
						&nbsp;<spring:message code="commons.pages.or" /> <a href="#" id="button-cancel" class="cancel"><spring:message code="commons.pages.cancel" /></a>
					</li>
				</sec:authorize>
			</ul>
		</fieldset>
	</div>

	<script src="scripts/util/settings_util.js"></script>
	<jsp:include page="../../scripts/pages/profile/profile_system_settings_init.js.jsp" flush="true"/>

</sec:authorize>