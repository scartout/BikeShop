$(document).ready(function(){

	initClock();
	initModalPolicy();
	initZoomModal();
	
	/*
    
	1. Init scroll to top
	
	*/
	
	
    //Check to see if the window is top if not then display button
    $(window).scroll(function(){
        if ($(this).scrollTop() > 100) {
            $('.go-top').fadeIn();
        } else {
            $('.go-top').fadeOut();
        }
    });

    //Click event to scroll to top
    $('.go-top').click(function(){
        $('html, body').animate({scrollTop : 0},800);
        return false;
    });
    
    /*
    
	2. Init clock
	
	*/

    function initClock(){
		var myVar = setInterval(myTimer, 1000);
		function myTimer() {
		    var d = new Date();
		    document.getElementById("clock").innerHTML = d.toLocaleTimeString();
		}
    }
    
    /*
    
	3. Init privacy policy modal
	
	*/
    
	function initModalPolicy(){
		// Get the modal
		var modal = document.getElementById('policy_modal');

		// Get the button that opens the modal
		var btn = document.getElementById("policy_button");

		// Get the span element that closes the modal
		var span = document.getElementsByClassName("policy-modal-close")[0];

		// When the user clicks on the button, open the modal
		btn.onclick = function() {
		    modal.style.display = "block";
		}

		// When the user clicks on span (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}

		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal) {
		        modal.style.display = "none";
		    }
		}
	}
	
    /*
    
	4. Init product image zoom modal
	
	*/
	
	function initZoomModal(){
		var modal = document.getElementById('myModal');
		var img = document.getElementById('myImg');
		var modalImg = document.getElementById("img01");
		var captionText = document.getElementById("caption");
		img.onclick = function(){
		    modal.style.display = "block";
		    url = img.src;
		    modalImg.src = url;
		    captionText.innerHTML = $(this).attr('alt');
		}
	
		var span = document.getElementsByClassName("close")[0];
	
		span.onclick = function() { 
		    modal.style.display = "none";
		}
	}

});