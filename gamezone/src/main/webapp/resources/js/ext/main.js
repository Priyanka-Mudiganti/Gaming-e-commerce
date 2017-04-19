var divvyup_theme = {
    window_obj : null,

    init: function() {
        divvyup_theme.window_obj = $(window);

        divvyup_theme.initMobileMenu();
        divvyup_theme.initMatchHeight();
        divvyup_theme.justifyHeadings();
        divvyup_theme.initCountdown();
        divvyup_theme.initDonationsMap();
        divvyup_theme.initSockHighlights();
        divvyup_theme.initTimeline();
        divvyup_theme.initFAQ();
        divvyup_theme.initExpandableSections();
        divvyup_theme.initProductListing();
        divvyup_theme.initInfoSections();
		divvyup_theme.initNewsletterSignup();

        // Callback for ajax add to cart
        $(document).on('added_to_cart', function(e, fragments, cart_hash, $thisbutton) {
            // Update all instances of this product on the page to match
            $('.add_to_cart_button[data-product_id=' + $thisbutton.data('product_id') + ']').each(function() {
                var button = $(this);
                button.addClass('added');

                var added_to_cart_text = button.siblings('.wc-added-to-cart-text').addClass('active');
                var success_msg = added_to_cart_text.find('.wc-add-to-cart-success-msg').addClass('just-added');

                added_to_cart_text.find('.wc-num-in-cart').text(function() {
                    return parseInt($(this).text()) + 1;
                });

                setTimeout(function() {
                    button.addClass('really-added');
                }, 500);

                setTimeout(function() {
                    success_msg.removeClass('just-added');
                }, 1000);
            });

            // Trigger Facebook Pixel event
            fbq('track', 'AddToCart'/*, {
                content_ids: $thisbutton.data('product_id'),
                content_type: ($thisbutton.hasClass('product_type_bundle') ? 'product_group' : 'product')
            }*/);
        });

        $('.add_to_cart_button').on('click', function() {
            $(this).removeClass('really-added');
        });

        // HACK: Pre-orders plugin uses success notice for cart errors...
        // This is an ugly workaround for that.
        $('.woocommerce-notice-success:contains("product cannot be added")')
            .removeClass('woocommerce-notice-success')
            .addClass('woocommerce-notice-error')
            .find('.icon-wrapper').html('<svg class="icon icon-warning" aria-hidden="true"><use xlink:href="#icon-warning"></use></svg>');

        $('.woocommerce-notice-success:contains("emptied because pre-orders")')
            .removeClass('woocommerce-notice-success')
            .addClass('woocommerce-notice-info')
            .find('.icon-wrapper').html('<svg class="icon icon-info" aria-hidden="true"><use xlink:href="#icon-info"></use></svg>');
		
		$('.sp-wrap').smoothproducts();
		
		  $('.section-part-slideshow').each(function() {
			  var element = $(this);
			  
			  element.backgroundCycle({
				imageUrls: element.data('images'),
				fadeSpeed: 1000,
				duration: 3000,
				backgroundSize: SCALING_MODE_COVER
			  });
		  }); 

		// Color pickers for custom socks form
        $('.sock-color-picker').spectrum({
            color: "#56a9b8",
            flat: false,
            showInput: true,
            showInitial: false,
            allowEmpty: false,
            showAlpha: false,
            disabled: false,
            showPalette: false,
            clickoutFiresChange: true,
            cancelText: '',
            chooseText: 'Choose',
            preferredFormat: 'hex',
            change: function(color) {
                $(this).parent().find('.sp-preview').css('box-shadow', '0 0 45px 0 ' + color.toHexString());
            },
            move: function(color) {
                $(this).parent().find('.sp-preview').css('box-shadow', '0 0 45px 0 ' + color.toHexString());
            },
            show: function(color) {
                $(this).parent().find('.sp-preview').css('box-shadow', '0 0 45px 0 ' + color.toHexString());
            }
        });

        // Range slider for custom sock form
        var sock_price_container = $('<div />').addClass('custom-sock-price-result').html('<span class="quantity">15</span> pairs at <span class="price">$14</span> each').
            append('<div class="you-pay">You pay: <span class="final-price">$210</span></div><div class="you-earn">Your profit: <span class="profit">$0</span></div>');

		var slider_outer_wrapper = $('.custom-sock-price-slider-wrap')
        slider_outer_wrapper.append(sock_price_container);

        var sock_price = sock_price_container.find('.price');
        var sock_quantity = sock_price_container.find('.quantity');
        var sock_final_price = sock_price_container.find('.final-price');
        var sock_profit = sock_price_container.find('.profit');
        var retail_price = $('.sock-retail-price');

        var slider_container = $('.custom-sock-price-slider');
		
		slider_container.wrap('<div class="sock-price-slider-container"></div>');
		var slider_wrapper = slider_outer_wrapper.find('.sock-price-slider-container');


        slider_container.ionRangeSlider({
            grid: false,
			min: 15,
			max: 1000,
			keyboard: true,
			keyboard_step: 0.1,
			force_edges: true,
			from: 15,
            //value: 15,
            //values: [15, 25, 50, 75, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900, 950, 1000],
            onChange: function() {
                onNumberChange();
            }
        });
		
		var resizeSliderBtns = function() {
			$('.irs-minus').css('height', $('.irs-minus').width()).css('line-height', $('.irs-minus').width() + 'px');
			$('.irs-plus').css('height', $('.irs-plus').width()).css('line-height', $('.irs-plus').width() + 'px');
		};
		
		$(window).resize(resizeSliderBtns);
		
		
		var isSliderStarted = function() {
			if(slider_wrapper.find('.irs').length > 0) {
				addIncrementButtons();
			} else {
				setTimeout(isSliderStarted, 50);
			}
		};
		
		setTimeout(isSliderStarted, 50);
		
		var addIncrementButtons = function() {
			slider_wrapper.prepend($('<div class="irs-minus-wrap" />').append($('<button />').addClass('irs-minus').attr('type', 'button').text('-').attr('aria-label', 'Decrease value')));
			slider_wrapper.append($('<div class="irs-plus-wrap" />').append($('<button />').addClass('irs-plus').attr('type', 'button').text('+').attr('aria-label', 'Increase value')));
			resizeSliderBtns();
		};

        var slider = slider_container.data("ionRangeSlider");
		
				
		slider_wrapper.on('click', '.irs-minus', function(e) {
			e.preventDefault();
			
			slider.update({
				from: parseInt(slider_container.val()) - 1
			});
			
			onNumberChange();
			
			return false;
		});
		
		slider_wrapper.on('click', '.irs-plus', function(e) {
			e.preventDefault();
			
			slider.update({
				from: parseInt(slider_container.val()) + 1
			});
			
			onNumberChange();
			
			return false;
		});

        var onNumberChange = function() {
            var quantity = slider_container.val();
            var sales_price = retail_price.val();

            sock_quantity.text(quantity);

            var unit_price = 14;

            if(quantity >= 100) {
                unit_price = 7;
            } else if(quantity >= 75) {
                unit_price = 8;
            } else if(quantity >= 50) {
                unit_price = 9;
            } else if(quantity >= 25) {
                unit_price = 10;
            } else if(quantity < 25) {
                unit_price = 14;
            }

            var price = quantity * unit_price;
            var sales = quantity * sales_price;

            sock_price.text('$' + unit_price);
            sock_final_price.text('$' + price);

            var profit = sales - price;

            sock_profit.text('$' + (profit < 0 ? 0 : profit));
        };

        retail_price.on('change', function() {
            onNumberChange();
        });

        // Highlight selected order type
        $('.custom-sock-order-type').change(function() {
            $(this).closest('ul').find('label').removeClass('selected').find('.button').text('Select');
            $(this).closest('label').addClass('selected').find('.button').text('Selected');

            if($(this).val() != 'Pre-order') {
                $('.you-pay').show();
            } else {
                $('.you-pay').hide();
            }
        });

        var label = $('<label/>').attr('for', $('.is-retailer').attr('id')).addClass('checkbox-label').text('Yes, I am a retailer');
        $('.is-retailer').after(label);

        // Style file upload button
        $('.sock-graphics-wrap input[type=file]').after('<button type="button" class="file-upload-button">Add a file</button>');

        $('.sock-graphics-wrap')
            .on('change', 'input[type=file]', function() {
                if($(this).closest('.sock-graphics-wrap').find('.MultiFile-label').length >= 5) {
                    $('.file-upload-button').fadeOut();
                }
            });

        setInterval(function() {
            if($('.sock-graphics-wrap').find('.MultiFile-label').length < 5) {
                $('.file-upload-button').fadeIn();
            }
        }, 1000);

        $('.file-upload-button').click(function() {
            $(this).closest('.sock-graphics-wrap').find('input[type=file]:last-of-type').trigger('click');
        });

        // Description for order types
        $('.custom-sock-order-type-wrap span > ul').addClass('options');
        $('.custom-sock-order-type-wrap li').each(function() {
            if($(this).find('input').val() == 'Pre-order') {
                $(this).find('label').append('<ul class="fa-ul"><li><i class="fa-li fa fa-star"></i>Custom Sales Page</li><li><i class="fa-li fa fa-calendar-o"></i>Collect orders for up to 14 days</li><li><i class="fa-li fa fa-truck"></i>Ships directly to customers (within 5 business days)</li><li><i class="fa-li fa fa-gift"></i>Gift an equal amount of socks to your local homeless shelter</li></ul>');
            } else {
                $(this).find('label').append('<ul class="fa-ul"><li><i class="fa-li fa fa-handshake-o"></i>Purchase your socks up-front</li><li><i class="fa-li fa fa-truck"></i>Ships within 5 business days</li><li><i class="fa-li fa fa-gift"></i>Gift an equal amount of socks to your local homeless shelter</li><li><i class="fa-li fa fa-money"></i>100% Money-Back Guarantee!</li></ul>');
            }

            $(this).find('label').append('<div class="button">Select</div>');

            $('.custom-sock-order-type-wrap li ul').matchHeight();
        });

        // Re-arrange retail price field
        $('.sock-retail-price-wrap').insertBefore('.custom-sock-price-result');

       retail_price.before('$');

       $('.have-finished-design-wrap label').wrapInner('<div></div>').append('<p>If you have a finished design based on our template, click here to upload your design. You may still upload your graphics and set your colors above to give us an even better idea of what you\'re after. (<a href="">Click here to download the template</a>)</p>');
    },

    initMatchHeight: function() {
        $('.woocommerce-customer-login-inner, .woocommerce-create-account-inner').matchHeight();
        $('.product-specifications-block').matchHeight();
        $('.related-blog-post .blog-post').matchHeight();
    },

    scrollToPosition: function(position) {
        $('html, body').animate({
            scrollTop: position
        }, 1000);
    },

    initExpandableSections: function() {
        $('.expandable-section-toggle').attr('role', 'button').attr('aria-expanded', 'false').attr('aria-controls', function() {
            return $(this).data('target-section');
        }).click(function(e) {
            e.preventDefault();

            var button = $(this);

            button.attr('aria-expanded', function() {
                return button.attr('aria-expanded') === 'true' ? 'false' : 'true';
            });

            var section = $('#' + button.data('target-section'));

            section.attr('aria-hidden', function() {
                return section.attr('aria-hidden') === 'true' ? 'false' : 'true';
            }).slideToggle();

            if(section.is(':visible')) {
                divvyup_theme.scrollToPosition(button.offset().top);
                section.siblings('.expandable-section').trigger('expandable-section-close', { giveFocusToSection: false });
            }

            return false;
        });

        $('.expandable-section').on('expandable-section-close', function(e, options) {
            var section = $(this);

            section.slideUp().attr('aria-hidden', 'true');

            var toggle = $('.expandable-section-toggle[data-target-section="' + section.attr('id') + '"]');
            toggle.attr('aria-expanded', 'false');

            if(options.giveFocusToSection) {
                toggle.trigger('focus');
            }
        });

        $('.expandable-section-close').click(function() {
            $(this).closest('.expandable-section').trigger('expandable-section-close', { giveFocusToSection: true });
        });
    },

    initMobileMenu: function() {
        var mobile_menu = $('.main-menu-mobile');
        mobile_menu.appendTo($('.header'));

        var default_nav_toggle = $('.header .navigation-toggle');

        var navigation_btn = $('<button/>').attr('type', 'button').addClass('navigation-toggle').html(default_nav_toggle.html()).on('click', function(e) {
            e.preventDefault();

            mobile_menu.toggleClass('navigation-open');

            if(mobile_menu.hasClass)

                return false;
        });

        default_nav_toggle.replaceWith(navigation_btn);
    },

    justifyHeadings: function() {
        var justified_headings = $('.justified-heading span');

        if(justified_headings.length > 0) {
            justified_headings.each(function () {
                textFit($(this)[0], {widthOnly: true, minFontSize: 30, maxFontSize: 85, multiLine: false})
            });
        }
    },

    initFAQ: function() {
        var faqs = $('.faq-list');

        if(faqs.length > 0) {
            faqs.addClass('faqs-list-togglable').attr('role', 'tablist');

            faqs.find('.faq-list-item').attr('tabindex', '0').attr('role', 'tab').attr('aria-expanded', 'false').attr('aria-controls', function() {
                return 'faq-answer-' + ($(this).attr('id').replace('faq-', ''));
            }).attr('aria-selected', 'false').find('.faq-answer').attr('aria-hidden', 'true').attr('aria-labelledby', function() {
                return 'faq-question-' + ($(this).attr('id').replace('faq-answer-', ''));
            }).attr('role', 'tabpanel');

            faqs
                .on('click', '.faq-question', function() {
                    if($(this).closest('.faq-list-item').hasClass('faq-list-item-open')) {
                        $(this).closest('.faq-list-item').trigger('blur');
                    } else {
                        $(this).closest('.faq-list-item').trigger('focus');
                    }
                })
                .on('focus', '.faq-list-item', function() {
                    $(this)
                        .addClass('faq-list-item-open').attr('aria-expanded', 'true').attr('aria-selected', 'true')
                        .siblings('.faq-list-item-open').attr('aria-expanded', 'false').attr('aria-selected', 'false').removeClass('faq-list-item-open')

                    $(this).find('.faq-answer').attr('aria-hidden', 'false');
                })
                .on('blur', '.faq-list-item', function() {
                    if(!$(this).has(':focus')) {
                        $(this).removeClass('faq-list-item-open').attr('aria-expanded', 'false').attr('aria-selected', 'false');
                        $(this).find('.faq-answer').attr('aria-hidden', 'true');
                    }
                })
            ;
        }
    },

    initCountdown: function() {
        var countdown_elem =  $('.countdown');

        if(countdown_elem.length > 0) {
            var countdown_end_time = countdown_elem.data('countdown-end');

            // Start counting
            divvyup_theme.updateCountdown(countdown_elem, countdown_end_time);
        }
    },

    updateCountdown: function(countdown_elem, countdown_end_time) {
        var timespan = countdown(null, new Date(countdown_end_time));

        if(timespan.value > 0) {
            countdown_elem.find('.countdown-card-number').each(function() {
                var card = $(this);

                card.text(divvyup_theme.zeroPad(timespan[card.data('value')]));
            });

            requestAnimationFrame(function() {
                divvyup_theme.updateCountdown(countdown_elem, countdown_end_time);
            }, countdown_elem[0].parentNode);
        } else {
            countdown_elem.addClass('countdown-ended');
            countdown_elem.parent().addClass('contains-ended-countdown').removeAttr('aria-label');
			
			$('.hide-when-campaign-ends').hide();
			$('.show-when-campaign-ends').show();
        }
    },

    initSockHighlights: function() {
        divvyup_theme.animateOnScroll($('.sock-highlights'), 'element-is-hidden', 'element-animate-in', 1.2);
    },

    initProductListing: function() {
        divvyup_theme.animateOnScroll($('.product-pack-item, .product-pack-info'), 'element-is-hidden', 'element-animate-in', 0.8);
    },

    initInfoSections: function() {
        divvyup_theme.animateOnScroll($('.info-block'), 'element-is-hidden', 'element-animate-in', 0.8);
    },

	initNewsletterSignup: function() {
        divvyup_theme.animateOnScroll($('.mc4wp-form button'), 'element-is-hidden', 'element-animate-in', 0.8);
    },
	
    initDonationsMap: function() {
        var donations_map = $('.donations-vmap');

        if(donations_map.length > 0) {
			FastClick.attach(document.body);
			
            var donations_map_wrapper = donations_map.closest('.donations-wrapper');

            var donations_info_container = donations_map_wrapper.find('.donations-info-container');
            var state_info_container = donations_info_container.find('.donations-region-info');
            var state_info_text = state_info_container.find('.region-text');
            var state_info_details = state_info_container.find('.region-details');
            var state_info_name = state_info_container.find('.region-name');
            var state_info_donations = state_info_container.find('.region-donations');
            var state_info_shelters = state_info_container.find('.region-shelters');

            var states = donations_map.data('states');
            var all_states = donations_map.data('all-states');
            var empty_state_msg = donations_map.data('empty-state-msg');

            // Set map aspect ratio on window resize
            $(window).on('resize', function() {
                // Is it split or full width?
                if(donations_info_container.width() / donations_map_wrapper.innerWidth() >= 0.7) {
                    donations_map.width(donations_info_container.width() - 1);
                } else {
                    donations_map.width(((donations_info_container.width() / 0.4) * 0.6) - 1);
                }

                donations_map.height(donations_map.width() * 0.626);
            });

            state_info_container.fadeIn();

            var cur_state = 'FL';

            var options = {
                map: 'usa_en',
                backgroundColor: 'white',
                borderColor: '#cccccc',
                borderOpacity: 1,
                borderWidth: 1,
                color: 'white',
                colors: {},
                selectedRegions: ['fl'],
                enableZoom: false,
                hoverColor: '#f26f6f',
                hoverOpacity: null,
                normalizeFunction: 'linear',
                scaleColors: ['#b6d6ff', '#005ace'],
                selectedColor: '#f26f6f',
                onRegionSelect: function (event, code) {
                    code = code.toUpperCase();
                    cur_state = code;

                    divvyup_theme.scrollToPosition(donations_map_wrapper.offset().top - 150);

                    var timeout = !donations_map_wrapper.hasClass('has-selected-region') ? 600 : 0;

                    donations_map_wrapper.addClass('has-selected-region');

                    setTimeout(function() {
                        state_info_container.fadeOut(function () {
                            if (states.hasOwnProperty(code)) {
                                var selected_state = states[code];

                                state_info_text.html(selected_state.text);
                                state_info_name.text(selected_state.name);
                                state_info_donations.text(selected_state.donations);
                                state_info_shelters.html(selected_state.shelters);
                                state_info_details.show();
                            } else {
                                state_info_details.hide();
                                var state_name = all_states.hasOwnProperty(code) ? all_states[code] : 'Unknown';
                                state_info_text.html(empty_state_msg.replace('{state}', state_name));
                                state_info_name.text(state_name);
                            }

                            state_info_container.fadeIn();
                        });
                    }, timeout);

                    return true;
                },
                onRegionClick: function(event, code) {
                    if(code.toUpperCase() == cur_state) {
                        return false;
                    }

                    return true;
                },
                onRegionDeselect: function (event, code) {
                    return false;
                }
            };

            // Each configured state should be highlighted on the map
            for(var state in states) {
                if (states.hasOwnProperty(state)) {
                    options.colors[states[state].code.toLowerCase()] = '#56a9b8';
                }
            }

            donations_map.vectorMap(options);
        }
    },

    zeroPad: function(n) {
        return n < 10 ? '0' + n : n;
    },

    initTimeline: function() {
        divvyup_theme.animateOnScroll($('.timeline-event'), 'event-is-hidden', 'event-bounce-in');
        divvyup_theme.animateOnScroll($('.timeline-cta').find('p, h2, h3, .button'), 'element-is-hidden', 'event-animate-in');
    },

    animateOnScroll: function(elements, hide_class, show_class, offset) {
		if(divvyup_theme.window_obj.width() > 640) {
			offset = (typeof offset).toLowerCase() == 'number' ? offset : 0.8;

			var options = {
				offset: (offset * 100) + '%'
			};

			if(elements.length > 0) {
				elements.each(function () {
					var elem = $(this);
					if(elem.offset().top > (divvyup_theme.window_obj.scrollTop() + (divvyup_theme.window_obj.height() * offset))) {
						elem.addClass(hide_class);
					}
				}).waypoint(function() {
					$(this.element).removeClass(hide_class).addClass(show_class);
				}, options);
			}
		}
    }
};

$(document).ready(divvyup_theme.init);