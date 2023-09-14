function hartBtnHandler() {
    var el = $("#hartBtn");
    var id = el.attr("value");
    var cnt = el.attr("value1");
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
                "item_post_id": id,
                "cnt": cnt
            },
            method: "get",
            dataType: "text"
        }).done((result) => {
            window.location = result;
        })
    }
}