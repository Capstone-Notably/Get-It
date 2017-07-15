
/*----------------------------------------------------------------------------------------------------------------------
    Navbar
 ----------------------------------------------------------------------------------------------------------------------*/


/*----------------------------------------------------------------------------------------------------------------------
    Home
 ----------------------------------------------------------------------------------------------------------------------*/
    var $heroText1 = $('.hero-text1');
    var $heroText2 = $('.hero-text2');
    var $brand = $('.brand');

    $heroText1.hide();
    $heroText2.hide();
    $brand.hide();
    $heroText1.delay(2500).fadeIn(2000);
    $heroText2.delay(4000).fadeIn(2000);
    $brand.delay(6100).fadeIn().animate({
        color: "rgb(235, 253, 212)",
        backgroundColor: "rgba(0, 0, 0, .4)"
    });


    $('#popover-category').popover({
        html : true,
        content: function() {
            return $("#popover-content").html();
        }
    });

    $('.btn-cancel-create').click(function () {
        $('#popover-category').popover('hide');
    });

    $('#popover-recipe').popover({
        html : true,
        content: function() {
            return $("#popover-recipe-content").html();
        }
    });

    $('.btn-cancel-recipe').click(function () {
        $('#popover-recipe').popover('hide');
    });

    $('.delete-category').click(function (e) {
        e.preventDefault();
        displaySwal("category", $(this));
    });

    $('.delete-recipe').click(function (e) {
        e.preventDefault();
        displaySwal("recipe", $(this));
    });

    function displaySwal(name, link) {
        swal({
                title: "Are you sure?",
                text: "Your will not be able to recover this " + name + "!",
                type: "warning",
                showCancelButton: true,
                confirmButtonClass: "btn-danger",
                confirmButtonText: "Yes, delete it!",
                closeOnConfirm: false
            },
            function(){
                swal("Deleted!", "Your " + name + " has been deleted.", "success");
                console.log("test");
                link.next()[0].click();
            });
    }

/*----------------------------------------------------------------------------------------------------------------------
    Login
 ----------------------------------------------------------------------------------------------------------------------*/
    $('#loginModal').modal('show');


/*----------------------------------------------------------------------------------------------------------------------
    Profile
 ----------------------------------------------------------------------------------------------------------------------*/
    $('#profileModal').modal('show');

    $('.edit-profile-button').click(function(e){
        e.preventDefault();
        $('#img-user').fadeIn();
        $('.user-details-edit').addClass('show-edit-profile');
        $('.user-account-details').addClass('hide');
    });


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

    $('.close-collapse').click(function () {
        $('.collapse').collapse('hide');
    });

/*----------------------------------------------------------------------------------------------------------------------
     Items edit/create
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
        console.log("test");

    });

    $currency.maskMoney({prefix:'$ ', allowZero:true});

    // $currency.keyup(function() {
    //     $('#price-in').val($(this).maskMoney('unmasked')[0]);
    // });

    $("body").on("keyup", ".currency", function(){
        $('#price-in').val($(this).maskMoney('unmasked')[0]);
    });



    $('.star').click(function () {
        $(this).toggleClass('glyphicon-star-empty glyphicon-star');
        $fav.each(function () { this.checked = !this.checked; });
    });


    $('#add-item-button').click(function () {
        var targetList;
        $viewItems.each(function () {
            if($(this).hasClass('active')){
                targetList = "#itemCreate" + $(this).children().val();
            }
        });
        console.log(targetList);
        $(this).attr("data-target", targetList);
    });



/*----------------------------------------------------------------------------------------------------------------------
     Groceries Lists
----------------------------------------------------------------------------------------------------------------------*/
    var json, item_json, item_barcode_json;
    var items_recipe_json = [];
    var counter_items = 0;
    var $tags = $( "#tags" );
    var $viewItems = $('.view-items');
    var $scannerInput = $('#scanner_input');
    var $itemQty = $('.item-list-qty');
    var $itemProperty = $('.item-property');
    var $btnPlusQty = $('.btn-plus-qty');
    var $btnMinusQty = $('.btn-minus-qty');

    function createItem(item, listId) {
        var html = '';
        html += '<div>';
        html += '<div class="item-all">';
        html +=     '<div class="item-name">';
        html +=         '<input type="checkbox" value="false" class="item-property" />';
        html +=         "<span class='item-property property-name'>" + item.name + "</span>";
        html +=     "</div>";
        html +=     '<div class="item-price-qty">';
        html +=         '<div class="item-price">';
        html +=             '<input type="text" data-item="' + item.id + '" class="qty-price-input currency price" value="$ ' + item.price.toFixed(2) + '" />';
        html +=         '</div>';
        html +=         '<div class="item-quantity">';
        html +=             '<input type="text" class="item-list-qty" data-itemqty="' + item.id + '" value="' + item.quantity + '" />';
        html +=             '<div>';
        html +=                 '<div class="glyphicon glyphicon-plus glyphicon-plus-minus btn-plus-qty" />';
        html +=                 '<div class="glyphicon glyphicon-minus glyphicon-plus-minus btn-minus-qty" />';
        html +=             '</div>';
        html +=         '</div>';
        html +=     '</div>';
        html +=     '<div class="item-img img-responsive">';
        html +=         "<img src='/uploads/items/" + item.imgUrl + "'/>";
        html +=     '</div>';
        html +=     '<div class="glist-item dropdown">';
        html +=         "<button class='btn btn-default dropdown-toggle item-edit' type='button' id='dropdownMenu" + item.id + "' data-toggle='dropdown' aria-haspopup='true' aria-expanded='true'>";
        html +=             '<span class="caret-size caret"></span>';
        html +=         '</button>';
        html +=         "<ul class='dropdown-menu dropdown-menu-right' aria-labelledby='dropdownMenu'" + item.id + "'>";
        html +=             '<li><a href="/list/items/edit?item_id=' + item.id + '">Edit</a></li>';
        html +=             '<li><a href="/list/items/delete?list_id=' + listId + '&item_id=' + item.id + '">Delete</a></li>';
        html +=             '<li class="dropdown-price-qty"><a data-toggle="modal" href="#itemSetPrice' + item.id + '">Set Price</a></li>';
        html +=             '<li class="dropdown-price-qty"><a data-toggle="modal" href="#itemSetQty' + item.id + '">Set Quantity</a></li>';
        html +=         '</ul>';
        html +=     "</div>";

        html +=     '<div class="modal fade" id="itemSetPrice' + item.id +'" tabindex="-1" role="dialog" aria-labelledby="modalPrice">';
        html +=         '<div class="modal-dialog modal-sm" role="document">';
        html +=             '<div class="modal-content">';
        html +=                 '<div class="modal-header">';
        html +=                     '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        html +=                     '<h4 class="modal-title" id="modalPrice">Enter Item Price</h4>';
        html +=                 '</div>';
        html +=                 '<div class="modal-body">';
        html +=                     '<div class="modal-price-input">';
        html +=                         '<input type="text" class="qty-price-input currency price" data-item="' + item.id +'" value="' + item.price.toFixed(2) + '" />';
        html +=                     '</div>';
        html +=                     '<div class="btn-ok-cancel">';
        html +=                         '<button class="btn btn-danger glyphicon glyphicon-remove btn-modal-cancel" data-dismiss="modal"></button>';
        html +=                         '<button type="submit" class="btn btn-success glyphicon glyphicon-ok btn-modal-ok" data-dismiss="modal"></button>';
        html +=                     "</div>";
        html +=                 "</div>";
        html +=             "</div>";
        html +=         "</div>";
        html +=     "</div>";

        html +=     '<div class="modal fade" id="itemSetQty' + item.id +'" tabindex="-1" role="dialog" aria-labelledby="modalQty">';
        html +=         '<div class="modal-dialog modal-sm" role="document">';
        html +=             '<div class="modal-content">';
        html +=                 '<div class="modal-header">';
        html +=                     '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        html +=                     '<h4 class="modal-title" id="modalQty">Enter Item Price</h4>';
        html +=                 '</div>';
        html +=                 '<div class="modal-body">';
        html +=                     '<div class="modal-input-qty">';
        html +=                         '<input type="text" class="item-list-qty" data-itemqty="' + item.id + '" value="' + item.quantity + '" />';
        html +=                     '<div>';
        html +=                         '<div class="glyphicon glyphicon-plus glyphicon-plus-minus btn-plus-qty" />';
        html +=                         '<div class="glyphicon glyphicon-minus glyphicon-plus-minus btn-minus-qty" />';
        html +=                     '</div>';
        html +=                 '</div>';
        html +=                 '<div class="btn-ok-cancel">';
        html +=                     '<button class="btn btn-danger glyphicon glyphicon-remove btn-modal-cancel" data-dismiss="modal"></button>';
        html +=                     '<button type="submit" class="btn btn-success glyphicon glyphicon-ok btn-modal-ok" data-dismiss="modal"></button>';
        html +=                 "</div>";
        html +=             "</div>";
        html +=         "</div>";
        html +=     "</div>";
        html +=     "</div>";
        html += "</div>";
        html += "</div>";
        return html;
    }

    function appendItem(item) {
        $viewItems.each(function () {
            if($(this).hasClass('active')){
                var listId = parseInt($(this).children().val());
                item.listId = listId;
                $(this).append(createItem(item, listId));
            }
        });

        $('.currency').maskMoney({prefix:'$ ', allowZero:true});
    }

    function sendJsonToController(item_json, url) {
        var token = $('#csrf-token').attr("content");
        var header = $('#csrf-header').attr("content");

        // send json to the controller
        $.ajax({
            url: url,
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

    }

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
        // console.log(json[0]);
    });

    //search item / grocery lists
    $('#search-submit').click(function (e) {
        e.preventDefault();

        if($tags.val() !== "") {
            json.forEach(function(item) {
                if(item.name === $tags.val()) {
                    appendItem(item);
                    item_json = item;
                }
            });
            sendJsonToController(item_json, "/lists/items");
            $tags.val("");
        }
    });

    function appendItemRecipe(item) {
        var html = "<li class='recipe-items-create'>" + item.name + "</li>";
        $('.recipe-append-items').append(html);
    }
    //search item / recipes
    $('#search-recipe-item').click(function (e) {
        e.preventDefault();

        if($tags.val() !== "") {
            json.forEach(function(item) {
                if(item.name === $tags.val()) {
                    // items_recipe_json[counter_items] = item;
                    // counter_items++;
                    console.log(item);
                    appendItemRecipe(item);
                    sendJsonToController(item, "/recipes/items");
                }
            });
            $tags.val("");
        }
    });

    $('#img-file-recipe').change(function () {
        // console.log($(this)[0].files[0].name);
        // var url = "url(/uploads/others/" + $(this)[0].files[0].name + ")";
        // $('.recipe-header').css("background-image", url);
    });

    //update price in database
    $viewItems.on( "change", '.currency', currencyFormatting);

    function currencyFormatting() {
        var item_id = parseInt($(this).attr("data-item"));
        var price = $(this).maskMoney('unmasked')[0];
        json.forEach(function(item) {
            if(item.id === item_id) {
                item.price = price;
                sendJsonToController(item, "/lists/items/setPrice");
                console.log(item);
            }
        });
    }

    //update qty in database
    function updateQty($qty_input) {
        var item_id = parseInt($qty_input.attr("data-itemqty"));
        json.forEach(function(item) {
            if(item.id === item_id) {
                item.quantity = $qty_input.val();
                sendJsonToController(item, "/lists/items/setQty");
                console.log(item);
            }
        });
    }

    function btnMinusQty() {
        var $qty_input = $(this).parent().parent().children();
        if ($qty_input.val() > 1) {
            $qty_input.val(parseInt($qty_input.val()) - 1);
        }
        updateQty($qty_input);
    }
    $viewItems.on( "click", '.btn-minus-qty', btnMinusQty);

    function btnPlusQty() {
        var $qty_input = $(this).parent().parent().children();
        $qty_input.val(parseInt($qty_input.val()) + 1);
        updateQty($qty_input);
    }
    $viewItems.on( "click", '.btn-plus-qty', btnPlusQty);


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


    //Checkout
    $viewItems.on( "click", '.item-property', itemClick);

    function itemClick(){
        var $inputPrice = $(this).parent().next().children().children();
        var qty = parseInt($inputPrice.parent().next().children().val());
        var price = $inputPrice.maskMoney('unmasked')[0];
        var $total = $('.calculated-total');
        var currentTotal = parseFloat($total.html());
        var $btnsQty = $inputPrice.parent().next().children().next();
        $(this).next().toggleClass('item-clicked');
        if($(this).next().hasClass('item-clicked')) {
            currentTotal += price * qty;
            //disable inputs price and qty
            $inputPrice.prop( "disabled", true ).css("background-color", "lightgrey");
            $btnsQty.fadeOut().css("background-color", "#bbd366");
        } else {
            currentTotal -= price * qty;
            //disable inputs price and qty
            $inputPrice.prop( "disabled", false ).css("background-color", "white");
            $btnsQty.fadeIn().css("background-color", "#bbd366");
        }
        $total.html(currentTotal.toFixed(2));
    }


/*----------------------------------------------------------------------------------------------------------------------
    Scan a barcode
 ----------------------------------------------------------------------------------------------------------------------*/
    // Create the QuaggaJS config object for the live stream
    var liveStreamConfig = {
        inputStream: {
            type : "LiveStream",
            constraints: {
                width: {min: 640},
                height: {min: 480},
                aspectRatio: {min: 1, max: 100},
                facingMode: "environment" // or "user" for the front camera
            }
        },
        locator: {
            patchSize: "medium",
            halfSample: true
        },
        numOfWorkers: (navigator.hardwareConcurrency ? navigator.hardwareConcurrency : 4),
        decoder: {
            "readers":[
                {"format":"ean_reader","config":{}}
            ]
        },
        locate: true
    };
    // The fallback to the file API requires a different inputStream option.
    // The rest is the same
    var fileConfig = $.extend(
        {},
        liveStreamConfig,
        {
            inputStream: {
                size: 800
            }
        }
    );
    // Start the live stream scanner when the modal opens
    $('#livestream_scanner').on('shown.bs.modal', function (e) {
        Quagga.init(
            liveStreamConfig,
            function(err) {
                if (err) {
                    $('#livestream_scanner .modal-body .error').html('<div class="alert alert-danger"><strong><i class="fa fa-exclamation-triangle"></i> '+err.name+'</strong>: '+err.message+'</div>');
                    Quagga.stop();
                    return;
                }
                Quagga.start();
            }
        );
    });

    // Make sure, QuaggaJS draws frames an lines around possible
    // barcodes on the live stream
    Quagga.onProcessed(function(result) {
        var drawingCtx = Quagga.canvas.ctx.overlay,
            drawingCanvas = Quagga.canvas.dom.overlay;

        if (result) {
            if (result.boxes) {
                drawingCtx.clearRect(0, 0, parseInt(drawingCanvas.getAttribute("width")), parseInt(drawingCanvas.getAttribute("height")));
                result.boxes.filter(function (box) {
                    return box !== result.box;
                }).forEach(function (box) {
                    Quagga.ImageDebug.drawPath(box, {x: 0, y: 1}, drawingCtx, {color: "green", lineWidth: 2});
                });
            }

            if (result.box) {
                Quagga.ImageDebug.drawPath(result.box, {x: 0, y: 1}, drawingCtx, {color: "#00F", lineWidth: 2});
            }

            if (result.codeResult && result.codeResult.code) {
                Quagga.ImageDebug.drawPath(result.line, {x: 'x', y: 'y'}, drawingCtx, {color: 'red', lineWidth: 3});
            }
        }
    });

    // Once a barcode had been read successfully, stop quagga and
    // close the modal after 1 second to let the user notice where
    // the barcode had actually been found.
    Quagga.onDetected(function(result) {
        if (result.codeResult.code){
            $scannerInput.val(result.codeResult.code);
            Quagga.stop();
            setTimeout(function(){ $('#livestream_scanner').modal('hide'); }, 1000);
        }
    });

    // Stop quagga in any case, when the modal is closed
    $('#livestream_scanner').on('hide.bs.modal', function(){
        if (Quagga){
            Quagga.stop();
            adddItem($scannerInput);
        }
    });

    function adddItem($input) {
        json.forEach(function(item) {
            if(item.barcode === $input.val()) {
                appendItem(item);
                item_barcode_json = item;
            }
        });
        console.log(item_barcode_json);
        sendJsonToController(item_barcode_json, "/lists/items");
    }

    // Call Quagga.decodeSingle() for every file selected in the
    // file input
    $("#livestream_scanner input:file").on("change", function(e) {
        if (e.target.files && e.target.files.length) {
            Quagga.decodeSingle($.extend({}, fileConfig, {src: URL.createObjectURL(e.target.files[0])}), function(result) {alert(result.codeResult.code);});
        }
    });

    $('video').css('width', '100%');




