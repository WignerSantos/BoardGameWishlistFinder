$(window).on("scroll",function () {
    // Menu
    var bodyScroll = $(window).scrollTop();
    var navbar = $(".navbar");

    if(bodyScroll > 50) {
        navbar.addClass("nav-scroll");
    } else {
        navbar.removeClass("nav-scroll");
    }

    // Scroll
    $("section").each(function() {

        var offsetTop = $(this).offset().top - 80;
        var offsetHeight = $(this).outerHeight();
        var id = $(this).attr("id");

        if(bodyScroll >= offsetTop && bodyScroll < offsetTop + offsetHeight) {
            $(".navbar .container .navbar-nav .nav-item a").each(function() {
                $(this).removeClass("active");

                $(".navbar .container .navbar-nav .nav-item a[href*=" + id + "]").addClass("active");
            })
        }
    })
});

$(document).ready(function() {
    const $searchInput = $("#searchInput");
    const $cards = $(".card");

    $searchInput.on("input", function() {
        const searchTerm = $searchInput.val().toLowerCase();

        $cards.each(function() {
            const $card = $(this);
            const cardName = $card.find(".info--text").text().toLowerCase();

            if (cardName.includes(searchTerm)) {
                $card.show();
            } else {
                $card.hide();
            }
        });
    });
});