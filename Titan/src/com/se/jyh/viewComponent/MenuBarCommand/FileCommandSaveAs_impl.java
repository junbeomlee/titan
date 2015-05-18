package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandSaveAs_impl extends JMenuItem implements Command {

	
	private demoController democontroller;
	
	public FileCommandSaveAs_impl(String name){
		super(name);
		democontroller= demoController.getInstance();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute saveas");
		democontroller.saveAsDsm();
		//System.out.println("New execute");
	}
	@Override
	public String toString(){
		String a = "New";
		return a;
		
	}
}
