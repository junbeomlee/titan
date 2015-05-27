package com.se.jyh.viewComponent.leftPanelCommand;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.se.jyh.controller.demoController;
import com.se.jyh.viewComponent.MenuBarCommand.Command;

public class ExpandAllCommand_impl  extends JButton implements Command {

	private demoController democontroller;
;
	
	public ExpandAllCommand_impl( demoController democontroller) {
		this.democontroller=democontroller;
		this.democontroller.setExpand(this);
		this.setEnabled(false);
	
		this.setIcon(new ImageIcon("image/expand.png"));
		this.setPreferredSize(new Dimension(20,20));
		this.setMargin(new Insets(-2, -2, -2, -2));
		this.setBorderPainted(false);
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
