package com.se.jyh.model;

import java.util.ArrayList;
import java.util.List;

public class DsmModel {

	private int size;
	private DependencyData[] dependencyData_arr;
	
	public DsmModel(){
		
	}
	public void setSize(int size){
		
		this.size=size;
		this.dependencyData_arr = new DependencyData[size];
		for(int i=0;i<this.size;i++){
			this.dependencyData_arr[i]= new DependencyData();
		}
		
	}
	
	public void setData(int number, String bufferline) {
		
		String[] stringTemp=bufferline.split(" ");
		int[] intTemp = new int[this.size];
		
		dependencyData_arr[number].setNumber(number);
		for(int i=0;i<this.size;i++){
			
			intTemp[i]= Integer.parseInt(stringTemp[i]);
			
		}
		
		this.dependencyData_arr[number].setData(intTemp);
	}
	
	public void setName(String readLine, int number) {
		this.dependencyData_arr[number].setName(readLine);
	}
	public void print() {
		for(int i=0;i<this.size;i++){
			System.out.print(this.dependencyData_arr[i].getName()+" ");
			this.dependencyData_arr[i].printData();
			System.out.println();
		}
	}
	public DependencyData[] getDependencyData_arr() {
		return dependencyData_arr;
	}
	public void setDependencyData_arr(DependencyData[] dependencyData_arr) {
		this.dependencyData_arr = dependencyData_arr;
	}
	public int getSize() {
		return size;
	}
	
}
