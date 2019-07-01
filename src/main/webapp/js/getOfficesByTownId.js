var offices = [
    {
        townid: 1,
        id: 1,
        phone: 8989
    },
    {
        townid: 1,
        id: 2,
        phone: 1234
    },
    {
        townid: 2,
        id: 3,
        phone: 1235
    },
    {
        townid: 2,
        id: 4,
        phone: 1236
    }
]

var onTownChange = function () {
    var townId = $(this).val();
    var officeSelect = $('.jsOfficeSelect');

    officeSelect.find('option').remove();
    $('offices').each(function (office) {

        if(office.townid == townId){
            officeSelect
                .append($('<option>'))
                .attr('value', office.id)
                .text(office.phone);
        }
    });
    officeSelect.show();
}

function main(){
    $('.jsTownSelect').on('change', onTownChange);
}

//     $.ajax({
//         url: '/GetOfficeJson',
//         dataType: 'json',
//         error: function () {
//             alert("Error by getting json array");
//         },
//         success: function (data) {
//             var offices = [];
//
//             $.each(data.jsonArray, function (index) {
//                 $.each(data.jsonArray[index], function (key, value) {
//                     var office = [];
//                     office.push(key);
//                     office.push(value);
//                     offices.push(office);
//                 });
//             });
//
//             var townId = $(this).val();
//             var officeSelect = $('officeSelect');
//
//             officeSelect.show();
//             officeSelect.find('option').remove();
//
//             offices.forEach(function (office) {
//                 if(office.townid == townId){
//                     officeSelect
//                         .append($('<option>'))
//                         .attr('value', office.id)
//                         .text(office.phone);
//                 }
//             })
//         }
//     });
// });