package com.wjs.treewiki.vo.item;

public class TreeItemVo {
	
		
	private Long id;
	private Long pId;
	// 节点名
	private String name;
	// icon样式
	private String iconSkin;
	
	// 排序
	private Integer sort;
	
	// 修改时间
	private Long updateTime;
	
	// 是否展开
	private Boolean open;
	
	/*以下开始权限相关*/
	
	private Boolean checked;
	// 右键是否禁止
	private Boolean noR;
	
	// 是否可以拖拽
	private Boolean drag;
	
	// 是否可以拖拽到我身上
	private Boolean drop;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconSkin() {
		return iconSkin;
	}
	public void setIconSkin(String iconSkin) {
		this.iconSkin = iconSkin;
	}
	public Boolean getOpen() {
		return open;
	}
	public void setOpen(Boolean open) {
		this.open = open;
	}
	

	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getNoR() {
		return noR;
	}
	public void setNoR(Boolean noR) {
		this.noR = noR;
	}
	public Boolean getDrag() {
		return drag;
	}
	public void setDrag(Boolean drag) {
		this.drag = drag;
	}
	public Boolean getDrop() {
		return drop;
	}
	public void setDrop(Boolean drop) {
		this.drop = drop;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	
}
