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
public class MyTreeModel extends JTree {

	private static DefaultMutableTreeNode root = new DefaultMutableTreeNode("$root");
	
	public MyTreeModel(){
		super(root);
	}
	
	public void setNode(DsmModel model){
		int i=0;
		//model.getSize();
		for(i=0;i<model.getSize();i++){
			root.add(new DefaultMutableTreeNode(model.getNameList().get(i)));
		}
	}
}
