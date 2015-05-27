package com.se.jyh.viewComponent.MenuBarCommand;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import com.se.jyh.controller.demoController;

public class FileCommandOpen_impl extends JMenuItem implements Command {

	private demoController democontroller;
	
	public FileCommandOpen_impl(demoController democontroller){
		super("Open");
		this.democontroller=democontroller;
		//democontroller= demoController.getInstance();
	}
	@Override
	public void execute() {
		System.out.println("execute open");
		democontroller.openDsm();
		
	}
	@Override
	public String toString(){
		String a = "opena";
		return a;
		
	}
	
	@Override
	public void notifyAction(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
