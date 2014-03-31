<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
var $actionDialog;
var $actionConfirmCP;
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
$(document).ready(function() {
	var videos = [];
		//INIT UI
		//Hide
		$('.reset-container, .sort-options, .view-options, #dialog-analyze, .missing-data, .status-viewport-loading, .spindown-child, #request-complete, .summary-container .summary, .filter-select, #processing-container, #system-messaging, #message-map, .messaging, .action-dialog, #dialog-map, .blankslate, #request-processing, .ui-state-error, .cp-title, #cp-active-container').hide();
		$('.toggle.off').next(".collapse").hide();
		$('#tabs').tabs();
		$('#system-messaging').pinFooter();
		$('.datepicker').datepicker();
		$('.datepicker-day').datepicker({ dateFormat: 'dd'});

		//INIT Map toggle icons
		$("#views").buttonset();
		$('.list').button( "option", "icons", {primary:'ui-icon-list'});

		$( ".arrow-button-down" ).button({
            icons: {
                primary: "ui-icon-triangle-1-s"
            }
		});

		var buttonsets = $("#switch, #switch-map, .switch").buttonset();
		// INIT href buttons
		$("a.button , input:submit").button();

		// INIT sortable
		$( ".sortable" ).sortable({
			connectWith: ".connected-sortable"
		}).disableSelection();

		$(".close-info").click(function() {
			return false;
		});
		$(".add-new-group").click(function() {
			$('#add-group-form .add-group-control').toggle();
			return false;
		});

		//sort prompt
		$('th.sort, .button-action-expand').hover(
			function () {
				$('div', this).stop(true, true).delay(200).slideDown(200);
			},
			function () {
				$('div', this).stop(true, true).slideUp(200);
		});

		//Trigger LRP on click
		$(".processing-action").click(function(e) {
			e.preventDefault();
			$actionLrpDialog.dialog('open');
		});

				$('.spindown').click(function(e) {
			e.preventDefault();
			$(this).parent().next('.spindown-child').toggle('blind',null,500);
			if($('span', this).hasClass('ui-icon-triangle-1-e')){
				$('span', this).switchClass('ui-icon-triangle-1-e', 'ui-icon-triangle-1-s', 500);
			}
			else{
				$('span', this).switchClass('ui-icon-triangle-1-s','ui-icon-triangle-1-e', 500);
			}
		});


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
			$('#fotos').val($('#fotos').val()+"|"+data.foto.cdfoto);
            $('pre').html(data);
        }

	});

$('#save').click(function() {
		var test = $('#fotos').val()
		a =  test.split('|')
		x=0;

		var fotos = [];
		while( x < a.length)
		{
		if((a[x] != ' ')&&(a[x] != ''))
			{
				var foto = {};
				foto.cdfoto= parseInt(a[x], 10);;
				fotos.push(foto);
			}
			x++;
		}
		var name          = $('#exercicio-name-create').val(),
			description   = $('#exercicio-description').val(),
		    grupoMuscular = $("#grupo-muscular option:selected").attr('id');

		var fnCallBack = function(data){

			if (data.operationSuccess){
			//	sensus.widgets.datatable.reloadTable(sensus.pages.group.groupTable);
			}
		};
		var oRequests = new ExercicioRequest(1,name,description,grupoMuscular,fotos,videos);

		$.sc.getJson('api/exercicio/insert',oRequests, false, fnCallBack);



		//{"grupomusculars":[{"cdgrmusc":0,"musculo":"55","dsgrmusc":"55","createdate":"2014-03-27-00-00-00-000","createuser":"superuser","tenantid":1,"userid":1}]}
});

$('#uploadvideos').click(function() {

 var span = document.createElement('span');
		  videoTest = $('#exercicio-videos').val();
		  span.innerHTML = ['<table class="table table-striped" role="presentation"><tbody class="files">'+
				'<tr class="template-upload fade in"><td><span class="preview" ><iframe class="reviwVideos" src="'+videoTest+'" frameborder="0" allowfullscreen></iframe></span></td>'+
				'<td class="test"><button class="btn btn-warning cancel ui-button-text"><i class="glyphicon glyphicon-ban-circle"></i><span>Cancel</span></button></td></tr></tbody></table>'].join('');

debugger;
          document.getElementById('videos').insertBefore(span, null);
		  var video = {};
				video.lcVideo = videoTest;
				videos.push(video);

console.log(videos);
});


		var fnCallBack = function(data){
			if (data.operationSuccess){
				console.log(data.grupomusculars);
				var html = "";
				for (var i =0;i<data.grupomusculars.length;i++){
					html = html +'<option id="'+data.grupomusculars[i].cdgrmusc+'" value="'+data.grupomusculars[i].musculo+'">'+data.grupomusculars[i].musculo+'</option>'
				}
				$('#grupo-muscular').append(html);
			}
		};
		var oRequest = '{"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"grupomusculars":[{"createuser":"superuser","tenantid":1,"userid":1}]}';
		$.sc.getJson('api/grupoMuscular/fetchall',{"startRow":0,"endRow":0,"pageSize":25,"sortExpressions":[{"field":"NAME","direction":"Ascending"}],"grupomusculars":[{"createuser":"superuser","tenantid":1,"userid":1}]}, false, fnCallBack);

$.sc.stopGlobalProgressBar();
});
</script>
