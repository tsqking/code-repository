/**
 * Project Name:clps_mms_copyright_201610
 * File Name:ActionName.java
 * Package Name:com.clps.mms.log.model
 * Date:2016年10月19日下午1:44:30
 * Copyright (c) 2016, tmbasama@163.com All Rights Reserved.
 *
*/

package com.clps.mms.log.sys.model;
/**
 * ClassName:ActionName 
 * Function: TODO ADD FUNCTION. 
 * Reason:	 TODO ADD REASON. 
 * Date:     2016年10月19日 下午1:44:30 
 * @author   tony.tan  
 * 	 
 */
public enum ActionName {
	UPDATE("更新",1),DELETE("删除",2),SAVE("保存",3);
	private String name;
	private Integer index;
	 private ActionName(String name, int index) {
         this.name = name;
         this.index = index;
     }
	 public static String getName(int index){
		 for (ActionName an : ActionName.values()) {
			if (an.getIndex()==index) {
				return an.name;
			}
		}
		 return null;
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public static void main(String[] args) {
		System.out.println(ActionName.valueOf("更新"));
	}
	 
}

/**
 * public enum Color {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Color(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 普通方法
        public static String getName(int index) {
            for (Color c : Color.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }

        // get set 方法
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
 */
