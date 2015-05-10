package com.se.jyh.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import com.se.jyh.model.Model;
import com.se.jyh.view.View;

public abstract class Controller implements ActionListener{

	
	public View view;
	public Model model;
	
	public Controller(){
		this.model=new Model();
		this.view=new View(this);
	}
	
	/**
	 * the parameter can be changed to more general component
	 * @param item
	 */
	abstract public void add(JMenuItem item);
	
	/**
	 * to register actionlistener it must implements this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
