package com.java.blog.bean;

import java.sql.Date;

public class Users {
  private Long id;

  public Users(String name, String password) {
    this.name = name;
    this.password = password;
  }

  private String img;
  private Date register_date;
  private Date last_login;
  private String name;
  private Long sex;
  private Long phone;
  private String email;
  private String password;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public Date getRegister_date() {
    return register_date;
  }

  public void setRegister_date(Date register_date) {
    this.register_date = register_date;
  }

  public Date getLast_login() {
    return last_login;
  }

  public void setLast_login(Date last_login) {
    this.last_login = last_login;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getSex() {
    return sex;
  }

  public void setSex(Long sex) {
    this.sex = sex;
  }

  public Long getPhone() {
    return phone;
  }

  public void setPhone(Long phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
