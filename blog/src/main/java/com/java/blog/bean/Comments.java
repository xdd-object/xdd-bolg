package com.java.blog.bean;

import java.sql.Date;

public class Comments {
  private String content;
  private Date date_time;
  private Long user_id;
  private Long parent_id;

    public Comments() {}

    public Comments(String content, Date date_time, Long user_id, Long parent_id) {
    this.content = content;
    this.date_time = date_time;
    this.user_id = user_id;
    this.parent_id = parent_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getDate_time() {
    return date_time;
  }

  public void setDate_time(Date date_time) {
    this.date_time = date_time;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getParent_id() {
    return parent_id;
  }

  public void setParent_id(Long parent_id) {
    this.parent_id = parent_id;
  }
}
