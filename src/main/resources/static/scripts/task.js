$(document).ready(function() {
    hideStatus();
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
