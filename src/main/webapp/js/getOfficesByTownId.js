var onTownChange = function () {

    $.getJSON("/GetOfficeJson", function (data) {
        var townId = $('.jsTownSelect').val();
        var officeSelect = $('.jsOfficeSelect');
        var objSel = document.getElementById("officeSelect").options;
        var isNull = true;

        officeSelect.find('option').remove();

        objSel[0] = new Option("Select office phone",null,true,true);
        objSel[0].disabled = true;

        $.each(data, function (index) {
            if(data[index].town.id == townId){
                isNull = false;
                objSel[objSel.length] = new Option(data[index].phone, data[index].id);
            }
        })

        if(isNull){
            officeSelect.hide();
        } else {
            officeSelect.show();
        }

    })
}

function main(){
    $('.jsTownSelect').on('change', onTownChange);
}
