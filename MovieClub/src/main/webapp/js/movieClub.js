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
    
    $('#submit-member-search').click(function(event){
        
        var member = $('#search-member-selected').val();
        var member_id = member.replace(/[^0-9]*-.*/i,"");
        
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/MovieClub/searchByMember/"+member_id,
            dataType: 'json',
            success: function(data) {   
                showResults(data);   
            }
            
        });
    });
    
    $('#search-date-range-button').click(function(event){
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/MovieClub/searchByDateRange",
            data: JSON.stringify({
                startDate: $('#startDate').val(),
                endDate: $('#endDate').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function(data) {
                showResults(data);
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
            var hostText = '<p class="text-center">Host: ' + host + "</p>";
            $('#upcoming-events').append(hostText);

            var locationText = "";
            if (location === null) {
                locationText = "Location: TBD - Check back soon";
                $('#upcoming-events').append(locationText);
            } else {
                locationText = '<p class="text-center">Location: ' + location + "</p>";
                $('#upcoming-events').append(locationText);
            }

            var themeText = "";
            if (theme === null) {
                themeText = "Theme: TBD - Check back soon";
                $('#upcoming-events').append(themeText);
            } else {
                var themeText = '<p class="text-center">Theme: ' + theme + "</p>";
                $('#upcoming-events').append(themeText);
            }

        });
    }
}

function showMemberSelectorForm() {
    $('#member-selector-form').show();
    $('#date-range-select-form').hide();    
}

function hideMemberSelectorForm() {
    $('#member-selector-form').hide();
}

function showDateRangeSelectorForm() {
    $('#date-range-select-form').show();
    $('#member-selector-form').hide();
}

function hideDateRangeSelectForm() {
    $('#date-range-select-form').hide();
}

function showResults(data) {
    $('#event-search-results').empty();
    
    if(data.length === 0) {
        var message = "<p>Your search did not return any results</p>";
        $('#no-data-message').append(message);
    } else {
        $('#eventResultsTable').show();
        $.each(data, function(index, dataItem){
            var monthText = dataItem.event_date.monthValue;
            var dayText = dataItem.event_date.dayOfMonth;
            var yearText = dataItem.event_date.year;
            var themeText = dataItem.theme;
            var member = dataItem.member.first_name + " " + dataItem.member.last_name;
            var movieText = dataItem.movie_name;
            var locationText = dataItem.location;
            
            var row = "<tr>";
            row += '<td>'+monthText+'/'+dayText+'/'+yearText+'</td>';
            
            if(themeText.length !== 0) {
                row += '<td>'+themeText+'</td>';
            } else {row += '<td>'+""+'</td>';}
            
            row += '<td>'+member+'</td>';
            
            if(movieText.length !== 0) {
                row += '<td>'+movieText+'</td>';
            } else {row += '<td>'+""+'</td>';}
            
            if(locationText !== null) {
                row += '<td>'+locationText+'</td>';
            } else {row += '<td>'+""+'</td>';}
            row += "</tr>";
            
            $('#event-search-results').append(row);
        }); 
    }
}
