<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<%-- START Main Page Content --%>
<div class="content-inner">
<%-- START Main Content Container --%>
            	<div id="yui-main">
                	<div class="content-header">
                	<h1 id="title-create-group" class="hide"><s:message code="groupcreate.page.createsmartpointgroup" /></h1>

                	<h1 id="title-edit-group" class="hide"><s:message code="groupcreate.page.editsmartpointgroup" /><span class="group-name"></span>"</h1>

    	                <p class="description"><s:message code="groupcreate.page.required" /></p>
        			</div>
        			<div id="group-list">
                       <div class="create-action-container">
                            <a class="alist" id="ajax-button" href="group"><s:message code="groupcreate.page.backgroups" /></a>&nbsp;&nbsp;
                       </div>
                  	   <div class="yui-gc">
                       	<div class="yui-u first">
                         	<form id="create-group-form" name="createGroupForm" method="post" action="#" >
                                <fieldset>
                                    <ul>
                                        <li>
                                        	<input type="hidden" name="groupId" id="group-id" tabindex="1" value="" />
                                        	<input type="hidden" name="groupOldName" id="group-old-name" tabindex="1" value="" />
                                            <label for="group-name"><s:message code="groupcreate.page.groupname" /><span class="required">*</span></label>
                                            <input type="text" name="groupName" id="group-name" tabindex="1" class="validate[required,maxSize[100],custom[specialCharsCustom]]" value="" />
                                        </li>
                                        <li>
                                            <label for="group-description"><s:message code="groupcreate.page.description" /></label>
                                            <textarea id="group-description" class="validate[maxSize[150]]" name="groupDescription" tabindex="2"></textarea>
                                        </li>
                                    </ul>
                                    <ul>
                                        <li class="submit-row-pad">
                                        	<input type="submit" class="fg-button ui-widget ui-state-default ui-corner-all group-create" id="group-create-button" value="<s:message code="groupcreate.page.creategroup" />"/>
                                        	 <s:message code="groupcreate.page.or" /> <a id="ajax-button" href="group" class="alist cancel"><s:message code="groupcreate.page.cancel" /></a>
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
<%-- START Main Page Content --%>
<jsp:include page="../../scripts/pages/group_create_main.js.jsp" flush="true"/>
<jsp:include page="../../scripts/pages/group_create_init.js.jsp" flush="true"/>
<%-- START Main Content Container --%>

</sec:authorize>
