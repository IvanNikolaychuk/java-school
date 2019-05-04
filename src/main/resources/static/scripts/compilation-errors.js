function hasCompilationErrors(compilation) {
    return compilation.problems.length > 0;
}

function showCompilationErrors(compilation) {
    $("#task-section .output-section").hide();
    $("#task-section .compilation-error").show();
    $("#task-section .compilation-error textarea").text(compilationErrorsSummary(compilation));
}

function hideCompilationErrorSection() {
    $("#task-section .compilation-error").hide()
}

function compilationErrorsSummary(compilation) {
    var problems = compilation.problems;

    var summary = 'Problems:\n';
    for (var i = 0; i < problems.length; i++) {
        summary += (i + 1) + '. ' + problems[i];
    }

    return summary
}
