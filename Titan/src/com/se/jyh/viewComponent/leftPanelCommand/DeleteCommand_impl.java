package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class DeleteCommand_impl extends JButton implements Command{

	private demoController democontroller;
	
	public DeleteCommand_impl(){
		this.setIcon(new ImageIcon("image/delete.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//System.out.println("delete");
		this.democontroller.delete();
	}

}
