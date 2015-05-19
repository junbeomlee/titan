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

	private List<String> nameList = new ArrayList<String>();
	private Object[][] data;
	private Tree treeModel;

	public Table() {

	}
	
	public void setSize(int size) {
		/**
		 * 0,x x,0은 name 과 index 쓰는 칸이다
		 */
		data = new Object[size + 1][size + 1];
	}

	public void setNameList(DefaultMutableTreeNode root) {
		for (int i = 0; i < root.getChildCount(); i++) {
			nameList.add(root.getChildAt(i).toString());
		}
	}

	public void setData() {

	}

	public void setModel(Tree treeModel) {

		this.treeModel = treeModel;

		DefaultMutableTreeNode currentNode = treeModel.getRoot();
		
		search(currentNode);
	}

	public void search(DefaultMutableTreeNode currentNode) {
		
		while(currentNode!=null){
			/**
			 * expanded면
			 */
			if(currentNode.getChildCount()>0){
				if (treeModel.isExpanded(new TreePath(currentNode.getPath()))) {
					search((DefaultMutableTreeNode) currentNode.getFirstChild());
					currentNode = currentNode.getNextSibling();
				} else {
					System.out.println(currentNode.toString());
					currentNode=currentNode.getNextSibling();
				}
			}else{
				System.out.println(currentNode.toString());
				currentNode = currentNode.getNextSibling();
			}
			
		}
	}

	public void addColumn(Object item) {

	}

	public void removeColunm() {

	}

	public void addRow() {

	}

	public void deleteRow() {

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
