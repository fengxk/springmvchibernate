<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
    <base href="<%=basePath%>" >
	<title>塞弗司导系统</title>
     <%@ include  file="head.jsp"%>
  </head>
	<body>
		<table id="datagrid">
		</table>
		<script type="text/javascript">
		var userAttrName = '${userAttrName}';
		$('#datagrid').datagrid({
		    url:'user/showInfos',
		    queryParams:{pageSize:15,pageNumber:1},
		    fit: true,//自动大小  
		    pagination:true,
		    rownumbers:true,
		    striped:true,
		    //fitColumns:true,
		    singleSelect:true,
		    method:'post',
		    pageSize: 15,//每页显示的记录条数，默认为10  
		    pageList: [15,30,50],//可以设置每页记录条数的列表 
		    columns:[[
		        {field:'userName',title:'用户名',width:200},
		        {field:'password',title:'密码',width:200},
		        //{field:'role.roleName',title:'角色',width:200},
		        {field:'status',title:'是否可用',width:100,
		        	formatter: function (value,row,index){
	        			return value == 1?'可用':'不可用';
        			}	
		        },
		        {field:'opt',title:'操作',width:150,
		          	formatter: function(value,row,index){
		          	var btn="";
		          		if(userAttrName==row.userName){
		          			btn = "  <a href='javascript:void(0)' onClick=\"edit('"+row.id+"')\">编辑</a>";
		          		}else{
		          			btn = "  <a href='javascript:void(0)' onClick=\"edit('"+row.id+"')\">编辑</a>  <a href='javascript:void(0)' onClick=\"removeData('"+row.id+"')\" style='margin-left:10px;'>删除</a>";
		          		}
			        	return btn;
		        	}
		        }
		    ]],
			toolbar:[{
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						$("#edit_form").form('clear');
						$('#edit_dailog').show();
						$('#edit_dailog').dialog({
						    title: '新增数据',
						    width: 400,
			   				height: 260,
						    closed: false,
						    cache: false,
						    modal: true,
						    buttons:[{
						    	text:'保存',
						    	handler:function(){
						    	$.messager.progress();	// 显示进度条
						    		$("#edit_form").form('submit',{
						    			url:'user/save',
						    			onSubmit: function(){
											var isValid = $(this).form('validate');
											if (!isValid){
												$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
											}
												return isValid;	// 返回false终止表单提交
										},
						    			success:function(data){ 
								    		$('#datagrid').datagrid('reload');
								    		$('#edit_dailog').dialog('close');
								    		$.messager.show({
												title:'提示',
												msg:data,
												timeout:2000
											});
											$.messager.progress('close');// 如果提交成功则隐藏进度条
									    }
						    		});
						    	}
						    },{
						    	text:'取消',
						    	handler:function(){
						    		$('#edit_dailog').dialog('close');
						    	}
						    }
						    ]
						});
					}
				}]
		});
	
		 //删除
    function removeData(id){
    	$.messager.confirm('确认删除','确定删除此用户吗?',function(r){
		    if (r){
		    	$.ajax( {  
		            type : 'POST',  
		            contentType : 'application/json',  
		            url : 'user/delete',  
		            data : JSON.stringify({id:id}),  
		            dataType : 'json',  
		            success : function(data) {  
		            	$.messager.show({
							title:'提示',
							msg:data.msg,
							timeout:2000
						});
		        	$('#datagrid').datagrid('reload');  
		            },  
		            error : function(data) {  
		              alert("服务器异常")  ;
		            }  
		    	 });
		    }
		});
    }
    
    //修改订单信息
    function edit(id){
    	$("#edit_form").form('clear');
    	$.getJSON("user/getUserById",{id:id},function(data){
    		$("#id").val(data.id);
    		$("#userName").textbox("setValue",data.userName);
    		
    		if(userAttrName==data.userName){
    			$("#userName").textbox('readonly',true);
    		}else{
    			$("#userName").textbox('readonly',false);
    		}
    		$("#password").textbox("setValue",data.password);
    		$('#edit_dailog').show();
			$('#edit_dailog').dialog({
			    title: '修改数据',
			    width: 400,
			    height: 260,
			    closed: false,
			    cache: false,
			    modal: true,
			    buttons:[{
			    	text:'保存',
			    	handler:function(){
			    	$.messager.progress();	// 显示进度条
		    		$("#edit_form").form('submit',{
		    			url:'user/save',
		    			onSubmit: function(){
							var isValid = $(this).form('validate');
							if (!isValid){
								$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
							}
								return isValid;	// 返回false终止表单提交
						},
		    			success:function(data){ 
				    		$('#datagrid').datagrid('reload');
				    		$('#edit_dailog').dialog('close');
				    		$.messager.show({
								title:'提示',
								msg:data,
								timeout:2000
							});
							$.messager.progress('close');// 如果提交成功则隐藏进度条
					    }
		    		});
		    		}
			    },{
			    	text:'取消',
			    	handler:function(){
			    		$('#edit_dailog').dialog('close');
			    	}
			    }]
			});
    	});
    }
    //搜索
    function doSearch(){
    	$('#datagrid').datagrid({
    		url:'authority/list',
			queryParams: {
				serviceProviderName: $("#div_search>input[name='serviceProviderName']").val(),
				serviceProviderPhone: $("#div_search>input[name='serviceProviderPhone']").val(),
				serviceProviderAddress: $("#div_search>input[name='serviceProviderAddress']").val()
			}
		});
    	$('#datagrid').datagrid('reload');
    }
    //重置查询
    function reSearch(){
		  $('#searchId').val("");
          doSearch("");
    }
    //用户唯一验证
    function validHouse(event){
    	var val = event.target.value;
    	$.getJSON("/houses/findhouseByName",{houseName:val},function(data){
    		if(data.state == 1){
	    		$.messager.alert('错误','房间已存在');
    		}
    	})
    }
	</script>
	
	<!-- 编辑订单的窗口 -->
	<div id="edit_dailog" style="display: none;">
		<form id="edit_form" method="post">
	    	<table align="center" cellpadding="5" style="margin-top: 30px;">
	    		<tr>
	    			<td>用户名:</td>
	    			<td>
	    				<input type="hidden" id="id" name="id" >
	    				<input id="userName" class="easyui-textbox" type="text" name="userName" value="" data-options="required:true" style="width:150px" missingMessage="用户名不能为空"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>密码:</td>
	    			<td><input id="password" class="easyui-textbox" type="password" name="password" value="" data-options="required:true" style="width:150px" missingMessage="密码不能为空"/></td>
	    		</tr>
	    	</table>
	    </form>
	</div>
	
</body>
</html>
