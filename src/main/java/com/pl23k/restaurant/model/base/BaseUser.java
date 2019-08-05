package com.pl23k.restaurant.model.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import com.pl23k.restaurant.model.User;

import java.util.Date;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public M setId(Integer id) {
		set("id", id);
		return (M)this;
	}

	public Integer getId() {
		return getInt("id");
	}

	public M setMemberId(String memberId) {
		set("memberId", memberId);
		return (M)this;
	}

	public String getMemberId() {
		return getStr("memberId");
	}

	public M setUsername(String username) {
		set("username", username);
		return (M)this;
	}

	public String getUsername() {
		return getStr("username");
	}

	public M setPassword(String password) {
		set("password", password);
		return (M)this;
	}

	public String getPassword() {
		return getStr("password");
	}

	public M setRealName(String realName) {
		set("realName", realName);
		return (M)this;
	}

	public String getRealName() {
		return getStr("realName");
	}

	public M setSex(Boolean sex) {
		set("sex", sex);
		return (M)this;
	}

	public Boolean getSex() {
		return get("sex");
	}

	public M setBirthday(java.util.Date birthday) {
		set("birthday", birthday);
		return (M)this;
	}

	public java.util.Date getBirthday() {
		return get("birthday");
	}

	public M setAddress(String address) {
		set("address", address);
		return (M)this;
	}

	public String getAddress() {
		return getStr("address");
	}

	public M setPhone(String phone) {
		set("phone", phone);
		return (M)this;
	}

	public String getPhone() {
		return getStr("phone");
	}

	public M setEmail(String email) {
		set("email", email);
		return (M)this;
	}

	public String getEmail() {
		return getStr("email");
	}

	public M setAvatar(String avatar) {
		set("avatar", avatar);
		return (M)this;
	}

	public String getAvatar() {
		return getStr("avatar");
	}

	public M setIsActive(Boolean isActive) {
		set("isActive", isActive);
		return (M)this;
	}

	public Boolean getIsActive() {
		return get("isActive");
	}

	public M setCoin(java.math.BigDecimal coin) {
		set("coin", coin);
		return (M)this;
	}

	public java.math.BigDecimal getCoin() {
		return get("coin");
	}

	public M setMoney(java.math.BigDecimal money) {
		set("money", money);
		return (M)this;
	}

	public java.math.BigDecimal getMoney() {
		return get("money");
	}

	public M setLevel(Integer level) {
		set("level", level);
		return (M)this;
	}

	public Integer getLevel() {
		return getInt("level");
	}

	public M setWechatId(Integer wechatId) {
		set("wechatId", wechatId);
		return (M)this;
	}

	public Integer getWechatId() {
		return getInt("wechatId");
	}

	public M setAddTime(java.util.Date addTime) {
		set("addTime", addTime);
		return (M)this;
	}
	
	public java.util.Date getAddTime() {
		if(get("addTime")==null){
			set("addTime",new Date(0));
			User user = User.getRecordById(getId());
			if(user!=null){
				user.setAddTime(new Date(0));
				user.update();
			}
		}
		return get("addTime");
	}

}
