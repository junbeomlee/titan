package com.se.jyh.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DependencyData {

	private String name;
	private int number;
	private List<Integer> data;
	
	public DependencyData(){
		data= new ArrayList<Integer>();
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

	public List<Integer> getData() {
		return data;
	}

	public void setData(int[] intTemp) {
		for(int i=0;i<intTemp.length;i++){
			this.data.add(intTemp[i]);
		}
	}
	
	public void printData() {
		// TODO Auto-generated method stub
		for(int i=0;i<data.size();i++){
			System.out.print(data.get(i)+" ");
		}
	}

	
}
