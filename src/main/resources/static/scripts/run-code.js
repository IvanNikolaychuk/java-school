$(document).ready(function() {
    $("#task-section .run-code").click(function() {
        hideStatus();

        $.ajax({
            url:'/runCode',
            type:'post',
            data: {
                code: ace.edit("code-editor").getValue(),
                programInput: $('#task-section .input-section textarea').val()
            }
        }).done(function(response) {
            handleResponse(JSON.parse(response));
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

    function handleResponse(response) {
        if (hasCompilationErrors(response)) {
            showCompilationErrors(response);
        } else {
            hideCompilationErrorSection();
            $("#task-section .output-section").show();
            $("#task-section .output-section textarea").text(response.output);
        }
    }
});