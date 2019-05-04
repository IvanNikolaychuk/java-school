$(document).ready(function () {
    var editor = ace.edit("code-editor");

    editor.setTheme("ace/theme/chrome");
    editor.session.setMode("ace/mode/java");

    editor.setOptions({
        enableBasicAutocompletion: true
    });
});