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

	private static DefaultMutableTreeNode root = new DefaultMutableTreeNode(
			"$root");

	private int[] index;

	public int[] getIndex() {
		return index;
	}

	public void setIndex(int[] index) {
		this.index = index;
	}

	public Tree() {
		super(root);
	}

	public int rootSize() {
		return root.getChildCount();
	}

	public void initNode(DsmModel model) {

		for (int i = 0; i < model.getSize(); i++) {
			root.add(new DefaultMutableTreeNode(
					model.getDependencyData_arr()[i].getName()));
		}
		DefaultMutableTreeNode asd = root.getNextNode();
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public void delete() {
		// TODO Auto-generated method stub
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		DefaultMutableTreeNode currentNode = root;

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
		DefaultMutableTreeNode currentNode = root;

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
		DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) root.getLastChild();

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
		DefaultMutableTreeNode currentNode = root;
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
				currentNode = currentNode.getNextNode();
				model.removeNodeFromParent(temp);

			} else {
				currentNode = currentNode.getNextNode();
			}
		}

		DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(groupName);

		model.insertNodeInto(newGroup, parent, index);

		for (int i = 0; i < selectionList.size(); i++) {
			newGroup.add(selectionList.get(i));
		}

	}

	public void unGroup() {
		// TODO Auto-generated method stub

		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();

		DefaultMutableTreeNode currentNode = root;
		DefaultMutableTreeNode parent = null;
		int index = 9999;
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();

		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))) {

				if (currentNode.getChildCount() > 0) {
					/**
					 * ungroup����
					 */
					for (int i = 0; i < currentNode.getChildCount(); i++) {
						selectionList.add((DefaultMutableTreeNode) currentNode
								.getChildAt(i));
					}
					parent = (DefaultMutableTreeNode) currentNode.getParent();
					index = parent.getIndex(currentNode);

					DefaultMutableTreeNode temp = currentNode;

					currentNode = currentNode.getNextSibling();

					model.removeNodeFromParent(temp);

					for (int i = 0; i < selectionList.size(); i++) {
						model.insertNodeInto(selectionList.get(i), parent,
								index + i);
					}
					selectionList.clear();
					continue;
				} else {
					/**
					 * �ڽľ�� ungroup�Ұ���
					 */
					System.out.println("ungroup �ȵ�");
				}

				currentNode = currentNode.getNextNode();
			} else {
				currentNode = currentNode.getNextNode();
			}
		}
	}

	public void sort() {
		
		DefaultMutableTreeNode currentNode = root;
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		List<DefaultMutableTreeNode> list = new ArrayList<DefaultMutableTreeNode>();
		/**
		 * searching ���� 
		 */
		while (currentNode != null) {
			/**
			 * select�Ȱ��� üũ 
			 */
			if (this.isPathSelected(new TreePath(currentNode.getPath()))){
				System.out.println(currentNode.getChildCount());
				for(int i=0; i<currentNode.getChildCount();i++){
					/**
					 * child node ���� 
					 */
					list.add((DefaultMutableTreeNode) currentNode.getChildAt(i));
				}
				for(int i=0; i<list.size();i++){
					//System.out.println(currentNode.getChildCount());
					/**
					 * node������ ����� ���߿� �߰��� ���� 
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
		DefaultMutableTreeNode currentNode = root;
		while (currentNode != null) {
			if (this.isPathSelected(new TreePath(currentNode.getPath()))){
				currentNode.setUserObject(groupName);
				
				break;
			}else{
			currentNode= currentNode.getNextNode();
			}
		}
	}
}
