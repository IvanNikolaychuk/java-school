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
        changeRequirementResultIcons(response);
        changeStatus(response);
    }

    function changeRequirementResultIcons(response) {
        var $requirementTextDiv = $(".requirement-text");
        for (var i = 0; i < $requirementTextDiv.length; i++) {
            var $requirementName = $($requirementTextDiv[i]).text();
            var $iconDiv = $($requirementTextDiv[i]).siblings(".requirement-result-icon")[0];
            if (response.results[$requirementName] === 'PASSED') {
                $($iconDiv).css("background-image", "url('../icons/status/passed.svg')")
            } else {
                $($iconDiv).css("background-image", "url('../icons/status/failed.svg')")
            }
        }
    }

    function changeStatus(response) {
        if (response.passed) {
            getStatus().css("background", "#3dce2bb3");
            getStatus().text('All check passed. Great job!');
        } else if (hasCompilationErrors(response.compilation)) {
            showCompilationErrors(response.compilation);
            getStatus().css("background", "#b98b09b8");
            getStatus().text('There is a compilation error.');
        } else {
            getStatus().css("background", "#ce2b2bb8");
            getStatus().text('Program compiled, but some checks failed. Please try one more time.');
        }

        showStatus();
    }

    function getTaskId() {
        var href = window.location.href;
        var relativePath = href.lastIndexOf('task/');
        return href.substr(relativePath).replace('task/', '');
    }
});