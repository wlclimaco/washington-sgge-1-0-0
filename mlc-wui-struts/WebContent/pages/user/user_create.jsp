<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<!-- Messaging -->
<div id="messaging-main" class="messaging" style="display: none;"><span	class="message"></span><a href="" class="remove"><s:text name="message.action.close" /></a></div>
<%-- START Main Page Content --%>
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
   	<div id="yui-main">
       	<div class="content-header">
        	<h1>
        	</h1>
            <p class="description"><s:text name="user.page.required" /></p>
		</div>
		<div id="user-list">                      
              <div class="create-action-container">
                   <a class="alist" id="ajax-button" href="<s:url includeParams="none" value="/user/ajax.list.action"/>"><s:text name="usercreate.page.backusers" /></a>&nbsp;&nbsp;
              </div>
         	  <div class="yui-gc">
	              	<div class="yui-u first">
	              		<s:if test="%{userId==null}">
	                   	<form id="create-user-form" name="createUserForm" class="register" method="post" action="#" >
	                	</s:if>
	                	<s:else>
	                		<form id="update-user-form" name="createUserForm" class="register" method="post" action="#" >
	                	</s:else>
	                	
	                       <fieldset>
	                           <ul>
	                               <li>
	                                   <label for="user-first-create"><s:text name="usercreate.page.firstusername" /><span class="required">*</span></label>
	                                   <input type="text" id="user-first-create" tabindex="1" class="validate[required,maxSize[50]]" value=""/>
	                               </li>
	                               <li>
	                                   <label for="user-last-create"><s:text name="usercreate.page.lastusername" /><span class="required">*</span></label>
	                                   <input type="text" id="user-last-create" tabindex="1" class="validate[required,maxSize[50]]" value=""/>
	                               </li>
	                               <li>
	                                   <label for="user-last-create"><s:text name="usercreate.page.username" /><span class="required">*</span></label>
	                                   <input type="text" id="user-user" tabindex="1" class="validate[required,maxSize[50]]" value="" /> <span class="note"><s:text name="usercreate.page.usernote" /></span>
	                               </li>
	                               <li>
	                                   <label for="user-email-create"><s:text name="usercreate.page.emailuser" /><span class="required">*</span></label>
	                                   <input type="text" id="user-email-create" tabindex="1" class="validate[required,maxSize[50],custom[email]]" value=""/> 
	                               </li>
	                           </ul>
	                     </fieldset>
	                     
	                     <fieldset id="roles">
	                     	<legend><s:text name="usercreate.page.userrole" /><span class="note click-form-definition-roles"><s:text name="usercreate.page.definition" /></span></legend>
	                       	<ul>
	                        	<li>
	                            	<label for="user-role-create"><s:text name="usercreate.page.role" /><span class="required">*</span></label>
									<ul class="radio-list">
	                                   	<li class="radio"><input checked type="radio" name="roles" id="role-admin" class="validate[required]" value="ROLE_Role.Admin"> <label for="role_admin" class=""><s:text name="usercreate.page.admin" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-operator" class="validate[required]" value="ROLE_Role.System Operator"> <label for="role_operator"><s:text name="usercreate.page.systemoperator" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-support" class="validate[required]" value="ROLE_Role.Customer Support"> <label for="role_support"><s:text name="usercreate.page.customsupport" /></label></li>
	                                   	<li class="radio"><input type="radio" name="roles" id="role-analytic" class="validate[required]" value="ROLE_Role.Analytic User"> <label for="role_analytic"><s:text name="usercreate.page.analyticuser" /></label></li>
	                                </ul>
	                        	</li>
	                     	</ul>
	                     </fieldset>
	                     
	                     <fieldset class="">
                         	<legend><s:text name="usercreate.page.accesslights" /></legend>
                            	<ul class="check-pad">
                                	<li class="checkbox select-container">
                                    	<div id="select-lights" class="sui-pad1v">
											<input value="true" type="radio" id="include-all-lights" name="select-lights" disabled checked /><label for="include-all-lights"><s:text name="usercreate.page.lights" /></label>&nbsp;&nbsp;
											<input value="false" type="radio" id="include-some-lights" name="select-lights"  disabled /><label for="include-some-lights"><s:text name="usercreate.page.selectlights" /></label>
                                         </div>
									</li>
									
									<li class="overflow highlight chzn-container">
                                       	<ul>
	                                        <li class="chzn-row select-group">
	                                            <label class="short" id="select-groups-label" for="select-groups"><s:text name="usercreate.page.selectgroups" /></label>
													
													<select data-placeholder="<s:text name='usercreate.page.selectagroups' />" data-no_results_text='<s:text name="usercreate.page.addnewgroup"/>'  multiple class="chzn-select" id="select_groups" name="select-groups" tabindex="8"></select>
							
	                                            <a href="" class="text_button"><small><s:text name="usercreate.page.selectallgroups" /></small></a>
	                                        </li>
										</ul>
	                                </li>
	                                
	                                <li class="summary submit-row">
										<span class="map-col"><strong></strong> <s:text name="usercreate.page.selectedlights" /> <a href="" class="text_button"><s:text name="usercreate.page.map" /></a> <span class="note"><s:text name="usercreate.page.selectgroupsnote" /></span></span>
									</li>
									
                                </ul>
                    	</fieldset>
                    	
                   		<fieldset>
								<legend><a href="" class="text_button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span><s:text name="usercreate.page.changepass" /></a></legend>
	                            <div class="spindown_child">
	                             	<ul>
	                                     <li>
	                                         <label for="user-password-create"><s:text name="usercreate.page.password" /><span class="required">*</span></label>
	                                         <input type="password" id="user-password-create" class="password validate[required,maxSize[50],minSize[8],custom[passSpecialChars]]" name="password"  tabindex="1"/>
	                                         <div class="password-meter">
	                                             <div class="password-meter-message"><s:text name="usercreate.page.minchars" /></div>
	                                             <div class="password-meter-bg">
	                                                 <div class="password-meter-bar"></div>
	                                             </div>
	                                         </div>    
	                                         <span class="note rounded bubble" rel="black" title="<ul><li>1 Upper Case</li><li>1 Lower Case</li><li>1 Alpha</li><li>1 Numeric</li><li>1 Special Character (!@#$%^*&*)</li></ul>"><s:text name="usercreate.page.typepass" /></span>                                       
	                                     </li>
	                                     <li>
	                                         <label for="user-password-create"><s:text name="usercreate.page.passwordconfirm" /><span class="required">*</span></label>
	                                         <input type="password" id="user-password-create" tabindex="1" class="validate[required,maxSize[50],minSize[8],equals[user-password-create],custom[passSpecialChars]] "/>
	                                     </li>
	                                 </ul>
	                             </div>
                                  <ul>
										<li class="submit-row-pad"> 
										<s:if test="%{userId==null}">
											<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all user-create" id="user-create-button" value="<s:text name='usercreate.page.createuser' />"/>
										</s:if>
										<s:else> 
											<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all user-create" id="user-create-button" value="<s:text name='usercreate.page.saveuser' />"/>
										</s:else>
										 <s:text name="usercreate.page.or" /> 
										<a id="users" class="alist cancel" href="<s:url includeParams="none" value="user/ajax.list.action"/>"><s:text name="usercreate.page.cancel" /></a>
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
		
</sec:authorize>
