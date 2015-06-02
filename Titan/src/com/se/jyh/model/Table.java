package com.se.jyh.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * 
 * @author lgpc
 * 
 *         TableModel
 * 
 *         Models for right panel
 */

public class Table extends AbstractTableModel {
	
	private String[] headers;
	
	private Object[][] data;
	
	private Tree treeModel;
	private DsmModel dsmModel;

	public Table() {
		
	}
	public Object[][] getData(){
		return data;
	}
	public void setData(Object[][] data) {
		
		this.data=data;
		
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data.length+1;j++){
				if(i+1==j){
					data[i][j]=" .";
				}else if(data[i][j].toString().equals("1")){
					data[i][j]="x";
				}else if(data[i][j].toString().equals("0")){
					data[i][j]=" ";
				}
			}
		}
	}

	public void setModel(Tree treeModel, DsmModel dsmModel) {

		this.treeModel = treeModel;
		this.dsmModel=dsmModel;
		
	}
	
	public void setHeaders(int number){
		this.headers = new String[number+1];
		headers[0]="";
		for(int i=0;i<number;i++){
			this.headers[i+1]=i+1+"";
		}
	}
	

	public List<String> getRowNumberList(){
		List<String> nameList = new ArrayList<String>();
		
		//search(treeModel.getRoot(),nameList);
		
		return nameList;
	}
	
	


	
	
	
	
	public String getColumnName(int col) 
	{  return headers[col];   } 

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
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
	public void clear() {
		// TODO Auto-generated method stub
		this.data=null;
		this.headers=null;
	}

}
