package com.se.jyh.model;

import java.util.ArrayList;
import java.util.List;

public class DsmModel {

	private int size;
	//private DependencyData[] dependencyData_arr;
	private List<DependencyData> dependency_arr;
	//private Object[][] dsmMap;
	
	public DsmModel(){
		dependency_arr = new ArrayList<DependencyData>();
	}
	public void set(String bufferline, int row){
		String[] stringTemp = bufferline.split(" ");
		int[] intTemp = new int[stringTemp.length];
		
		for(int i=0;i<stringTemp.length;i++){
			intTemp[i]= Integer.parseInt(stringTemp[i]);
		}
		DependencyData temp = new DependencyData();
		temp.setData(intTemp);
		this.dependency_arr.add(temp);
		
		
	}
	
	public void setSize(int size){
		
	}	
	public void setName(String readLine, int number) {
		
		this.dependency_arr.get(number).setNumber(number);
		this.dependency_arr.get(number).setName(readLine);
	}
	public List<DependencyData> getDependencyData_arr() {
		return this.dependency_arr;
	}
	public int getSize() {
		return this.dependency_arr.size();
	}
	public void addDsmRow(String dsmName,int[] fromThis,int number){
		/**
		 * 자기자신과의 관계는 0이니까 fromThis는 한칸 더 길고 그걸 0으로 세팅
		 */
		DependencyData temp=new DependencyData();
		
		temp.setName(dsmName);
		temp.setData(fromThis);
		temp.setNumber(number);
		
		this.dependency_arr.add(temp);
	}
	public void print() {
		for(int i=0;i<this.dependency_arr.size();i++){
			System.out.print(this.dependency_arr.get(i).getName()+" : "+this.dependency_arr.get(i).getNumber());
			for(int j=0;j<this.dependency_arr.size();j++){
				System.out.print(this.dependency_arr.get(i).getData().get(j));
			}
			System.out.println();
		}
	}
	
	public void addDsmCol(int[] toThis) {
		//System.out.println(this.dependency_arr.size());
		toThis[toThis.length-1]=0;
		for(int i=0;i<toThis.length;i++){
			dependency_arr.get(i).getData().add(toThis[i]);
		}
		
	}
	
	public int getNumFromName(String name){
		for(int i=0;i<this.dependency_arr.size();i++){
			if(name.equals(this.dependency_arr.get(i).getName())){
				return i;
			}
		}
		return -1;
	}
	
	public Integer[] getMergedRow(int[] numberList) {
		Integer[] MergedRow=new Integer[this.dependency_arr.size()];
		for(int i=0;i<this.dependency_arr.size();i++){
			int a=0;
			for(int j=0;j<numberList.length;j++){
				if(dependency_arr.get(numberList[j]).getData().get(i)==1){
					a=1;
				}
			}
			MergedRow[i]=a;
		}
		return MergedRow;
	}
	
}
