package com.clps.tmp.campusRecruit.univ.coop.vo;

public class CoopAttnVo {
	 private int coop_id;
	 private int att_id;
	public int getCoop_id() {
		return coop_id;
	}
	public void setCoop_id(int coop_id) {
		this.coop_id = coop_id;
	}
	public int getAtt_id() {
		return att_id;
	}
	public void setAtt_id(int att_id) {
		this.att_id = att_id;
	}
	@Override
	public String toString() {
		return "CoopAttnVo [coop_id=" + coop_id + ", att_id=" + att_id + "]";
	}
}
