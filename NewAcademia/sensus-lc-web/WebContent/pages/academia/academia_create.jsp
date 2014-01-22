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
        			<div id="group-list">
                  	   <div class="yui-gc">
                       	<div class="yui-u first">
                         	<form id="create-academia-form" name="createAcademiaForm" method="post" action="#" >
                                <fieldset>
                                    <ul>
                                        <li>
                                        	<input type="hidden" name="academiaId" id="academiaId" tabindex="1" value="" />
                                        	<input type="hidden" name="groupOldName" id="group-old-name" tabindex="1" value="" />
                                            <label for="academia"><s:message code="groupcreate.page.groupname" /><span class="required">*</span></label>
                                            <input type="text" name="academia" id="academia" tabindex="1" class="validate[required,maxSize[100],custom[specialCharsCustom]]" value="" />
                                        </li>
										<li>
                                            <label for="logradouro"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="logradouro" id="logradouro" tabindex="1" value="" />
                                        </li>
										<li>
                                            <label for="numero"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="numero" id="numero" tabindex="1" value="" />
                                        </li>
										<li>
                                            <label for="bairro"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="bairro" id="bairro" tabindex="1" value="" />
                                        </li>
                                        <li>
                                            <label for="cidade"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="cidade" id="cidade" tabindex="1" value="" />
                                        </li>
                                        <li>
                                            <label for="cep"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="cep" id="cep" tabindex="1" value="" />
                                        </li>
                                        <li>
                                            <label for="telef"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="telef" id="telef" tabindex="1" value="" />
                                        </li>
                                        <li>
                                            <label for="dataini"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="dataini" id="dataini" tabindex="1" value="" class="datepicker" />
                                        </li>
                                        <li>
                                            <label for="datafin"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="datafin" id="datafin" tabindex="1" value="" class="datepicker" />
                                        </li>
                                        <li>
                                            <label for="atual"><s:message code="groupcreate.page.groupname" /></label>
                                            <input type="text" name="numero" id="numero" tabindex="1" value="" />
                                        </li>
                                        <li title="0" class="checkbox">
                                        	<input type="checkbox" value="true" id="atual">
                                        	<label for="EVENT_TYPE_1" class="on">Academia Atual</label>
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
<%-- START Main Content Container --%>
<jsp:include page="../../scripts/pages/academia/academia_init.js.jsp" flush="true"/>

</sec:authorize>