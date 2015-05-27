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
	public void setData() {

	}

	public void setModel(Tree treeModel, DsmModel dsmModel) {

		this.treeModel = treeModel;
		this.dsmModel=dsmModel;
		
		
		
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

}
