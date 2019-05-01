$(document).ready(function() {
    $("#task-section .compilation-error").hide();
    $("#task-section .run-code").click(function() {
        var $code = $("#task-section .code-section textarea").val();
        var $programInput = $('#task-section .input-section textarea').val();
        $.ajax({
            url:'/runCode',
            type:'post',
            data: {
                code: $code,
                programInput: $programInput,
                taskId: 'taskId'
            }
        }).done(function(response) {
            handleResponse(JSON.parse(response));
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

    function handleResponse(response) {
        if (response.compilation.problems.length > 0) {
            $("#task-section .output-section").hide();
            $("#task-section .compilation-error").show();
            $("#task-section .compilation-error textarea").text(problemSummary(response.compilation.problems));
        } else {
            $("#task-section .compilation-error").hide();
            $("#task-section .output-section").show();
            $("#task-section .output-section textarea").text(response.output);
        }
    }

    function problemSummary(problems) {
        var summary = 'Problems:\n';
        for (var i = 0; i < problems.length; i++) {
            summary += (i + 1) + '. ' + problems[i];
        }

        return summary
    }
});