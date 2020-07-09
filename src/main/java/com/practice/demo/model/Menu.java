package com.practice.demo.model;

import java.util.List;

public class Menu {

	private int	id;
	private String	name;
	private String	path;
	private String	component;
	private String	redirect;
	private String	url;
	private String	meta_title;
	private String	meta_icon;
	private int	meta_nocache;
	private int	alwaysshow;
	private int	meta_affix;
	private int	type;
	private int	hidden;
	private int	pid;
	private int	sort;
	private int	status;
	private int	level;
	private String ctime;
	
	private List<Menu> menuchildren;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMeta_title() {
		return meta_title;
	}
	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}
	public String getMeta_icon() {
		return meta_icon;
	}
	public void setMeta_icon(String meta_icon) {
		this.meta_icon = meta_icon;
	}
	public int getMeta_nocache() {
		return meta_nocache;
	}
	public void setMeta_nocache(int meta_nocache) {
		this.meta_nocache = meta_nocache;
	}
	public int getAlwaysshow() {
		return alwaysshow;
	}
	public void setAlwaysshow(int alwaysshow) {
		this.alwaysshow = alwaysshow;
	}
	public int getMeta_affix() {
		return meta_affix;
	}
	public void setMeta_affix(int meta_affix) {
		this.meta_affix = meta_affix;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public List<Menu> getMenuchildren() {
		return menuchildren;
	}
	public void setMenuchildren(List<Menu> menuchildren) {
		this.menuchildren = menuchildren;
	}
	
	
	
	
}
