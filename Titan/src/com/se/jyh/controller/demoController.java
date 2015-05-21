package com.se.jyh.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.se.jyh.model.DsmModel;
import com.se.jyh.model.Model;
import com.se.jyh.model.Table;
import com.se.jyh.model.Tree;
import com.se.jyh.viewComponent.Frame;
import com.se.jyh.viewComponent.LeftPanel;
import com.se.jyh.viewComponent.RightPanel;

/**
 * 
 * @author lgpc
 * Controller
 * 
 * im thinking about makeing a factory register class -> this factory only do set and get
 */
public class demoController{
	
	private static demoController controller= new demoController();
	private ObserverController observer;
	private Frame frame; 
	private Table tableModel = new Table();
	private Tree treeModel = new Tree();
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private DsmModel dsmModel;
	/**
	 * item list -> those which has a an action
	 */
	private demoController(){
		
		treeModel.addTreeSelectionListener(observer);

	}
	
	/**
	 * register leftPanel
	 * @param leftPanel
	 */
	public void setLeftPanel(LeftPanel leftPanel){
		this.leftPanel=leftPanel;
	}
	/**
	 * register rightPanel
	 * @param rightPanel
	 */
	public void setRightPanel(RightPanel rightPanel){
		this.rightPanel=rightPanel;
	}
	/**
	 * return controller
	 * @return
	 */
	public static demoController getInstance(){
		return controller;
	}
	/**
	 * �ʿ���� �� ����� 
	 * @param frame
	 * @param model
	 */
	public void set(Frame frame,Model model){
		
		this.observer=ObserverController.getInstance();
		this.frame=frame;
		dsmModel= new DsmModel();
	}
	/**
	 * set tree model
	 * @param model
	 */
	public void setTreeModel(Model model){
		
	}
	/**
	 * set table model
	 * @param model
	 */
	public void setTableModel(Table model){
		this.tableModel=model;
	}
	
	/**
	 * All actions from menubar command
	 * 
	 */
	
	public void exitDsm(){
		System.out.println("controller exitdsm");
	}
	public void loadCluster(){
		System.out.println("controller loadCluster");
		
	}  
	public void newDsm(){
		System.out.println("controller newdsm");
		
	}
	public void newCluster(){
		System.out.println("controller newcluster");
	}
	public void openDsm(){
		System.out.println("controller opendsm");
		/**
		 * �ϴ��� 
		 * 1 �ϴ� dsmModel�� ��� ������ �����Ѵ�
		 * 2 �״����� leftpanel�� tree�� ��������
		 * 3 ���� �⺻�� root�� �ٰ� �ְ�
		 * 4 rightpanel�� leftpanel�� �������� update
		 * rightpanel�� leftpanel�� ���⸸ ����
		 */
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		
		/** filefilter�� ����*/
		//fc.setFileFilter(new FileFilter(".dsm"));
		
		fc.setDialogTitle("Save a File");
		int result= fc.showOpenDialog(null);
		if(result==JFileChooser.APPROVE_OPTION){
			//fc.getSelectedFile().getPath()
			try {
				BufferedReader buffer = new BufferedReader(new FileReader(fc.getSelectedFile().getPath()));
				String line= "";
				String s = "";
				try {
					s=buffer.readLine();
					
					dsmModel.setSize(Integer.parseInt(s));
					String[] temp;
					
					for(int i=0; i< Integer.parseInt(s); i++){
						dsmModel.setData(i,buffer.readLine());
					}
					for(int i=0; i< Integer.parseInt(s); i++){
						dsmModel.setName(buffer.readLine(),i);
					}
					dsmModel.print();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println(line);
				
				if(buffer!=null){
					try {
						buffer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		/**
		 * leftpanel ������Ʈ
		 */
		
		/**
		 * dsmModel�� ��������ؼ� treeModel�� ����
		 */
		treeModel.initNode(dsmModel);
		treeModel.addTreeSelectionListener(observer);
		treeModel.addMouseListener(this.leftPanel);
		
		observer.getCollapseAll().notifyAction(true);
		observer.getExpand().notifyAction(true);
		
		/**
		 * ������� treemodel�� leftpanel�� �ֱ�
		 */
		leftPanel.add(new JScrollPane(treeModel));
		//leftPanel.add(treeModel);
		leftPanel.revalidate();
		
		/**
		 * table�� tree model�� ���� ������
		 * table�� tree model �ֱ�
		 */
		
		tableModel.setModel(treeModel);

	}
	public void saveDsm(){
		System.out.println("controller savedsm");
		tableModel.setModel(this.treeModel);
		leftPanel.revalidate();
	}
	public void saveAsDsm(){
		System.out.println("controller saveasdsm");
	}
	public void saveCluster(){
		System.out.println("controller savecluster");
	}
	public void saveClusterAs(){
		System.out.println("controller saveclusteras");
	}
	public void help(){
		System.out.println("controller help");
	}
	public void redraw(){
		System.out.println("controller redraw");
		tableModel.setModel(this.treeModel);
		leftPanel.revalidate();
	}
	public void showRowLabels(){	
		System.out.println("controller showRL");
	}

	/**
	 * action from leftpanel
	 */
	public void collapseAll() {
		// TODO Auto-generated method stub
		treeModel.collapseAll();
	}

	public void unGroup() {
		treeModel.removeTreeSelectionListener(observer);
		treeModel.unGroup();
		treeModel.addTreeSelectionListener(observer);
		//observer.valueChanged(new TreeSelectionEvent(treeModel, treeModel.getSelectionPaths(), null, null, null));
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		treeModel.removeTreeSelectionListener(observer);
		treeModel.moveUp();
		treeModel.addTreeSelectionListener(observer);
		observer.valueChanged(new TreeSelectionEvent(treeModel, treeModel.getSelectionPaths(), null, null, null));
	}

	public void moveDown() {
		
		treeModel.removeTreeSelectionListener(observer);
		treeModel.moveDown();
		treeModel.addTreeSelectionListener(observer);
		observer.valueChanged(new TreeSelectionEvent(treeModel, treeModel.getSelectionPaths(), null, null, null));
		
	}

	public void group() {
		treeModel.removeTreeSelectionListener(observer);
		String groupName = JOptionPane.showInputDialog(null,"Group Name:");
		treeModel.group(groupName);
		treeModel.addTreeSelectionListener(observer);
	}

	public void expandAll() {
		// TODO Auto-generated method stub
		treeModel.expandAll();
	}

	public void delete() {
		
		treeModel.removeTreeSelectionListener(observer);
		treeModel.delete();
		treeModel.addTreeSelectionListener(observer);
		//observer.valueChanged(new TreeSelectionEvent(treeModel, treeModel.getSelectionPaths(), null, null, null));
	}

	

	public void rename() {
		// TODO Auto-generated method stub
		treeModel.removeTreeSelectionListener(observer);
		
		String groupName = JOptionPane.showInputDialog(null,"Enter new group name:");
		treeModel.rename(groupName);
		
		treeModel.addTreeSelectionListener(observer);
	}

	public void sort() {
		// TODO Auto-generated method stub
		treeModel.removeTreeSelectionListener(observer);
		treeModel.sort();
		treeModel.addTreeSelectionListener(observer);
	}


}
