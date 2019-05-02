$(document).ready(function() {
    $.ajax({
        url:'/task/code',
        type:'get',
        data: {
            taskId: 'section01.task01'
        }
    }).done(function(response) {
        handleResponse(JSON.parse(response));
    }).fail(function() {
        alert('Oops, something is wrong...')
    });


    function handleResponse(response) {
        ace.edit("code-editor").setValue(response.code, 1);
    }
});