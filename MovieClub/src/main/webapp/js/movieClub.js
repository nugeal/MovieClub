$(document).ready(function () {

    $('#view-details-button').click(function (event) {
        //ajax call to webservice for the upcoming event details
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/MovieClub/upcoming",
            dataType: 'json',
            success: function (data, status) {
                homeMessage(data);
            },
            error: function () {
                $("#errorMessages")
                    .append($("<li>")
                    .attr({class: "list-group-item list-group-item-danger"})
                    .text("Error calling web service. Please try again later."));
            }
        });
    });
});

function homeMessage(data) {
    $('#upcoming-events').empty();
    
    if (data.length === 0) {
        var message = '<p class="text-center"> No upcoming events</p>';
        $('#upcoming-events').append(message);
    } else {
        $.each(data, function (index, dataItem) {
            var month = dataItem.event_date.monthValue;
            var day = dataItem.event_date.dayOfMonth;
            var year = dataItem.event_date.year;
            var host = dataItem.member.first_name + " " + dataItem.member.last_name;
            var location = dataItem.location;
            var theme = dataItem.theme;

            var date = '<p class="text-center">';
            date += 'Date: ' + month + '/' + day + '/' + year + '</p>';
            $('#upcoming-events').append(date);
            var host = '<p class="text-center">Host: ' + host + "</p>";
            $('#upcoming-events').append(host);
            var location = '<p class="text-center">Location: ' + location + "</p>";
            $('#upcoming-events').append(location);
            var theme = '<p class="text-center">Theme: ' + theme + "</p>";
            $('#upcoming-events').append(theme);
        });
    }
}
