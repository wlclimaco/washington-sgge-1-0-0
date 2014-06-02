<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
$(document).ready(function ()
{ 
	$('#StatusBar').jnotifyInizialize({
        oneAtTime: true
    });
	
	$('#county_id_mvc_hdr').html(county.grid.cid.title); 
	$('#county_desc_mvc_hdr').html(county.grid.cdescription.title); 


	if ($('#be_status').val() == false)
	{
		wd.core.displayNotificationMessage('#StatusBar',"Operation Successful: " + $('#be_status').val() , false, 'error', 4000);			
	}
});
</script>
		