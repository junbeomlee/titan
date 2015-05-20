package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.ObserverController;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class CollapseAllCommand_impl extends JButton implements Command {

	private demoController democontroller;
	private ObserverController observer;
	
	public CollapseAllCommand_impl(){
		this.democontroller=demoController.getInstance();
		this.observer=ObserverController.getInstance();
		this.observer.setCollapseAll(this);
		
		
		this.setIcon(new ImageIcon("image/collapse.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setBorderPainted(false);
		this.setToolTipText("Collapse All");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		democontroller.collapseAll();
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}
}
