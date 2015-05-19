package com.se.jyh.model;

import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;;
/**
 * 
 * @author lgpc
 * TreeModel
 * 
 * not sure
 */
public class Tree extends JTree  {

	private static DefaultMutableTreeNode root = new DefaultMutableTreeNode("$root");
	
	private int[] index;
	
	public int[] getIndex() {
		return index;
	}
	
	public void setIndex(int[] index) {
		this.index = index;
	}
	
	public Tree(){
		super(root);
	}
	
	public int rootSize(){
		return root.getChildCount();
	}
	
	public void initNode(DsmModel model){
		
		for(int i=0;i<model.getSize();i++){
			root.add(new DefaultMutableTreeNode(model.getDependencyData_arr()[i].getName()));
		}
		DefaultMutableTreeNode asd = root.getNextNode();
	}
	
	public DefaultMutableTreeNode getRoot(){
		return root;
	}
	public void delete() {
		// TODO Auto-generated method stub
		DefaultTreeModel model= (DefaultTreeModel) this.getModel();
		
		DefaultMutableTreeNode currentNode= root;
		
		while(currentNode!=null){
			if(this.isPathSelected(new TreePath(currentNode.getPath()))){
				
				DefaultMutableTreeNode temp=currentNode;
				currentNode=currentNode.getNextNode();
				model.removeNodeFromParent(temp);
			}else{
				currentNode=currentNode.getNextNode();
			}
		}
	}

	public void expandAll() {
		for(int i=0;i<this.getRowCount();i++){
			this.expandRow(i);
		}
		
	}

	public void collapseAll() {
		// TODO Auto-generated method stub
		for(int i=0;i<this.getRowCount();i++){
			this.collapseRow(i);
		}
	}
}
