package com.se.jyh.viewComponent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

import com.se.jyh.controller.demoController;

public class LeftPanel extends JScrollPane{

	private demoController democontroller;

	public LeftPanel(demoController controller){
		this.democontroller= controller;
	}
	public LeftPanel(JTree asd, int verticalScrollbarAsNeeded,
			int horizontalScrollbarAsNeeded) {
		// TODO Auto-generated constructor stub
		super(asd,verticalScrollbarAsNeeded,horizontalScrollbarAsNeeded);
	}
}
