package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;
import com.se.jyh.viewComponent.leftPanelCommand.CollapseAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.DeleteCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.ExpandAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.GroupCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveDownCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveUpCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.UnGroupCommand_impl;

public class LeftPanel extends JPanel implements ActionListener, MouseListener {

	private demoController democontroller;
	
	private JButton expandAll;
	private JButton collapseAll;
	private JButton delete;
	private JButton group;
	private JButton moveUp;
	private JButton moveDown;
	private JButton unGroup;
	private JToolBar toolbar;

	public LeftPanel(BorderLayout borderLayout){
		
		super(borderLayout);
		this.setToolBar();
		this.setActionListener();
		this.democontroller= demoController.getInstance();
		this.democontroller.setLeftPanel(this);
		
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
		collapseAll = new CollapseAllCommand_impl();
		delete = new DeleteCommand_impl();
		group = new GroupCommand_impl() ;
		moveUp = new MoveUpCommand_impl();
		moveDown = new MoveDownCommand_impl();
		unGroup = new UnGroupCommand_impl();
	
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		//toolbar.getBackground();
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
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("mouse");
		if (arg0.getButton() == MouseEvent.BUTTON2){
			System.out.println("오른쪽 마우스 클릭 event");
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Command command = (Command) e.getSource();
		command.execute();
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
