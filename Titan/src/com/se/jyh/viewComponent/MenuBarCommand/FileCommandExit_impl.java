package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandExit_impl extends JMenuItem implements MenuCommand {


	private demoController democontroller;
	
	public FileCommandExit_impl(String name){
		super(name);
		System.out.println("exit");
		
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("Exit execute");
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
