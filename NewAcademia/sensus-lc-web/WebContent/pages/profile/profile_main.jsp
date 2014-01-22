<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<div id="bd" class="content-container">
	<div id="settings-container" class="content-inner">
            <!-- Messaging -->
        <div id="messaging-main" class="messaging"><span class="message"></span><a href="" class="remove">close</a></div>
        <div id="tabs" class="yui-t2">
                <div class="yui-b settings-nav-container">
                    <ul class="settings-nav">
                        <li><a href="#tabs-1"><s:message code="systemsettings.page.tab.general"/></a></li>
                        <li><a href="#tabs-2"><s:message code="profile.page.changepassword"/></a></li>
                        <li><a href="#tabs-3"><s:message code="profile.page.manageEmails"/></a></li>
                    </ul>
                </div>
		<div id="yui-main" >
		<div class="yui-b settings-content">
                <div  id="tabs-1" class="profile" >

                      	<fieldset>
						<ul class="ui-tabs">
							<li>
								<label><s:message code="profile.page.languagepreference" /></label>

								<select id="language">
									<option value="en_US">
										<s:message code="profile.page.englishEN" />
									</option>
									<option value="pt_BR">
										<s:message code="profile.page.portugueseBR" />
									</option>
									<option value="es_MX">
										<s:message code="profile.page.spanish" />
									</option>
								</select>
							</li>

							</li>
							<li>
								<label><s:message code="profile.page.defaulttimezone" /></label>

								<select id="timezone" name="timezone" class="chzn-select" data-placeholder="Select Time Zone">
									<optgroup label="US">
										<option value="US/Alaska">Alaska </option>
										<option value="US/Aleutian">Aleutian </option>
										<option value="US/Arizona">Arizona </option>
										<option value="CST">Central </option>
										<option value="US/East-Indiana">East-Indiana </option>
										<option value="US/Eastern">Eastern </option>
										<option value="US/Hawaii0">Hawaii </option>
										<option value="US/Indiana-Starke">Indiana-Starke </option>
										<option value="US/Michigan">Michigan </option>
										<option value="MST">Mountain </option>
										<option value="SystemV/PST8">Pacific </option>
										<option value="US/Pacific-New">Pacific-New </option>
										<option value="Pacific/Samoa">Samoa </option>
									</optgroup>
									<optgroup label="Canada">
										<option value="America/Toronto">Atlantic </option>
										<option value="Canada/Central">Central </option>
										<option value="Canada/East-Saskatchewan">East-Saskatchewan </option>
										<option value="Canada/Eastern">Eastern </option>
										<option value="Canada/Mountain">Mountain </option>
										<option value="Canada/Newfoundland">Newfoundland </option>
										<option value="Canada/Pacific">Pacific </option>
										<option value="Canada/Saskatchewan">Saskatchewan </option>
										<option value="Canada/Yukon">Yukon </option>
									</optgroup>
									<optgroup label="Europe">
										<option value="Europe/Amsterdam">Amsterdam </option>
										<option value="Europe/Andorra">Andorra </option>
										<option value="Europe/Athens">Athens </option>
										<option value="Europe/Belfast">Belfast </option>
										<option value="Europe/Belgrade">Belgrade </option>
										<option value="Europe/Berlin">Berlin </option>
										<option value="Europe/Bratislava">Bratislava </option>
										<option value="Europe/Brussels">Brussels </option>
										<option value="Europe/Bucharest">Bucharest </option>
										<option value="Europe/Budapest">Budapest </option>
										<option value="Europe/Chisinau">Chisinau </option>
										<option value="Europe/Copenhagen">Copenhagen </option>
										<option value="Europe/Dublin">Dublin </option>
										<option value="Europe/Gibraltar">Gibraltar </option>
										<option value="Europe/Guernsey">Guernsey </option>
										<option value="Europe/Helsinki">Helsinki </option>
										<option value="Europe/Isle_of_Man">Isle_of_Man </option>
										<option value="Europe/Istanbul">Istanbul </option>
										<option value="Europe/Jersey">Jersey </option>
										<option value="Europe/Kaliningrad">Kaliningrad </option>
										<option value="Europe/Kiev">Kiev </option>
										<option value="Europe/Lisbon">Lisbon </option>
										<option value="Europe/Ljubljana">Ljubljana </option>
										<option value="Europe/London">London </option>
										<option value="Europe/Luxembourg">Luxembourg </option>
										<option value="Europe/Madrid">Madrid </option>
										<option value="Europe/Malta">Malta </option>
										<option value="Europe/Mariehamn">Mariehamn </option>
										<option value="Europe/Minsk">Minsk </option>
										<option value="Europe/Monaco">Monaco </option>
										<option value="Europe/Moscow">Moscow </option>
										<option value="Europe/Nicosia">Nicosia </option>
										<option value="Europe/Oslo">Oslo </option>
										<option value="Europe/Paris">Paris </option>
										<option value="Europe/Podgorica">Podgorica </option>
										<option value="Europe/Prague">Prague </option>
										<option value="Europe/Riga">Riga </option>
										<option value="Europe/Rome">Rome </option>
										<option value="Europe/Samara">Samara </option>
										<option value="Europe/San_Marino">San_Marino </option>
										<option value="Europe/Sarajevo">Sarajevo </option>
										<option value="Europe/Simferopol">Simferopol </option>
										<option value="Europe/Skopje">Skopje </option>
										<option value="Europe/Sofia">Sofia </option>
										<option value="Europe/Stockholm">Stockholm </option>
										<option value="Europe/Tallinn">Tallinn </option>
										<option value="Europe/Tirane">Tirane </option>
										<option value="Europe/Tiraspol">Tiraspol </option>
										<option value="Europe/Uzhgorod">Uzhgorod </option>
										<option value="Europe/Vaduz">Vaduz </option>
										<option value="Europe/Vatican">Vatican </option>
										<option value="Europe/Vienna">Vienna </option>
										<option value="Europe/Vilnius">Vilnius </option>
										<option value="Europe/Volgograd">Volgograd </option>
										<option value="Europe/Warsaw">Warsaw </option>
										<option value="Europe/Zagreb">Zagreb </option>
										<option value="Europe/Zaporozhye">Zaporozhye </option>
										<option value="Europe/Zurich">Zurich </option>
									</optgroup>
									<optgroup label="Australia">
										<option value="Australia/ACT">ACT </option>
										<option value="Australia/Adelaide">Adelaide</option>
										<option value="Australia/Brisbane">Brisbane </option>
										<option value="Australia/Broken_Hill">Broken_Hill </option>
										<option value="Australia/Canberra">Canberra </option>
										<option value="Australia/Currie">Currie </option>
										<option value="Australia/Darwin">Darwin </option>
										<option value="Australia/Eucla">Eucla </option>
										<option value="Australia/Hobart">Hobart </option>
										<option value="Australia/LHI">LHI </option>
										<option value="Australia/Lindeman">Lindeman </option>
										<option value="Australia/Lord_Howe">Lord_Howe </option>
										<option value="Australia/Melbourne">Melbourne </option>
										<option value="Australia/NSW">NSW </option>
										<option value="Australia/North">North </option>
										<option value="Australia/Perth">Perth </option>
										<option value="Australia/Queensland">Queensland </option>
										<option value="Australia/South">South </option>
										<option value="Australia/Sydney">Sydney </option>
										<option value="Australia/Tasmania">Tasmania </option>
										<option value="Australia/Victoria">Victoria </option>
										<option value="Australia/West">West </option>
										<option value="Australia/Yancowinna">Yancowinna </option>
									</optgroup>
								</select>
							</li>
                            <li>
								<label><s:message code="profile.page.defaultdateformat" /></label>

									<select name="dateformat" id="dateformat">
										   <option value="<s:message code="profile.page.endataformat" />"><s:message code="profile.page.endataformat" /></option>
										   <option value="<s:message code="profile.page.ptdataformat" />"><s:message code="profile.page.ptdataformat" /></option>
									</select>
							</li>
							<li>
								<label><s:message code="profile.page.pagesize" /></label>

									<select name="pagesize" id="pagesize">
										<option value="<s:message code="option.pagesize.fifteen" />"><s:message code="option.pagesize.fifteen" /></option>
										<option value="<s:message code="option.pagesize.twentyFive" />"><s:message code="option.pagesize.twentyFive" /></option>
										<option value="<s:message code="option.pagesize.fifty" />"><s:message code="option.pagesize.fifty" /></option>
										<option value="<s:message code="option.pagesize.hundred" />"><s:message code="option.pagesize.hundred" /></option>
									</select>
							</li>
									<!--
									<li class="checkbox">
										<input id="page-size-show-dialog" type="checkbox" name=""/>
										<label><s:message code="action.pagesize.dialog.preferences" /></label>
									</li>
									-->
                        </ul>
 							</fieldset>
 							<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator', 'ROLE_Role.Customer Support')">
	                          	<fieldset>
	                              	<legend><s:message code="profile.page.monitorrequeststatus" /></legend>
	                               <ul id="prompt-monitor">
		                                <li class="radio monitor"><input type="radio" value="1" name="monitor"/><label><s:message code="profile.page.alwaysmonitor" /></label></li>
		                                <li class="radio monitor"><input type="radio" value="2" name="monitor"/><label><s:message code="profile.page.alwaysdismiss" /> <a id="user-profile-event-history" href="systemintelligence/process" class="alist"><s:message code="profile.page.eventhistory" /></a> </label></li>
		                                <li class="radio monitor"><input type="radio" value="3" name="monitor" checked="checked" /><label><s:message code="profile.page.promptme" /></label></li>
	                               </ul>
								</fieldset>
							</sec:authorize>
                          <fieldset>
						<legend><s:message code="profile.page.unitsConversion" /></legend>
						<ul>
							<li class="checkbox"><input id="unit-convert" type="checkbox" name=""/><label><s:message code="profile.page.consertUnitsDescription" /> </label></li>
							<li>
								<table id="units-table" class="list">
									<thead>
										<tr>
											<th><s:message code="profile.page.convertUnit.value" /></th>
											<th><s:message code="profile.page.convertUnit.symbol" /></th>
											<th><s:message code="profile.page.convertUnit.name" /></th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td class="unit-kwh" ><s:message code="profile.page.convertUnit.base" /><sup></sup></td>
											<td><s:message code="profile.page.convertUnit.kwh" /></td>
											<td><s:message code="profile.page.convertUnit.kilowattHhour" /></td>
										</tr>
										<tr class="alt">
											<td class="unit-mwh"><s:message code="profile.page.convertUnit.base" /><sup></sup></td>
											<td><s:message code="profile.page.convertUnit.MWh" /></td>
											<td><s:message code="profile.page.convertUnit.megawattHour" /></td>
										</tr>
										<tr>
				                        	<td class="unit-gwh"><s:message code="profile.page.convertUnit.base" /><sup></sup></td>
				                            <td><s:message code="profile.page.convertUnit.GWh" /></td>
				                            <td><s:message code="profile.page.convertUnit.gigawattHour" /></td>
										</tr>
										<tr class="alt">
											<td class="unit-twh"><s:message code="profile.page.convertUnit.base" /><sup></sup></td>
				                            <td><s:message code="profile.page.convertUnit.TWh" /></td>
				                            <td><s:message code="profile.page.convertUnit.terawattHour" /></td>
										</tr>
										<tr>
											<td class="unit-pwh"><s:message code="profile.page.convertUnit.base" /><sup></sup></td>
				                            <td><s:message code="profile.page.convertUnit.PWh" /></td>
				                            <td><s:message code="profile.page.convertUnit.petawattHour" /></td>
										</tr>
									</tbody>
								</table>
							</li>
						</ul>
						<ul>
                 			<li class="submit-row"><a id="user-profile-save" href="#" class="button save-profile-main"><s:message code="profile.page.save" /></a> <s:message code="profile.page.or" /> <a id="user-profile-cancel" href="profile" class="cancel alist"><s:message code="profile.page.cancel" /></a></li>
                       </ul>
					</fieldset>
				</div>
					<!-- START Password Reset -->
                    <div  id="tabs-2" class="password-reset" >
						<form id="user-form" name="userForm" class="register" method="post" action="#" >
                              <fieldset>
                                        <ul>
                                            <li>
                                                <label for="user-password-create"><s:message code="profile.page.currentpassword" />:<span class="required">*</span></label>
                                                <input type="password" id="user-password-current" tabindex="1" class="required validate[required]"  />
                                            </li>
                                            <li>
                                                <label for="user-password-create"><s:message code="profile.page.newpassword" />:<span class="required">*</span></label>
                                                <input type="password" id="user-password-new" class="password validate[required,maxSize[50],minSize[8],custom[passSpecialChars]]" name="password" tabindex="1"/>
                                                <div class="password-meter">
                                                    <div class="password-meter-message"><s:message code="profile.validate.characters" /></div>
                                                    <div class="password-meter-bg">
                                                        <div class="password-meter-bar"></div>
                                                    </div>
                                                </div>
                                            <span class="note rounded bubble" rel="black" title="<ul><li>1 Upper Case</li><li>1 Lower Case</li><li>1 Alpha</li><li>1 Numeric</li><li>1 Special Character (!@#$%^*&*)</li></ul>"><s:message code="profile.validate.password" />&hellip;</span>
                                            </li>
                                            <li>
                                                <label for="user-password-create"><s:message code="profile.page.confirmpassword" />:<span class="required">*</span></label>
                                                <input type="password" id="user-password-confirm" tabindex="1" class="validate[required,maxSize[50],minSize[8],equals[user-password-new],custom[passSpecialChars]]"  />
                                            </li>
                                        </ul>
                                    <ul>
                          			<li class="submit-row"><a id="user-profile-save" href="#" class="button save-profile-main"><s:message code="profile.page.savePassword" /></a> <s:message code="profile.page.or" /> <a id="user-profile-cancel" href="profile" class="cancel alist"><s:message code="profile.page.cancel" /></a></li>
                                    </ul>

                              </fieldset>
                          </form>
					</div>
				<!-- END Password Reset -->
				<!-- START Manage Alerts -->
				    <div  id="tabs-3" class="manage-alerts" >
						  <fieldset>
									<ul>
										<li>
										<form name="emailSubscribe" id="email-subscribe-form" class="register" method="post" action="#">
											<label for="user-password-create">Email:<span class="required">*</span></label>
											<input type="text" id="user-email" tabindex="1" class="validate[required,maxSize[50],custom[email]]" />
											<input type="submit" id="user-profile-save"  class="fg-button ui-widget ui-state-default ui-corner-all user-email" value="<s:message code="profile.page.saveEmail"/>">
										</form>
										</li>
									</ul>
						  </fieldset>
					   <fieldset>
						<legend ><s:message code="profile.page.subscribeAlerts"/></legend>
						<div id="subscribe-html"></div>
					   </fieldset>
					</div>
					<!-- END  Manage Alerts -->
				</div>
         </div>
		</div>
	</div>
</div>
<%@ include file="../../scripts/pages/profile/profile_main.js.jsp" %>
<%@ include file="../../scripts/pages/profile/profile_init.js.jsp" %>
<%@ include file="../../scripts/pages/profile/email_subscribe_actions.js"%>
