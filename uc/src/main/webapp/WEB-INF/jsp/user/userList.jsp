<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- <link rel="stylesheet" href="static/layui/layui.css"> -->
<script type="text/javascript" src="static/layui/layui.js"></script>
<%--<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>--%>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="static/js/cookie.js"></script>

</head>
<body>
	<form class=" layui-form" action="stores/storeList.do" method="post"
		id="stlForm" name="stlForm">
		<button type="button" onclick="test()">退出</button>
		<hr style="height: 6px; background: #f5f4f3">
		<div class="layui-form-item mylayui-form-item">
			<div class=" layui-form-item shopsOperate">
				<a class="layui-btn layui-btn-primary" onclick="addVip();">新增会员</a>
				<a class="layui-btn layui-btn-primary" onclick="editVip();">编辑会员</a>
				<a class="layui-btn layui-btn-primary" onclick="delVip();">删除会员</a>
				<!--<a class="layui-btn layui-btn-primary" onclick="editProduct(this);">编辑商品</a>-->
			</div>
		</div>
		<!--  表格 -->
		<div class="layui-field-box layui-form">
			<table class="layui-table" lay-even lay-skin="nob">
				<thead>
					<tr>
						<th style="width: 30px;"><input type="checkbox" id="checkbox"
							lay-filter="allselector" lay-skin="primary"></th>
						<th class="center" style="width: 50px;">序号</th>
						<th class="center">会员信息编号</th>
						<th class="center">用户昵称</th>
						<th class="center">会员名称</th>
					</tr>
				</thead>
				<tbody id="content">
					<!--  开始循环 -->
					<c:choose>
						<c:when test="${not empty users}">
							<c:forEach items="${users}" var="user" varStatus="vs">
								<tr>
									<td class='center' style="width: 30px;">
										<input type='checkbox' name='ids' value="${user.userId}" id="ids"
										class="" lay-skin="primary" />
										<span class="lbl"></span>
									</td>
									<td class='center' style="width: 50px;">${vs.index+1}</td>
									<td class='center'>${user.userId}</td>
									<td class='center'>${user.userAccount}</td>
									<td class='center'>${user.addTime}</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="11" class="center">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!--分页-->
		<div id="pagenation"></div>
	</form>
</body>
<script type="text/javascript">

	function test(){
	    var userList = [];
		for(var i = 0;i<5;i++){
		    var user = {};
		    user["userId"] = i;
		    user["userAccount"] = i+"account";
		    user["userPass"] = "132456";
		    user["addTime"] = new Date();
		    userList.push(user);
		}
		console.log(userList);

        $.ajax({
            //cache : "False",
            type : "post",
            //async: false, 【(A)】
            url : "users/test",
			data:JSON.stringify(userList),
			contentType : 'application/json;charset=utf-8',
            // data : {accToken:getAccToken()},
            //data : {userAccount : $("#userAccount").val(),userPass : $("#userPass").val()},
            success : function(result) {
                console.log(result);
                if (200 == result.code) {
                    setAllCookie(result.data.accToken);
                    console.log(result.data.accToken);
                    window.open('<%=basePath%>sso/toLogin', '_self');
                }
            },
            error : function(xhr, ajaxOptions, thrownError) {
            }
        });
	}

	function logout() {
		console.log(getAccToken());
		$.ajax({
			//cache : "False",
			type : "post",
			//async: false, 【(A)】
			url : "sso/logout",
			data : {accToken:getAccToken()},
			//data : {userAccount : $("#userAccount").val(),userPass : $("#userPass").val()},
			success : function(result) {
				console.log(result);
				if (200 == result.code) {
					setAllCookie(result.data.accToken);
					console.log(result.data.accToken);
					window.open('<%=basePath%>sso/toLogin', '_self');
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
			}
		});
	}
</script>
</html>