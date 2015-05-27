package com.se.jyh.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.eclipse.swt.events.TreeEvent;

/**
 * 
 * @author lgpc TreeModel
 * 
 *         not sure
 */
public class Tree extends JTree {

	private int[] index;

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}

	public Tree(DefaultMutableTreeNode root) {
		super(root);
	}

	public int rootSize() {
		DefaultMutableTreeNode root=(DefaultMutableTreeNode) this.getModel().getRoot();
		return root.getChildCount();
	}

	public DefaultMutableTreeNode getRoot(){
		DefaultMutableTreeNode root=(DefaultMutableTreeNode) this.getModel().getRoot();
		return root;
	}
	public void initNode(DsmModel model) {

		DefaultMutableTreeNode root=(DefaultMutableTreeNode) this.getModel().getRoot();
		
		for (int i = 0; i < model.getSize(); i++) {
			root.add(new DefaultMutableTreeNode(
					model.getDependencyData_arr().get(i).getName()));
		}
		DefaultMutableTreeNode asd = root.getNextNode();
	}
	public void addNode(DsmModel model){
		System.out.println("zzz");
		DefaultTreeModel treeModel = (DefaultTreeModel) this.getModel();
		treeModel.insertNodeInto(new DefaultMutableTreeNode(model.getDependencyData_arr().get(model.getSize()-1).getName()), this.getRoot(), this.getRoot().getChildCount()-1);
		
	}

	public void delete() {
		// TODO Auto-generated method stub
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		DefaultMutableTreeNode currentNode = this.getRoot();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				DefaultMutableTreeNode temp = currentNode;
				currentNode = currentNode.getNextNode();
				model.removeNodeFromParent(temp);
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
	}

	public void expandAll() {
		for (int i = 0; i < this.getRowCount(); i++) {
			this.expandRow(i);
		}

	}

	public void collapseAll() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.getRowCount(); i++) {
			this.collapseRow(i);
		}
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();

		DefaultMutableTreeNode parent = null;
		DefaultMutableTreeNode selection = null;
		DefaultMutableTreeNode currentNode = this.getRoot();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				parent = (DefaultMutableTreeNode) currentNode.getParent();
				DefaultMutableTreeNode before = currentNode
						.getPreviousSibling();
				if(before==null){
					currentNode =null;
				}else{
					DefaultMutableTreeNode temp = currentNode;
					//this.removeSelectionPath(new TreePath(currentNode.getPath()));
					model.removeNodeFromParent(currentNode);
					model.insertNodeInto(temp, parent, parent.getIndex(before));
					
					selectionList.add(currentNode);
					//currentNode=currentNode.getNextSibling();
					
				}

			} else {
				currentNode = currentNode.getNextNode();
			}
		}

		TreePath[] selectionPath = new TreePath[selectionList.size()];
		
		for (int i = 0; i < selectionList.size(); i++) {
			selectionPath[i] = new TreePath(selectionList.get(i).getPath());
		}
		
		this.setSelectionPaths(selectionPath);
		
	}

	public void moveDown() {

		
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();

		DefaultMutableTreeNode parent = null;
		DefaultMutableTreeNode selection = null;
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) this.getRoot().getLastChild();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				parent = (DefaultMutableTreeNode) currentNode.getParent();
				DefaultMutableTreeNode after = currentNode.getNextSibling();
				if(after==null){
					currentNode = null;
					continue;
				}else{
					
					DefaultMutableTreeNode temp = after;
					model.removeNodeFromParent(after);
					model.insertNodeInto(temp, parent, parent.getIndex(currentNode));
					selectionList.add(currentNode);
				}
				currentNode=currentNode.getPreviousSibling();

			} else {
				currentNode = currentNode.getPreviousNode();
			}
		}

		TreePath[] selectionPath = new TreePath[selectionList.size()];
		for (int i = 0; i < selectionList.size(); i++) {
			selectionPath[i] = new TreePath(selectionList.get(i).getPath());
		}
		
		this.setSelectionPaths(selectionPath);

	}

	public void group(String groupName) {
		// TODO Auto-generated method stub

		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();
		
		DefaultMutableTreeNode currentNode = this.getRoot();
		DefaultMutableTreeNode parent = null;
		int index = 9999;
		
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {
				
				if (parent == null) {
					parent = (DefaultMutableTreeNode) currentNode.getParent();
					index = parent.getIndex(currentNode);
				}
				DefaultMutableTreeNode temp = currentNode;
				selectionList.add(currentNode);
				currentNode = currentNode.getNextSibling();
				model.removeNodeFromParent(temp);

			} else {
				currentNode = currentNode.getNextNode();
			}
		}

		DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(groupName);
		System.out.println(newGroup.toString());
		System.out.println(parent.toString());
		model.insertNodeInto(newGroup, parent, index);

		for (int i = 0; i < selectionList.size(); i++) {
			newGroup.add(selectionList.get(i));
		}
		///this.removeSelectionPath(new TreePath(newGroup.getPath()));
	}

	public void unGroup() {
		// TODO Auto-generated method stub

		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();

		DefaultMutableTreeNode currentNode = this.getRoot();
		DefaultMutableTreeNode parent = null;
		int index = 9999;
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				if (currentNode.getChildCount() > 0) {
					/**
					 * ungroup가능
					 */
					for (int i = 0; i < currentNode.getChildCount(); i++) {
						selectionList.add((DefaultMutableTreeNode) currentNode
								.getChildAt(i));
					}
					parent = (DefaultMutableTreeNode) currentNode.getParent();
					index = parent.getIndex(currentNode);

					DefaultMutableTreeNode temp = currentNode;

					currentNode = currentNode.getNextSibling();
					//System.out.println(currentNode.toString());
					model.removeNodeFromParent(temp);
					
					for (int i = 0; i < selectionList.size(); i++) {
						model.insertNodeInto(selectionList.get(i), parent,
								index + i);
					}
					
					selectionList.clear();
					continue;
					
				} else {
					/**
					 * 자식없어서 ungroup불가능
					 */
					System.out.println("ungroup 안됨");
				}

				currentNode = currentNode.getNextNode();
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
	}

	public void sort() {
		
		DefaultMutableTreeNode currentNode = this.getRoot();
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		List<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
		/**
		 * searching 시작 
		 */
		while (currentNode != null) {
			/**
			 * select된건지 체크 
			 */
			if (this.isPathSelected(new TreePath(currentNode.getPath()))){
				System.out.println(currentNode.getChildCount());
				for(int i=0; i<currentNode.getChildCount();i++){
					/**
					 * child node 저장 
					 */
					list.add((DefaultMutableTreeNode) currentNode.getChildAt(i));
				}
				for(int i=0; i<list.size();i++){
					//System.out.println(currentNode.getChildCount());
					/**
					 * node저장후 지우기 나중에 추가할 예정 
					 */
					model.removeNodeFromParent(list.get(i));
				}
				Collections.sort(list,new Comparator() {

					@Override
					public int compare(Object arg0, Object arg1) {
						// TODO Auto-generated method stub
						return arg0.toString().toLowerCase().compareTo(arg1.toString().toLowerCase());
					}
					
				});
				
				for(int i=0;i<list.size();i++){
					model.insertNodeInto(list.get(i), currentNode, i);
				}
				this.expandPath(new TreePath(currentNode.getPath()));
				break;
			}else{
			currentNode= currentNode.getNextNode();
			}
		}
		
	}

	public void rename(String groupName) {

		// TODO Auto-generated method stub
		DefaultMutableTreeNode currentNode = this.getRoot();
		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))){
				currentNode.setUserObject(groupName);
				
				break;
			}else{
			currentNode= currentNode.getNextNode();
			}
		}
	}

	public DefaultMutableTreeNode getGroupNode() {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode currentNode = this.getRoot();
		DefaultMutableTreeNode newRoot = null;
		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				newRoot=new DefaultMutableTreeNode(currentNode.toString());
				
				currentNode = currentNode.getNextNode();
				/**
				 * NODEADD하면 그 NODE가 없어지는듯 이거아는데 3시간 날림 
				 */
				while(currentNode != null){
					DefaultMutableTreeNode temp=null;
					if(currentNode.getChildCount()>0){ // 리커시브
						temp = this.getParentNode(currentNode);
					}else{ // 그냥 복사
						temp = new DefaultMutableTreeNode(currentNode.toString());
					}
					newRoot.add(temp);
					currentNode=currentNode.getNextSibling();
				}
				
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		
		
		return newRoot;
	}

	public DefaultMutableTreeNode getParentNode(DefaultMutableTreeNode currentNode){
		
		DefaultMutableTreeNode parentNode= new DefaultMutableTreeNode(currentNode.toString());
		currentNode=currentNode.getNextNode();
		
		while(currentNode!=null){
			
			DefaultMutableTreeNode temp=null;
			
			if(currentNode.getChildCount()>0){ // 리커시브
				temp = this.getParentNode(currentNode);
			}else{ // 그냥 복사
				temp = new DefaultMutableTreeNode(currentNode.toString());
			}
			parentNode.add(temp);
			currentNode=currentNode.getNextSibling();
		}
		
		return parentNode;
	}

	public DefaultMutableTreeNode selectedNode() {
		// TODO Auto-generated method stub
		DefaultMutableTreeNode currentNode = this.getRoot();
		
		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				return currentNode;
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
		return null;
	}
	
	public void search(DefaultMutableTreeNode currentNode,List<DefaultMutableTreeNode> groupNodeList) {
		
		while(currentNode!=null){
			/**
			 * expanded면
			 */
			if(currentNode.getChildCount()>0){
				if (this.isExpanded(new TreePath(currentNode.getPath()))) {
					
					search((DefaultMutableTreeNode) currentNode.getFirstChild(),groupNodeList);
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
	
	public  void search2(DefaultMutableTreeNode currentNode,List<DefaultMutableTreeNode> groupNodeList){
		
		while(currentNode!=null){
			/**
			 * expanded면
			 */
			if(currentNode.getChildCount()>0){
				if (this.isExpanded(new TreePath(currentNode.getPath()))) {
					
					//search((DefaultMutableTreeNode) currentNode.getFirstChild(),groupNodeList);
					currentNode = currentNode.getNextNode();
					
				} else {
					//System.out.println(currentNode.toString());
					groupNodeList.add(currentNode);
					currentNode=currentNode.getNextNode();
				}
			}else{
				//System.out.println(currentNode.toString());
				currentNode = currentNode.getNextNode();
			}
			
		}
	}
}
