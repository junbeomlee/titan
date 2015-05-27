package com.se.jyh.test;

import com.se.jyh.controller.demoController;
import com.se.jyh.model.DsmModel;
import com.se.jyh.model.Model;
import com.se.jyh.model.Tree;
import com.se.jyh.viewComponent.Frame;

public class main {
	
	public static void main(String[] args) {
		
		demoController controller = new demoController();
		Frame frame = new Frame(controller);
		
	}

}
