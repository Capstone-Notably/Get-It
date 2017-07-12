

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
    var json, item_json, item_barcode_json;
    var $tags = $( "#tags" );
    var $viewItems = $('.view-items');
    var $scannerInput = $('#scanner_input');

    function addItemToView(item) {
        var html = 0;
        html += "<div class='item-all'>";
        html += "<div class='item-name'>";
        html += "<input type='checkbox' value='false' class='item-property' />";
        html += "<span class='item-property'>" + item.name + "</span>";
        html += "</div>";
        html += "<div class='item-img'>";
        html += "<img src='/uploads/items/" + item.imgUrl + "'/>";
        html += "</div>";
        html += "<div class='glist-item dropdown'>";
        html += "<button class='btn btn-default dropdown-toggle item-edit' type='button' id='dropdownMenu" + item.id + "' data-toggle='dropdown' aria-haspopup='true' aria-expanded='true'>";
        html += "<span class='caret-size caret'></span>";
        html += "</button>";
        html += "<ul class='dropdown-menu dropdown-menu-right' aria-labelledby='dropdownMenu'" + item.id + "'>";
        html += "<li><a href='#'>Edit</a></li>";
        html += '<li><a href="#">Delete</a></li>';
        html += '<li><a href="#">Set Price</a></li>';
        html += '<li><a href="#">Set Quantity</a></li>';
        html += '</ul>';
        html += "</div>";
        html += "</div>";
        return html;
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
                html += addItemToView(item);

                $viewItems.each(function () {
                    if($(this).hasClass('active')){
                        item.listId = parseInt($(this).children().val());
                        $(this).html(html);
                    }
                });

                item_json = item;
            }
        });

        sendJsonToController(item_json, "/lists/items");
        $tags.val("");
    }
});

    //update price in database
    $currency.change(function () {
        var item_id = parseInt($(this).attr("data-item"));
        var item_json;
        var price = $(this).maskMoney('unmasked')[0];
        json.forEach(function(item) {
            if(item.id === item_id) {
                item_json = item;
                item_json.price = price;
                sendJsonToController(item_json, "/lists/items/setPrice");
                console.log(item_json);
            }
        });
    });


    $('.btn-minus-qty').click(function () {
        var $qty_input = $(this).parent().parent().children();
        if ($qty_input.val() > 1) {
            $qty_input.val(parseInt($qty_input.val()) - 1);
        }
    });

    $('.btn-plus-qty').click(function () {
        var $qty_input = $(this).parent().parent().children();
        $qty_input.val(parseInt($qty_input.val()) + 1);
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


    // Scan a barcode
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

    // Call Quagga.decodeSingle() for every file selected in the
    // file input
    $("#livestream_scanner input:file").on("change", function(e) {
        if (e.target.files && e.target.files.length) {
            Quagga.decodeSingle($.extend({}, fileConfig, {src: URL.createObjectURL(e.target.files[0])}), function(result) {alert(result.codeResult.code);});
        }
    });

    $('video').css('width', '100%');


    function adddItem($input) {
        var html;
        $viewItems.each(function () {
            if($(this).hasClass('active')){
                html = $(this).html();
            }
        });
        json.forEach(function(item) {
            if(item.barcode === $input.val()) {
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

                item_barcode_json = item;
            }
        });
        console.log(item_barcode_json);
        sendJsonToController(item_barcode_json);
    }


    $('.item-property').click(function(){
        $(this).next().toggleClass('item-clicked');
    });


    var $prices = $('.price');
    var $qty = $('.quantity');

    function addListTotal() {
        var price = 0, i=0;
        var quantity = [];

        $prices.each(function () {
            quantity = parseInt($qty[i].innerText);
            price += parseFloat($(this).text()) * quantity;
            console.log(quantity)
            i++;
        });
        $('.calculated-total').html(price);
    }

    addListTotal();
