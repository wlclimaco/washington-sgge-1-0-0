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
</style>

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
													<input type="text" id="exercicio-name-create" tabindex="1" class="required long"/> <button id="uploadvideos" class="btn btn-warning cancel ui-button-text">
													 <output id="videos"></output>
													 <!--<iframe class="thumb" src="//www.youtube.com/embed/IAnzAWt5tCI" frameborder="0" allowfullscreen></iframe>-->
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
		  span.innerHTML = ['<table class="table table-striped" role="presentation"><tbody class="files"><tr class="template-upload fade in"><td><span class="preview" ><img class="thumb" src="', e.target.result,'" title="', escape(theFile.name), '"/></span></td><td class="test"><p class="name">"'+escape(theFile.name)+'"    </p><strong class="error text-danger"></strong></td><td class="test"><p class="size">"'+theFile.size+'"</p><div aria-valuenow="0" aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active"><div style="width:0%;" class="progress-bar progress-bar-success"></div></div><td class="test"><progress value="0" max="100"></progress><span id="porcentagem">0%     </span></td></td><td class="test"><button type="submit" class="btn btn-primary start ui-button-text"><i class="glyphicon glyphicon-upload"></i><span>Start</span></button><button class="btn btn-warning cancel ui-button-text"><i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span></button></td></tr></tbody></table>'].join('');

/*           span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join(''); */
          document.getElementById('list').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }

  }

  document.getElementById('files').addEventListener('change', handleFileSelect, false);

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
			$('.btn-primary').hide();
		console.log(data);
		$('#fotos').val($('#fotos').val()+"|"+data.foto.cdfoto);
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


