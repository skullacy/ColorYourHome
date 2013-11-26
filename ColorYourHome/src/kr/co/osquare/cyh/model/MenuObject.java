package kr.co.osquare.cyh.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MenuObject implements Serializable {

	private int id;
	private int iconRes;
	private String name;
	private boolean arrow;
	private int notifySize;
	private boolean children;
	private List<MenuObject> menuObjects = new ArrayList<MenuObject>();

	public MenuObject(String name, int iconRes, int id) {
		this.id = id;
		this.iconRes = iconRes;
		this.name = name;
	}
	
	public MenuObject(String name, int iconRes, int id, boolean children) {
		this.id = id;
		this.iconRes = iconRes;
		this.name = name;
		this.children = children;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIconRes() {
		return iconRes;
	}

	public void setIconRes(int iconRes) {
		this.iconRes = iconRes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isArrow() {
		return arrow;
	}

	public void setArrow(boolean arrow) {
		this.arrow = arrow;
	}

	public int getNotifySize() {
		return notifySize;
	}

	public void setNotifySize(int notifySize) {
		this.notifySize = notifySize;
	}
	
	public void put(MenuObject object) {
		menuObjects.add(object);
		this.arrow = true;
	}

	public int getChildrenSize() {
		return this.menuObjects.size();
	}

	public MenuObject get(int i) {
		return this.menuObjects.get(i);
	}

	public boolean isChildren() {
		return children;
	}


}
