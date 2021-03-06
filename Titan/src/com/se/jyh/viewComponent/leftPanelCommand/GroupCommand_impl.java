package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class GroupCommand_impl extends JButton implements Command{

	private demoController democontroller;
	
	public GroupCommand_impl(demoController democontroller){
		
		this.setEnabled(false);
		this.democontroller=democontroller;
		this.democontroller.setGroup(this);
		this.setIcon(new ImageIcon("image/group.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		//this.democontroller = demoController.getInstance();
		this.setToolTipText("Group");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.democontroller.group();
	}

	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}

}
