$(document).ready(function() {
    $("#task-section .run-code").click(function() {
        var $code = $("#task-section .code-section textarea").val();
        var $input = $('#task-section .input-section textarea').val();
        $.ajax({
            url:'/runCode',
            type:'post',
            data: {
                code: $code,
                input: $input
            }
        }).done(function(data) {
            var output = JSON.parse(data).output;
            $("#task-section .output-section textarea").text(output);
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

});