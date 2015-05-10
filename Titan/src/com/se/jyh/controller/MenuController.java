package com.se.jyh.controller;

import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import com.se.jyh.view.menuitem.MenuCommand;

public class MenuController extends Controller{

	public MenuController(){
		super();
	}
	public void add(JMenuItem item){
		System.out.println("added");
		System.out.println(item.toString());
		item.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * update models
		 * and call view.update(model)
		 */
		// TODO Auto-generated method stub
		MenuCommand command = (MenuCommand) e.getSource();
		command.execute();
	}
	@Override
	public String toString(){
		String a="I am a menuController";
		return a;
	}
}
