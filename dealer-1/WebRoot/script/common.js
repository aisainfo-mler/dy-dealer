function getURL(url){
	return _basePath+url;
}
function loadPage(target,url,data,callback) {
//	$j('#tempFocus').show().focus().hide();
	target.html("<div class=\"loadingBig\"></div>");
	try{
		target.load(url,data,callback);
	}catch(e){alert(e.message);}	
}


function IsSpace(strMain){
	var strComp = strMain;
	try {
		//alert("sss"+ (typeof strMain === "undefined"));
		if (strComp == "\u3000" || strComp == "" || strComp == " " || strComp == null || strComp == "null" || strComp.length == 0 || typeof strMain == "undefined" || strMain == "undefined") {
			return true;
		} else {
			return false;
		}
	}
	catch (e) {
		return false;
	}
}
//是否中文字符
String.prototype.isNotCN = function() {
	if ($j.trim(this).length == $j.trim(this).replace(/[^\x00-\xff]/gi,'xx').length) {
		return true;
	} else {
		return false;
	}
}

// 是否为空
String.prototype.isNull = function(){
	if (this == null || $j.trim(this)== "") {
		return true;
	} else {
		return false;
	}
}

function loadPage_1(ele,url){
	try{
		var target =$j(ele.attr("dest"));
		var targetForm = $j(ele.attr("formId"));
		var data = {};
		if(targetForm.length != 0){
			var post_data=targetForm.serializeArray();
			$j.each( post_data, function(i, field){
				if(url.indexOf(field.name) != -1){
					//去掉多余
					var xx = GetRequestValue(url)[field.name];
//					alert(GetRequestValue(url)[field.name]);
					url = url.replace("&" + field.name + "=" + xx,"");
					url = url.replace("?" + field.name + "=" + xx,"?")
				}
				 data[field.name]=field.value;
			}); 
			
		}
		target.load(url,data);
	}catch(e){alert(e.message);}	
}

function getTime() {
	var now= new Date();
	h=now.getHours();
	m=now.getMinutes();
	s=now.getSeconds();
	return (h+":"+m+":"+s);
}

function getDate(){
	 var now = new Date();
	 var year = now.getFullYear();       //年
	 var month = now.getMonth() + 1;     //月
	 var day = now.getDate();            //日
	
	 var hh = now.getHours();            //时
	 var mm = now.getMinutes();          //分
	
	 var clock = year + "-";
	
	 if(month < 10)
	     clock += "0";
	
	 clock += month + "-";
	
	 if(day < 10)
	     clock += "0";
	    
	 clock += day + " "; 
	 return(clock); 
}


function StringToDate(DateStr)  
{   
  
    var converted = Date.parse(DateStr);  
    var myDate = new Date(converted);  
    if (isNaN(myDate))  
    {   
        //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';  
        var arys= DateStr.split('-');  
        myDate = new Date(arys[0],--arys[1],arys[2]);  
    }  
    return myDate;  
}  

function StringToNum(numStr)  
{   
	if(/^\d+$/g.test(numStr)){
		if(/^0+/g.test(numStr)){
			numStr = numStr.replace(/^0+/g,"");
		}
		return parseInt(numStr);
	}else{
		return undefined;
	}
}

/**
 * 隐藏菜单栏
 */
function  sw_frameset_v(){
	$j("#menuD").toggle();
	var mainNode = $j("#swbutton_menu");
	var buttonopen = new Image();
	buttonopen.src = "images/sort_left.gif";
	var buttonclose = new Image();
	buttonclose.src = "images/sort_right.gif";
	if (mainNode.attr("src").indexOf("sort_left.gif") >= 0) {
		mainNode.attr("src",buttonclose.src);
	} else {
		mainNode.attr("src",buttonopen.src);
	}
	
}
/**
 * 隐藏头信息
 */
function  sw_frameset_h(){
	$j("#logoutD").toggle();
	var mainNode = $j("#swbutton_head");
	var buttonopen = new Image();
	buttonopen.src = "images/sort_up.gif";
	var buttonclose = new Image();
	buttonclose.src = "images/sort_down.gif";
	if (mainNode.attr("src").indexOf("sort_up.gif") >= 0) {
		mainNode.attr("src",buttonclose.src);
	} else {
		mainNode.attr("src",buttonopen.src);
	}
	
}

//根据路径获取文件扩展名  
function getPostfix(path){  
    return path.substring(path.lastIndexOf("."),path.length);  
}

/**
 * 获取form里面的数据
 * @param form
 * @return
 */
function getFormData(form,data)
{
	if(data == null || data == undefined)
	data = {};
	var fields = form.serializeArray();
	jQuery.each( fields, function(i, field)
	{
		data[field.name]=jQuery.trim(field.value);
	});
	
	return data;
}

function initSubWindow(style)
{
	if(style == undefined || style == null)
		style = "width:700px; left:200px;";
			
	if($j("#Sub_Window").length == 0)
	{
		var sub_window = "<div id=\"Sub_Window\" class=\"floatBox\" style=\""+style+"\">";
			sub_window += "	<div class=\"floatContainer\">";
			sub_window += "		<a class=\"colseMask\" href=\"javascript:void(0)\" onclick=\"hideElement(jQuery('#Sub_Window'));\" title=\"关闭\">关闭</a>";
			sub_window += "		<div id=\"Sub_Window_Body\" class=\"floatCon\"></div>";
			sub_window += "	</div>";
			sub_window += "</div>";
			
		$j(document.body).append(sub_window);
	}
	
}

function OpenSubWindow(url,param,callback,width)
{
	if($j("#Sub_Window").length == 0)
		initSubWindow();
	
	$j("#Sub_Window").css("width",width+"px");
	
	$j("#Sub_Window_Body").html("<div class='loading'></div>");//清除子窗口缓存
	
	$j("#Sub_Window_Body").load(url,param,callback);
	
	ShowSubWindow();
}

function OpenSubWindowWithHtml(html,width)
{
	if($j("#Sub_Window").length == 0)
		initSubWindow();
	
	$j("#Sub_Window").css("width",width+"px");
	
	$j("#Sub_Window_Body").html("<div class='loading'></div>");//清除子窗口缓存
	
	$j("#Sub_Window_Body").html(html);
	
	ShowSubWindow();
}

function CloseSubWindow()
{
	if($j("#Sub_Window").length >= 0)
		hideElement($j("#Sub_Window"));
}

function ShowSubWindow()
{
	if($j("#Sub_Window").length >= 0)
		showElement($j("#Sub_Window"));
}

/**
 * 页面如果需要遮罩，先添加一个<div id='pageMask'></div>层
 * 再调用此方法进行初始化
 * @return
 */
function initMask() {
	
	if($j("#pageMask").length == 0)
	{
//		if($j(document.body).children()>0)
//		{
//			alert($j(document.body).children().first());
//			$j(document.body).children().first().prepend("<div id='pageMask' class='pageMask'></div>");
//		}
//		else
			$j(document.body).prepend("<div id='pageMask' class='pageMask'></div>");
	}
	
	if($j("#newIfm").length == 0)
		$j("#pageMask").append("<iframe id='newIfm' border='0'></iframe>");
	
	if($j("#newPM").length == 0)
	{
		$j("#pageMask").append("<div id='newPM'></div>");
		$j("#newPM").css({background:"#000","z-index":10,position:"absolute",top:0,left:0});
	}
		
	$j("#newIfm").width(document.body.scrollWidth-5);
	$j("#newPM").css("width",document.body.scrollWidth-5);
	
	$j("#newIfm").height(document.body.scrollHeight+10);
	$j("#newPM").css("height",document.body.scrollHeight+10);
	
	$j("#pageMask").hide();
	
}

/**
 * 带遮罩显示element对象
 * @param element
 * @return
 */
function showElement(element,ifcenter)
{
	ifcenter = ifcenter == null || ifcenter == undefined?true:ifcenter;
	$j("#pageMask").show();
	ShowDivAndMoveCenter(element,ifcenter);
	var t,w;
	t = document.documentElement.scrollTop;
	w = document.documentElement.scrollWidth;
	if(ifcenter){
		element.css("top",t+200+"px");
		element.css("left",(w-1003)/2+150+"px");
	}
	$j("#newIfm").width(document.body.scrollWidth-5);
	$j("#newPM").css("width",document.body.scrollWidth-5);
	
	$j("#newIfm").height(document.body.scrollHeight+10);
	$j("#newPM").css("height",document.body.scrollHeight+10);
}

/**
 * 带遮罩隐藏对象
 * @param element
 * @return
 */
function hideElement(element){
	$j("#pageMask").hide();
	element.hide();
}

/**
* 显示层并且显示在正中央
**/
function ShowDivAndMoveCenter(element,ifcenter){
	ifcenter = ifcenter == null || ifcenter == undefined?true:ifcenter;
	/**
	 * 这里的代码不是用来居中，而是向上对其,并且向下偏移5个象素
	 */
	//alert($j("#todo_container").attr("offsetWidth")+":"+document.getElementById("todo_container").offsetWidth);
	if(ifcenter){
		element.css("top",document.documentElement.scrollTop + 120);
		element.css("left",document.documentElement.scrollLeft+(document.documentElement.clientWidth-element.attr("offsetWidth"))/2);
	}
	
//	if(ifcenter){
//		element.css("top",(document.body.clientHeight-$j("#Sub_Window").clientHeight)/2+document.body.scrollTop);
//		element.css("left",(document.body.clientWidth-$j("#Sub_Window").clientWidth)/2+document.body.scrollLeft);
//	}
	
	element.show();
}

function setCheckbox(dest_id,res_id,checkedValue,uncheckedValue)
{	
	if($j("#"+res_id).attr("checked")=="checked")
	{
		$j("#"+dest_id).val(checkedValue);
	}
	else
	{
		$j("#"+dest_id).val(uncheckedValue);
	}
}

function getUrl(url)
{
	return base_path+"/"+url;
}

/**
 * editor--编辑器对象
 * @param {Object} divKey
 * @return {TypeName} 
 */
var editor;
var currEditor;

function createEditor(divKey)
{
	if ( editor )
		return;
	// Create a new editor inside the <div id="editor">, setting its value to html
	var config = {};
	editor = CKEDITOR.appendTo(divKey, config, "" );
}

function removeEditor()
{
	if ( !editor ) 
		return;
		
	editor.destroy();
	editor = null;
}

function InsertHTML(html)
{
	if ( editor.mode == 'wysiwyg' )
	{
		editor.insertHtml( html );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function InsertText(value)
{
	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Insert as plain text.
		// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#insertText
		editor.insertText( value );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function SetContents(html)
{
	// Set editor contents (replace current contents).
	// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#setData
	editor.setData( html );
}

function GetContents()
{
	// Get editor contents
	// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#getData
	return editor.getData();
}

function ExecuteCommand( commandName )
{
	// Check the active editing mode.
	if ( editor.mode == 'wysiwyg' )
	{
		// Execute the command.
		// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#execCommand
		editor.execCommand( commandName );
	}
	else
		alert( 'You must be in WYSIWYG mode!' );
}

function CheckDirty()
{
	// Checks whether the current editor contents present changes when compared
	// to the contents loaded into the editor at startup
	// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#checkDirty
	return editor.checkDirty();
}

function ResetDirty()
{
	// Resets the "dirty state" of the editor (see CheckDirty())
	// http://docs.cksource.com/ckeditor_api/symbols/CKEDITOR.editor.html#resetDirty
	return editor.resetDirty();
}

//--------------
/**
 * 获得url参数
 */
function GetRequestValue(url_) {
	
	   var startIndex = url_.indexOf("?");
	   var url = url_.substr(startIndex); //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}

function openEasyUIWindow(url,data,title,width,height,callback,target){
	if(IsSpace(url)){
		return false;
	}
	var top = 200;
	var left = 400;
	if(IsSpace(width)){
		width = 450;
	}else if(width >= 600){
		left = 300;
	}
	
	var st = $j(document).scrollTop();
//	if(!IsSpace(document.body.scrollTop)){
//		st = document.body.scrollTop;
//	}else if(!IsSpace(document.getElementById("mainBody_div").scrollTop)){
//		st = document.getElementById("mainBody_div").scrollTop;
//	}else{
//		st = window.pageYOffset;
//	}
	var hsh = document.body.scrollHeight / 2;
	var qsh = document.body.scrollHeight / 4;
	var sh = 0;
	if(st > hsh){
		sh = st - 200;
	}
	if(qsh<=st && st<=hsh){
		sh = st / 1.2;
	}
//	if(document.body.scrollTop > 70){
//		sh = (document.body.scrollHeight - document.body.scrollTop) * 1.2;
//	}
	if(IsSpace(height) || height < 500){
		top = sh + 100;
	}else if(height >= 500){
		if($j(window).height() + 100 > document.body.scrollHeight){
			top = sh + 100;
		}else{
			top = sh + 200;
		}
	}
	if(IsSpace(target)){
		target = $j('#common_backDiv');
	}else if(!IsSpace(target.html())){
		closeEasyUIWindow(target);
	}
	loadPage(target,url,data,function(_d){
		target.window({
			title: title,
			width: width,
			minimizable:false,
			modal: true,
			shadow: false,
			closed: false,
			height: height,
			resizable:false
		})
		.window('move',{
			 left:left,  
			  top:top  
		});
		if(!IsSpace(callback)){
			callback();
		}
	});
	
}

function openNoColsedEasyUIWindow(url,data,title,width,height,callback,target){
	if(IsSpace(url)){
		return false;
	}
	var top = 200;
	var left = 400;
	if(IsSpace(width)){
		width = 450;
	}else if(width >= 600){
		left = 300;
	}
	if(IsSpace(height)){
		height = 200;
	}else if(height >= 500){
		top = 45;
	}
	if(IsSpace(target)){
		target = $j('#common_backDiv');
	}else if(!IsSpace(target.html())){
		closeEasyUIWindow(target);
	}
	loadPage(target,url,data,function(_d){
		target.window({
			title: title,
			width: width,
			closable:false,
			maximizable:false,
			minimizable:false,
			minimizable:false,
			modal: true,
			shadow: false,
			closed: false,
			height: height,
			resizable:false
		}).window('move',{
			 left:left,  
			  top:top  
		});
		if(!IsSpace(callback)){
			callback();
		}
	});
	
}


function justOpenEasyUIWindow(title,width,height,target){
	if(IsSpace(target)){
		return;
	}
	var top = 200;
	var left = 550;
	if(IsSpace(width)){
		width = 450;
	}else if(width >= 600){
		left = 300;
	}
	
	var st = $j(document).scrollTop();
	var hsh = document.body.scrollHeight / 2;
	var qsh = document.body.scrollHeight / 4;
	var sh = 0;
	if(st > hsh){
		sh = st - 200;
	}
	if(qsh<=st && st<=hsh){
		sh = st / 1.2;
	}
	
	if(IsSpace(height) || height < 500){
		top = sh + 250;
	}else if(height >= 500){
		if($j(window).height() + 100 > document.body.scrollHeight){
			top = sh + 100;
		}else{
			top = sh + 200;
		}
	}
	
	$j('#common_backDiv').html(target.html())
	$j('#common_backDiv').window({
		title: title,
		width: width,
		minimizable:false,
		modal: true,
		shadow: false,
		closed: false,
		height: height,
		resizable:false
	}).window('move',{
		 left:left,  
		  top:top  
	});
	
}

function closeEasyUIWindow(target){
	if(target){
		var targetId = target.attr("id");
		target.html("");
		target.window('close');
		if($j("div[id='" + targetId + "']").length > 1){//easyui  多窗口弹出 的一个bug
			target.parent().remove();
		}
		
	}else{
		$j('#common_backDiv').html("");
		$j('#common_backDiv').window('close');
	}
	
}

function selectedTr(src){
	$j(src).siblings().removeClass();
	$j(src).addClass("tr_focus");
}

/**
 * 动态展现左右切换
 * @return
 */
function animateLeftRight(leftTarget,rightTarget){
	if(leftTarget.css('display') == 'block'){
//		  leftTarget.animate({width:0},"normal");
		  leftTarget.css('display','none');
		  rightTarget.css('display','block');
//		   rightTarget.animate({},"normal");
	}else{
//		rightTarget.animate({width:0},"normal");
		rightTarget.css('display','none');
		leftTarget.css('display','block');
//		leftTarget.animate({},"normal");
	}
}

/**
 * 是否为图片
 * @return
 */
function isImage(filePath){
	//先获得后缀名
	 var postfix = getPostfix(filePath); 
	 
	 if(!IsSpace(postfix)){
		 postfix = postfix.toUpperCase();
		var imgArr = ['.JPG','.PNG','.GIF','.BMP'];
		for(var i = 0; i < imgArr.length; i++){
			if(postfix ==imgArr[i] ){
				return true;
			}
		}
	 }
		 return false;
}

/**
 * 是否为视频
 * @return
 */
function isAvi(filePath){
	//先获得后缀名
	 var postfix = getPostfix(filePath); 
	 
	 if(!IsSpace(postfix)){
		 postfix = postfix.toUpperCase();
		var aviArr = ['.MP4'];
		for(var i = 0; i < aviArr.length; i++){
			if(postfix ==aviArr[i] ){
				return true;
			}
		}
	 }
		 return false;
}

/**
 * form校验
 * @param formTaget
 * @return
 */
function commonValidate(formTaget){
	
	formTaget.validate({
			errorElement :"div",// 使用"div"标签标记错误， 默认:"label"
			wrapper:"li",// 使用"li"标签再把上边的errorELement包起来
			errorClass :"WarningClew",// 错误提示的css类名"error"
			focusCleanup:true// 当未通过验证的元素获得焦点时,并移除错误提示
	//		onfocusout:true
		});
	return formTaget.validate().form();
}

/**
 * 打开ajax进度条
 * @return
 */
function openAjaxProgress(){
	var target = $j('#common_progress');	
	target.window({
		title: "请稍候",
		width: 430,
		modal: true,
		shadow: false,
		closed: false,
		height: 65,
		collapsible:false,
		closable:false,
		maximizable:false,
		minimizable:false,
		resizable:false}
	);
	updateAjaxProgress();
}

/**
 * 进度条造假
 * @return
 */
function updateAjaxProgress(){
	var value = $j('#common_progressBar').progressbar('getValue');
	if (value < 80){
	
		value += Math.floor(Math.random() * 10);
		$j('#common_progressBar').progressbar('setValue', value);
		setTimeout(arguments.callee, 350);
	}
}

/**
 * 关闭进度条
 * @return
 */
function closeAjaxProgress(){
	$j('#common_progressBar').progressbar('setValue', 100);
	setTimeout(function(){
		$j('#common_progress').window('close');
		$j('#common_progressBar').progressbar('setValue',0);
	}, 300);
}


