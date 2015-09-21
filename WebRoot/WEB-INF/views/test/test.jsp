<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'showreport.jsp' starting page</title>
    <%@ include  file="../include/head.jsp"%>
    <style type="text/css"> 
table{ 
border-collapse: collapse; 
border: 0px solid #999; 

} 
table td { 
border-top: 0; 
border-right: 1px solid #999; 
border-bottom: 1px solid #999; 
border-left: 0; 
} 
table tr.lastrow td { 
border-bottom: 0; 
} 
table tr td.lastCol { 
border-right: 0; 
} 
</style> 
    
 	<script type="text/javascript">
 	

	
	function doCondition(){
	
	   var para = "pam="+$('#name').val();	
			xmlHttp = $.ajax({
				url:"<%=request.getContextPath()%>/testCon/dotest",
				async:true,
				data:para,
				dataType:"json",
				type:"POST",
			error:function(){
				xmlHttp.responseText;
				alert('操作出现异常');
			},success:function(data) {
				alert(data.eti);
			}
		});
	}

	</script>
  </head>
  
  <body>
 <body  style="overflow-x:hidden;" >
 <!-- 查询条件 -->
  <table class="table_con" border="0" cellspacing="0" >
	<tr>
		<td align="left" nowrap="nowrap">要保存的数据：</td>
		<td nowrap="nowrap">
			<input type="text" id="name" name="name"/>
		</td>
	</tr>
	<tr align="left"><td colspan="2" align="left"><input type="button" value="执行" onclick="doCondition();"></input>
	</td></tr>
	</table>
	





  </body>
</html>
