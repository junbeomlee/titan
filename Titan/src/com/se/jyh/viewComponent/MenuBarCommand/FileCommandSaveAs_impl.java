package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class FileCommandSaveAs_impl extends JMenuItem implements MenuCommand {

	private JFrame frame;
	public FileCommandSaveAs_impl(String name, JFrame frame){
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
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
