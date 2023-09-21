$(window).scroll(function(){
    $('.header').css('left', 0-$(this).scrollLeft());
});

function hartBtnHandler() {
    var el = $("#hartBtn");
    var id = el.attr("value");
    var name = el.attr("name");

    el.attr("disabled", true);

    if (name === "plus") {
        $.ajax({
            url: "/api/item/hartPlus",
            data: {
                "item_post_id": id
            },
            method: "get",
            dataType: "text"
        }).done((result) => {
            window.location = result;
        })
    } else if (name === "minus") {
        $.ajax({
            url: "/api/item/hartMinus",
            data: {
                "item_post_id": id
            },
            method: "get",
            dataType: "text"
        }).done((result) => {
            window.location = result;
        })
    }
}

function completeBtnHandler(writer) {
    var el = $("#completeBtn");
    var id = el.attr("value");

    el.attr("disabled", true);

    $.ajax({
        url: "/api/item/getBuyer",
        data: {id, writer},
        method: "get",
        dataType: "text"
    }).done((result) => {
        console.log(result);
        el.attr("disabled", false);
    })
}