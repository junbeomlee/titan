package com.se.jyh.test;

import com.se.jyh.controller.*;
import com.se.jyh.viewComponent.*;

public class main {
	
	public static void main(String[] args) {
		demoController controller = demoController.getInstance();
		Frame view=new Frame();
		controller.setView(view);
		controller.setModel();
	}

}
