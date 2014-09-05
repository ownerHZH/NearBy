package com.owner.entity;

import java.io.Serializable;

public class DetailInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3331226431872606131L;
	long distance;	//int32	距离中心点的距离
	String type;	//string	所属分类，如’hotel’、’cater’。
	String tag;	//string	标签
	String detail_url;	//string	poi的详情页
	String price;	//string	poi商户的价格
	String shop_hours;	//string	营业时间
	String overall_rating;	//string	总体评分
	String taste_rating;	//string	口味评分
	String service_rating;	//string	服务评分
	String environment_rating;	//string	环境评分
	String facility_rating;	//string	星级（设备）评分
	String hygiene_rating;	//string	卫生评分
	String technology_rating;	//string	技术评分
	String image_num;	//string	图片数
	int groupon_num;	//int	团购数
	int discount_num;	//int	优惠数
	String comment_num;	//string	评论数
	String favorite_num;	//string	收藏数
	String checkin_num;	//string	签到数
	public long getDistance() {
		return distance;
	}
	public void setDistance(long distance) {
		this.distance = distance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDetail_url() {
		return detail_url;
	}
	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getShop_hours() {
		return shop_hours;
	}
	public void setShop_hours(String shop_hours) {
		this.shop_hours = shop_hours;
	}
	public String getOverall_rating() {
		return overall_rating;
	}
	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}
	public String getTaste_rating() {
		return taste_rating;
	}
	public void setTaste_rating(String taste_rating) {
		this.taste_rating = taste_rating;
	}
	public String getService_rating() {
		return service_rating;
	}
	public void setService_rating(String service_rating) {
		this.service_rating = service_rating;
	}
	public String getEnvironment_rating() {
		return environment_rating;
	}
	public void setEnvironment_rating(String environment_rating) {
		this.environment_rating = environment_rating;
	}
	public String getFacility_rating() {
		return facility_rating;
	}
	public void setFacility_rating(String facility_rating) {
		this.facility_rating = facility_rating;
	}
	public String getHygiene_rating() {
		return hygiene_rating;
	}
	public void setHygiene_rating(String hygiene_rating) {
		this.hygiene_rating = hygiene_rating;
	}
	public String getTechnology_rating() {
		return technology_rating;
	}
	public void setTechnology_rating(String technology_rating) {
		this.technology_rating = technology_rating;
	}
	public String getImage_num() {
		return image_num;
	}
	public void setImage_num(String image_num) {
		this.image_num = image_num;
	}
	public int getGroupon_num() {
		return groupon_num;
	}
	@Override
	public String toString() {
		return "DetailInfo [distance=" + distance + ", type=" + type + ", tag="
				+ tag + ", detail_url=" + detail_url + ", price=" + price
				+ ", shop_hours=" + shop_hours + ", overall_rating="
				+ overall_rating + ", taste_rating=" + taste_rating
				+ ", service_rating=" + service_rating
				+ ", environment_rating=" + environment_rating
				+ ", facility_rating=" + facility_rating + ", hygiene_rating="
				+ hygiene_rating + ", technology_rating=" + technology_rating
				+ ", image_num=" + image_num + ", groupon_num=" + groupon_num
				+ ", discount_num=" + discount_num + ", comment_num="
				+ comment_num + ", favorite_num=" + favorite_num
				+ ", checkin_num=" + checkin_num + "]";
	}
	public void setGroupon_num(int groupon_num) {
		this.groupon_num = groupon_num;
	}
	public int getDiscount_num() {
		return discount_num;
	}
	public void setDiscount_num(int discount_num) {
		this.discount_num = discount_num;
	}
	public String getComment_num() {
		return comment_num;
	}
	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}
	public String getFavorite_num() {
		return favorite_num;
	}
	public void setFavorite_num(String favorite_num) {
		this.favorite_num = favorite_num;
	}
	public String getCheckin_num() {
		return checkin_num;
	}
	public void setCheckin_num(String checkin_num) {
		this.checkin_num = checkin_num;
	}
}
