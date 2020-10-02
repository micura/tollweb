$(document).ready(function(){
    $(".searchable-tab a").click(function(e){
        let filteredText = $(e.target).text();

        if (filteredText == 'Összes') {
            filteredText = '';
        }

        $('#activePlaceTable').DataTable()
            .columns(6) //Where?
            .search(filteredText)
            .draw();

        $('#inactivePlaceTable').DataTable()
            .columns(6) //Where?
            .search(filteredText)
            .draw()
    });
})

$(document).ready(function() {
    $('a[data-toggle="tab"]').on( 'shown.bs.tab', function (e) {
        $.fn.dataTable.tables( {visible: true, api: true} ).columns.adjust();
    } );

    $('#activePlaceTable').DataTable( {
        "order": [[ 4, "desc" ]],
        "paging": false,
    }).columns(5)
        .search('Aktív')
        .draw();

    // Apply a search to the second table
    $('#inactivePlaceTable').DataTable({
        "order": [[ 4, "desc" ]],
        "paging": false,
    }).columns(5)
        .search('Törölve')
        .draw();
} );

$(document).ready(function(){
    $(".show-btn").click(function(){
        $(".new-contact").toggle();
    });
});

function insertNewPlace() {
    var newPlace = {
        name: document.getElementById("name").value,
        city: document.getElementById("city").value,
        amount: document.getElementById("amount").value,
        phoneNumber: document.getElementById("phoneNumber").value,
        syncDate: document.getElementById("date").value,
        status: "Aktív",
        source: document.getElementById("source").value
    };

    var token = $("meta[name='_csrf']").attr("content");
    $.ajax({
        url : '/saveplace', // url to make request
        headers: {"X-CSRF-TOKEN": token}, //send CSRF token in header
        contentType: "application/json",
        type : 'POST',
        data: JSON.stringify(newPlace),
    });
}