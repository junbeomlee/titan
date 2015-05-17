package com.se.jyh.controller;

import javax.swing.JTree;

import com.se.jyh.model.Model;
import com.se.jyh.model.MyTableModel;
import com.se.jyh.viewComponent.Frame;
import com.se.jyh.viewComponent.LeftPanel;

/**
 * 
 * @author lgpc
 * Controller
 * 
 * im thinking about makeing a factory register class -> this factory only do set and get
 */
public class demoController {
	
	private static demoController controller= new demoController();
	private Frame frame;
	private Model TreeModel; // right panel
	private MyTableModel TableModel; // left panel
	private LeftPanel leftPanel;
	/**
	 * item list -> those which has a an action
	 */
	private demoController(){ }
	
	public static demoController getInstance(){
		return controller;
	}
	public void set(Frame frame,Model model){
		this.frame=frame;
		//this.model=model;
		leftPanel=this.frame.getLeftpanel();
	}
	public void setTreeModel(Model model){
		
	}
	public void setTableModel(MyTableModel model){
		this.TableModel=model;
		//JTree asd= new JTree();
		//leftPanel.add(asd);
		//leftPanel.updateUI();
		//JScrollPane spTable = new JScrollPane(this.TableModel);
		//JPanel asd= new JPanel(this.TableModel);
		//leftPanel //(this.TableModel);
	}
	public void newDsm(){
		
	}
	public void openDsm(){
		
	}
	public void saveDsm(){
		
	}
	public void saveAsDsm(){
		
	}
	public void exitDsm(){
		
	}
	public void newcluster(){
		
	}
}
