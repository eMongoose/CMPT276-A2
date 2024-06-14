// dynamically change the size and colour of the rectangle
function updateRectangle() {
    // get parameters from inputs
    var width = document.getElementById('width').value;
    var height = document.getElementById('height').value;
    var color = document.getElementById('color').value;
    var rectangle = document.getElementById('rectangle');

    // change the attributes accordingly
    rectangle.style.width = width + 'px';
    rectangle.style.height = height + 'px';
    rectangle.style.backgroundColor = color;

    // update the rectangle
    document.getElementById('widthOutput').textContent = width;
    document.getElementById('heightOutput').textContent = height;
}

window.onload = updateRectangle;
