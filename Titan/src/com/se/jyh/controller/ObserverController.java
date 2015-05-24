package com.se.jyh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.se.jyh.viewComponent.leftPanelCommand.AddCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.CollapseAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.DeleteCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.ExpandAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.GroupCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveDownCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveUpCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.UnGroupCommand_impl;

public class ObserverController implements TreeSelectionListener{

	private static ObserverController observer= new ObserverController();
	
	
	private AddCommand_impl add;
	private CollapseAllCommand_impl collapseAll;
	private DeleteCommand_impl delete;
	private ExpandAllCommand_impl expand;
	private MoveDownCommand_impl moveDown;
	private MoveUpCommand_impl moveUp;
	private GroupCommand_impl group;
	
	public static void setObserver(ObserverController observer) {
		ObserverController.observer = observer;
	}

	public void setCollapseAll(CollapseAllCommand_impl collapseAll) {
		this.collapseAll = collapseAll;
	}

	public void setDelete(DeleteCommand_impl delete) {
		this.delete = delete;
	}

	public void setExpand(ExpandAllCommand_impl expand) {
		this.expand = expand;
	}

	public void setMoveDown(MoveDownCommand_impl moveDown) {
		this.moveDown = moveDown;
	}

	public void setMoveUp(MoveUpCommand_impl moveUp) {
		this.moveUp = moveUp;
	}

	public void setGroup(GroupCommand_impl group) {
		this.group = group;
	}

	public void setUnGroup(UnGroupCommand_impl unGroup) {
		this.unGroup = unGroup;
	}
	private UnGroupCommand_impl unGroup;
	
	private ObserverController(){
		//this.sort.addActionListener(arg0);
	}
	
	public static ObserverController getInstance (){
		return observer;
	}
	
	public static ObserverController getObserver() {
		return observer;
	}

	public CollapseAllCommand_impl getCollapseAll() {
		return collapseAll;
	}

	public DeleteCommand_impl getDelete() {
		return delete;
	}

	public ExpandAllCommand_impl getExpand() {
		return expand;
	}

	public MoveDownCommand_impl getMoveDown() {
		return moveDown;
	}

	public MoveUpCommand_impl getMoveUp() {
		return moveUp;
	}

	public GroupCommand_impl getGroup() {
		return group;
	}

	public UnGroupCommand_impl getUnGroup() {
		return unGroup;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		System.out.println(e.getSource());
		JTree tree = (JTree) e.getSource();
		TreePath[] paths = tree.getSelectionPaths();
		DefaultTreeModel treeModel= (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
		List<DefaultMutableTreeNode> selectionList= new ArrayList<DefaultMutableTreeNode>();
		if(paths==null) return;
		for(int i=0;i<paths.length;i++){
			selectionList.add((DefaultMutableTreeNode) paths[i].getLastPathComponent());
		}
		if(selectionList.get(0)==treeModel.getRoot()){
			this.delete.notifyAction(false);
			this.moveDown.notifyAction(false);
			this.moveUp.notifyAction(false);
			this.group.notifyAction(false);
			this.unGroup.notifyAction(false);
		}else{
			this.delete.notifyAction(this.checkDelete(selectionList));
			this.moveDown.notifyAction(this.checkMoveDown(selectionList));
			this.moveUp.notifyAction(this.checkMoveUp(selectionList));
			this.group.notifyAction(this.checkGroup(selectionList));
			this.unGroup.notifyAction(this.checkUnGroup(selectionList));
		}
		
	}
	public boolean checkMoveUp(List<DefaultMutableTreeNode> selectionList){
		DefaultMutableTreeNode tempNode;
		
		tempNode=selectionList.get(0);
		
		for(int i=1;i<selectionList.size();i++){
			if(!tempNode.isNodeSibling(selectionList.get(i))){
				return false;
			}
			tempNode=selectionList.get(i);
		}
		for(int i=0;i<selectionList.size();i++){
			if(selectionList.get(i).getPreviousSibling() == null){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkMoveDown(List<DefaultMutableTreeNode> selectionList){
		DefaultMutableTreeNode tempNode;
		tempNode=selectionList.get(0);
		
		for(int i=1;i<selectionList.size();i++){
			if(!tempNode.isNodeSibling(selectionList.get(i))){
				return false;
			}
			tempNode=selectionList.get(i);
		}
		for(int i=0;i<selectionList.size();i++){
			if(selectionList.get(i).getNextSibling() == null){
				return false;
			}
		}
		return true;
	}
	public boolean checkGroup(List<DefaultMutableTreeNode> selectionList){
		DefaultMutableTreeNode tempNode;
		
		tempNode=selectionList.get(0);
		
		for(int i=1;i<selectionList.size();i++){
			if(!tempNode.isNodeSibling(selectionList.get(i))){
				return false;
			}
			tempNode=selectionList.get(i);
		}
		return true;
	}
	public boolean checkUnGroup(List<DefaultMutableTreeNode> selectionList){
		
		for(int i=0;i<selectionList.size();i++){
			if(selectionList.get(i).getChildCount()==0){
				return false;
			}
		}
		return true;
	}
	public boolean checkDelete(List<DefaultMutableTreeNode> selectionList){
		
		return true;
	}

	public void setAdd(AddCommand_impl addCommand_impl) {
		// TODO Auto-generated method stub
		this.add=addCommand_impl;
	}

	public AddCommand_impl getAdd() {
		// TODO Auto-generated method stub
		return this.add;
	}

	
}
