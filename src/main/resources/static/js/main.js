

/*----------------------------------------------------------------------------------------------------------------------
    Navbar
 ----------------------------------------------------------------------------------------------------------------------*/
    $('#search-trigger').click(function() {
        $('div.search').addClass('show');
    });


    $('#search-trigger2').click(function() {
        $('div.search').addClass('show');
        console.log("test");
    });

/*----------------------------------------------------------------------------------------------------------------------
    Home
 ----------------------------------------------------------------------------------------------------------------------*/
    $('#popover-category').popover({
        html : true,
        content: function() {
            return $("#popover-content").html();
        }
    });

    $('.btn-cancel-create').click(function () {
        $('#popover-category').popover('hide');
    });

/*----------------------------------------------------------------------------------------------------------------------
    Login
 ----------------------------------------------------------------------------------------------------------------------*/
    $('#loginModal').modal('show');


/*----------------------------------------------------------------------------------------------------------------------
     Register
----------------------------------------------------------------------------------------------------------------------*/
    //assign a default preference to the input
    var $menu_li = $('.menu-li');
    $('.menu-in').val($menu_li.children().html());

    $('.menu').click(function () {
        $(this).toggleClass('menu-click');
    });

    $menu_li.click(function (e) {
        e.preventDefault();
        var name = ' <span class="caret"></span> ' + $(this).html();
        $('.menu').html(name);
        $('.menu-in').val($(this).children().html());
    });

/*----------------------------------------------------------------------------------------------------------------------
    Index
 ----------------------------------------------------------------------------------------------------------------------*/

    $("[data-toggle=popover]").popover({
        html : true,
        content: function() {
            var content = $(this).attr("data-popover-content");
            return $(content).children(".popover-body").html();
    }
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


/*----------------------------------------------------------------------------------------------------------------------
     Groceries Lists
----------------------------------------------------------------------------------------------------------------------*/
    var json, item_json;
    var $tags = $( "#tags" );
    var $viewItems = $('.view-items');

    //receive json file from the controller
    request = $.ajax({
        'url': '/items.json'
    });
    request.done(function (items) {
        var availableTags = ["itemName"];
        var i= 0;
        json = items;
        items.forEach(function(item) {
            availableTags[i] = item.name;
            i++;
        });
        $tags.autocomplete({
            source: availableTags
        });
    });


    $('#search-submit').click(function (e) {
        e.preventDefault();
        if($tags.val() !== "") {
            var html;
            $viewItems.each(function () {
                if($(this).hasClass('active')){
                    html = $(this).html();
                }
            });
            json.forEach(function(item) {
                if(item.name === $tags.val()) {
                    html += "<div class='item-all'>";
                    html += "<div class='item-name'>";
                    html += "<input type='checkbox' value='false' class='item-property' />";
                    html += "<span class='item-property'>" + item.name + "</span>";
                    html += "</div>";
                    html += "<div class='item-img'>";
                    html += "<img src='/uploads/items/" + item.imgUrl + "'/>";
                    html += "</div>";
                    html += "</div>";

                    $viewItems.each(function () {
                        if($(this).hasClass('active')){
                            item.listId = parseInt($(this).children().val());
                            $(this).html(html);
                        }
                    });

                    item_json = item;
                }
            });


            var token = $('#csrf-token').attr("content");
            var header = $('#csrf-header').attr("content");
            console.log(token);
            console.log(header);
            console.log(item_json);

            // send json to the controller
            $.ajax({
                url:"/lists/items",
                type:"POST",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(item_json), //Stringified Json Object
                async: false,    //Cross-domain requests and dataType: "jsonp" requests do not support synchronous operation
                cache: false,    //This will force requested pages not to be cached by the browser
                processData:false, //To avoid making query String instead of JSON
                beforeSend: function(xhr){
                    if (header && token) {
                        xhr.setRequestHeader(header, token);
                    }
                }
            });

            $tags.val("");

        }
    });


    $('.ul-tabs li').first().addClass('active');
    $viewItems.first().addClass('in active');


    $('.item-all').each(function () {
        if(!$(this).children().hasClass('item-name')){
            $(this).hide();
        }
    });

    $('.btn-cancel').click(function () {
        $('[data-original-title]').popover('hide');
    });

    $('#popover-list').popover({
        html : true,
        content: function() {
            return $("#popover-content").html();
        }
    });






