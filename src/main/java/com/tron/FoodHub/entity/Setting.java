package com.tron.FoodHub.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "setting_master")
public class Setting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "setting_id")
	private long settingId;
	
	@Column(name = "business_name")
	private String businessName;
	
	@Column(name = "business_mobile")
	private long businessMobile;
	
	@Column(name = "business_email")
	private String businessEmail;
	
	@Column(name = "business_address")
	private String businessAddress;
	
	@Column(name = "business_gst_number")
	private String businessGstNumber;
	
	@Column(name = "business_logo")
	private String businessLogo;
	
	@Column(name = "cover_image_url")
    private String coverImage;

	public long getSettingId() {
		return settingId;
	}

	public void setSettingId(long settingId) {
		this.settingId = settingId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public long getBusinessMobile() {
		return businessMobile;
	}

	public void setBusinessMobile(long businessMobile) {
		this.businessMobile = businessMobile;
	}

	public String getBusinessEmail() {
		return businessEmail;
	}

	public void setBusinessEmail(String businessEmail) {
		this.businessEmail = businessEmail;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getBusinessGstNumber() {
		return businessGstNumber;
	}

	public void setBusinessGstNumber(String businessGstNumber) {
		this.businessGstNumber = businessGstNumber;
	}

	public String getBusinessLogo() {
		return businessLogo;
	}

	public void setBusinessLogo(String businessLogo) {
		this.businessLogo = businessLogo;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	@Override
	public String toString() {
		return "SettingMaster [settingId=" + settingId + ", businessName=" + businessName + ", businessMobile="
				+ businessMobile + ", businessEmail=" + businessEmail + ", businessAddress=" + businessAddress
				+ ", businessGstNumber=" + businessGstNumber + ", businessLogo=" + businessLogo + "]";
	}
	
	
}
