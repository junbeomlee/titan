package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandSave_impl extends JMenuItem implements Command {

	private demoController democontroller;
	
	public FileCommandSave_impl(demoController democontroller){
		super("Save");
		this.democontroller=democontroller;
		//democontroller= demoController.getInstance();
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("execute save");
		democontroller.saveDsm();
		//System.out.println("New execute");
	}
	@Override
	public String toString(){
		String a = "New";
		return a;
		
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
