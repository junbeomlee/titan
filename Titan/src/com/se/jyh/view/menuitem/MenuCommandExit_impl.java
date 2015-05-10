package com.se.jyh.view.menuitem;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class MenuCommandExit_impl extends JMenuItem implements MenuCommand {
	private JFrame frame;
	public MenuCommandExit_impl(String name, JFrame frame){
		super(name);
		this.frame= frame;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("New execute");
		FileDialog dialog = new FileDialog(frame,"Open",FileDialog.LOAD);
		dialog.show();
	}
	@Override
	public String toString(){
		String a = "New";
		return a;
		
	}
}
