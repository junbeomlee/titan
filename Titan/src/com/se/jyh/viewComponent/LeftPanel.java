package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.leftPanelCommand.ExpandAllCommand_impl;

public class LeftPanel extends JPanel implements ActionListener, MouseListener{

	private demoController democontroller;
	
	private JButton expandAll;

	public LeftPanel(BorderLayout borderLayout){
		super(borderLayout);
		this.democontroller= democontroller;
	}
	public LeftPanel(){
		super();
	}
	public void setToolBar(){
		JToolBar toolbar = new JToolBar();
		
		expandAll = new ExpandAllCommand_impl(new ImageIcon("image/expand.png"));
		
		/**
		 * set buttons 
		 */
	
		this.add(toolbar,BorderLayout.PAGE_START);
		
		toolbar.setFloatable(false);
		toolbar.setRollover(true);
		toolbar.add(expandAll);
	}
	public void setActionListener(){
		expandAll.addActionListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == MouseEvent.BUTTON2){
			System.out.println("오른쪽 마우스 클릭 event");
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
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
