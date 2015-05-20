package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.ObserverController;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class MoveUpCommand_impl extends JButton implements Command{

	private demoController democontroller;
	private ObserverController observer;
	
	public MoveUpCommand_impl(){
		this.observer=ObserverController.getInstance();
		this.observer.setMoveUp(this);
		this.setEnabled(false);
		
		this.setIcon(new ImageIcon("image/up.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
		this.setToolTipText("Move Up");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.democontroller.moveUp();
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}

}
