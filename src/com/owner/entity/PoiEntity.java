package com.owner.entity;

import java.io.Serializable;

public class PoiEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3514311648902680138L;
	String name;
	Location location;
	String address;
	String street_id;
	String telephone;
	String uid;
	DetailInfo detail_info;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet_id() {
		return street_id;
	}

	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public DetailInfo getDetail_info() {
		return detail_info;
	}

	public void setDetail_info(DetailInfo detail_info) {
		this.detail_info = detail_info;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BDSimplePoi [name=" + name + ", location=" + location
				+ ", address=" + address + ", street_id=" + street_id
				+ ", telephone=" + telephone + ", uid=" + uid
				+ ", detail_info=" + detail_info + "]";
	}
}
