package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.ObserverController;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class ExpandAllCommand_impl  extends JButton implements Command {

	private demoController democontroller;
	private ObserverController observer;
	
	public ExpandAllCommand_impl() {
		this.observer=ObserverController.getInstance();
		this.observer.setExpand(this);
		// TODO Auto-generated constructor stub
		//super(imageIcon);
		this.setIcon(new ImageIcon("image/expand.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
		this.setToolTipText("Expand All");
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.democontroller.expandAll();
	}



	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}

}
