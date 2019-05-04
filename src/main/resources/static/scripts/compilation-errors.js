function hasCompilationErrors(executionResult) {
    return executionResult.compilation.problems.length > 0;
}

function showCompilationErrors(executionResult) {
    $("#task-section .output-section").hide();
    $("#task-section .compilation-error").show();
    $("#task-section .compilation-error textarea").text(compilationErrorsSummary(executionResult));
}

function compilationErrorsSummary(executionResult) {
    var problems = executionResult.compilation.problems;

    var summary = 'Problems:\n';
    for (var i = 0; i < problems.length; i++) {
        summary += (i + 1) + '. ' + problems[i];
    }

    return summary
}
