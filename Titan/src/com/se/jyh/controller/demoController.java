package com.se.jyh.controller;

import java.util.HashMap;

import javax.swing.JMenuItem;

import com.se.jyh.model.Model;
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
	private Frame view;
	private Model TreeModel; // right panel
	private Model TableModel; // left panel
	/**
	 * item list -> those which has a an action
	 */
	private demoController(){}
	
	public static demoController getInstance(){
		return controller;
	}
	public void set(Frame view,Model model){
		this.view=view;
		//this.model=model;
	}
	public void setTreeModel(Model model){
		
	}
	public void setTableModel(Model model){
		
	}
	public void newDsm(){
		
	}
	public void openDsm(){
		//logic 처리
		//model 바꿔 
		//view 너 model업데이트해
		//rightpanel
	}
	public void saveDsm(){
		
	}
	public void saveAsDsm(){
		
	}
	public void exitDsm(){
		System.out.println("demo exit");
	}
	public void newcluste(){
		
	}
}
