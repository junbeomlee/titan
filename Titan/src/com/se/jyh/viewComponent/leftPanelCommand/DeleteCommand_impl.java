package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.ObserverController;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class DeleteCommand_impl extends JButton implements Command{

	private demoController democontroller;
	private ObserverController observer;
	
	public DeleteCommand_impl(){
		this.observer=ObserverController.getInstance();
		this.observer.setDelete(this);
		
		
		this.setIcon(new ImageIcon("image/delete.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
		this.setToolTipText("Delete");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//System.out.println("delete");
		this.democontroller.delete();
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}

}
