<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
	<title>塞弗司导系统</title>
     <%@ include  file="../include/head.jsp"%>
     <script type="text/javascript">
     var chart1;
     var highchartsOptions = Highcharts.setOptions(Highcharts.theme);

	//汉化图表菜单
	Highcharts.setOptions({
	    lang: {
	        contextButtonTitle: "图表菜单",
	        printChart: "打印图片",
	        downloadJPEG: "下载JPEG 图片",
	        downloadPDF: "下载PDF文档",
	        downloadPNG: "下载PNG 图片",
	        downloadSVG: "下载SVG 矢量图",
	        exportButtonTitle: "导出图片"
	    }
	});
     
	function init(){
		createChart();
		loadData();
	}
	function createChart(){
		chart1 = new Highcharts.Chart({
                chart: {
                    renderTo: "container",
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type:'line'
                },title: {
                    text: 'XX曲线图'
                },subtitle: {
		            text: 'Source: 青岛大巴'
		        },xAxis: {
		            categories: []
		        },yAxis: {
		            title: {
		                text: 'Temperature (°C)'
		            }
		        },tooltip: {
                    pointFormat: '{series.name}: <b>{point.y}</b>'
                },plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }

                        },
                    }
                },credits: {  
                    enabled: false // remove high chart logo hyper-link  
                },series : [{name:'',data:[]},{name:'',data:[]},{name:'',data:[]}]
            });
	}
	function loadData(){
		var para="";
		xmlHttp = $.ajax({
				url:"<%=request.getContextPath()%>/highcharts_con/getdata",
				async:false,
				data:para,
				dataType:"json",
				type:"POST",
				error:function(){
					xmlHttp.responseText;
					alert('操作出现异常');
				},success:function(data) {
					chart1.xAxis[0].setCategories(data.hp.categories);
					for(var i=0;i<data.hp.series.length;i++){
						chart1.series[i].setData(data.hp.series[i].data);
						chart1.series[i].name=data.hp.series[i].name;
					}
					chart1.setTitle({text:chart1.series[0].name}); 
					chart1.redraw();
					//chart1.tooltip.pointFormat='{series.name}: <b>{point.y}</b>';
				}
			});
	}
	</script>
  </head>
	<body onload="init();">
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>
