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
  }
</style>
<div id="eco-mode">
<form id="import-eco-mode" name="importEcoModeForm"  method="post" action="ecomode/upload" enctype="multipart/form-data">
	<div id="list-baseline-type-contianer">
    	<fieldset>
      	<p class="desc"><s:message code="systemsettings.ecomode.page.note" /> <span class="note"><a href="#" class="text_button"><s:message code="systemsettings.ecomode.page.importfileformat" /></a></span></p>
             <ul>
                 <li class="highlight">
                     <label for="upload-ecomode"><s:message code="systemsettings.ecomode.page.selectfile" /> <span class="required-small">*<s:message code="systemsettings.ecomode.page.required" /></span></label>
                     <input id="upload-ecomode" name="upload" type="file" multiple="true"/>
					 <input type="file" id="files" name="files[]" multiple="true" />
						<output id="list"></output>
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
 </form>
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

  document.getElementById('files').addEventListener('change', handleFileSelect, false);
</script>
<%-- START Main Page Content --%>
<%@ include file="../../scripts/pages/ecomode/ecomode_main.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_actions.js.jsp" %>
<%@ include file="../../scripts/pages/ecomode/ecomode_init.js.jsp" %>
<%-- START Main Content Container --%>
</sec:authorize>