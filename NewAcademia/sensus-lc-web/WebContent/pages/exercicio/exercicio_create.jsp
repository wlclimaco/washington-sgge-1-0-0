<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>

<sec:authorize access="hasAnyRole('ROLE_Role.Admin', 'ROLE_Role.System Operator')">
<%-- START Main Page Content --%>
<%-- START Main Page Content --%>
<style>
  .thumb {
    height: 75px;
    border: 1px solid #000;
    margin: 10px 5px 0 0;
	width :100px ;
	height :70px;
  }
  .test{
	width :284px;
  }
  .centro{
	padding-left : 132px
  }

   .reviwVideos {
    border: 1px solid #000;
    margin: 10px 5px 0 0;
	width :240px ;

  }
</style>

            <!-- Messaging -->
            	<div id="messaging-main" class="messaging"><span class="message"></span><a href="" class="remove">close</a></div>
                <div id="yui-main">
                    <!-- laoding dialog -->
                    <div id="loading">
                    	<h5>Updating Results&hellip;</h5>
                        <div id="progressbar"></div>
                    </div>
                   <!-- blank slate -->
                    <div class="blankslate">
                    	<h5>You currently have no schedules</h5>
                        <p>Remove filters or use broader search terms  and try again.</p>
                    </div>
                  <div id="schedule-list">
                       <div class="create-action-container">
                            <a href="system-iq-event.html">&laquo;Back to Events</a>&nbsp;&nbsp;
                            <!--<a href="schedules.html" class="button small">Create Schedule</a> <a href="schedules.html" class="button small">Cancel</a>-->
                       </div>
                                <fieldset>
                                    <ul>
                                        <li>
                                            <label for="exercicio-name-create">Name exercicio:<span class="required">*</span></label>
                                            <input type="text" id="exercicio-name-create" tabindex="1" class="required long"/>
                                        </li>
                                        <li>
                                            <label for="exercicio-description">Description:</label>
                                            <textarea id="exercicio-description" tabindex="2" class="long"></textarea>
                                        </li>
										<li class="centro">
											 <select id="grupo-muscular">
											</select>
										 </li>
										 <li>

										  </li>
                                    </ul>
                                </fieldset>
                                <fieldset>
                                    <legend><a href="" class="text-button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> Imagens</a></legend>
                                    <div class="spindown-child">
                                            <!-- START: Group Search -->
                                            <div class="advanced-search-container group yui-gf">
                                                <div class="yui-u first">
                                                    <h5>Imagens

                                                    </h5>
                                                </div>
                                                <div class="yui-u">
													 <form enctype="multipart/form-data" action="ecomode/upload" method="post" name="forms" id="forms" class="">
														 <input type="file" id="files" name="upload" multiple>
															<output id="list"></output>
													</form>
                                                </div>
                                            </div>
                                    </div>
								</fieldset>
								<fieldset>
                                    <legend><a href="" class="text-button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> Videos</a></legend>
                                    <div class="spindown-child">
                                            <!-- START: Group Search -->
                                            <div class="advanced-search-container group yui-gf">
                                                <div class="yui-u first">
                                                    <h5>Videos

                                                    </h5>
                                                </div>
                                                <div class="yui-u">
												<label for="exercicio-name-create">Link Video :<span></span></label>
													<input type="text" id="exercicio-videos" tabindex="1" class="required long"/> <button id="uploadvideos" class="btn btn-warning cancel ui-button-text">Buscar</button>
													 <output id="videos"></output>
                                                </div>
                                            </div>
                                    </div>
							    </fieldset>
							 <fieldset>
							  <button id="save" class="btn btn-warning cancel ui-button-text">
							  <fieldset>
                       </div>
					  <p class=hide id="fotos"></p>
				</div>
        <div class="yui-u footer-links"></div>
</div>
<div id="dialog-map"></div>
<%-- START Main Page Content --%>
<%-- START Main Content Container --%>
<jsp:include page="../../scripts/pages/exercicio/exercicio_create_init.js.jsp" flush="true"/>

</sec:authorize>


