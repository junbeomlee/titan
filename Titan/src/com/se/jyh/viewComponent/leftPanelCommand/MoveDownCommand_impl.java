package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.ObserverController;
import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class MoveDownCommand_impl extends JButton implements Command{

	private demoController democontroller;
	private ObserverController observer;
	
	public MoveDownCommand_impl(){
		this.observer=ObserverController.getInstance();
		this.observer.setMoveDown(this);
		this.setEnabled(false);
		
		this.setIcon(new ImageIcon("image/down.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
		this.democontroller = demoController.getInstance();
		this.setToolTipText("Move Down");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		this.democontroller.moveDown();
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		this.setEnabled(b);
	}

}
