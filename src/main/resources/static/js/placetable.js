/*
$(document).ready(function() {
    $('#activePlaceTable').DataTable({
        "order": [[ 4, "desc" ]],
        "paging": false,
    }).draw();
} );


$(document).ready(function(){
    $(".nav-tabs a").click(function(e){
        $('#activePlaceTable').DataTable()
        .columns(6) //Where?
        .search($(e.target).text())
        .draw();
    });
})
 */

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