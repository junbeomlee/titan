package com.se.jyh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DependencyData {

	private String name;
	private int number;
	private int[] data;
	
	public DependencyData(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	public void printData() {
		// TODO Auto-generated method stub
		for(int i=0;i<data.length;i++){
			System.out.print(data[i]+" ");
		}
	}

	
}
