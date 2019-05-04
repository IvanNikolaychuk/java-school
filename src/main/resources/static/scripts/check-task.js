$(document).ready(function() {
    $("#task-section .check-task").click(function() {
        $.ajax({
            url:'/task/verify/' + getTaskId(),
            type:'post',
            data: {
                code: ace.edit("code-editor").getValue()
            }
        }).done(function(response) {
            handleResponse(JSON.parse(response));
        }).fail(function() {
            alert('Oops, something is wrong...')
        })
    });

    function handleResponse(response) {
        var $status = $("#task-section .program-section .status");
        $status.show();
        if (response.result) {
            $status.css("background", "#3dce2bb3");
            $status.text('All check passed. Great job!');
        } else if (hasCompilationErrors(response.executionResult)) {
            showCompilationErrors(response.executionResult);
            $status.css("background", "#b98b09b8");
            $status.text('There is a compilation error.');
        } else {
            $status.css("background", "#ce2b2bb8");
            $status.text('Program compiled, but some checks failed. Please try one more time.');
        }
    }

    function getTaskId() {
        var href = window.location.href;
        var relativePath = href.lastIndexOf('task/');
        return href.substr(relativePath).replace('task/', '');
    }
});