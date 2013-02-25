<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
            	<div id="yui-main">
                	<div class="content-header">
	                	<h1>
	                		<s:if test="%{groupId==null}">
	                			<s:text name="groupcreate.page.createsmartpointgroup" />
	                		</s:if>
	                		<s:else>
	                			<s:text name="groupcreate.page.editsmartpointgroup" />${groupName}"
	                		</s:else>
	                	</h1>
    	                <p class="description"><s:text name="groupcreate.page.required" /></p>
        			</div>
        			<div id="group-list">                      
                       <div class="create-action-container">
                            <a class="alist" id="ajax-button" href="<s:url includeParams="none" value="/group/ajax.list.action"/>"><s:text name="groupcreate.page.backgroups" /></a>&nbsp;&nbsp;
                            <!--<a href="groups.html" class="button small">Create Group</a> <a href="groups.html" class="button small">Cancel</a>-->
                       </div>
                  	   <div class="yui-gc">
                       	<div class="yui-u first">
                       		<s:if test="%{groupId==null}">
                            	<form id="create-group-form" name="createGroupForm" method="post" action="#" >
                         	</s:if>
                         	<s:else>
                         		<form id="update-group-form" name="createGroupForm" method="post" action="#" >
                         	</s:else>
                                <fieldset>
                                    <ul>
                                        <li>
                                        	<input type="hidden" name="groupId" id="group-id" tabindex="1" value="${groupId}" />
                                        	<input type="hidden" name="groupOldName" id="group-old-name" tabindex="1" value="${groupName}" />
                                            <label for="group-name"><s:text name="groupcreate.page.groupname" /><span class="required">*</span></label>
                                            <input type="text" name="groupName" id="group-name" tabindex="1" class="validate[required,maxSize[100],custom[specialCharsCustom]]" value="${groupName}" />
                                        </li>
                                        <li>
                                            <label for="group-description"><s:text name="groupcreate.page.description" /></label>
                                            <textarea id="group-description" class="validate[maxSize[150]]" name="groupDescription" tabindex="2">${groupDescription}</textarea>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li class="submit-row-pad">
                                        	<s:if test="%{groupId==null}">
                                        		<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all group-create" id="group-create-button" value="<s:text name="groupcreate.page.creategroup" />"/>
                                        	</s:if>
                                        	<s:else>
                                        		<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all group-create" id="group-create-button" value="<s:text name="groupcreate.page.editgroup" />"/>
                                        	</s:else>
                                        	 <s:text name="groupcreate.page.or" /> <a id="ajax-button" href="<s:url includeParams="none" value="/group/ajax.list.action"/>" class="alist cancel"><s:text name="groupcreate.page.cancel" /></a>
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
