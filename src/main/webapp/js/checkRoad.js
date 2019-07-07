var onChange = function (event) {
    var left = parseInt($('.jsLeft').val())
    var right = parseInt($('.jsRight').val())

    if(left == right) {
        alert("Please, choose different towns")
    }
}

var onSaveClick = function (event) {
    var left = parseInt($('.jsLeft').val())
    var right = parseInt($('.jsRight').val())

    if(left == right) {
        event.preventDefault()
        alert("Please, choose different towns")
    }
}


function main() {
    $('.jsRight').on('change', onChange)
    $('.jsLeft').on('change', onChange)
    $('.jsSave').on('click', onSaveClick)
}