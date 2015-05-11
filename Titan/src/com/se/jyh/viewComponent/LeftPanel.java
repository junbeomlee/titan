package com.se.jyh.viewComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.se.jyh.controller.demoController;

public class LeftPanel extends JScrollPane implements ActionListener, MouseListener{

	private demoController democontroller;

	public LeftPanel(demoController controller){
		this.democontroller= controller;
	}
	public LeftPanel(JTree asd, int verticalScrollbarAsNeeded,
			int horizontalScrollbarAsNeeded) {
		// TODO Auto-generated constructor stub
		super(asd,verticalScrollbarAsNeeded,horizontalScrollbarAsNeeded);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton() == MouseEvent.BUTTON2){
			System.out.println("오른쪽 마우스 클릭 event");
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
