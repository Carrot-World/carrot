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

let completeModal = null;

function completeBtnHandler(postId) {
    if (completeModal === null) {
        completeModal = new bootstrap.Modal("#completeModal");
    }

    $.ajax({
        url: "/page/modal/complete/"+postId,
        method: "get"
    }).done((result) => {
        document.querySelector("#completeModal .modal-dialog").innerHTML = result;
        completeModal.show();
    })
}

function submitBtnHandler(postId) {
    const value = document.querySelector("#buyerSelector").value;
    if (value === '') {
        return;
    }
    $.ajax({
        url: "/api/item/trade/"+postId+"/"+value,
        method: "get",
    }).done(() => {
        location.reload();
    }).catch((err) => {
        console.log(err);
    })
}