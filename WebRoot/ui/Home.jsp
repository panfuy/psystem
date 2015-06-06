<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Personnel System</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/home.css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/liger_ui/css/ligerui-all.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.js">
		</script>
		<script type="text/javascript" src="<%=basePath%>js/ligerui.all.js">
		</script>
		<script type='text/javascript' src='<%=basePath%>dwr/engine.js'>
		</script>
		<script type='text/javascript'
			src='<%=basePath%>dwr/interface/__home.js'>
		</script>

		<script type="text/javascript">
var home = {
	accordion : null,
	tab : null,
	nowdate : new Date(),
	LoadTime : function() {//时间加载
		// 清除定时器
		window.clearInterval("inttime");
		var cent_date = new Date();
		//Ajax获取服务器时间
		var now_date = "";
		//var now_date = "2000-12-20 23:59:50";
		try {
			dwr.engine.setAsync(false);
 			__home.getServerTime(function(o){now_date = o});
 			dwr.engine.setAsync(true);
			//如果获取成功
			if (now_date.length > 0) {
				now_date = now_date.split(" ");
				//截取年、月、日
				var date = now_date[0].split("-");
				//截取时、分、秒
				var time = now_date[1].split(":");
				//设置当前的时间 年、月、日
				home.nowdate.setFullYear(date[0]);
				home.nowdate.setMonth(date[1]);
				//月份需要减一天 才等于当前月
				home.nowdate.setMonth(home.nowdate.getMonth() - 1)
				home.nowdate.setDate(date[2]);
				//设置当前时间 时、分、秒
				home.nowdate.setHours(time[0]);
				home.nowdate.setMinutes(time[1]);

				//计算ajax获取的秒数是否相同
				var cen_sec2 = new Date().getSeconds();
				var cen_sec1 = cent_date.getSeconds();

				if (cen_sec2 > cen_sec1) {
					//比较获取服务器时长，时长+当前时间 =服务器时间
					var ser_sec = parseInt(time[2]);
					home.nowdate.setSeconds(ser_sec + (cen_sec2 - cen_sec1));
				} else {
					home.nowdate.setSeconds(time[2]);
				}
			}
		} catch (e) {
		}
		//调用时间显示
		home.settime(true);
		//延迟1秒加载时间
		var inttime = window.setInterval("home.settime()", 1000);
	},
	getFormatDay : function(day) {
		var ch_Day = "";
		switch (day) {
		case 0:
			ch_Day = "星期日";
			break;
		case 1:
			ch_Day = "星期一";
			break;
		case 2:
			ch_Day = "星期二";
			break;
		case 3:
			ch_Day = "星期三";
			break;
		case 4:
			ch_Day = "星期四";
			break;
		case 5:
			ch_Day = "星期五";
			break;
		case 6:
			ch_Day = "星期六";
			break;
		}
		return ch_Day;
	},
	settime : function(o) {//设置时间显示
		if (o != true) {
			home.nowdate.setSeconds(home.nowdate.getSeconds() + 1);
		}
		$("#toptime").html(
				home.nowdate.toLocaleString() + " "
						+ home.getFormatDay(home.nowdate.getDay()));
	},
	f_heightChanged : function(options) {
		if (home.tab)
			home.tab.addHeight(options.diff);
		if (home.accordion && options.middleHeight - 24 > 0)
			home.accordion.setHeight(options.middleHeight - 24);
	},
	f_addTab : function(tabid, text, url) {
		home.tab.addTabItem( {
			tabid : tabid,
			text : text,
			url : url
		});
	}
};
//布局
$(function() {
	//框架布局
	$("#layout_main").ligerLayout( {
		leftWidth : 150,
		height : '100%',
		heightDiff : -34,
		space : 3,
		onHeightChanged : home.f_heightChanged
	});
	//获取中间布局的高度
	var height = $(".l-layout-center").height();
	//左面板设置
	$("#accordion1").ligerAccordion( {
		height : height - 24,
		speed : null
	});
	//中间面板中的 Tab 设置
	$("#framecenter").ligerTab( {
		height : height
	});
	//设置初始值
	home.tab = $("#framecenter").ligerGetTabManager();
	home.accordion = $("#accordion1").ligerGetAccordionManager();
});

//事件  
$(document).ready(function() {
	//初始化时间
		home.LoadTime();

		

	});
</script>
	</head>
	<body>
		<div id="topmenu" class="l-topmenu">
			<div class="l-topmenu-logo">
				Personnel System (HR Manage)
			</div>
			<div class="l-topmenu-welcome">
				<span id="toptime" class="space"></span>&nbsp;
				<span class="space">|</span>&nbsp;
				<span class="space">欢迎 <strong>${session.account.accountId}</strong>&nbsp;[
				</span>
				<a href="<%=basePath%>account_exit.action" class="l-link2">退出</a>
				<span class="space">]</span>
			</div>
		</div>

		<div id="layout_main">
			<div id="accordion1" position="left" title="主菜单">
				<div title="客户化">
					<a class="l-link" href="javascript:home.f_addTab('account_setting','帐号设置','account_setting.action')">帐号设置</a>
					<a class="l-link" href="javascript:home.f_addTab('project_name','项目工程','custom_project.action')">项目工程</a>
					<a class="l-link" href="javascript:home.f_addTab('evaluation_setting','考勤设置','custom_evaluation.action')">考勤设置</a>
					<a class="l-link" href="javascript:home.f_addTab('system_setting','系统设置','custom_system.action')">系统设置</a>
				</div>
				<div title="人力资源">
				<a class="l-link" href="javascript:home.f_addTab('employee_info','员工信息','hr_employee.action')">员工信息</a>
				<a class="l-link" href="javascript:home.f_addTab('work_evaluation','工作考勤','hr_work.action')">工作考勤</a>
				</div>
				<div title="财务会计">
				<a class="l-link" href="javascript:home.f_addTab('advance_money','预支/借款','finance_advance.action')">预支/借款</a>
				<a class="l-link" href="javascript:home.f_addTab('salay_settlement','薪资结算','finance_salay.action')">薪资结算</a>
				</div>
			</div>
			<div id="framecenter" position="center">
				<div tabid="home" title="首页">
				<div style="width: 100%; height: 100%; text-align: center; margin-top: 100px">
					<img 
					src="<%=basePath%>images/loading.gif" />
					</div>
				</div>
			</div>
		</div>
		<div class="div_bottom">
			Copyright © 2012-2013 PersonnelSystem
		</div>

	</body>
</html>
