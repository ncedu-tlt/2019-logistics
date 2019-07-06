var onSearchClick = function (event) {
    var townId = $('.jsTown').val()
    var productId = $('.jsProduct').val()

    if(townId == null || productId == null){
        event.preventDefault()
        alert("Please select town and product")
    }
}

function main() {
    $('.jsSearch').on('click', onSearchClick)
}