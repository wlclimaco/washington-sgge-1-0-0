<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags' %>
<script type="text/javascript">
//async long polling function
var messagesWaiting = false;
function getMessages()
{
    if(!messagesWaiting)
    {
        $.ajax({
        			type: "GET",
        			url: WDHost + "qat-webdaptive/async/api/fetchMessages/1",
                 	dataType:"text",
                 	xhrFields: {
                 	       withCredentials: true
                 	},
			        async: true,
			        cache: false,
			        timeout:30000,
                 	success: function(data,textStatus,jqXHR)
                 	{
                        messagesWaiting = false;
						try
                        {
						     var arr = eval(data);
						     var dataValue = "";
                             for(var i=0; i<arr.length; i++)
                             {
                            	dataValue = dataValue + arr[i] + "\n";
                             }
                             $("#txtaMessages").val(dataValue + "\nPolled@" + $.formatter.date(new Date(), "yyyy-MM-dd'T'HH:mm:ss"));
                        }
                        catch(e)
                        {
                           	wd.core.displayNotificationMessage('#StatusBar', e.message, true, 'error', 5000);
                        }
                 	},
                 	error: function (jqXHR, textStatus, errorThrown) { wd.core.process_xhr_error(jqXHR, textStatus, errorThrown); }
        });
        messagesWaiting = true;
    }
};

</script>