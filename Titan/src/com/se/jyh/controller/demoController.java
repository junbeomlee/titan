package com.se.jyh.controller;

import java.util.HashMap;

import javax.swing.JMenuItem;

import com.se.jyh.model.Model;
import com.se.jyh.view.View;

/**
 * 
 * @author lgpc
 * Controller
 * 
 * im thinking about makeing a factory register class -> this factory only do set and get
 */
public class demoController {
	
	public View view;
	public Model model;

	/**
	 * item list -> those which has a an action
	 */
	private HashMap<String,JMenuItem> JMenuItems;
	private JMenuItem newDsm;
	private JMenuItem openDsm;
	private JMenuItem saveDsm;
	private JMenuItem saveAsDsm;
	private JMenuItem exitDsm;
	
	public demoController(){
		System.out.println("democontroller");
		JMenuItems = new HashMap<String,JMenuItem>();
		this.model=new Model();
		this.view=new View(this);
	}
	public demoController(Model model){
		//JMenuItems = new HashMap<String,JMenuItem>();
	}
	public void regMenuItems(JMenuItem item,String name){
		JMenuItems.put(name,item);
	}
	public void regMenuItemNew(JMenuItem item){
		this.newDsm = item;
	}
	public void regMenuItemOpen(JMenuItem item){
		this.openDsm = item;
	}
	public void regMenuItemSaveAs(JMenuItem item){
		this.saveAsDsm = item;
	}
	public void regMenuItemExitDsm(JMenuItem item){
		this.exitDsm = item;
	}
	public void regMenuItemSave(JMenuItem item){
		this.saveDsm = item;
	}
	public void exit() {
		// TODO Auto-generated method stub
		System.out.println(JMenuItems.get("Exit").toString());
		System.out.println("demo exit");
	}
}
