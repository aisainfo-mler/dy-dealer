var $j = jQuery.noConflict();
String.prototype.trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g,"");	
}
function checkLogin(loginType){
	var username = document.getElementById("username").value.trim();
	var password = document.getElementById("password").value.trim();
	if(username == null || username == undefined || username == ""){
		$j("#username").css("color","red");
		return false;
	}
	if(password == null || password == undefined || password == ""){
		$j("#password").css("color","red");
		return false;
	}
	var data = {
			'user.userCode':username,
			'user.password':password
			}
	var url = baseUrl + "/common/check.do?request_locale=" + _lang;
	var options = { 
	        type:'post',
			url:url,
			data:data,
			beforeSend:function(xhr){
				$j("#submit1").hide();
				$j("div .loadingBig").show();
			},
			success: function(dataObjca){
				eval("var json = " + dataObjca);
				if(json.flag){
					$j("#submit1").hide();
					window.location.href = baseUrl + "/commonL/login.do";
				}else{
					$j("div .loadingBig").hide();
					$j("#submit1").show();
					alert(text_login_fail+":" + json.msg);
				}
	        },
	        error:function(xhr,status,exception){
				alert(xhr.responseText);
				$j("#submit1").removeAttr("disabled");
			}
	};
	$j.ajax(options);
	
}

$j(document).ready(function(){
	var indexLocation = window.location.href;
//	alert(indexLocation);

//	var replaceLocation = window.location.search;
//	
//	if(replaceLocation==undefined || replaceLocation=="" || replaceLocation==null){
//		if(indexLocation != currentUrl){
//			window.location.href = currentUrl;
//		};
//	}else {
//		if(replaceLocation.indexOf("request_locale") == -1 && replaceLocation.indexOf("requestLang") == -1){
//			if(indexLocation != currentUrl){
//				window.location.href = currentUrl;
//			};
//		}
//			
//	}
	
	if(indexLocation.indexOf("en.do") == -1 && indexLocation.indexOf("cn.do") == -1){
	if(indexLocation != currentUrl){
		window.location.href = currentUrl;
	};
}
	
	$j("#username").keyup(function(){
		var value = $j("#username").val();
		if (value != "" && value != null) {
			$j("#username").css("color","gray");
		}
	 });
	$j("#password").keyup(function(){
		var value = $j("#password").val();
		if (value != "" && value != null) {
			$j("#password").css("color","gray");
		}
	 });
	document.onkeydown = function(e){
		   if(!e) e = window.event;//火狐中是 window.event
		   if((e.keyCode || e.which) == 13){
			   $j("#submit1").focus();
		       $j("#submit1").click();
		   }
		}
	$j("#username").focus();
	});