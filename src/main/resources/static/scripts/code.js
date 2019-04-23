$(document).ready(function() {
    $("#run-code").click(function() {
        var $code = $("#code").val();
        var $input = $('#input').val();
        $.ajax({
            url:'/runCode',
            type:'post',
            data: {
                code: $code,
                input: $input
            }
        }).done(function(data) {
            alert(data);
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

});