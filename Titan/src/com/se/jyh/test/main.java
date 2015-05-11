package com.se.jyh.test;

import com.se.jyh.controller.demoController;
import com.se.jyh.model.Model;
import com.se.jyh.model.TableModel;
import com.se.jyh.model.TreeModel;
import com.se.jyh.viewComponent.Frame;

public class main {
	
	public static void main(String[] args) {
		demoController controller = demoController.getInstance();
		Frame view=new Frame();
		
		Model model= new Model();
		//Model treeModel= new TreeModel();
		
		controller.set(view, model);
	}

}
