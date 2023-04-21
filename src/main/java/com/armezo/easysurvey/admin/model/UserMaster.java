package com.armezo.easysurvey.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_user_master")
public class UserMaster {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="userid")
	private String userid;
	
	@Column(name="password")
	private String password;
	
	@Column(name="roleid")
	private String roleid;
	
	@Column(name="status")
	private String status;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="client_name")
	private String clientName;
	
	@Column(name="cmp_short_name")
	private String cmpShortName;
	
	@Column(name="photo_flag")
	private String photoFlag;
	
	@Column(name="photo_key")
	private String photoKey;
	
	@Column(name="system_type")
	private String systemType;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCmpShortName() {
		return cmpShortName;
	}

	public void setCmpShortName(String cmpShortName) {
		this.cmpShortName = cmpShortName;
	}

	public String getPhotoFlag() {
		return photoFlag;
	}

	public void setPhotoFlag(String photoFlag) {
		this.photoFlag = photoFlag;
	}

	public String getPhotoKey() {
		return photoKey;
	}

	public void setPhotoKey(String photoKey) {
		this.photoKey = photoKey;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		return "UserMaster [id=" + id + ", userid=" + userid + ", password=" + password + ", roleid=" + roleid
				+ ", status=" + status + ", emailId=" + emailId + ", contactNo=" + contactNo + ", clientName="
				+ clientName + ", cmpShortName=" + cmpShortName + ", photoFlag=" + photoFlag + ", photoKey=" + photoKey
				+ ", systemType=" + systemType + "]";
	}
	
	
}
