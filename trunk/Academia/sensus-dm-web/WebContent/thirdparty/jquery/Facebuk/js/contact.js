$(document).ready(function () {
    $('div#output').hide();
    //bind send message here
    $('#send').click(sendMessage);
    $('button.close').live('click', function () {
        $(this).parent().find('p').html('');
        $(this).parent().hide();
    });
});

/* Contact Form */
function checkEmail(email) {
    var check = /^[\w\.\+-]{1,}\@([\da-zA-Z-]{1,}\.){1,}[\da-zA-Z-]{2,6}$/;
    if (!check.test(email)) {
        return false;
    }
    return true;
}

function sendMessage() {
    
    // receive the provided data
    var name = $("input#name").val();
    var email = $("input#email").val();
    var message = $("textarea#message").val();

    message = 'message';
    // check if all the fields are filled
    if (name == '' || email == '' || message == '') {
        $("div#output").show().html('<button type="button" class="close" data-dismiss="alert-close">x</button><p class="alert-close">You must enter all the fields!</p>');

        return false;
    }

    // verify the email address
    if (!checkEmail(email)) {
        $("div#output").show().html('<button type="button" class="close" data-dismiss="alert">x</button><p>Please enter a valid Email Address</p>');
        return false;
    }

    // make the AJAX request
    var dataString = $('#cform').serialize();
    $.ajax({
        type: "POST",
        url: 'contact.php',
        data: dataString,
        dataType: 'json',
        success: function (data) {
            if (data.success == 0) {
                var errors = '<ul><li>';
                if (data.name_msg != '')
                    errors += data.name_msg + '</li>';
                if (data.email_msg != '')
                    errors += '<li>' + data.email_msg + '</li>';
                if (data.message_msg != '')
                    errors += '<li>' + data.message_msg + '</li>';

                $("div#output").removeClass('alert-success').addClass('alert-error').show().html('<button type="button" class="close" data-dismiss="alert">x</button><p> Could not complete your request. See the errors below!</p>' + errors);
            }
            else if (data.success == 1) {

                $("div#output").removeClass('alert-error').addClass('alert-success').show().html('<button type="button" class="close" data-dismiss="alert">x</button><p>You message has been sent successfully!</p>');
            }

        },
        error: function (error) {
            $("div#output").removeClass('alert-success').addClass('alert-error').show().html('<button type="button" class="close" data-dismiss="alert">x</button><p> Could not complete your request. See the error below!</p>' + error.statusText);
        }
    });

    return false;
}