package org.itstack.interview;
import java.util.Date;
//user ifor class
public class UserInfo {
	int uid;
	String name;
	Date inCallTime;
	Date outCallTime;
	int stat;
	
	public Date getInCallTime() {
		return inCallTime;
	}
	public void setInCallTime(Date inCallTime) {
		this.inCallTime = inCallTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getOutCallTime() {
		return outCallTime;
	}
	public void setOutCallTime(Date outCallTime) {
		this.outCallTime = outCallTime;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
}
