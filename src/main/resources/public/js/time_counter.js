var startTime = new Date();        //Start the clock!
window.onbeforeunload = function()        //When the user leaves the page(closes the window/tab, clicks a link)...
{
    var endTime = new Date();        //Get the current time.
    var timeSpent = (endTime - startTime);        //Find out how long it's been.
    var xmlhttp;        //Make a variable for a new ajax request.
    if (window.XMLHttpRequest)        //If it's a decent browser...
    {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();        //Open a new ajax request.
    }
    else        //If it's a bad browser...
    {
        // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");        //Open a different type of ajax call.
    }
    var url = "/api/visit_time?time="+timeSpent;        //Send the time on the page to the server
    xmlhttp.open("GET",url,false);        //The false at the end tells ajax to use a synchronous call which wont be severed by the user leaving.
    xmlhttp.send(null);        //Send the request and don't wait for a response.
};
