package com.obp.system.common.entity;

import com.obp.system.model.metatype.BaseVO;

/**
 * 
 * @Title:MenuVO.java
 * @Package:com.obp.system.common.entity
 * @Description:菜单POJO
 * @Copyright: Copyright(c)1995-2013
 * @Company:上海华腾软件系统有限公司
 *
 * @author: wangzhao
 * @date: 2014年4月25日下午6:46:07
 * @mail: wangzhao@huateng.com
 * @vision: V1.0
 */
public class MenuVO extends BaseVO{

	private static final long serialVersionUID = 1L;
	private String menuId;
	private String menuName;
	private String iconCls;
	private String parentId;
	private String requestUrl;
	private String leaf;
	private String isNotLast;
	private String isRoot;
	private String expanded;
	private String menuPath;
	private String icon;
	private String checked;
	private String shortCut;
	private Integer width;
	private Integer height;
	private String scrollBar;
	private String maxed;
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getIsNotLast() {
		return isNotLast;
	}
	public void setIsNotLast(String isNotLast) {
		this.isNotLast = isNotLast;
	}
	public String getIsRoot() {
		return isRoot;
	}
	public void setIsRoot(String isRoot) {
		this.isRoot = isRoot;
	}
	public String getExpanded() {
		return expanded;
	}
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getShortCut() {
		return shortCut;
	}
	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public String getScrollBar() {
		return scrollBar;
	}
	public void setScrollBar(String scrollBar) {
		this.scrollBar = scrollBar;
	}
	public String getMaxed() {
		return maxed;
	}
	public void setMaxed(String maxed) {
		this.maxed = maxed;
	}
	
}
