<%@ include file="/common/include/taglib.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<obp:html title="${sysTitle}" showLoading="false" exportParams="true"
	isSubPage="false" exportExceptionWindow="true" exportUserinfo="true">
	<obp:import src="/common/commonjs/extTabCloseMenu.js" />
	<obp:import src="/system/admin/js/frame.js" />
	<obp:extCodeStore fields="SEX" />
	<obp:body>
		<obp:extViewport northTitle="${sysTitle}" westTitle="${westTitle}" />
	</obp:body>
</obp:html>