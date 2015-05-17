package com.se.jyh.test;

import com.se.jyh.controller.demoController;
import com.se.jyh.model.Model;
import com.se.jyh.viewComponent.Frame;
import com.se.jyh.viewComponent.LeftPanel;

public class main {
	
	public static void main(String[] args) {
		demoController controller = demoController.getInstance();
		Frame frame = new Frame();
		
		Model model= new Model();
		//Model treeModel= new TreeModel();
		
		controller.set(frame, model);
	}

}
