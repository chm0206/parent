<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uids = "";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="static/css/myicon.css" />
<link rel="stylesheet" type="text/css" href="static/css/main.css" />
<link rel="stylesheet" type="text/css" href="static/css/ed-pc.css"/>
<!-- <script type="text/javascript" src="static/layui/js/cookie.js"></script> -->
<script type="text/javascript" src="static/js/jquery-3.3.1.min.js"></script>
<style>
</style>
</head>
<body class="no-skin">
	<input type="hidden" id="jsid" name="jsid" value="" />
	<!-- 顶部 -->
	<div class="layui-header header header-demo">
		<div class="layui-main">
			<!-- logo区域 -->
			<div>
				<i class="icon-logo"></i> 用户管理中心
			</div>
			<!-- <span class="collection"> <i class="icon-collection"></i> 收藏
			</span> -->
			右侧导航
			<span class="layui-breadcrumb" lay-separator="|">
			<!-- 
			<a href="javascript:;" class="headPortraitBox"> 
				<i class="icon-ONLINE" style="font-size: 21px;"></i> 在线客服 
				<span class="headPortraitArrow"></span>
				<div class="headPortraitContentBox">
					<ul class="headPortraitContent">
						<li>客服一</li>
					</ul>
				</div>
			</a> -->
			<a href="javascript:;" class="headPortraitBox"> 
			<img src="static/img/headPortrait.png" alt="error" class="headPortrait"> 系统管理员
				<span class="headPortraitArrow"></span>
				<div class="headPortraitContentBox">
					<ul class="headPortraitContent">
						<li onclick="editUserInfo()">编辑资料</li>
						<li onclick="editPwd()">修改密码</li>
						<li>系统设置</li>
					</ul>
				</div>
			</a>
			<a href="javascript:exitLogou();"> 
				<i class="icon-quit"></i>退出平台
			</a>
			</span>
		</div>
	</div>

	<!-- 左边导航 -->
	<div class="layui-side layui-side-bg layui-larry-side" id="larry-side">
	<div class="layui-side-scroll" id="larry-nav-side" lay-filter="side">
		<ul class="layui-nav layui-nav-tree" lay-filter="openNav">
			<!-- 要加左边的导航只需加li，有三级下拉的多加一个a标签，需要页面跳转的div里面需要放打开的页面路径 -->
			<li class="layui-nav-item layui-this" data-url="login_default.do"
				data-id="home">
				<dd>
					<a href="javascript:;" style="padding-left: 20px !important;"><span
						class="icon-home myIcon"></span>后台首页</a>
				</dd>
			</li>
			<!-- forEach menu1 start -->
			<c:forEach items="${menuList}" var="menu1">
				<!--1级菜单start-->
				<li class="layui-nav-item">
					<a href="javascript:;" class="PrimaryTitle">${menu1.menuName }</a> 
					<!--下面这行↓是修饰的,如果子菜单不为空则展示，根据具体的样式决定是否屏蔽--> 
					<c:if test="${menu1.child }">
						<b class="arrow fa fa-angle-down"></b>
					</c:if> 
					<b class="arrow"></b> 
					
					<c:if test="${menu1.child}">
						<!--2级菜单start-->
						<c:forEach items="${menu1.childMenu}" var="menu2">
							<dl class="layui-nav-child">
								<!--如果菜单URL等于空或者 # 则菜单url ="" -->
								<c:if test="${!menu2.child}">
									<!--没有子菜单的2级菜单-->
									<dd
										<c:if test="${not empty menu2.menuUrl && '#' != menu2.menuUrl}"> data-url="${menu2.menuUrl}" </c:if>
										<c:if test="${menu2.child}"> class="dropdown-toggle"</c:if>
										data-id="${menu2.menuID }">
										<a href="javascript:;">
											${menu2.menuName }
										</a>
									</dd>
								</c:if>
								<c:if test="${menu2.child}">
									<!--有子菜单的2级菜单-->
									<div class="nav3">
										<a href="javascript:;"> <%-- <span class="${menu2.MENU_ICON }"></span> --%>
											${menu2.menuName } 
											<i class="layui-icon TitleLv3"></i>
										</a>
										<ul id="nav3">
											<c:forEach items="${menu2.childMenu}" var="menu3">
												<li
													<c:if test="${not empty menu3.menuUrl && '#' != menu3.menuUrl}"> data-url="${menu3.menuUrl}?accToken=${cookie['accToken'].value}" </c:if>
													<c:if test="${ menu3.child}"> class="dropdown-toggle"</c:if>
													data-id="${menu3.menuID }">${menu3.menuName }
												</li>
											</c:forEach>
										</ul>
									</div>
								</c:if>
							</dl>
						</c:forEach>
						<!--2级菜单end-->
					</c:if>
				</li>
				<!--1级菜单end-->
			</c:forEach>
			<!-- forEach menu1 end -->
		</ul>
	</div>
</div>
	<!-- 主体内容 -->
	<div class="layui-body" id="layui-body">
		<div class="layui-tab" lay-filter="openTab" lay-allowclose="true">
			<ul class="layui-tab-title">
				<!--
					    <li>用户管理</li>
					    <li>权限分配</li>
					    <li>商品管理</li>
					    <li>订单管理</li> -->
			</ul>
			<div class="layui-tab-content">
				<!--  <div class="layui-tab-item layui-show">内容1</div>
					    <div class="layui-tab-item">内容2</div>
					    <div class="layui-tab-item">内容3</div>
					    <div class="layui-tab-item">内容4</div>
					    <div class="layui-tab-item">内容5</div> -->
			</div>
		</div>
	</div>
	<div id="zoomImg">
		<div id="zoomImgBox">
			<div id="closeBtnOutSideBox">
				<span onclick="closeZoomImg(this)">
					<img src="static/img/close.png" alt="error" class="close">
				</span> 
				<img src="" alt="error" id="trulyZoomImg">
			</div>
		</div>
	</div>

</body>
</html>
<script type="text/javascript" src="static/layui/layui.js"></script>
<script>
	/* var uid = getCookieValue("userID") ; */
	var element;
	var $;
	var winH, winW;
	window.onload = function() {
		if (window.innerheight && window.innerwidth) {
			winH = window.innerheight;
			winW = window.innerwidth;
		} else if ((document.body) && (document.body.clientHeight)
				&& (document.body.clientWidth)) {
			winH = document.body.clientHeight - 80;
			winW = document.body.clientWidth - 80;
		}
		document.getElementById("trulyZoomImg").style.maxHeight = winH + "px";
		document.getElementById("trulyZoomImg").style.maxWidth = winW + "px";
	}
	layui.use([ 'layer', 'form', 'element', 'jquery' ],function() {
		var layer = layui.layer, form = layui.form;
		element = layui.element, $ = layui.jquery;
		// 新增一个Tab项
		function _openNav(getType, mynav) {
			var elem = $(this)
			var aa = "home", bb = "home", urlu = "login_defaultssss.do", 
			FrameName = "frame"+ $(this).attr("data-id"),
			url = (getType == "nav3") ? mynav.attr("data-url"): elem.attr("data-url"), //判断是否自己加的nav
			id = (getType == "nav3") ? mynav.attr("data-id"): elem.attr("data-id"), 
			tabName = (getType == "nav3") ? mynav.text(): elem.children().text(), 
			endH = parseInt($("div.layui-body").css("height")) - 90
			
			console.log("FrameName="+FrameName)
			console.log("url="+url)
			console.log("id="+id)
			console.log("tabName="+tabName)
			console.log("endH="+endH)
			//alert(url);
			if (!$("[lay-id='home']").length) {
				id = "home";
				tabName = "后台首页";
				url = "";
				element.tabAdd('openTab',{
					title : tabName,
					content : '<iframe id="'+ id+ '" name="'+ id
						+ '" src="'+ url+ '" frameborder="0" style="width: 100%;height:'
						+ endH + 'px"'+ '></iframe>',id : id
				});
			} else {
				if (!$("[lay-id=" + id + "]").length) {// 是否已经存在iframe
					element.tabAdd('openTab',{
						title : tabName,
						content : '<iframe id="'+ id + '" name="'+ id
							+ '" src="'+ url+ '" frameborder="0" style="width: 100%;height:'
							+ endH + 'px"' + '></iframe>',id : id
					});
					/* layer.msg($("[lay-id="+id+"]").length) */
				} else {
					window.parent.document.getElementById(id).src = url;
				}
			}
			element.tabChange('openTab', id);
		}
		// 菜单点击打开iframe
		element.on('nav(openNav)', _openNav);
		$("#nav3 li").click(function() {//三级菜单
			$(".layui-this").removeClass("layui-this");
			$(this).addClass("layui-this")
			_openNav("nav3", $(this))
		})
		/*默认打开首页*/
		_openNav()

		/*监听一级菜单打开情况*/
		$(".PrimaryTitle").each(function() {
			$(this).click(function() {
				if ($(this).parent().hasClass("yetOpen") == true) {
					$(".PrimaryTitle").parent().removeClass("layui-nav-itemed")
						.removeClass("yetOpen")
					$(this).parent().removeClass("layui-nav-itemed")
						.removeClass("yetOpen")
				} else {
					$(".PrimaryTitle").parent().removeClass("layui-nav-itemed")
						.removeClass("yetOpen")
					$(this).parent().addClass("layui-nav-itemed")
						.addClass("yetOpen")
				}
			})
		});
		//三级菜单点击展开
		$(".nav3 a").each(function() {
			$(this).click(function(event) {
				if ($(this).next().is(":hidden")) {
					$(this).find("i").addClass("TitleLv3-open");
					$(this).next().show()
				} else {
					$(this).find("i").removeClass("TitleLv3-open");
					$(this).next().hide()
				}
			})
		})
	}); //use结束

	//关闭Tab
	function closeTab(id) {
		element.tabDelete("openTab", id);
	}

	function closeZoomImg(_this) {
		_this.parentNode.parentNode.parentNode.style.display = "none"
	}

	/*其它页面想要打开新选项卡*/
	function otherPageOpenNav(id, url, tabName) {
		var endH = parseInt($("div.layui-body").css("height")) - 90
		if (!$("[lay-id=" + id + "]").length) {// 是否已经存在iframe
			element.tabAdd('openTab', {
				title : tabName,
				content : '<iframe id="' + id + '" name="' + id + '" src="'
						+ url + '" frameborder="0" style="width: 100%;height:'
						+ endH + 'px"' + '></iframe>',id : id
			});
		} else {
			window.parent.document.getElementById(id).contentWindow.location.reload(true)
		}
		element.tabChange('openTab', id);
	}
</script>