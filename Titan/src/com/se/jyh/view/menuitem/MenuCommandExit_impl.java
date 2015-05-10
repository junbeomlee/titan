package com.se.jyh.view.menuitem;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class MenuCommandExit_impl extends JMenuItem implements MenuCommand {
	private JFrame frame;
	private demoController democontroller;
	
	public MenuCommandExit_impl(String name, JFrame frame,demoController democontroller){
		super(name);
		this.frame= frame;
		System.out.println("exit");
		this.democontroller = democontroller;
		this.democontroller.regMenuItems(this, "Exit");
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Exit execute");
		democontroller.exit();
		//FileDialog dialog = new FileDialog(frame,"Open",FileDialog.LOAD);
		//dialog.show();
	}
	
	@Override
	public String toString(){
		String a = "i am a exit";
		return a;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		System.out.println("update");
	}
}
