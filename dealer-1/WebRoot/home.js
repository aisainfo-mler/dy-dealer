
/**
 * 切换菜单样式
 * @param total
 * @param src
 * @return
 */
function home_changeMenu(total,src,url,menuId,isWhole){
	$j("div[name='mainMenu_div'] ul li").removeClass();
	$j(src).parent("li").addClass("navSelected");
	
//	var sub_div = $j("div[name='subMenu_div']");
//	sub_div.hide();
//	/** total < 0 为主页 **/
//	if(total >= 0 )
//	{
//		sub_div.eq(total).show();
//		sub_div.eq(total).children("ul").children("li").eq(0).children("a").click();
//	}
	$j('#common_backDiv').window('close');
	$j("#common_backDiv").html("");
	$j("#mainBody_div").html("");
//	alert(isWhole);
	if(isWhole == 0 || isWhole == '0'){
		if(IsSpace(url)){
			url = "";
//			return false;
		}
		url =  getURL("/common/subContent.do?menuId=" + menuId + "&menuUrl=" + url);
	}else{
		if(IsSpace(url)){
			return false;
		}else{
			url =  getURL(url);
		}
		
	}
	
	loadPage($j("#mainBody_div"),url,{},function(_d){});
	return false;
	
}

/**
 * 点击子菜单，在主界面打开对应的内容
 * @param src
 * @param url
 * @return
 */
function home_loadMenu(src ,url){
	
	$j('#common_backDiv').window('close');
	$j("#common_backDiv").html("");
	$j("#mainBody_div").html("");
	
	if(IsSpace(url)){
		return false;
	}
	url =  getURL(url);
	$j("div[name='subMenu_div'] ul li").removeClass();
	$j(src).parent("li").addClass("navSelected");
	loadPage($j("#mainBody_div"),url,{},function(_d){});
	$j.history.load(url);
}

/**
 * 主菜单界面，打开主菜单，
 * 如果有子菜单则展示子菜单，如果还包含url，
 * 则在mainBody_div中展示url对应的内容
 * 
 * @param total
 * @param src
 * @return
 */
function home_openApp(total,src,url){
	
	$j("div[name='mainMenu_div'] ul li").removeClass();
	$j(src).parent("li").addClass("navSelected");
	$j("div[name='subMenu_div']").hide();
	
	if(url == undefined || url == null || IsSpace(url))
		return;
	
	var url = getURL(url);

	loadPage($j("#mainBody_div"),url,{},function(_d){});
	
}
/**
 * 退出登陆 
 * @return
 */
function closeAlert(){
	$j.messager.confirm('warn', 'Log out ？', function(r){
		if (r){
			window.location.href = getURL("/common/logout.do") ;
		}
	});
}

function home_leftOpen(url, selectElement){
	$j(".sicurrent").removeClass();
	$j(selectElement).parent("li").addClass("sicurrent");
	
	if(IsSpace(url)){
		return false;
	}
	url = getURL(url);
	loadPage($j("#sub_Content"),url,{},function(_d){});
}
