package com.themusic.backend.dto;

import java.io.Serializable;

import com.themusic.backend.domain.Instrument;

public class InstrumentDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String brand;
	private String category;
	private Integer year;
	private String description;
	private Boolean isUsed;
	private String color;
	private String from;
	private Boolean delivery;
	private String price;
	private String plots;
	private String picture;
	
	public InstrumentDTO() {
		
	}
	
	public InstrumentDTO(Instrument obj) {
		id = obj.getId();
		name = obj.getName();
		brand = obj.getBrand();
		category = obj.getCategory();
		year = obj.getYear();
		description = obj.getDescription();
		isUsed = obj.getIsUsed();
		color = obj.getColor();
		from = obj.getFrom();
		delivery = obj.getDelivery();
		price = obj.getPrice();
		plots = obj.getPlots();
		picture = obj.getPicture();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Boolean getDelivery() {
		return delivery;
	}

	public void setDelivery(Boolean delivery) {
		this.delivery = delivery;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPlots() {
		return plots;
	}

	public void setPlots(String plots) {
		this.plots = plots;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
