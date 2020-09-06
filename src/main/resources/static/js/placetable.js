$(document).ready(function() {
    $('#placetable').DataTable({
        "order": [[ 4, "desc" ]],
        paging: false
    }).draw();
} );

$(document).ready(function(){
    $(".nav-tabs a").click(function(){
        $('#placetable').DataTable()
            .columns(6) //Where?
            .search($(this).text())
            .draw();
        //console.log($(this).text());
    });
});