package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandExit_impl extends JMenuItem implements Command {


	private demoController democontroller;
	
	public FileCommandExit_impl(String name){
		super(name);
		democontroller= demoController.getInstance();
		System.out.println("exit");
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Exit execute");
		democontroller.exitDsm();
	}
	@Override
	public String toString(){
		String a = "i am a exit";
		return a;
	}
}
