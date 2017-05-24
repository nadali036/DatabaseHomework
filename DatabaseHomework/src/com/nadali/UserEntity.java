package com.nadali;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UserEntity {
	
	private SimpleIntegerProperty id = null;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty singIn;
	private SimpleStringProperty indexNum;
	private SimpleStringProperty city;
	
	public UserEntity(Integer id, String firstName, String lastName, String singIn, String br_indexa, String city) {
		this.id=new SimpleIntegerProperty(id);
		this.firstName= new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.singIn = new SimpleStringProperty(singIn);
		this.indexNum = new SimpleStringProperty(br_indexa);
		this.city = new SimpleStringProperty(city);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public String getSingIn() {
		return singIn.get();
	}
	public void setSingIn(String singIn) {
		this.singIn.set(singIn);
	}
	public String getIndexNum() {
		return indexNum.get();
	}
	public void setIndexNum(String indexNum) {
		this.indexNum.set(indexNum);
	}
	public String getCity() {
		return city.get();
	}
	public void setCity(String city) {
		this.city.set(city);
	}

	public SimpleIntegerProperty getId() {
		return id;
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}
}
