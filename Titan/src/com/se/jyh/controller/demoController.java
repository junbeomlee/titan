package com.se.jyh.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTree;

import com.se.jyh.model.DsmModel;
import com.se.jyh.model.Model;
import com.se.jyh.model.MyTableModel;
import com.se.jyh.model.MyTreeModel;
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
public class demoController {
	
	private static demoController controller= new demoController();
	private Frame frame;
	private Model TreeModel; // right panel
	private MyTableModel TableModel; // left panel
	private MyTreeModel treeModel = new MyTreeModel();
	private LeftPanel leftPanel;
	private RightPanel rightPanel;
	private DsmModel dsmModel;
	/**
	 * item list -> those which has a an action
	 */
	private demoController(){ }
	
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
	 * 필요없음 곧 지울듯 
	 * @param frame
	 * @param model
	 */
	public void set(Frame frame,Model model){
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
	public void setTableModel(MyTableModel model){
		this.TableModel=model;
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
		 * 하는일 
		 * 1 일단 dsmModel에 모든 정보를 저장한다
		 * 2 그다음에 leftpanel의 tree에 정보저장
		 * 3 제일 기본은 root에 다가 넣고
		 * 4 rightpanel을 leftpanel을 기준으로 update
		 * rightpanel은 leftpanel의 영향만 받음
		 */
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		
		/** filefilter는 보류*/
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
						temp=buffer.readLine().split(" ");
						dsmModel.setData(i,temp);
					}
					for(int i=0; i< Integer.parseInt(s); i++){
						
						dsmModel.setName(buffer.readLine());
					}
					dsmModel.print();
	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(line);
				
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
		 * leftpanel 없데이트
		 */
		MyTreeModel asd = new MyTreeModel();
		asd.setNode(dsmModel);
		leftPanel.add(asd);
		leftPanel.revalidate();
	}
	public void saveDsm(){
		System.out.println("controller savedsm");
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
	}
	public void showRowLabels(){	
		System.out.println("controller showRL");
	}
}
