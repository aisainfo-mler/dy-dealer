//IE6下浮动
if(navigator.userAgent.indexOf("MSIE 6")>0){
	lastScrollY=0;
	function heartBeat(){
		var diffY;
		if (document.documentElement && document.documentElement.scrollTop)
			diffY = document.documentElement.scrollTop;
		else if (document.body)
			diffY = document.body.scrollTop
		else
		{/*Netscape stuff*/}
		percent=.1*(diffY-lastScrollY);
		if(percent>0)percent=Math.ceil(percent);
		else percent=Math.floor(percent);
		document.getElementById("editorSide").style.top=parseInt(document.getElementById("editorSide").style.top)+percent+"px";
		
		lastScrollY=lastScrollY+percent;
	}
	
	window.setInterval("heartBeat()",1);
}