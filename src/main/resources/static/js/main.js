
/*----------------------------------------------------------------------------------------------------------------------
 Register
 ----------------------------------------------------------------------------------------------------------------------*/

$('.menu').click(function () {
    $(this).toggleClass('menu-click');
});

$('.preference').click(function (e) {
    e.preventDefault();
    var name = ' <span class="caret"></span> ' + $(this).html();
    $('.menu').html(name);
    $('#pref').val($(this).children().html());
});
