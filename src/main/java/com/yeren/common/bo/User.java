package com.yeren.common.bo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "common_user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9199221859503370530L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column
	private String password;
	@Column
	private String nickname;// 昵称
	@Column(name = "real_name")
	private String realName;
	@Column
	private Integer age;
	@Column
	private String idcard;// 身份证
	@Column
	private String mobile;
	@Column
	private String emial;
	@Column(name = "pic_url")
	private String picUrl;// 头像URL地址
	@Column
	private String province;// 省份
	@Column(name = "reg_time")
	private Date regTime;// 用户注册时间
	@Column(name = "reg_source")
	private String regSource;// 用户注册来源
	@Column(name = "real_valid")
	private Integer realValid;// 是否实名
	@Column(name = "mobile_valid")
	private Integer mobileValid;// 手机号绑定状态
	@Column(name = "email_valid")
	private Integer emailValid;// 邮箱绑定状态

	public User() {

	}

	public User(Integer id, String username, String password,
			String nickname, String realName, Integer age, String idcard,
			String mobile, String emial, String picUrl, String province,
			Date regTime, String regSource, Integer realValid,
			Integer mobileValid, Integer emailValid) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.realName = realName;
		this.age = age;
		this.idcard = idcard;
		this.mobile = mobile;
		this.emial = emial;
		this.picUrl = picUrl;
		this.province = province;
		this.regTime = regTime;
		this.regSource = regSource;
		this.realValid = realValid;
		this.mobileValid = mobileValid;
		this.emailValid = emailValid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer userId) {
		this.id = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmial() {
		return emial;
	}

	public void setEmial(String emial) {
		this.emial = emial;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getRegSource() {
		return regSource;
	}

	public void setRegSource(String regSource) {
		this.regSource = regSource;
	}

	public Integer getRealValid() {
		return realValid;
	}

	public void setRealValid(Integer realValid) {
		this.realValid = realValid;
	}

	public Integer getMobileValid() {
		return mobileValid;
	}

	public void setMobileValid(Integer mobileValid) {
		this.mobileValid = mobileValid;
	}

	public Integer getEmailValid() {
		return emailValid;
	}

	public void setEmailValid(Integer emailValid) {
		this.emailValid = emailValid;
	}

}
