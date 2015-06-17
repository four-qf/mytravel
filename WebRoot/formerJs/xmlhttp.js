var xmlHttp=false;

function createXMLHttpRequest() 
{
	if (window.ActiveXObject)                          //在IE浏览器中创建XMLHttpRequest对象
	{
		try
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch(e)
		{
			try
			{
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
			}
			catch(ee)
			{
				xmlHttp=false;
			}
		}
    }
	else if (window.XMLHttpRequest)                 //在非IE浏览器中创建XMLHttpRequest对象
	{
		try
		{
			xmlHttp = new XMLHttpRequest();                      
		}
		catch(e)
		{
			xmlHttp=false;
		}
    }
} 

	