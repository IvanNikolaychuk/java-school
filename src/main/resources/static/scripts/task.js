$(document).ready(function() {
    hideStatus();
    hideRequirementResultIcons();
    hideCompilationErrorSection();
});

function getStatus() {
    return $("#task-section .program-section .status");
}

function showStatus() {
    $("#code-editor").css("height", "70%");
    getStatus().show();
}

function hideStatus() {
    $("#code-editor").css("height", "77%");
    getStatus().hide();
}

function hideRequirementResultIcons() {
    $(".requirement-result-icon").hide();
}

function showRequirementResultIcons() {
    $(".requirement-result-icon").show();
}
