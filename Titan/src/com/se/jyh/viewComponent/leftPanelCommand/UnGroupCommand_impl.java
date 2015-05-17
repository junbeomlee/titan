package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class UnGroupCommand_impl extends JButton implements Command{

	public UnGroupCommand_impl(){
		this.setIcon(new ImageIcon("image/ungroup.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
