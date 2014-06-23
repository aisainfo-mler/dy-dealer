

/**选择角色*/
function selectRole(roleNames, roleCodes, appId) {
	var param = {
		'selectedRoleNames': roleNames,
		'selectedRoleCodes': roleCodes
	};
	openEasyUIWindow(getURL('/roleManager/selectRole.do'), param, '选择角色', 600, 450, '');
}

/**打开上传附件窗口*/
function openUploadFile(fileExt, fileStatus, mappingId) {
	
	var sendUrl = $j("#upload_send_ip").val();
	var callbackUrl = $j("#upload_callback_ip").val() + "/fileUpload/fileUploadCallBack.html?callback=";
	if ( '01' == fileExt ) {
		callbackUrl += "uploadImageCallback";
	} else if ( '02' == fileExt ) {
		callbackUrl += "uploadFileCallback";
	}
	
	var param = {
		'sendUrl': sendUrl,
		'callbackUrl': callbackUrl,
		'fileUpload.fileExt': fileExt,
		'fileUpload.fileStat': fileStatus,
		'saveArticleTitle.aid': mappingId
	};
	
	var url = getURL("/productManagerSimple/productUpload.do");
	openEasyUIWindow(url, param, '上传文件', 450, 200, '');
	
}

/**上传附件回调方法*/
function uploadFileCallback(data) {
//	$j.messager.progress('close');
//	closeEasyUIWindow();
//	$j.messager.alert("SUCCESS","上传成功！");
	$j.messager.progress('close');
	eval("var json = " + data);
	if(json.flag){
		closeEasyUIWindow();
		$j.messager.alert("SUCCESS","上传成功！");
	}else{
		$j.messager.alert("上传失败",json.msg);
	}
	showFileList();
}

/**上传图片回调方法*/
function uploadImageCallback(data) {
//	$j.messager.progress('close');
//	closeEasyUIWindow();
//	$j.messager.alert("SUCCESS","上传成功！");
	
	$j.messager.progress('close');
	eval("var json = " + data);
	if(json.flag){
		closeEasyUIWindow();
		$j.messager.alert("SUCCESS","上传成功！");
	}else{
		$j.messager.alert("上传失败",json.msg);
	}
	showImages();
}

/**删除附件*/
function deleteFile(fileId) {
	
	$j.messager.confirm('删除附件', '确定要删除此附件？', function(r){
		if (r){
			$j.ajax({
				url : getURL('/articleTitle/deleteImage.do'),
				method : 'post',
				data : { 'fileUpload.fileId' : fileId },
				success : function(data) {
					showFileList();
				}
			});
		}
	});
}

/**
 * 校验上传前的文件
 * @param form
 * @return
 */
function product_check_uploadForm(formId){
	if($j("#" + formId).length != 0){
		var files = $j("#" + formId + " input[name='file']");
		var fileExt = $j("input[name='fileUpload.fileExt']").val();
//		$j.each( files, function(i, eleDom){
		for(var i = 0; i < files.length; i++){	
			if(IsSpace(files[i].value)){
				$j.messager.alert("Warning","请选择文件","warning",function(){
					$j(files[i]).focus();
				});
				return false;
			}else{
				if(!IsSpace(fileExt)){
					if(fileExt == "01"){
						if(isImage(files[i].value)){
							$j.messager.progress({
								title:'请稍等',
								msg:'正在上传...'
							});
							return true;
						}else{
							$j.messager.alert("Warning","文件类型有误，请重选!","warning",function(){
								$j(files[i]).focus();
							});
							return false;
						}
					}else if(fileExt == "02"){
						var bol = isImage(files[i].value);
						if(bol){
							$j.messager.alert("Warning","文件类型有误，请重选!","warning",function(){
								$j(files[i]).focus();
							});
							return false;
						}else{
							$j.messager.progress({
								title:'请稍等',
								msg:'正在上传...'
							});
							return true;
						}
					}
				}
			}
		}; 
		return false;
	}else{
		return false;
	}
	
}