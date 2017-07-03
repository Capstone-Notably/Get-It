$(function() {

    /*----------------------------------------------------------------------------------------------------------------------
     Register
     ----------------------------------------------------------------------------------------------------------------------*/

    $('.menu').click(function () {
        $(this).toggleClass('menu-click');
    });

    $('.menu-li').click(function (e) {
        e.preventDefault();
        var name = ' <span class="caret"></span> ' + $(this).html();
        $('.menu').html(name);
        $('.menu-in').val($(this).children().html());
    });


    /*----------------------------------------------------------------------------------------------------------------------
     items/create
     ----------------------------------------------------------------------------------------------------------------------*/
    var $input = $('.qty-input');
    var $currency = $('.currency');
    var $fav = $('#fav-input');

    $('.btn-minus').click(function () {
        if ($input.val() > 0) {
            $input.val(parseInt($input.val()) - 1);
        }
    });

    $('.btn-plus').click(function () {
        $input.val(parseInt($input.val()) + 1);
    });

    $currency.maskMoney({prefix:'$ ', allowZero:true});

    $currency.keyup(function() {
        $('#price-in').val($(this).maskMoney('unmasked')[0]);
    });

    $('.star').click(function () {
        $(this).toggleClass('glyphicon-star-empty glyphicon-star');
        $fav.each(function () { this.checked = !this.checked; });
    });

});