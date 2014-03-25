<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<sec:authorize access="hasAnyRole('ROLE_Role.Admin')">
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
</style>
<div id="eco-mode">
	<div id="list-baseline-type-contianer">
    	<fieldset>
      	<p class="desc"><s:message code="systemsettings.ecomode.page.note" /> <span class="note"><a href="#" class="text_button"><s:message code="systemsettings.ecomode.page.importfileformat" /></a></span></p>
             <ul>
                 <li class="highlight">
                     <label for="upload-ecomode"><s:message code="systemsettings.ecomode.page.selectfile" /> <span class="required-small">*<s:message code="systemsettings.ecomode.page.required" /></span></label>

					 <form enctype="multipart/form-data" action="ecomode/upload" method="post" name="forms" id="forms" class="">
						 <input type="file" id="files" name="upload" multiple>
							<output id="list"></output>
					</form>
                 </li>
                 <li  class="chzn-row select-tags">
					<label for="select_tags"><s:message code="systemsettings.ecomode.page.tagtheselights" /> <small class="optional"><s:message code="systemsettings.ecomode.page.optional" /></small></label><br />
					<select data-placeholder="Create or select multiple Tags"  multiple class="chzn-select" id="select_tags" name="uploadTag" tabindex="8">
					</select>
                 </li>
                 <li class="submit-row-pad">
                     <input type="submit" id="submit-ecomode" class="submit-short-form" value="Import Baseline Definitions" /> <s:message code="systemsettings.ecomode.page.or"/> <a href="#" class="cancel-edit cancel"><s:message code="commons.pages.cancel" /></a>
                 </li>
             </ul>
         <!-- START default season container -->
         </fieldset>
     </div>
</div>
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
		  span.innerHTML = ['<table class="table table-striped" role="presentation"><tbody class="files"><tr class="template-upload fade in"><td><span class="preview" ><img class="thumb" src="', e.target.result,'" title="', escape(theFile.name), '"/></span></td><td class="test"><p class="name">"'+escape(theFile.name)+'"    </p><strong class="error text-danger"></strong></td><td class="test"><p class="size">"'+theFile.size+'"</p><div aria-valuenow="0" aria-valuemax="100" aria-valuemin="0" role="progressbar" class="progress progress-striped active"><div style="width:0%;" class="progress-bar progress-bar-success"></div></div><td class="test"><progress value="0" max="100"></progress><span id="porcentagem">0%     </span></td></td><td><button type="submit" class="btn btn-primary start"><i class="glyphicon glyphicon-upload"></i><span>Start</span></button><button class="btn btn-warning cancel"><i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span></button></td></tr></tbody></table>'].join('');

/*           span.innerHTML = ['<img class="thumb" src="', e.target.result,
                            '" title="', escape(theFile.name), '"/>'].join(''); */
          document.getElementById('list').insertBefore(span, null);
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }

/* 	 $('.btn-primary').click(function() {
		debugger
		document.getElementById('files').addEventListener('change', handleFileSelect, false);
		console.log($('#files'))

	 }); */
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
            $('pre').html(data);
        }

});

});
</script>
<%-- START Main Page Content --%>
<%@ include file="../../scripts/pages/ecomode/ecomode_main.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_actions.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_init.js.jsp" %>
<%-- START Main Content Container --%>
</sec:authorize>