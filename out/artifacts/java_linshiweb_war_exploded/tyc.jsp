

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>天眼查抓取</title>

  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
  <meta http-equiv="description" content="This is my page">
  <!--
  <link rel="stylesheet" type="text/css" href="styles.css">
  -->

</head>

<body>
<!-- enctype 默认是 application/x-www-form-urlencoded -->
<p>天眼查上传</p>
<h1><font color="#FF0000">备注:   公司名文件必须为excel文件,第一行必须为空,其它位置只允许填入公司名称</br>参数文件为：代理账户\t代理密码\t搜索线程数\t详情抓取线程数   且每一行为一个进程 </font></h1>
<form action="/java_web/servlet/TycServlet" enctype="multipart/form-data" method="post" >

  上传公司名文件： <input type="file" name="公司名"><br/>

  上传参数文件： <input type="file" name="参数"><br/><br/><br/>

  请选择需要抓取数据？<br /><br />
  <label><input name="Fruit" type="checkbox" value="基本信息" />基本信息 </label>
  <label><input name="Fruit" type="checkbox" value="主要成员" />主要成员 </label>
  <label><input name="Fruit" type="checkbox" value="股东信息" />股东信息 </label>
  <label><input name="Fruit" type="checkbox" value="对外投资" />对外投资 </label>
  <label><input name="Fruit" type="checkbox" value="变更记录" />变更记录 </label>
  <label><input name="Fruit" type="checkbox" value="分支机构" />分支机构 </label>
  <label><input name="Fruit" type="checkbox" value="融资历史" />融资历史 </label>
  <label><input name="Fruit" type="checkbox" value="核心团队" />核心团队 </label>
  <label><input name="Fruit" type="checkbox" value="企业业务" />企业业务 </label>
  <label><input name="Fruit" type="checkbox" value="投资事件" />投资事件 </label>
  <label><input name="Fruit" type="checkbox" value="竞品信息" />竞品信息 </label>
  <label><input name="Fruit" type="checkbox" value="法律诉讼" />法律诉讼 </label>
  <label><input name="Fruit" type="checkbox" value="法院公告" />法院公告 </label>
  <label><input name="Fruit" type="checkbox" value="被执行人" />被执行人 </label>
  <label><input name="Fruit" type="checkbox" value="失信人" />失信人 </label>
  <label><input name="Fruit" type="checkbox" value="经营异常" />经营异常 </label>
  <label><input name="Fruit" type="checkbox" value="行政处罚" />行政处罚 </label>
  <label><input name="Fruit" type="checkbox" value="严重违法" />严重违法 </label>
  <label><input name="Fruit" type="checkbox" value="股权出质" />股权出质 </label>
  <label><input name="Fruit" type="checkbox" value="动产抵押" />动产抵押 </label>
  <label><input name="Fruit" type="checkbox" value="欠税公告" />欠税公告 </label>
  <label><input name="Fruit" type="checkbox" value="招投标" />招投标 </label>
  <label><input name="Fruit" type="checkbox" value="债券信息" />债券信息 </label>
  <label><input name="Fruit" type="checkbox" value="购地信息" />购地信息 </label>
  <label><input name="Fruit" type="checkbox" value="招聘" />招聘 </label>
  <label><input name="Fruit" type="checkbox" value="税务评级" />税务评级 </label>
  <label><input name="Fruit" type="checkbox" value="抽查检查" />抽查检查 </label>
  <label><input name="Fruit" type="checkbox" value="产品信息" />产品信息 </label>
  <label><input name="Fruit" type="checkbox" value="资质证书" />资质证书 </label>
  <label><input name="Fruit" type="checkbox" value="商标信息" />商标信息 </label>
  <label><input name="Fruit" type="checkbox" value="专利" />专利 </label>
  <label><input name="Fruit" type="checkbox" value="著作权" />著作权 </label>
  <label><input name="Fruit" type="checkbox" value="网站备案" />网站备案 </label>
  <label><input name="Fruit" type="checkbox" value="微信公众号" />微信公众号 </label><br/><br/>
  <input type="button" id="btn" value="全选" />
  <input type="button" id="btn1" value="取消全选" />
  <script>
    window.onload = function() {
      document.getElementById('btn').onclick = function() {
        var obj = document.getElementsByTagName('input'); //获取文档中所有的input元素
        for (var i = 0; i < obj.length; i ++) {
          if (obj[i].type == 'checkbox') {
            obj[i].checked = true; //设置复选框元素对象的checked属性值为true就能勾选该复选框；false即为取消选择
          }
        }
      }
      document.getElementById('btn1').onclick = function() {
        var obj = document.getElementsByTagName('input'); //获取文档中所有的input元素
        for (var i = 0; i < obj.length; i ++) {
          if (obj[i].type == 'checkbox') {
            obj[i].checked = false; //设置复选框元素对象的checked属性值为true就能勾选该复选框；false即为取消选择
          }
        }
      }
    }

  </script>

  <input type="submit" value="提交"/>
</form>



</body>
</html>
