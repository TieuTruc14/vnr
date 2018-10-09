//Make sure you downloaded the image file in numbered_markers.js

//Note that the text could also be letters instead of numbers if that's more appropriate
var marker = new L.Marker(new L.LatLng(0, 0), {
    icon:	new L.NumberedDivIcon({number: '1'})
});