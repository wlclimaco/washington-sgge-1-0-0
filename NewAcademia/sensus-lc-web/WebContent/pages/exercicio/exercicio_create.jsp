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
  }
</style>
<div id="system-messaging"></div>
    <div id="bd" class="content-container">
		<!-- END - Subnavigation -->
		<div class="content-inner">

		 <form enctype="multipart/form-data" action="ecomode/upload" method="post" name="forms" id="forms" class="">

    <div id="file">
        <input type="file" id="files" name="upload" multiple>
        <br ><br >
        <progress value="0" max="100"></progress><span id="porcentagem">0%</span>

    </div>
    <br />
 <input type="submit">
</form>
            <!-- Messaging -->
            	<div id="messaging-main" class="messaging"><span class="message"></span><a href="" class="remove">close</a></div>
                <div id="yui-main">
                	<div class="content-header">
	                	<h1>Schedule Event</h1>
    	                <p class="description">Use System Intelligence events to define and schedule work based on time or analytical triggers. Enter all <span class="required">*Required</span> information.</p>
        			</div>
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
                            <form id="create-event-form" name="create-event-form" method="post" action="system-iq-event.html?name=value&name=value" >
                                <fieldset>
                                    <ul>
                                        <li>
                                            <label for="schedule-name-create">Event Name:<span class="required">*</span></label>
                                            <input type="text" id="schedule-name-create" tabindex="1" class="required long"/>
                                        </li>
                                        <li>
                                            <label for="schedule-description">Description:</label>
                                            <textarea id="schedule-description" tabindex="2" class="long"></textarea>
                                        </li>
										<li>
                                            <label for="schedule-description">Description:</label>
                                            <textarea id="schedule-description" tabindex="2" class="long"></textarea>
                                        </li>
										<li>
                                            <label for="schedule-description">Description:</label>
                                            <textarea id="schedule-description" tabindex="2" class="long"></textarea>
                                        </li>
										<li>
                                            <label for="schedule-description">Description:</label>
                                            <textarea id="schedule-description" tabindex="2" class="long"></textarea>
                                        </li>
										<li>
<input type="file" id="files" name="filesd[]" multiple />
<output id="list"></output>




                                        </li>
                                    </ul>
                                </fieldset>
								<fieldset class="demand-read-schedule-fields hide">
                                	<legend>Scheduled Demand Reset Options</legend>
									<ul>
                                        <li>
                                            <label for="enrollment-code">Program devices:</label>
                                            <input type="text" id="enrollment-code" class="date-short" /> days prior to event at <input type="text" id="" class="time short" value="9:00am" />
                                        </li>
                                    </ul>
								</fieldset>

                                <fieldset>
                                	<legend>Select a Group or import a delimited list of <strong>SmartPoints</strong></legend>
	                            </fieldset>
                                <fieldset>
                                    <legend><a href="" class="text-button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> Groups</a></legend>
                                    <div class="spindown-child">
                                            <!-- START: Group Search -->
                                            <div class="advanced-search-container group yui-gf">
                                                <div class="yui-u first">
                                                    <h5>Groups
                                                        <small>Tip: Check or search for Groups to include in Event. Unchecked Groups are excluded by default.</small>
                                                    </h5>
                                                </div>
                                                <div class="yui-u">
                                                   <form enctype="multipart/form-data" method="POST" action="//jquery-file-upload.appspot.com/" id="fileupload" class="">
        <!-- Redirect browsers with JavaScript disabled to the origin page -->
        <noscript>&lt;input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"&gt;</noscript>
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" multiple="" name="filesd[]">
                </span>
                <button class="btn btn-primary start" type="submit">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button class="btn btn-warning cancel" type="reset">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <button class="btn btn-danger delete" type="button">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active">
                    <div style="width:0%;" class="progress-bar progress-bar-success"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>

                                                </div>
                                            </div>
                                            <!-- END: Group Search -->
                                    </div>
							 </fieldset>
                            <fieldset>
                                <legend><a href="" class="text-button spindown"><span class="ui-icon-triangle-1-e ui-icon"></span> Upload List</a></legend>
                                <div class="spindown-child">
                                    <!-- START: Configuration Search -->
                                    <div class="advanced-search-container configuration yui-gf">
                                        <div class="yui-u first">
                                            <h5>Delimited List
                                                <small>Tip: Select file or paste your comma delimited list of Meter or FlexNet ID's here to apply the Action.</small>
                                            </h5>
                                        </div>
                                        <div class="yui-u">
                                            <ul class="collapse">
                                                <li class="highlight">
                                                    <label for="upload-ids">Import by:</label>
                                                    <div class="radio sui-padv">
                                                        <input type="radio" name="upload-type" id="upload-type-meter" value="meter" checked="checked"><label for="upload-type-meter">Meter ID</label>
                                                        <input type="radio" name="upload-type" id="upload-type-flexnet" value="flexnet"><label for="upload-type-flexnet">FlexNet ID</label>
                                                    </div>
                                                </li>
                                                <li class="row-pad"><div class="sui-pad">Upload file</div><input type="file" id="upload-ids" /> <span class="note">Comma delimited list of SmartPoints &ldquo;,&rdquo;.</span></li>
                                                <li class="row-pad"><div class="sui-pad">or copy and paste</div><textarea id="groupDescription" rows="8" cols="20" class="long"></textarea></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- END: Address Search -->
                                 </div>
                           </fieldset>
                            <fieldset>
                                <legend>Monitor Event</legend>
                                    <!-- START: monitor event -->
                                    <div class="advanced-search-container configuration yui-gf">
                                        <div class="yui-u first">
                                            <h5>
                                                <small>Tip: Define how you would like to monitor this event in the system. If you uncheck all items you can still view results in event history.</small>
                                            </h5>
                                        </div>
                                        <div class="yui-u">
                                            <ul class="collapse">
                                                <li class="checkbox"><input type="checkbox" checked/> <label for="">Recent Requests</label></li>
                                                <li class="checkbox"><input type="checkbox" /> <label for="">Dashboard</label></li>
                                               <!-- <li class="checkbox"><input type="checkbox" checked/> <label for="">Email me a summary</label></li>-->
                                            </ul>
                                        </div>
                                    </div>
                                    <!-- END: monitor event -->
                           </fieldset>
                            <fieldset id="">
                                <legend><strong label>Apply To&hellip;</strong> 0 SmartPoints <span class="note"><em>Number of SmartPoints may change as Group members are added or removed</em></span></legend>
                                <ul>
									<!--<li class="highlight row-pad checkbox"><span id="lock-icon" class="unlocked"></span><input type="checkbox" id="lock"> <label for="lock">Lock this Event for all other Roles</label></li>                -->
                                    <li class="row-pad"><input type="submit" id="createAction" class="button" value="Create Event" /> or <a href="system-iq-event.html" class="cancel">Cancel</a></li>
                                </ul>
                            </fieldset>
                          </form>
                       </div>
				</div>
              </div>
			  <form enctype="multipart/form-data" method="POST" action="ecomode/upload" id="fileuploads">
        <!-- Redirect browsers with JavaScript disabled to the origin page -->
        <noscript>&lt;input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"&gt;</noscript>
        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <!-- The fileinput-button span is used to style the file input field as button -->
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>Add files...</span>
                    <input type="file" multiple="" name="filese">
                </span>
                <button class="btn btn-primary start" type="submit">
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>Start upload</span>
                </button>
                <button class="btn btn-warning cancel" type="reset">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>Cancel upload</span>
                </button>
                <button class="btn btn-danger delete" type="button">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>Delete</span>
                </button>
                <input type="checkbox" class="toggle">
                <!-- The global file processing state -->
                <span class="fileupload-process"></span>
            </div>
            <!-- The global progress state -->
            <div class="col-lg-5 fileupload-progress fade">
                <!-- The global progress bar -->
                <div aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active">
                    <div style="width:0%;" class="progress-bar progress-bar-success"></div>
                </div>
                <!-- The extended global progress state -->
                <div class="progress-extended">&nbsp;</div>
            </div>
        </div>
        <!-- The table listing the files available for upload/download -->
        <table class="table table-striped" role="presentation"><tbody class="files"></tbody></table>
    <div class="alert alert-danger">Upload server currently unavailable - Wed Feb 26 2014 22:10:41 GMT-0300</div></form>
            </div>
		</div>
      </div>
    </div>
    <div id="ft" class="yui-g">
        <div class="yui-u first">
            <div class="vcard">               <div class="fn org">&copy;2011 Sensus Metering Systems</div>
               <div class="adr">
                 <div class="street-address">12345 Metering Road</div>
                 <div>
                   <span class="locality">Raleigh</span>,
                   <span class="region">NC</span> <span class="postal-code">27602</span>
                 </div>
                 <div class="country-name">USA</div>
	        	<p class="version">v1.0.0</p>
               </div>
<!--
               <div>Phone: <span class="tel">+1-727-231-11</span></div>
               <div>Email: <span class="email">info@wikimedia.org</span></div>
               <div>
                 <span class="tel"><span class="type">Fax</span>:
                 <span class="value">+1-727-258-0207</span></span>
               </div>
-->
             </div>
        </div>
        <div class="yui-u footer-links"><!--            <ul class="link-list">
                <li><a href="">Training</a></li>
                <li><a href="">Support</a></li>
                <li class="last"><a href="">Contact Us</a></li>
            </ul>-->
</div>
    </div>
</div>


<!-- DIALOG START - Add Time -->
<div id="action-dialog-repeat-define" class="action-dialog">
	<form id="add-repeat-definition-form" name="add-repeat-definition-form" method="post" >
    <!-- Messaging -->
    <div id="save-form-messaging" class="messaging"><span class="message"></span></div>
        <fieldset class="label-right">
                <ul>
                    <li class="highlight">
                    	<label for="repeats" class="title short">Repeats:</label>
                        <select id="repeats">
                            <option value="day">Daily</option>
                            <option value="weekday">Every weekday (Mon-Fri)</option>
                            <option value="every-other">Every Mon., Wed., and Fri</option>
                            <option value="every-t">Every Tues., and Thurs.</option>
                            <option value="weekly" selected="selected">Weekly</option>
                            <option value="monthly">Monthly</option>
                            <option value="yearly">Yearly</option>
                            <option value="custom">Custom</option>
                        </select>
					</li>
                    <li id="upload-dates">
                        <div class="sui-pad">Copy and paste &ldquo;,&rdquo; delimeted dates (mm/dd/yyyy hh:mm) </div>
                        <textarea id="group-description" class="long" cols="20" rows="8"></textarea>
                    </li>
                    <li id="starts-on">
                    	<label for="starts-on" class="title short">Starts on:</label>
                        <input type="text" class="short datepicker" id="starts-on" value="6/27/2011" />
					</li>
                    <li id="repeats-every">
                    	<label for="saved-search-description" class="title short">Repeats every:</label>
                        <input type="text" class="date-short" id="repeats-every-value" value="1" /> <span id="repeats-text">weeks</span>
					</li>
                    <li id="repeats-monthly-custom">
                    	<label for="startime-combobox" class="title short">Repeat by:</label>
                        <ul class="radio-list">
                            <li class="radio"><input type="radio" id="day-month" name="repeat-by" value="never" checked="checked" /><label for="never">date</label> <input type="radio" id="day-week" name="repeat-by" value="after"><label for="after" />day of week</label></li>
                        </ul>
                    </li>
                    <li id="day-custom-container">
                    	<label class="title short">Repeats on:</label>
                        <div id="" class="event-day-custom checkbox">
                            <input type="checkbox" id="day1Mon" value="monday" checked="checked"><label for="day1Mon">Mon</label>
                            <input type="checkbox" id="day1Tue" value="tuesday"><label for="day1Tue">Tue</label>
                            <input type="checkbox" id="day1Wed" value="wednesday"><label for="day1Wed">Wed</label>
                            <input type="checkbox" id="day1Thu" value="thursday"><label for="day1Thu">Thu</label>
                            <input type="checkbox" id="day1Fri" value="friday"><label for="day1Fri">Fri</label>
                            <input type="checkbox" id="day1Sat" value="saturday"><label for="day1Sat">Sat</label>
                            <input type="checkbox" id="day1Sun" value="sunday"><label for="day1Sun">Sun</label>
                        </div>
                    </li>
                    <li id="ends-on">
                    	<label for="startime-combobox" class="title short">Ends:</label>
                        <ul class="radio-list">
                            <li class="radio"><input type="radio" id="never" name="ends" value="never" checked="checked" /><label for="never">Never</label></li>
                            <li class="radio"><input type="radio" id="after" name="ends" value="after"><label for="after" />After</label> <input type="text" class="date-short" /> occurrences</li>
                            <li class="radio"><input type="radio" id="on" name="ends" value="on"><label for="on" />On</label> <input type="text" class="short datepicker" /></li>
                        </ul>
                    </li>
                    <li class="ui-state-highlight">
                    	<label for="starts-on" class="title short"><strong>Summary:</strong></label>
                        <p class="read-only" ><strong id="repeats-summary">Weekly on Monday</strong></p>
					</li>
                </ul>
   		 </fieldset>
      </form>






<script>
  function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {

      // Only process image files.
      if (!f.type.match('image.*')) {
        continue;
      }

      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          // Render thumbnail.
          var span = document.createElement('span');
		  //span.innerHTML = ['<table class="table table-striped" role="presentation"><tbody class="files"><tr class="template-upload fade in"><td><span class="preview"><canvas width="72" height="80"><img class="thumb" src="', e.target.result,'" title="', escape(theFile.name), '"/></canvas></span></td><td><p class="name">1912492_641211102592938_434653435_n.jpg</p><strong class="error text-danger"></strong></td><td><p class="size">75.58 KB</p><div aria-valuenow="0" aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active"><div style="width:0%;" class="progress-bar progress-bar-success"></div></div></td><td><button class="btn btn-primary start"><i class="glyphicon glyphicon-upload"></i><span>Start</span></button><button class="btn btn-warning cancel"><i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span></button></td></tr></tbody></table>'].join('');
          span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join('');
          document.getElementById('list').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
  }

$(document).ready(function(){
/*     $('form').bind('submit', function(){
        var params = $(this.elements).serialize();
        $.ajax({
            type: 'POST',
	    url: "ecomode/upload",
            data: params,
            beforeSend: function(){
		$('#loading').show(),
                $('#loading').html("Carregando...");
            },
            success: function(txt){
		$('#loading').hide(),
                $('#conteudo').prepend(txt);
            }
        })
        return false;
    }); */

/*
			 $('#form_upload_img').ajaxSubmit({
                target: '#retorno',
                beforeSubmit: antes,
                success: sucesso
            });


    function antes()  {
        $('#retorno').html(''); //Apaga o conteúdo atual do elemento de retorno [Erase the actual content of the return element]
        $('#logo_img').fadeOut('fast'); //Esconde a imagem atual. [hides the actual image]

        //Vc pode tb colocar no lugar uma imagem de carregando... [you can also put an image of "loading..."]
    };

    function sucesso(dados) {
        console.log(dados)
    };     */

	$('#forms').ajaxForm({

        uploadProgress: function(event, position, total, percentComplete) {
            $('progress').attr('value',percentComplete);
            $('#porcentagem').html(percentComplete+'%');
        },
		beforeSubmit: function(data) {
             document.getElementById('files').addEventListener('change', handleFileSelect, false);
        },
        success: function(data) {
            $('progress').attr('value','100');
        $('#porcentagem').html('100%');
            $('pre').html(data);
        }

});

});

</script>
</div>
<div id="dialog-map"></div>
<%-- START Main Page Content --%>
<%-- START Main Content Container --%>
<jsp:include page="../../scripts/pages/exercicio/exercicio_create_init.js.jsp" flush="true"/>

</sec:authorize>


