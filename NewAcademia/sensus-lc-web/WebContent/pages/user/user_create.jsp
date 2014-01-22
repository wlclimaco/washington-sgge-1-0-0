<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<!-- Messaging -->
<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove"><s:message code="message.action.close" /></a></div>
<%-- START Main Page Content --%>
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
   	<div id="yui-main">
       	<div class="content-header">
        	<h1>
        	</h1>
            <p class="description"><s:message code="user.page.required" /></p>
		</div>
		<div id="user-list">
              <div class="create-action-container">
                   <a class="alist" id="ajax-button" href="user"><s:message code="usercreate.page.backusers" /></a>&nbsp;&nbsp;
              </div>
         	  <div class="yui-gc">
	              	<div class="yui-u first">
	                	<form name="createUserForm" class="register" method="post" action="#">

	                       <fieldset>
	                           <ul>
	                               <li>
	                                   <label for="user-first-create"><s:message code="usercreate.page.firstusername" /><span class="required">*</span></label>
	                                   <input type="text" id="user-first-create" tabindex="1" class="validate[required,maxSize[50]]" value=""/>
	                               </li>
	                               <li>
	                                   <label for="user-last-create"><s:message code="usercreate.page.lastusername" /><span class="required">*</span></label>
	                                   <input type="text" id="user-last-create" tabindex="1" class="validate[required,maxSize[50]]" value=""/>
	                               </li>
	                               <li>
	                                   <label for="user-last-create"><s:message code="usercreate.page.username" /><span class="required">*</span></label>
	                                   <input type="text" id="user-user" tabindex="1" class="validate[required,maxSize[50]]" value="" /> <span class="note"><s:message code="usercreate.page.usernote" /></span>
	                               </li>
	                               <li>
	                                   <label for="user-email-create"><s:message code="usercreate.page.emailuser" /><span class="required">*</span></label>
	                                   <input type="text" id="user-email-create" tabindex="1" class="validate[required,maxSize[50],custom[email]]" value=""/>
	                               </li>
	                           </ul>
	                     </fieldset>

	                     <fieldset id="roles">
	                     	<legend><s:message code="usercreate.page.userrole" /><span class="note click-form-definition-roles"><s:message code="usercreate.page.definition" /></span></legend>
	                       	<ul>
	                        	<li>
	                            	<label for="user-role-create"><s:message code="usercreate.page.role" /><span class="required">*</span></label>
									<ul class="radio-list">
	                                   	<li class="radio"><input checked type="radio" name="roles" id="role-admin" class="validate[required]" value="ROLE_Role.Admin"> <label for="role_admin" class=""><s:message code="usercreate.page.admin" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-operator" class="validate[required]" value="ROLE_Role.System Operator"> <label for="role_operator"><s:message code="usercreate.page.systemoperator" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-support" class="validate[required]" value="ROLE_Role.Customer Support"> <label for="role_support"><s:message code="usercreate.page.customsupport" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-analytic" class="validate[required]" value="ROLE_Role.Analytic User"> <label for="role_analytic"><s:message code="usercreate.page.analyticuser" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-api" class="validate[required]" value="ROLE_Role.API"> <label for="role-api"><s:message code="usercreate.page.api" /></label></li>
	                                </ul>
	                        	</li>
	                     	</ul>
	                     </fieldset>

	                     <fieldset class="">
                         	<legend><s:message code="usercreate.page.accesslights" /></legend>
                            	<ul class="check-pad">
                                	<li class="checkbox select-container">
                                    	<div id="select-lights" class="sui-pad1v">
											<input value="true" type="radio" id="include-all-lights" name="select-lights" disabled checked /><label for="include-all-lights"><s:message code="usercreate.page.lights" /></label>&nbsp;&nbsp;
											<input value="false" type="radio" id="include-some-lights" name="select-lights"  disabled /><label for="include-some-lights"><s:message code="usercreate.page.selectlights" /></label>
                                         </div>
									</li>

									<li class="overflow highlight chzn-container">
                                       	<ul>
	                                        <li class="chzn-row select-group">
	                                            <label class="short" id="select-groups-label" for="select-groups"><s:message code="usercreate.page.selectgroups" /></label>

													<select data-placeholder="<s:message code='usercreate.page.selectagroups' />" data-no_results_text='<s:message code="usercreate.page.addnewgroup"/>'  multiple class="chzn-select" id="select_groups" name="select-groups" tabindex="8"></select>

	                                            <a href="" class="text_button"><small><s:message code="usercreate.page.selectallgroups" /></small></a>
	                                        </li>
										</ul>
	                                </li>

	                                <li class="summary submit-row">
										<span class="map-col"><strong></strong> <s:message code="usercreate.page.selectedlights" /> <a href="" class="text_button"><s:message code="usercreate.page.map" /></a> <span class="note"><s:message code="usercreate.page.selectgroupsnote" /></span></span>
									</li>

                                </ul>
                    	</fieldset>
						  <fieldset id="subscribe-fieldset">
						<legend><s:message code="profile.page.subscribeAlerts"/></legend>
						<div id="subscribe-html"></div>
					   </fieldset>

                   		<fieldset>
									<br/><legend><a href="" class="text_button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span><s:message code="usercreate.page.changepass" /></a></legend>
	                            <div class="spindown_child">
	                             	<ul>
	                                     <li>
	                                         <label for="user-password-create"><s:message code="usercreate.page.password" /><span class="required">*</span></label>
	                                         <input type="password" id="user-password-create" class="password validate[required,maxSize[50],minSize[8],custom[passSpecialChars]]" name="password"  tabindex="1"/>
	                                         <div class="password-meter">
	                                             <div class="password-meter-message"><s:message code="usercreate.page.minchars" /></div>
	                                             <div class="password-meter-bg">
	                                                 <div class="password-meter-bar"></div>
	                                             </div>
	                                         </div>
	                                         <span class="note rounded bubble" rel="black" title="<s:message code='profile.validate.rules'/>"><s:message code="usercreate.page.typepass" /></span>
	                                     </li>
	                                     <li>
	                                         <label for="user-password-create"><s:message code="usercreate.page.passwordconfirm" /><span class="required">*</span></label>
	                                         <input type="password" id="user-password-create" tabindex="1" class="validate[required,maxSize[50],minSize[8],equals[user-password-create],custom[passSpecialChars]] "/>
	                                     </li>
	                                 </ul>
	                             </div>
                                  <ul>
									<li class="submit-row-pad user-create-button">
										<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all user-create" id="user-create-button" value="" />
										<s:message code="usercreate.page.or" />
										<a id="users" class="alist cancel" href="user"><s:message code="usercreate.page.cancel" /></a>
									</li>
                                 </ul>
                        </fieldset>

	                </form>
	                </div>
               	<div class="yui-u">
                </div>
 			</div>
		</div>
	</div>
</div>

<div id="dialog-map"></div>
<%@ include file="../../scripts/pages/profile/profile_main.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_main.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_create_main.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_create_actions.js.jsp" %>
<%@ include file="../../scripts/pages/user/user_create_init.js.jsp" %>
<%@ include file="../../scripts/pages/profile/email_subscribe_actions.js"%>
</sec:authorize>
