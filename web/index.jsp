<%@ page import="java.io.File" %>
<%@ page import="java.io.FileReader" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
  Created by IntelliJ IDEA.
  User: guojie
  Date: 2017/8/1
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>批量反地址解析</title>
    <style type="text/css">
      body, html{
        width: 100%;
        height: 100%;
        margin:0;
        font-family:"微软雅黑";
      }
      #l-map {
        height:100%;
        width:100%;
      }
      #r-result{
        width:100%;
        font-size:14px;
        line-height:20px;
      }
    </style>
    <!-- API from Baidu -->
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=YjHDKaV9qWGdS4GT0pGxceNnMRNuDRXI"></script>
  </head>
    <body>
      <div id="l-map"></div>
      <div id="r-result">
      <input type="button" value="批量反地址解析" onclick="bdGEO(0)" />
      <div id="result"></div>
    </div>
  </body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(116.328749,40.026922), 13);
    map.enableScrollWheelZoom(true);
    var index = 0;
    var myGeo = new BMap.Geocoder();

    <%
      String txt = request.getParameter("txt"); // 设置URL连接传参数 ?txt=1000
    //  String path = "D:\\10000More400\\UsersOrigFiles\\";
      String path = "D:\\10000More400UsersData\\StayPointsOfUserOne\\";
      File file = new File(path,txt+".txt");
      FileReader fr = new FileReader(file);  //字符输入流
      BufferedReader br = new BufferedReader(fr);  //使文件可按行读取并具有缓冲功能
      StringBuffer strB = new StringBuffer();   //strB用来存储jsp.txt文件里的内容
      String str = br.readLine();
      int lines = Integer.valueOf(str.split("\\s+")[1]);
      List<String> lngs = new LinkedList<>();
      List<String> lats = new LinkedList<>();

      while( lines > 0){
          lines--;
          str = br.readLine();
          String[] strs = str.split("\\s+");
          if(strs.length >= 3){
              lngs.add(strs[0]);
              lats.add(strs[1]);
          }
      }
      br.close();    //关闭输入流

  %>
//    var adds = [
//        new BMap.Point(116.307901,40.05901),
//        new BMap.Point(116.2317,39.5427)
//    ];
    console.log(<%=lngs.size()%>);
    var adds  = new Array(<%=lngs.size()%>);

    for(var j = 0;j < <%=lngs.size()%> ; j++){
        adds[j] = new BMap.Point(<%=lngs%>[j], <%=lats%>[j]);
    }

    for(var i = 0; i<adds.length; i++){
        var marker = new BMap.Marker(adds[i]);
        map.addOverlay(marker);
        marker.setLabel(new BMap.Label("位置:"+(i+1),{offset:new BMap.Size(20,-10)}));
    }
    function bdGEO(){
        var pt = adds[index];
        geocodeSearch(pt);
        index++;
    }
    function geocodeSearch(pt){
        if(index < adds.length-1){
            setTimeout(window.bdGEO,400);
        }
        myGeo.getLocation(pt, function(rs){
            var addComp = rs.addressComponents;
            document.getElementById("result").innerHTML += index + ". " +adds[index-1].lng + "," + adds[index-1].lat + "："  + "位置(" + rs.business + ")  结构化数据(" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber + ")<br/><br/>";
        });
    }
</script>