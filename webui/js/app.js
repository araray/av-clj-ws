$(document).ready(function() {
    $("#repl-form").submit(function(event) {
        event.preventDefault();
        var code = $("#code").val();

        $.ajax({
            url: "http:///127.0.0.1:37587/eval", // Replace with your Leiningen REPL endpoint
            method: "POST",
            data: { code: code },
            success: function(response) {
                $("#result").html("<p>Result: " + response + "</p>");
            },
            error: function() {
                $("#result").html("<p>Error: Unable to evaluate code.</p>");
            }
        });
    });
});

