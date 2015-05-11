package com.se.jyh.controller;

import java.util.HashMap;

import javax.swing.JMenuItem;

import com.se.jyh.viewComponent.Frame;

/**
 * 
 * @author lgpc
 * Controller
 * 
 * im thinking about makeing a factory register class -> this factory only do set and get
 */
public class demoController {
	
	private static demoController controller= new demoController();
	public Frame view;
	//public Model model;
	/**
	 * item list -> those which has a an action
	 */
	private JMenuItem newDsm;
	private JMenuItem openDsm;
	private JMenuItem saveDsm;
	private JMenuItem saveAsDsm;
	private JMenuItem exitDsm;
	
	private demoController(){}
	
	public static demoController getInstance(){
		return controller;
	}
	public void setView(Frame view){
		this.view=view;
	}
	public void setModel(){
		
	}
}
