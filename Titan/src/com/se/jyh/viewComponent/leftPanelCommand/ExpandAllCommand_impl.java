package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class ExpandAllCommand_impl  extends JButton implements Command {

	private demoController democontroller;
	
	public ExpandAllCommand_impl() {
		// TODO Auto-generated constructor stub
		//super(imageIcon);
		this.setIcon(new ImageIcon("image/expand.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.democontroller.expandAll();
	}

}
