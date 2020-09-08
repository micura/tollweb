$(document).ready(function() {
    $('#placetable').DataTable({
        "order": [[ 4, "desc" ]],
        "paging": false,
    }).draw();
} );

$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $('#placetable').DataTable({
            "columnDefs": [
                { "width": "30%", "targets": 1 }
            ]}
        )
        .columns(6) //Where?
        .search($(this).text())
        .draw();
        //console.log($(this).text());
    });
});