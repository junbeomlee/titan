package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class GroupCommand_impl extends JButton implements Command{

	public GroupCommand_impl(){
		this.setIcon(new ImageIcon("image/group.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
