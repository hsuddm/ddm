package shop;

import java.io.Serializable;

public class Shop implements Serializable {
	private String shop_name;
	private String name;
	private String crnumber;
	private String phone;
	private String email;
	private String password;
	private String complex;
	private String floor;
	private String unit;
	private int shop_number;
	private String introduction;

	public Shop() {
		this.shop_name = "";
		this.name = "";
		this.crnumber = "";
		this.phone = "";
		this.email = "";
		this.password = "";
		this.complex = "";
		this.floor = "";
		this.unit = "";
		this.shop_number = -1;
		this.introduction = "";

	}

	public Shop(String shop_name, String name, String crnumber, String phone, String email,
			String password, String complex, String floor, String unit, int shop_number,
			String introduction) {
		super();
		this.shop_name = shop_name;
		this.name = name;
		this.crnumber = crnumber;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.complex = complex;
		this.floor = floor;
		this.unit = unit;
		this.shop_number = shop_number;
		this.introduction = introduction;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCrnumber() {
		return crnumber;
	}

	public void setCrnumber(String crnumber) {
		this.crnumber = crnumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
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

	public String getComplex() {
		return complex;
	}

	public void setComplex(String complex) {
		this.complex = complex;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getShop_number() {
		return shop_number;
	}

	public void setShop_number(int shop_number) {
		this.shop_number = shop_number;
	}

	@Override
	public String toString() {
		return "<p>" + shop_name + "," + name + "," + crnumber + "," + phone + "," + email + ","
				+ password + "," + complex + "," + floor + "," + unit + "," + shop_number + ","
				+ introduction + "</p>";
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

}
