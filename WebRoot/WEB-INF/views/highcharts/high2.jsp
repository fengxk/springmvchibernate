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
     var options = {
                chart: {
                    renderTo: "container",
                    plotBackgroundColor: null,
                    plotBorderWidth: null,
                    plotShadow: false,
                    type:'line'
                },title: {
                    text: ''
                },subtitle: {
		            text: ' '
		        },xAxis: {
		            categories: []
		        },yAxis: {
		        	title: {                    
						text: ''              
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
                    enabled: false // 删除右下角自带的图标
                },series : []
            }
     
	function init(){
		createChart();
		loadData();
	}
	function createChart(){
		chart1 = new Highcharts.Chart(options);
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
					clearPlot();
					for(var i=0;i<data.hp.series.length;i++){
						chart1.addSeries(data.hp.series[i],false);
					}
					
					chart1.setTitle({text:'如何动态添加图表坐标轴(xAxis/yAxis)和series序列数据'});
					
					chart1.redraw();
					addY('自定义单位',false);
				}
			});
	}
	function clearPlot() {  
         var series=chart1.series;                  
         while(series.length > 0) {  
             series[0].remove(false);  
         }  
         chart1.redraw();  
     } 
     function  addY(a,b){
     	var yAxisLength = chart1.yAxis.length;
     	//第一个参数表示相关轴的配置参数
     	//第二个参数表示十分为xAxis
     	//第三个参数表示十分redraw
     	//第四个参数表示十分现实动画效果
		chart1.addAxis({labels: { enabled: true,formatter: function () { 
										return this.value + '°C';                     
									}      
					},title: {                    
						text: a +  yAxisLength             
					},opposite: b//坐标轴显示在右侧 
					}, false, true, true);
		chart1.redraw();
     }
	</script>
  </head>
	<body onload="init();" >
<div id="container" style="min-width:700px;height:400px"></div>
</body>
</html>
