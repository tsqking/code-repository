package com.clps.tmp.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
  * @ClassName: TreeViewVo
  * @Description: TODO
  * @author Comsys-liuchen
  * @date 2015年12月7日 上午10:22:23
 */
public class TreeViewVo implements Serializable{

	/**
	 * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<TreeViewVo> nodes;
	private String id;
	private String text;
	private String tags;
	private String point;
	private HashMap<String,String> data;
	
	public TreeViewVo(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	
	public TreeViewVo(String id, String text, HashMap<String,String> data,boolean b) {
		super();
		this.id = id;
		this.text = text;
		this.data = data;
		if(b){
			this.point = "point";
		}
	}
	
	public TreeViewVo(String id, String text,boolean b) {
		super();
		this.id = id;
		this.text = text;
		if(b){
			this.point = "point";
		}
	}
	
	
	public ArrayList<TreeViewVo> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<TreeViewVo> nodes) {
		this.nodes = nodes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public HashMap<String,String> getData() {
		return data;
	}
	public void setData(HashMap<String,String> data) {
		this.data = data;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	
}
