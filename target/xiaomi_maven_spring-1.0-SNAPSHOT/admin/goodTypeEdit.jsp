<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/9 0009
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <style type="text/css">
        .aaa{
            width: 80%;
            margin-left: 350px;
            margin-top: 20px;
        }
    </style>
<body>
<div class="aaa">
    <form id="formid" class="form-horizontal" action="${pageContext.request.contextPath}/admingoodsservlet">
        <input type="hidden" name="method" value="Uppdate">
        <div class="form-group">
            <label  class="col-sm-2 control-label">序号</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" value="${goodstype.id}" readonly="readonly" name="id" placeholder="Id">
            </div>
                <div class="col-sm-2">
                <label class="error" for="id"></label>
            </div>
        </div>
        <div class="form-group">
            <label  class="col-sm-2 control-label">类型</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="name" value="${goodstype.name}" placeholder="Name">
            </div>
            <div class="col-sm-2">
                <label class="error" style="color: red" for="name"></label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">等级</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="level" value="${goodstype.level}" placeholder="Level">
            </div>
            <div class="col-sm-2">
                <label class="error" style="color: red" for="level"></label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">所属类型</label>
            <div class="col-sm-3">
                <input type="text" class="form-control" name="parent" value="${goodstype.parent}" placeholder="Parent">
            </div>
            <div class="col-sm-2">
                <label class="error" style="color: red" for="parent"></label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-primary">修改</button>
                <button type="reset" class="btn btn-default">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript">

</script>
</html>
