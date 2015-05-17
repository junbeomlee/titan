package com.se.jyh.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author lgpc
 * 
 * TableModel
 * 
 * Models for right panel
 */

public class MyTableModel extends AbstractTableModel{

	//private int[] columns={1,2,3};
	//private String[] rows = {"asd", "Zxc", "qwe"};
	private Object[][] data;
	
	public MyTableModel(int size){
		data=new Object[size][size];
		data[0][0]='1';
		System.out.println(data.length);
	}
	public void setSize(int size){
		
	}
	
	public void addColumn(Object item){
		
	}
	public void removeColunm(){
		
	}
	public void addRow(){
		
	}
	public void deleteRow(){
		
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return this.data[arg0][arg1];
	}

	
}
