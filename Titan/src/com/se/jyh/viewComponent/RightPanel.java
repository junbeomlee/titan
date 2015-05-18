package com.se.jyh.viewComponent;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.se.jyh.controller.demoController;

public class RightPanel extends JPanel {
	
	private demoController democontroller;
	
	public RightPanel(BorderLayout borderLayout){
			
			super(borderLayout);
			this.democontroller= demoController.getInstance();
			this.democontroller.setRightPanel(this);
			
	}
}
