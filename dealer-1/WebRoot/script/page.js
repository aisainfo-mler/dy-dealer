var goPage=function (ele,page)
{	
	var pos=0;	
	var dest = $j(ele).parent(".pagination").attr("dest");
	if(IsSpace(page))
	{
			page=document.getElementById('goPage'+dest).value;
			if(page=="" || !isNumber(page))
			{
				document.getElementById('goPage'+dest).value=1;
				document.getElementById('goPage'+dest).focus();
				alert(text_page_error+"!");
				return;
			}
			
		
	}else if(!isNumber(page)){
		document.getElementById('goPage'+dest).value=1;
		document.getElementById('goPage'+dest).focus();
		alert(text_page_error+"!");
		return;
	}
	
	var parm=document.getElementById('sppageparm'+dest).value;
	var length=document.getElementById('sppagelength'+dest).value;
	var maxpage=document.getElementById('sppagetotal'+dest).value;
	if (parseInt(page)>0 && parseInt(page)<=parseInt(maxpage))
	{
		pos = parm.indexOf("**");
		var url = parm.substring(0,pos)+(page - 1)*length+parm.substring(pos+2);
		url = encodeURI(url);//格式转换
//		alert(parm);
		//document.location.href=url;
		if(parm.indexOf("select=1")>0)
		{
			nextpage.href=url;
			nextpage.click();	
		}else{
			//window.navigate(url);
			//window.location.href=url;
			loadPage_1($j(ele).parent(".pagination"),url);
			//loadPageContainer($j(ele).parents(".pagination"),data,callback);
		}
		//showWaiting();
	}
	else
	{
		document.getElementById('goPage'+dest).value=1;
		document.getElementById('goPage'+dest).focus();
		alert(text_page_error+"!");
	}
}
/**
 * 输入框中回车直接进行查询
 */
function KeyDown(event,ele,pageNo)
{
    if (event.keyCode == 13 || event.which == 13)
    {
        event.returnValue=false;
        event.cancel = true;
        //Form1.btnsubmit.click();
        goPage(ele,pageNo);
    }
}
function isNumber( s ){
	if(s=="")
		return true;
	var regu = "^[0-9]+$";
	var re = new RegExp(regu);
	return re.test(s);
}

//CheckBoxȫѡ
function CA()
{
	var frm=document.forms[1];//alert(frm.elements);
	for (var i=0;i<frm.elements.length;i++)
	{
		var e=frm.elements[i];
		if ((e.name != 'allbox') && (e.type=='checkbox'))
		{
			e.checked=frm.allbox.checked;
			if (frm.allbox.checked)
			{
				hL(e);
			}//endif
			else
			{
				dL(e);
			}//endelse
		}//endif
	}//endfor
}

//CheckBoxѡ����
function CCA(CB)
{
	var frm=document.forms[1];
	if (CB.checked)
		hL(CB);
	else
		dL(CB);
	var TB=TO=0;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e=frm.elements[i];
		if ((e.name != 'allbox') && (e.type=='checkbox'))
		{
			TB++;
			if (e.checked)
				TO++;
		}
	}
	frm.allbox.checked=(TO==TB)?true:false;
}

function hL(E)
{
	//while (E.tagName!="TR")
	{
		E=E.parentElement;
		//E.checked = E.parentElement.checked
		//alert(E.parentElement);
	}
	//E.className="H";
}

function dL(E)
{
	//while (E.tagName!="TR")
	{
		E=E.parentElement;
	}
	//E.className="aaa";
}
function Cancle()
{
	var frm=document.forms[1];
	for (var i=0;i<frm.elements.length;i++)
	{
		var e=frm.elements[i];
		if ((e.name != 'allbox') && (e.type=='checkbox'))
		{			
			if(e.checked)
			{
				e.checked = false;
				dL(e);
			}
		}//endif
	}//endfor
	return false;
}

//////////////////
function CCC()
{
	var frm=document.forms[1];
	for(var i=0;i<frm.elements.length;i++)
	{
		var E = frm.elements[i];
				
		if(E.type=='checkbox')
		{
			
			var name = E.name.substring(E.name.length,E.name.lastIndexOf(":")+1);
			if(name=='DeleteThis')
			{
			//alert(name);
			CCA1(E);
			}	
		}	
	}	
}
function CCA1(CB)
{
	var frm=document.forms[1];
	if (CB.checked)
		hL1(CB);
	else
		dL1(CB);

	var TB=TO=0;
	for (var i=0;i<frm.elements.length;i++)
	{
		var e=frm.elements[i];
		if (e.type=='checkbox')
		{
			TB++;
			if (e.checked)
				TO++;
		}
	}
	//frm.allbox.checked=(TO==TB)?true:false;
}

function hL1(E)
{
	while (E.tagName!="TR")
	{
		E=E.parentElement;
	}
	E.className="H";
}

function dL1(E)
{
	while (E.tagName!="TR")
	{
		E=E.parentElement;
	}
	E.className="aaa";
}