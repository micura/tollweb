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

    // Apply a search to the second table for the demo
    $('#inactivePlaceTable').DataTable({
        "order": [[ 4, "desc" ]],
        "paging": false,
    }).columns(5)
        .search('Törölve')
        .draw();
} );