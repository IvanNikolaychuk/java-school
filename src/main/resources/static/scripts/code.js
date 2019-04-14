$(document).ready(function() {
    $("#run-code").click(function() {
        var $code = $("#code").val();
        $.ajax({
            url:'/runCode',
            type:'post',
            data: {
                code: $code
            }
        }).done(function(data) {
            alert(data);
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

});