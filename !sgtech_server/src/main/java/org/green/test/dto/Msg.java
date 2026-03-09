package org.green.test.dto;

import java.util.Date;

public class Msg {
	private int msg_no;
	private String msg_title;
	private String sender_name; //from_id
	private String receiver_name; //to_id
	private String msg_content;
	private Date msg_datetime;
	private String rnk_name;
	private int renotesender;
	private int from_id; 
    private int to_id;
    private int msg_is_read;
    
    
	public Msg() {}


	public Msg(int msg_no, String msg_title, String sender_name, String receiver_name, String msg_content,
			Date msg_datetime, String rnk_name, int renotesender, int from_id, int to_id, int msg_is_read) {
		this.msg_no = msg_no;
		this.msg_title = msg_title;
		this.sender_name = sender_name;
		this.receiver_name = receiver_name;
		this.msg_content = msg_content;
		this.msg_datetime = msg_datetime;
		this.rnk_name = rnk_name;
		this.renotesender = renotesender;
		this.from_id = from_id;
		this.to_id = to_id;
		this.msg_is_read = msg_is_read;
	}


	public int getMsg_no() {
		return msg_no;
	}


	public void setMsg_no(int msg_no) {
		this.msg_no = msg_no;
	}


	public String getMsg_title() {
		return msg_title;
	}


	public void setMsg_title(String msg_title) {
		this.msg_title = msg_title;
	}


	public String getSender_name() {
		return sender_name;
	}


	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getMsg_content() {
		return msg_content;
	}


	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}


	public Date getMsg_datetime() {
		return msg_datetime;
	}


	public void setMsg_datetime(Date msg_datetime) {
		this.msg_datetime = msg_datetime;
	}


	public String getRnk_name() {
		return rnk_name;
	}


	public void setRnk_name(String rnk_name) {
		this.rnk_name = rnk_name;
	}


	public int getRenotesender() {
		return renotesender;
	}


	public void setRenotesender(int renotesender) {
		this.renotesender = renotesender;
	}


	public int getFrom_id() {
		return from_id;
	}


	public void setFrom_id(int from_id) {
		this.from_id = from_id;
	}


	public int getTo_id() {
		return to_id;
	}


	public void setTo_id(int to_id) {
		this.to_id = to_id;
	}


	public int getMsg_is_read() {
		return msg_is_read;
	}


	public void setMsg_is_read(int msg_is_read) {
		this.msg_is_read = msg_is_read;
	}
	
	
	
	
}