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
            var output = JSON.parse(data).output;
            $("#output").text(output);
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

});