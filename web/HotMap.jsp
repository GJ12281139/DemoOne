<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.BufferedReader" %><%--
  Created by IntelliJ IDEA.
  User: guojie
  Date: 2017/8/1
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=YjHDKaV9qWGdS4GT0pGxceNnMRNuDRXI"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script>
    <title>热力图</title>
    <style type="text/css">
        ul,li{list-style: none;margin:0;padding:0;float:left;}
        html{height:100%}
        body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
        #container{height:700px;width:100%;}
        #r-result{width:100%;}
    </style>
</head>
<body>
<div id="container"></div>
<div id="r-result">
    <input type="button"  onclick="openHeatmap();" value="显示热力图"/><input type="button"  onclick="closeHeatmap();" value="关闭热力图"/>
</div>
</body>
</html>
<script type="text/javascript">
    var map = new BMap.Map("container");          // 创建地图实例

    var point = new BMap.Point(116.418261, 39.921984);
    map.centerAndZoom(point, 15);             // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(); // 允许滚轮缩放

//    var points =[
//        {"lng":116.424579,"lat":39.914987,"count":57},
//        {"lng":116.42076,"lat":39.915251,"count":70},
//        {"lng":116.425867,"lat":39.918989,"count":8}];

    <%
        String txt = request.getParameter("txt"); // 设置URL连接传参数 ?txt=1000
        String path = "D:\\10000More400\\UsersOrigFiles\\";
        File file = new File(path,txt+".txt");
        FileReader fr = new FileReader(file);  //字符输入流
        BufferedReader br = new BufferedReader(fr);  //使文件可按行读取并具有缓冲功能
        String str = br.readLine();
        int lines = Integer.valueOf(str.split("\\s+")[1]);
        String ss = "";
        while( lines > 0){
            lines--;
            str = br.readLine();
            String[] strs = str.split("\\s+");
            if(strs.length >= 3){
                ss += "{\"lng\":"+strs[0]+",\"lat\":"+strs[1]+",\"count\":"+strs[2]+"}";
                if(lines == 0)
                    ss+="\n";
                else
                    ss+=",\n";
            }
        }
        br.close();    //关闭输入流
    %>
    var points = [
        <%=ss%>
    ];

    if(!isSupportCanvas()){
        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~')
    }
    //详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
    //参数说明如下:
    /* visible 热力图是否显示,默认为true
     * opacity 热力的透明度,1-100
     * radius 势力图的每个点的半径大小
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     *	{
            .2:'rgb(0, 255, 255)',
            .5:'rgb(0, 110, 255)',
            .8:'rgb(100, 0, 255)'
        }
        其中 key 表示插值的位置, 0~1.
            value 为颜色值.
     */
    heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
    map.addOverlay(heatmapOverlay);
    heatmapOverlay.setDataSet({data:points,max:100});
    //是否显示热力图
    function openHeatmap(){
        heatmapOverlay.show();
    }
    function closeHeatmap(){
        heatmapOverlay.hide();
    }

    closeHeatmap();
    function setGradient(){
        /*格式如下所示:
       {
             0:'rgb(102, 255, 0)',
             .5:'rgb(255, 170, 0)',
             1:'rgb(255, 0, 0)'
       }*/
        var gradient = {};
        var colors = document.querySelectorAll("input[type='color']");
        colors = [].slice.call(colors,0);
        colors.forEach(function(ele){
            gradient[ele.getAttribute("data-key")] = ele.value;
        });
        heatmapOverlay.setOptions({"gradient":gradient});
    }
    //判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
</script>