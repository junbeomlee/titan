package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;
import com.se.jyh.viewComponent.leftPanelCommand.AddCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.CollapseAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.DeleteCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.ExpandAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.GroupCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveDownCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveUpCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.RenameCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.SortCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.UnGroupCommand_impl;

public class LeftPanel extends JPanel implements ActionListener, MouseListener {

	private demoController democontroller;
	
	private JButton add;
	private JButton expandAll;
	private JButton collapseAll;
	private JButton delete;
	private JButton group;
	private JButton moveUp;
	private JButton moveDown;
	private JButton unGroup;
	private JToolBar toolbar;
	
	private JPopupMenu popUp;
	private JMenuItem sort;
	private JMenuItem rename;

	public LeftPanel(BorderLayout borderLayout){
		
		super(borderLayout);
		//this.setLayout(new ScrollPaneLayout());
		this.setToolBar();
		this.setPopUp();
		this.setActionListener();
		this.democontroller= demoController.getInstance();
		this.democontroller.setLeftPanel(this);
		
	}
	private void setPopUp() {
		// TODO Auto-generated method stub
		popUp = new JPopupMenu();
		sort = new RenameCommand_impl();
		rename = new SortCommand_impl();
		popUp.add(sort);
		popUp.add(rename);
	}
	public void test(){
		System.out.println("left register");
	}
	public void setToolBar(){
		toolbar = new JToolBar();
		
		/**
		 * set buttons 
		 */
		
		expandAll = new ExpandAllCommand_impl();
		expandAll.setContentAreaFilled(false);
		
		add = new AddCommand_impl();
		collapseAll = new CollapseAllCommand_impl();
		delete = new DeleteCommand_impl();
		group = new GroupCommand_impl() ;
		moveUp = new MoveUpCommand_impl();
		moveDown = new MoveDownCommand_impl();
		unGroup = new UnGroupCommand_impl();
		
		
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		
		/**
		 * add items
		 */
		toolbar.add(expandAll);
		toolbar.add(collapseAll);
		
		toolbar.addSeparator();
		
		toolbar.add(group);
		toolbar.add(unGroup);
		
		
		toolbar.addSeparator();
		toolbar.add(moveUp);
		toolbar.add(moveDown);

		toolbar.addSeparator();
		
		toolbar.add(add);
		
		toolbar.add(delete);
		
		this.add(toolbar,BorderLayout.PAGE_START);
		
	}
	
	public void setActionListener(){

		expandAll.addActionListener(this);
		
		collapseAll.addActionListener(this);
		
		delete.addActionListener(this);
	
		group.addActionListener(this);
	
		moveUp.addActionListener(this);

		moveDown.addActionListener(this);
	
		unGroup.addActionListener(this);
	
		sort.addActionListener(this);
	
		add.addActionListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Command command = (Command) e.getSource();
		command.execute();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
			JTree tree = (JTree) e.getSource();
			TreePath path=tree.getSelectionPath();
			DefaultMutableTreeNode node= (DefaultMutableTreeNode) path.getLastPathComponent();
			if(node.getChildCount()>0){
				popUp.show(e.getComponent(), e.getX(), e.getY());
			}
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
