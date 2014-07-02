/**
 * 登陆页面
 * 
 * @author wangzhao
 * @since 2014-05-01
 */
Ext.onReady(function() {
	var panel = Ext.create('Ext.panel.Panel',{
		autoTabs: true,
		deferredRender: false,
		border: false,
		dockedItems:{
			src: '../resource/image/login_banner.png',
			xtype: 'image',
			dock: 'top',
			id: 'loginImage',
			height: 60
		},
		items: {
			xtype: 'tabpanel',
			id: 'loginTabs',
			activeTab: 0,
			height: 230,
			border: false,
			items: [{
				title: "身份认证",
				xtype: 'form',
				id: 'loginForm',
				defaults: {
					width: 260
				},
				bodyStyle: 'padding:20 0 0 50',
				defaultType: 'textfield',
				labelWidth: 40,
				labelSeparator: '：',
				items: [{
							fieldLabel: '帐&nbsp;号',
							name: 'userNo',
							id: 'userNo',
							cls: 'user',
							blankText: '帐号不能为空,请输入!',
							maxLength: 30,
							maxLengthText: '账号的最大长度为30个字符',
							allowBlank: false,
							listeners: {
								specialkey: function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										Ext.getCmp('password').focus();
									}
								}
							}
						}, {
							fieldLabel: '密&nbsp;码',
							name: 'password',
							id: 'password',
							cls: 'key',
							inputType: 'password',
							blankText: '密码不能为空,请输入!',
							maxLength: 20,
							maxLengthText: '密码的最大长度为20个字符',
							allowBlank: false,
							listeners: {
								specialkey: function(field, e) {
									if (e.getKey() == Ext.EventObject.ENTER) {
										login();
									}
								}
							}
						}]
			}, {
				title: '信息公告',
				contentEl: 'infoDiv',
				defaults: {
					width: 230
				}
			}, {
				title: '关于',
				contentEl: 'aboutDiv',
				defaults: {
					width: 230
				}
			}]
		}
	});

	// 清除按钮上下文菜单
	var mainMenu = Ext.create('Ext.menu.Menu',{
				id: 'mainMenu',
				items: [{
					text: '清除记忆',
					iconCls: 'status_awayIcon',
					handler: function() {
						Ext.util.Cookies.clear('login.userNo');
						var userNo = Ext.getCmp('userNo');
						Ext.getCmp('loginForm').form.reset();
						userNo.setValue('');
						userNo.focus();
					}
				}, {
					text: '切换到全屏模式',
					iconCls: 'imageIcon',
					handler: function() {
						window.location.href = './fullScreen.htm';
					}
				}]
			});

	var win = Ext.create('Ext.window.Window',{
		title: '系统登录',
		renderTo: Ext.getBody(),
		layout: 'fit',
		width: 460,
		height: 300,
		closeAction: 'hide',
		plain: true,
		modal: true,
		collapsible: true,
		titleCollapse: true,
		maximizable: false,
		draggable: false,
		closable: false,
		resizable: false,
		animateTarget: document.body,
		items: panel,
		buttons: [{
			text: '&nbsp;登录',
			iconCls: 'acceptIcon',
			handler: function() {
				if (Ext.isIE) {
					/*
					if (!Ext.isIE8) {
						Ext.MessageBox
								.alert(
										'温馨提示',
										'系统检测到您正在使用基于MSIE内核的浏览器<br>我们强烈建议立即切换到<b><a href="http://firefox.com.cn/" target="_blank">FireFox</a></b>或者<b><a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">GoogleChrome</a></b>浏览器体验飞一般的感觉!'
												+ '<br>如果您还是坚持使用IE,那么请使用基于IE8内核的浏览器登录!')
						return;
					}
					*/
					login();
				} else {
					login();
				}
			}
		}, {
			text: '&nbsp;选项',
			iconCls: 'tbar_synchronizeIcon',
			menu: mainMenu
		}]
	});

	win.show();

	win.on('show', function() {
		setTimeout(function() {
					var userNo = Ext.getCmp('userNo');
					var password = Ext.getCmp('password');
					var c_userNo = Ext.util.Cookies.get('login.userNo');
					userNo.setValue(c_userNo);
					if (Ext.isEmpty(userNo.getValue())) {
						userNo.focus();
					} else {
						password.focus();
					}
				}, 200);
	}, this);

	/*Ext.get('id_reg_div').on('click', function() {
				// addUserFormPanel.getForm().reset();
				// addUserWindow.show();
				// panel.setActiveTab(1);
				Ext.getCmp('loginTabs').setActiveTab(1);
			});*/

	var addUserFormPanel = Ext.create('Ext.form.FormPanel',{
		id: 'addUserFormPanel',
		name: 'addUserFormPanel',
		defaultType: 'textfield',
		labelAlign: 'right',
		labelWidth: 65,
		bodyStyle: 'padding:5 5 5 5',
		frame: false,
		items: [{
					fieldLabel: '登录帐户',
					name: 'userNo',
					allowBlank: false,
					emptyText: '请使用Email作为OBP帐户',
					regex: /^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/,
					regexText: '请以电子邮箱地址作为OBP帐户',
					maxLength: 30,
					anchor: '99%'
				}, {
					fieldLabel: '姓名/昵称',
					name: 'username',
					allowBlank: false,
					anchor: '99%'
				}, {
					fieldLabel: '密码',
					name: 'password',
					inputType: 'password',
					allowBlank: false,
					anchor: '99%'
				}, {
					fieldLabel: '确认密码',
					name: 'password1',
					inputType: 'password',
					allowBlank: false,
					anchor: '99%'
				}]
	});

	var addUserWindow = Ext.create('Ext.window.Window',{
				layout: 'fit',
				width: 280,
				height: 185,
				resizable: false,
				draggable: false,
				closeAction: 'hide',
				title: '<span style="font-weight:normal">注册新帐户</span>',
				iconCls: 'group_addIcon',
				modal: true,
				collapsible: false,
				maximizable: false,
				buttonAlign: 'right',
				border: false,
				animCollapse: true,
				animateTarget: Ext.getBody(),
				constrain: true,
				items: [addUserFormPanel],
				buttons: [{
							text: '保存',
							iconCls: 'acceptIcon',
							handler: function() {
								reguserNo();
							}
						}, {
							text: '重置',
							id: 'btnReset',
							iconCls: 'tbar_synchronizeIcon',
							handler: function() {
								clearForm(addUserFormPanel.getForm());
							}
						}]
			});

	/**
	 * 提交登陆请求
	 */
	function login() {
		if (Ext.getCmp('loginForm').form.isValid()) {
			Ext.getCmp('loginForm').form.submit({
				url: 'login.do',
				waitTitle: '提示',
				method: 'POST',
				waitMsg: '正在验证您的身份,请稍候.....',
				success: function(form, action) {
					var userNo = Ext.getCmp('userNo');
					Ext.util.Cookies.set("login.userNo",userNo.getValue());
					Ext.util.Cookies.set("login.userid",action.result.userid);
					Ext.util.Cookies.set("lockflag",'0');
					window.location.href = '/obp/index/init.do';
				},
				failure: function(form, action) {
					var errmsg = action.result.msg;
					var errtype = action.result.errorType;
					Ext.Msg.alert('提示', errmsg, function() {
								var userNo = Ext.getCmp('userNo');
								var password = Ext.getCmp('password');
								if (errtype == '1') {
									Ext.getCmp('loginForm').form.reset();
									userNo.focus();
									userNo.validate();
								} else if (errtype == '2') {
									password.focus();
									password.setValue('');
								} else if (errtype == '3') {
									userNo.focus();
								}
							});
				}
			});
		}
	}

	/**
	 * 注册新帐户
	 */
	function reguserNo() {
		if (!addUserFormPanel.form.isValid()) {
			return;
		}
		var values = addUserFormPanel.getForm().getValues();
		if (values.password1 != values.password) {
			Ext.Msg.alert('提示', '两次输入的密码不匹配,请重新输入!');
			Ext.getCmp('password').setValue('');
			Ext.getCmp('password1').setValue('');
			return;
		}
		
		addUserFormPanel.form.submit({
					url: 'login.do?reqCode=reguserNo',
					waitTitle: '提示',
					method: 'POST',
					waitMsg: '正在处理数据,请稍候...',
					success: function(form, action) {
						addUserWindow.hide();
						Ext.MessageBox.alert('提示', '帐户注册成功,点击[登录]按钮进入系统!');
						var password = Ext.getCmp('password');
						var userNo = Ext.getCmp('userNo');
						password.setValue(values.password);
						userNo.setValue(values.userNo);
					},
					failure: function(form, action) {
						Ext.MessageBox.alert('提示', action.result.msg);
					}
				});
	}
	
	

	// 演示模式
	// if (runMode == '0') {
	// Ext.getCmp('userNo').setValue('developer');
	// Ext.getCmp('password').setValue('111111');
	//Ext.getCmp('id_reg_panel').show();
		// }

});
