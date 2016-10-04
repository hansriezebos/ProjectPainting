function imgToData() {
	var canvasData = SketchPad.toDataURL('image/png');
	document.getElementById('image_data').value = canvasData;
}

function showImg() {
	var data = document.getElementById('image_data').value;
	alert(data);
	var myCV = document.getElementById('SketchPad');
	var ctx = myCV.getContext('2d');
	var img = new Image;
	img.onload = function(){
	  ctx.drawImage(img,0,0); // Or at whatever offset you like
	};
	img.src = data;
}