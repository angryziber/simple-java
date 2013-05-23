$(function() {
    $.ajaxSetup({
       error: function(req) {
           if (req.status == 0) return;
           console.log(req);
           alert('Failed: ' + req.status + ' ' + req.statusText + (req.responseText && req.responseText.length < 200 ? ': ' + req.responseText : ''));
       }
    });
});

function detectLocation(callback) {
    navigator.geolocation.getCurrentPosition(
        callback
    );
}
