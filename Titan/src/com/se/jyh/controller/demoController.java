package com.se.jyh.controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.se.jyh.model.DsmModel;
import com.se.jyh.model.GroupModel;
import com.se.jyh.model.Table;
import com.se.jyh.model.Tree;
import com.se.jyh.viewComponent.Frame;
import com.se.jyh.viewComponent.LeftPanel;
import com.se.jyh.viewComponent.RightPanel;
import com.se.jyh.viewComponent.leftPanelCommand.AddCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.CollapseAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.DeleteCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.ExpandAllCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.GroupCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveDownCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.MoveUpCommand_impl;
import com.se.jyh.viewComponent.leftPanelCommand.UnGroupCommand_impl;

/**
 * 
 * @author lgpc Controller
 * 
 *         im thinking about makeing a factory register class -> this factory
 *         only do set and get
 */
public class demoController implements TreeSelectionListener {

	/**
	 * fork from parent
	 */
	private DefaultMutableTreeNode SharedNode;

	/**
	 * when i am a parent fork to child
	 */
	private DefaultMutableTreeNode localNode;
	private DefaultMutableTreeNode pointNode;
	/**
	 * 
	 */
	private demoController childController = null;
	private demoController parentController = null;
	/**
	 * main frame
	 */
	private Frame frame;
	private JFileChooser Dsmfc;
	private File Dsmfile;
	private File cluFile;
	private JScrollPane scrollPane;
	private DefaultMutableTreeNode clusterRoot;
	/*
	 * dsmModel -> file로 부터 입력받음
	 */
	private DsmModel dsmModel;
	/**
	 * tableModel -> tree랑 dsmModel의 영향만 받는다
	 */
	private Table tableModel;

	/**
	 * treeModel -> 외부에서 넣어줌
	 */
	private Tree treeModel = null;
	/**
	 * leftPanel -> controller에 등록
	 */
	private LeftPanel leftPanel;
	/**
	 * rightPanel -> controller에 등록
	 */
	private RightPanel rightPanel;
	private int checkLabel = 0; 

	/**
	 * leftmenu items 바뀌어야 하기때문에 등록된다.
	 */
	private AddCommand_impl add;
	private CollapseAllCommand_impl collapseAll;
	private DeleteCommand_impl delete;
	private ExpandAllCommand_impl expand;
	private MoveDownCommand_impl moveDown;
	private MoveUpCommand_impl moveUp;
	private GroupCommand_impl group;
	private UnGroupCommand_impl unGroup;

	/**
	 * 생성자
	 */
	public demoController() {

		this.dsmModel = new DsmModel();
		scrollPane = new JScrollPane();
		Dsmfc = new JFileChooser();

		Dsmfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Dsm file (*.dsm)", "dsm");
		Dsmfc.setFileFilter(filter);
	}

	/**
	 * 
	 * getter setter
	 * 
	 */

	public Frame getFrame() {
		return frame;
	}

	public demoController getChildController() {
		return childController;
	}

	public void setChildController(demoController childController) {
		this.childController = childController;
	}

	public demoController getParentController() {
		return parentController;
	}

	public void setParentController(demoController parentController) {
		this.parentController = parentController;
	}

	public DefaultMutableTreeNode getSharedNode() {
		return SharedNode;
	}

	public void setSharedNode(DefaultMutableTreeNode sharedNode) {
		SharedNode = sharedNode;
	}

	public DefaultMutableTreeNode getLocalNode() {
		return localNode;
	}

	public void setLocalNode(DefaultMutableTreeNode localNode) {
		this.localNode = localNode;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public DsmModel getDsmModel() {
		return dsmModel;
	}

	public void setDsmModel(DsmModel dsmModel) {
		this.dsmModel = dsmModel;
	}

	public Tree getTreeModel() {
		return treeModel;
	}

	public void setTreeModel(Tree dupliTree) {
		this.treeModel = dupliTree;
	}

	public LeftPanel getLeftPanel() {
		return leftPanel;
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}

	public void setLeftPanel(LeftPanel leftPanel) {
		this.leftPanel = leftPanel;
	}

	public void setRightPanel(RightPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	

	public void setTableModel(Table model) {
		this.tableModel = model;
	}

	public void setCollapseAll(CollapseAllCommand_impl collapseAll) {
		this.collapseAll = collapseAll;
	}

	public void setDelete(DeleteCommand_impl delete) {
		this.delete = delete;
	}

	public void setExpand(ExpandAllCommand_impl expand) {
		this.expand = expand;
	}

	public void setMoveDown(MoveDownCommand_impl moveDown) {
		this.moveDown = moveDown;
	}

	public void setMoveUp(MoveUpCommand_impl moveUp) {
		this.moveUp = moveUp;
	}

	public void setGroup(GroupCommand_impl group) {
		this.group = group;
	}

	public void setUnGroup(UnGroupCommand_impl unGroup) {
		this.unGroup = unGroup;
	}

	public CollapseAllCommand_impl getCollapseAll() {
		return collapseAll;
	}

	public DeleteCommand_impl getDelete() {
		return delete;
	}

	public ExpandAllCommand_impl getExpand() {
		return expand;
	}

	public MoveDownCommand_impl getMoveDown() {
		return moveDown;
	}

	public MoveUpCommand_impl getMoveUp() {
		return moveUp;
	}

	public GroupCommand_impl getGroup() {
		return group;
	}

	public UnGroupCommand_impl getUnGroup() {
		return unGroup;
	}

	public void setAdd(AddCommand_impl addCommand_impl) {
		// TODO Auto-generated method stub
		this.add = addCommand_impl;
	}

	public AddCommand_impl getAdd() {
		// TODO Auto-generated method stub
		return this.add;
	}

	/**
	 * 
	 * 
	 * all Actions
	 * 
	 * 
	 * 
	 */
	public void exitDsm() {
		System.out.println("controller exitdsm");
	}

	public void loadCluster() {
		
		if(this.Dsmfile==null){
			return;
		}
		System.out.println("controller loadCluster");
		
		JFileChooser jfc = new JFileChooser(new File("."));

		jfc.setDialogTitle("Open a File");
		
		jfc.setFileFilter(new FileFilter() {
			public String getDescription() { return "Clsx Files(*.clsx)";	}
			public boolean accept(File f) {	return f.isDirectory() || f.getName().contains(".clsx");}
		});
		
		int result = jfc.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			
			File nowFile = jfc.getSelectedFile();
			
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = null;
			
			try {
				docBuild = docBuildFact.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
			Document doc = null;
			
			try {
				doc = docBuild.parse(nowFile);
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doc.getDocumentElement().normalize();
			
			//this.treeModel = new Tree(new DefaultMutableTreeNode("$root"));
			
			//root.removeAllChildren();
			
			Node cluster = doc.getFirstChild();
			Node docRoot = cluster.getFirstChild().getNextSibling();
			
			DefaultMutableTreeNode root =new DefaultMutableTreeNode(docRoot.getAttributes().getNamedItem("name").getNodeValue());//새로만들 트리 루트
			
			NodeList start = docRoot.getChildNodes();
			//this.group(docRoot);
			for(int i=0;i<start.getLength();i++){
				Node p=start.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element node = (Element) p;
					NodeList check=p.getChildNodes();
					if(check.getLength()==0){
						/**
						 * child이다 
						 */
						System.out.println(node.getAttribute("name"));
						root.add(new DefaultMutableTreeNode(node.getAttribute("name")));
					}else{
						/**
						 * group이다
						 */
						System.out.println("<group--------------");
						System.out.println(node.getAttribute("name"));
						DefaultMutableTreeNode temp = new DefaultMutableTreeNode(node.getAttribute("name"));
						//System.out.println(temp.getChildCount());
						root.add(this.groupNode(temp,p));
						System.out.println("-----------------group>");
					}
				}
			}
			
			this.treeModel = new Tree(root);
			DefaultTreeModel model = (DefaultTreeModel) this.treeModel.getModel();
			//System.out.println(root.getLevel());
			//model.reload();
			this.leftPanel.remove(1);
			this.leftPanel.add(new JScrollPane(this.treeModel));
			treeModel.addTreeSelectionListener(this);
			treeModel.addMouseListener(this.leftPanel);
			this.collapseAll.notifyAction(true);
			this.expand.notifyAction(true);
			this.add.notifyAction(true);
			model.reload();
			this.leftPanel.revalidate();
		}
		
	}
	
	public DefaultMutableTreeNode groupNode(DefaultMutableTreeNode tempNode,Node tempRootNode){
		
		NodeList start = tempRootNode.getChildNodes();
		
		for(int i=0;i<start.getLength();i++){
			Node p=start.item(i);
			if(p.getNodeType()==Node.ELEMENT_NODE){
				Element node = (Element) p;
				NodeList check=p.getChildNodes();
				if(check.getLength()==0){
					/**
					 * child이다 
					 */
					System.out.println(node.getAttribute("name"));
					tempNode.add(new DefaultMutableTreeNode(node.getAttribute("name")));
				}else{
					/**
					 * group이다
					 */
					System.out.println(node.getAttribute("name"));
					DefaultMutableTreeNode temp = new DefaultMutableTreeNode(node.getAttribute("name"));
					tempNode.add(this.groupNode(temp,p));
				}
			}
		}
		
		//System.out.println(tempNode.getChildCount());
		
		return tempNode;
	}
	public void newDsm() {
		
		this.Dsmfile=null;
		System.out.println("controller newdsm");
		/**
		 * new dsmName 받기
		 */
		
		String dsmName = JOptionPane
				.showInputDialog(null, "Enter new DsmName:");

		if (dsmName == null)
			return;

		String numberOfRow = JOptionPane.showInputDialog(null,
				"Enter the number of row:");

		if (numberOfRow == null)
			return;

		JTextField rowList[] = new JTextField[Integer.parseInt(numberOfRow)];
		JLabel rowNumber[] = new JLabel[Integer.parseInt(numberOfRow)];

		for (int i = 0; i < Integer.parseInt(numberOfRow); i++) {
			rowList[i] = new JTextField();
			rowNumber[i] = new JLabel("New Eentity " + (i + 1) + " : ");
		}

		JPanel namePane = new JPanel(new GridLayout(
				Integer.parseInt(numberOfRow), 2));
		JScrollPane namePanel = new JScrollPane(namePane);// ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
															// JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		namePanel.setPreferredSize(new Dimension(400, 250));
		for (int i = 0; i < Integer.parseInt(numberOfRow); i++) {
			namePane.add(rowNumber[i]);
			namePane.add(rowList[i]);
		}
		int answer = JOptionPane.showConfirmDialog(null, namePanel,
				"Put the name of row", JOptionPane.OK_CANCEL_OPTION);
		//JOptionPane.YES_NO_CANCEL_OPTION
		
		if (answer != JOptionPane.OK_OPTION)
			return;
		JCheckBox[][] checkbox = new JCheckBox[Integer.parseInt(numberOfRow)][Integer
				.parseInt(numberOfRow)];
		JPanel checkPanel = new JPanel(new GridLayout(
				Integer.parseInt(numberOfRow),
				Integer.parseInt(numberOfRow) + 1));
		// checkPanel.setPreferredSize(new Dimension(400,400));

		for (int i = 0; i < Integer.parseInt(numberOfRow); i++) {
			for (int j = 0; j < Integer.parseInt(numberOfRow); j++) {
				checkbox[i][j] = new JCheckBox();
			}
		}
		for (int i = 0; i < Integer.parseInt(numberOfRow); i++) {
			JLabel temp = new JLabel("row" + (i + 1));
			temp.setPreferredSize(new Dimension(50, 20));
			checkPanel.add(temp);
			for (int j = 0; j < Integer.parseInt(numberOfRow); j++) {
				checkPanel.add(checkbox[i][j]);
			}
		}
		JScrollPane checkScrollPanel = new JScrollPane(checkPanel);// ,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		checkScrollPanel.setPreferredSize(new Dimension(600, 400));
		answer = JOptionPane.showConfirmDialog(null, checkScrollPanel,
				"check the Dependency", JOptionPane.OK_CANCEL_OPTION);
		if (answer != JOptionPane.OK_OPTION)
			return;

		/**
		 * check box-> 1, 0 으로 변환하고 dsmModel에 add 해주기
		 * 
		 * 
		 */
		int[][] toThis = new int[checkbox.length][checkbox.length];

		for (int i = 0; i < checkbox.length; i++) {
			for (int j = 0; j < checkbox.length; j++) {
				if (checkbox[i][j].isSelected()) {
					toThis[i][j] = 1;
				} else {
					toThis[i][j] = 0;
				}
			}
		}

		if (this.treeModel != null) {
			treeModel.collapseAll();
			leftPanel.remove(leftPanel.getComponent(1));
		}
		this.dsmModel = new DsmModel();

		for (int i = 0; i < checkbox.length; i++) {
			dsmModel.addDsmRow(rowList[i].getText(), toThis[i],i);
		}

		dsmModel.print();

		this.treeModel = new Tree(new DefaultMutableTreeNode("$root"));
		this.tableModel = new Table();

		treeModel.initNode(dsmModel);
		treeModel.addTreeSelectionListener(this);
		treeModel.addMouseListener(this.leftPanel);
		this.collapseAll.notifyAction(true);
		this.expand.notifyAction(true);
		this.add.notifyAction(true);
		/**
		 * 만들어진 treemodel을 leftpanel에 넣기
		 */
		leftPanel.add(new JScrollPane(treeModel));
		// leftPanel.add(treeModel);
		leftPanel.revalidate();
		/**
		 * table은 tree model로 부터 결정됨 table에 tree model 넣기
		 */
		
		tableModel.setModel(treeModel,dsmModel);
		this.rightPanel.setTableModel(tableModel);
		Dsmfile=null;
	}

	public void newCluster() {
		System.out.println("controller newcluster");

		treeModel.treeinitialization();
		treeModel.initNode(dsmModel);
		
		tableModel.setModel(treeModel,dsmModel);
		
		DefaultTreeModel asd = (DefaultTreeModel) treeModel.getModel();
		asd.reload();
		leftPanel.revalidate();	
	}

	public void openDsm() {
		System.out.println("controller opendsm");

		if (this.treeModel != null) {
			treeModel.collapseAll();
			leftPanel.remove(this.leftPanel.getComponent(1));
		}

		/**
		 * 하는일 1 일단 dsmModel에 모든 정보를 저장한다 2 그다음에 leftpanel의 tree에 정보저장 3 제일 기본은
		 * root에 다가 넣고 4 rightpanel을 leftpanel을 기준으로 update rightpanel은
		 * leftpanel의 영향만 받음
		 */
		// JFileChooser fc = new JFileChooser();

	
		/** filefilter는 보류 */
		// fc.setFileFilter(new FileFilter(".dsm"));

		Dsmfc.setDialogTitle("Open a File");
		int result = Dsmfc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			this.dsmModel = new DsmModel();

			// fc.getSelectedFile().getPath()
			Dsmfile = Dsmfc.getSelectedFile();
			try {
				BufferedReader buffer = new BufferedReader(new FileReader(Dsmfile.getPath()));
				String line = "";
				String s = "";
				try {
					s = buffer.readLine();

					dsmModel.setSize(Integer.parseInt(s));
					String[] temp;

					for (int i = 0; i < Integer.parseInt(s); i++) {
						dsmModel.set(buffer.readLine(),i);
					}
					for (int i = 0; i < Integer.parseInt(s); i++) {
						dsmModel.setName(buffer.readLine(), i);
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(line);

				if (buffer != null) {
					try {
						buffer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// buffer.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}

			/**
			 * dsmModel을 기반으로해서 treeModel을 만듬
			 */
			this.tableModel = new Table();
			this.treeModel = new Tree(new DefaultMutableTreeNode("$root"));
			treeModel.initNode(dsmModel);
			treeModel.addTreeSelectionListener(this);
			treeModel.addMouseListener(this.leftPanel);
			this.collapseAll.notifyAction(true);
			this.expand.notifyAction(true);
			this.add.notifyAction(true);
			/**
			 * 만들어진 treemodel을 leftpanel에 넣기
			 */
			leftPanel.add(new JScrollPane(treeModel));
			// leftPanel.add(treeModel);
			leftPanel.revalidate();
			//DefaultTreeModel asd = (DefaultTreeModel) treeModel.getModel();
			//asd.reload();
			/**
			 * table은 tree model로 부터 결정됨 table에 tree model 넣기
			 */
			System.out.println("treeModel");
			tableModel.setModel(treeModel,dsmModel);
			this.redraw();
		}
	}

	public void saveDsm() {
		
		if (Dsmfile != null) {
			if (Dsmfile == Dsmfc.getSelectedFile()) {
				System.out.println("그냥 save");
				FileWriter fw;
				try {
					fw = new FileWriter(Dsmfile.getPath());

					if (this.dsmModel == null)
						return;

					fw.write(this.dsmModel.getSize() + "\n");

					for (int i = 0; i < this.dsmModel.getSize(); i++) {
						for (int j = 0; j < this.dsmModel.getSize(); j++) {

							fw.write(Integer.toString(this.dsmModel
									.getDependencyData_arr().get(i).getData()
									.get(j)));

							if (j == this.dsmModel.getSize() - 1) {

							} else {
								fw.write(" ");
							}
						}
						fw.write("\n");
					}

					for (int i = 0; i < this.dsmModel.getSize(); i++) {
						fw.write((String) this.dsmModel.getDependencyData_arr()
								.get(i).getName());
						fw.write("\n");
					}

					fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				this.saveAsDsm();
			}
		}else {
			this.saveAsDsm();
		}

	}

	public void saveAsDsm() {
		System.out.println("controller saveasdsm");

		Dsmfc.setDialogTitle("Save a file");

		int returnVal = Dsmfc.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Dsmfile = Dsmfc.getSelectedFile();
			try {
				System.out.println("asd");
				FileWriter fw = new FileWriter(Dsmfile.getPath());
				if (this.dsmModel == null)
					return;

				fw.write(this.dsmModel.getSize() + "\n");

				for (int i = 0; i < this.dsmModel.getSize(); i++) {
					for (int j = 0; j < this.dsmModel.getSize(); j++) {

						fw.write(Integer.toString(this.dsmModel
								.getDependencyData_arr().get(i).getData()
								.get(j)));

						if (j == this.dsmModel.getSize() - 1) {

						} else {
							fw.write(" ");
						}
					}
					fw.write("\n");
				}

				for (int i = 0; i < this.dsmModel.getSize(); i++) {
					fw.write((String) this.dsmModel.getDependencyData_arr()
							.get(i).getName());
					fw.write("\n");
				}

				fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void saveCluster() {
		System.out.println("controller savecluster");
		//File clufc;
		try {
		
			if(cluFile == null) //파일 새로 만들어줘야함
			{
					JFileChooser clufc = new JFileChooser(new File("."));
					clufc.setDialogTitle("Save a cluster");
			
					clufc.setFileFilter(new FileFilter() {
						public String getDescription() { return "Clsx Files(*.clsx)";	}
						public boolean accept(File f) {	return f.isDirectory() || f.getName().contains(".clsx");}
					});
					
					
					int returnVal = clufc.showSaveDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION)
					{
						cluFile = new File( clufc.getSelectedFile().getAbsolutePath() + ".clsx" );
						System.out.println("file 선택됨");
					} else return;
			
			}
		
			DefaultMutableTreeNode previousNode, nowNode, lastLeaf;
			
			FileWriter fw = new FileWriter(cluFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			String output = new String("");
			String EOL = System.getProperty("line.separator");

			output += "<cluster>";
			output += EOL;
				
			nowNode = treeModel.getRoot();
			previousNode = nowNode;
			lastLeaf = nowNode;
			
			output += groupName(nowNode.toString());
			output += EOL;
			
			nowNode = nowNode.getNextNode();
			lastLeaf = lastLeaf.getLastLeaf();
			

			while (true) {
				if (nowNode.isLeaf()) 
				{
					output += leafName(nowNode.toString());
					previousNode = nowNode;
					nowNode = nowNode.getNextNode();
					// Last Leaf check
					if (nowNode == lastLeaf) {
						output += (EOL + leafName(nowNode.toString()));
						for (int i = 0; i < nowNode.getLevel(); i++)
							output += EOL + "</group>";
						output += EOL + "</cluster>";
						break;
					}else if (!previousNode.isNodeSibling(nowNode)) {
						for (int i = 0; i < (previousNode.getLevel() - nowNode.getLevel()); i++)
							output += EOL + "</group>";	
					}

				} else if (!nowNode.isLeaf()) {
					output += groupName(nowNode.toString());
					previousNode = nowNode;
					nowNode = nowNode.getNextNode();

					if (nowNode.isLeaf()) {

					}
				}
				output += EOL;
			}
		
			
			bw.write(output);
			bw.close();
		
			
		}catch (IOException ex)
			{
				JOptionPane.showMessageDialog(null, "There is not file to save");			
			}
		
	}

	public void saveClusterAs() {
		System.out.println("controller saveclusteras");
			
		cluFile = null;
		
		System.out.println("cluFile에 null 들어감");
		
		saveCluster();
		System.out.println("SaveClusterAs 안의 SaveCluster 실행됨");
		
		
			
	}
	
	public String groupName(String nodename) {

		String GroupNode;
		GroupNode = "<group name=\"" + nodename + "\">";
		return GroupNode;

	}

	public String leafName(String nodename) {
		String LeafNode;

		LeafNode = "<item name=\"" + nodename + "\"/>";
		return LeafNode;
	}
	
	public void help() {
		System.out.println("controller help");
		JOptionPane asd=new JOptionPane("Help");
		asd.showMessageDialog(null, "2015-SE\n 김희주 \n 신윤호 \n 이준범");
	}

	public void redraw() {
		//System.out.println("controller redraw");
		
		/**
		 * groupNodelist는 그룹으로 묵여져 있는 애를 가지고 있다.
		 */
		this.rightPanel.clear();
		this.tableModel.clear();
		
		List<GroupModel> groupList = new ArrayList<GroupModel>();
		List<DefaultMutableTreeNode> groupNode = new ArrayList<DefaultMutableTreeNode>();
		List<Integer> willBePrintedNodeNum = new ArrayList<Integer>();
		List<String> willBePrinttedRowName = new ArrayList<String>();
		
		/**
		 * groupList에는 group의 위치 number랑 그룹에 속해있는 node의 number를 집어 넣는다.
		 */
		DefaultMutableTreeNode currentNode = this.treeModel.getRoot();
		
		int nowLevel=0;
		
		while(currentNode!=null){
			/**
			 * expanded면
			 */
			if(currentNode.getChildCount()>0){
				if (this.treeModel.isExpanded(new TreePath(currentNode.getPath()))) {
					
					if(currentNode.getChildCount()==0){
						for(int i=0;i<this.dsmModel.getDependencyData_arr().size();i++){
							if(currentNode.toString().equals(this.dsmModel.getDependencyData_arr().get(i).getName())){
								willBePrintedNodeNum.add(i);
								willBePrinttedRowName.add(currentNode.toString());
								break;
							}
						}
						currentNode = currentNode.getNextNode();
					}else{
						currentNode= currentNode.getNextNode();
					}
				} else {
					/**
					 * group이 된 애들
					 */
					willBePrintedNodeNum.add(-1);
					willBePrinttedRowName.add(currentNode.toString());
					/**
					 * -1은 group이라는 뜻
					 */
					groupNode.add(currentNode);
					
					//if(currentNode.getParent()==currentNode.getNex)
					
					if(currentNode==this.treeModel.getRoot()){
						currentNode=null;
						break;
					}
					if(currentNode.getNextNode()==null){
						currentNode=null;
						break;
					}
					
					int standardLevel=currentNode.getLevel();
					int check=0;
					if(currentNode.getNextSibling()==null){
						currentNode=currentNode.getNextNode();
						while(currentNode.getLevel()>=standardLevel){
							
							currentNode=currentNode.getNextNode();
							if(currentNode==null){
								check=1;
								break;
							}
						}
						//currentNode=null;
						if(check==1){
							currentNode=null;
						}
						continue;
					}
					currentNode=currentNode.getNextSibling();
				}
				
			}else{
				
				for(int i=0;i<this.dsmModel.getDependencyData_arr().size();i++){
					if(currentNode.toString().equals(this.dsmModel.getDependencyData_arr().get(i).getName())){
						willBePrintedNodeNum.add(i);
						willBePrinttedRowName.add(currentNode.toString());
						break;
					}
				}
				currentNode = currentNode.getNextNode();
			}
		}
			
		for(int i=0;i<willBePrintedNodeNum.size();i++){
			System.out.print(willBePrintedNodeNum.get(i)+" : ");
			System.out.println(willBePrinttedRowName.get(i));
		}
		
		for(int i=0;i<groupNode.size();i++){
			GroupModel group= new GroupModel();
			
			//System.out.println(groupNode.toString());
			group.setName(groupNode.get(i).toString());
			
			for(int k=0;k<groupNode.get(i).getChildCount();k++){
				for(int j=0;j<this.dsmModel.getDependencyData_arr().size();j++){
					if(groupNode.get(i).getChildAt(k).toString().equals(this.dsmModel.getDependencyData_arr().get(j).getName())){
						group.getChildNumber().add(j);
						break;
					}
				}
			}
			groupList.add(group);
		}
		
		/*for(int i=0;i<groupList.size();i++){
			System.out.print(groupList.get(i).getName()+" has ");
			for(int j=0;j<groupList.get(i).getChildNumber().size();j++){
				System.out.print(groupList.get(i).getChildNumber().get(j)+" ");
			}
			System.out.println(" ");
		}*/
		
		Object[][] tempDsmModel = new Object[this.dsmModel.getDependencyData_arr().size()][this.dsmModel.getDependencyData_arr().size()];
		for(int i=0;i<this.dsmModel.getDependencyData_arr().size();i++){
			for(int j=0;j<this.dsmModel.getDependencyData_arr().size();j++){
				tempDsmModel[i][j]=this.dsmModel.getDependencyData_arr().get(i).getData().get(j);
			}
		}
		
		/*for(int i=0;i<this.dsmModel.getDependencyData_arr().size();i++){
			for(int j=0;j<this.dsmModel.getDependencyData_arr().size();j++){
				System.out.print(tempDsmModel[i][j]);
			}
			System.out.println(" ");
		}*/
		System.out.println("---------------------------------");
		Object[][] asd=new Object[willBePrintedNodeNum.size()][willBePrintedNodeNum.size()+1];
		int grouprownum=0;
		for(int i=0;i<willBePrintedNodeNum.size();i++){
			
			int groupcolnum=0;
			asd[i][0]=i+". "+willBePrinttedRowName.get(i);
			//System.out.println(asd[i][0]);
			for(int j=0;j<willBePrintedNodeNum.size();j++){
				/**
				 * 4가지 경우 둘다 그룹, row가 그룹, col이 그룹, 다 그룹 아님 
				 */
				if(willBePrintedNodeNum.get(i)==-1&&willBePrintedNodeNum.get(j)==-1){
					int check=0;
					for(int k=0;k<groupList.get(grouprownum).getChildNumber().size();k++){
						for(int m=0;m<groupList.get(groupcolnum).getChildNumber().size();m++){
							if((int)tempDsmModel[groupList.get(grouprownum).getChildNumber().get(k)][groupList.get(groupcolnum).getChildNumber().get(m)]==1){
								check=1;
							}
						}
					}
					asd[i][j+1]=check;
					groupcolnum++;
				}else if (willBePrintedNodeNum.get(i)==-1){//row가 그룹 
					int check=0;
					for(int k=0;k<groupList.get(grouprownum).getChildNumber().size();k++){
						if((int)tempDsmModel[groupList.get(grouprownum).getChildNumber().get(k)][willBePrintedNodeNum.get(j)]==1){
							check=1;
							break;
						}
					}
					
					//groupList.re
					asd[i][j+1]=check;
				}else if (willBePrintedNodeNum.get(j)==-1){//col이 그룹
					int check=0;
					for(int k=0;k<groupList.get(groupcolnum).getChildNumber().size();k++){
						if((int)tempDsmModel[willBePrintedNodeNum.get(i)][groupList.get(groupcolnum).getChildNumber().get(k)]==1){
							check=1;
							break;
						}
					}
					groupcolnum++;
					asd[i][j+1]=check;
				}else{
					asd[i][j+1]=tempDsmModel[willBePrintedNodeNum.get(i)][willBePrintedNodeNum.get(j)];
				}
			}
			
			if(willBePrintedNodeNum.get(i)==-1){
				grouprownum++;
			}
			groupcolnum=0;
			
		}
		
		
		for(int i=0;i<willBePrintedNodeNum.size();i++){
			for(int j=1;j<willBePrintedNodeNum.size()+1;j++){
				System.out.print(asd[i][j]);
			}
			System.out.println(" ");
		}
		
		this.tableModel.setData(asd);
		this.tableModel.setHeaders(asd.length);
		this.rightPanel.setTableModel(tableModel);
		this.rightPanel.revalidate();

	}
		/**
		 * 넘버 가져오기
		 */
		
		//leftPanel.revalidate();


	public void showRowLabels() {
		
		System.out.println("controller showRL");
		
		if(this.checkLabel==0){
			DefaultTreeModel model =(DefaultTreeModel) this.treeModel.getModel();
			
			Object[][] temp=this.tableModel.getData();
			
			for(int i=0;i<tableModel.getColumnCount()-1;i++){
				temp[i][0]=i;
			}
			
			
			
			this.rightPanel.showRowLabel();
			
			for(int i=0;i<tableModel.getColumnCount()-1;i++){
				this.tableModel.fireTableCellUpdated(i, 0);
			}
			this.checkLabel=1;
		}else{
			this.redraw();
			this.checkLabel=0;
		}
		
	}

	/**
	 * action from leftpanel
	 */

	public void collapseAll() {
		// TODO Auto-generated method stub
		treeModel.collapseAll();
	}

	public void unGroup() {
		treeModel.removeTreeSelectionListener(this);
		treeModel.unGroup();
		treeModel.addTreeSelectionListener(this);
		this.update();
		this.updateSharedNode();
	}

	public void moveUp() {
		// TODO Auto-generated method stub
		System.out.println("move");
		treeModel.removeTreeSelectionListener(this);
		treeModel.moveUp();
		treeModel.addTreeSelectionListener(this);
		this.valueChanged(new TreeSelectionEvent(treeModel, treeModel
				.getSelectionPaths(), null, null, null));
		this.update();
		this.updateSharedNode();
	}

	public void moveDown() {

		treeModel.removeTreeSelectionListener(this);
		treeModel.moveDown();
		treeModel.addTreeSelectionListener(this);
		this.valueChanged(new TreeSelectionEvent(treeModel, treeModel
				.getSelectionPaths(), null, null, null));
		this.update();
		this.updateSharedNode();
	}

	public void group() {
		treeModel.removeTreeSelectionListener(this);
		String groupName = JOptionPane.showInputDialog(null, "Group Name:");
		treeModel.group(groupName);
		treeModel.addTreeSelectionListener(this);
		this.update();
		this.updateSharedNode();
	}

	public void expandAll() {
		// TODO Auto-generated method stub
		treeModel.expandAll();
	}

	public void delete() {

		treeModel.removeTreeSelectionListener(this);
		treeModel.delete();
		treeModel.addTreeSelectionListener(this);
		this.update();
		this.updateSharedNode();
	}

	public void rename() {
		// TODO Auto-generated method stub
		treeModel.removeTreeSelectionListener(this);

		String groupName = JOptionPane.showInputDialog(null,
				"Enter new group name:");
		treeModel.rename(groupName);

		treeModel.addTreeSelectionListener(this);
		this.update();
		this.updateSharedNode();
	}

	public void sort() {
		// TODO Auto-generated method stub
		System.out.println("sort");
		treeModel.removeTreeSelectionListener(this);
		treeModel.sort();
		treeModel.addTreeSelectionListener(this);
		this.update();
		this.updateSharedNode();
	}

	public void add() {

		/**
		 * new dsmName 받기
		 */
		String dsmName = JOptionPane.showInputDialog(null,
				"Enter new group name:");

		/*
		 * dependency check 하기
		 */
		JCheckBox[] checkbox = new JCheckBox[this.dsmModel.getSize()];

		/**
		 * toThis 는 다른 module이 newdsm에 의존하는 지
		 */
		int[] toThis = new int[checkbox.length + 1];
		/**
		 * fromThis는 이 module이 다른 module에 의존하는지 한칸 더 긴 이유는 추가 해주려고
		 */
		int[] fromThis = new int[checkbox.length];

		/**
		 * checkbox에 이름정해주고
		 */
		for (int i = 0; i < checkbox.length; i++) {
			checkbox[i] = new JCheckBox(this.dsmModel.getDependencyData_arr()
					.get(i).getName());
		}

		JPanel layoutPanel = new JPanel(new GridLayout(checkbox.length, 1));
		for (JCheckBox c : checkbox) {
			layoutPanel.add(c);
		}
		/**
		 * scroller 판넬 설정해주기
		 */
		JScrollPane scroller = new JScrollPane(layoutPanel);
		scroller.setPreferredSize(new Dimension(400, 500));
		int answerTo = JOptionPane.showConfirmDialog(null, scroller,
				"Check the dependency to this module",
				JOptionPane.OK_CANCEL_OPTION);

		if (answerTo == JOptionPane.OK_OPTION) {

			for (int i = 0; i < checkbox.length; i++) {
				if (checkbox[i].isSelected()) {
					toThis[i] = 1;
				} else {
					toThis[i] = 0;
				}
				checkbox[i].setSelected(false);
			}
			int answerFrom = JOptionPane.showConfirmDialog(null, scroller,
					"Check the dependency from this module",
					JOptionPane.OK_CANCEL_OPTION);
			if (answerTo == JOptionPane.OK_OPTION) {
				for (int i = 0; i < checkbox.length; i++) {
					if (checkbox[i].isSelected()) {
						fromThis[i] = 1;
					} else {
						fromThis[i] = 0;
					}
					checkbox[i].setSelected(false);
				}
				/**
				 * 새로 만들어인 dsmrow -> 1010011000001 이렇게 fromThis를 넣어준다.
				 */
				dsmModel.addDsmRow(dsmName, fromThis,dsmModel.getSize());
				this.dsmModel.addDsmCol(toThis);

				treeModel.addNode(this.dsmModel);
			}
		}
		this.dsmModel.print();
		this.update();
		this.updateSharedNode();
	}

	public void duplicate() {

		/**
		 * 새로운 root 가될 node의 카피버전 가져오기
		 */

		DefaultMutableTreeNode newRootNode = treeModel.getGroupNode();
		demoController dupliController = new demoController();

		Tree dupliTree = new Tree(newRootNode);

		Frame dupliFrame = new Frame(dupliController);

		dupliController.setTreeModel(dupliTree);
		dupliController.setDsmModel(dsmModel);
		dupliTree.addTreeSelectionListener(dupliController);
		dupliTree.addMouseListener(dupliController.leftPanel);

		dupliController.collapseAll.notifyAction(true);
		dupliController.expand.notifyAction(true);
		dupliController.add.notifyAction(true);
		dupliController.setTableModel(new Table());
		
		dupliController.getLeftPanel().add(new JScrollPane(dupliTree));
		dupliController.getLeftPanel().revalidate();

	}

	public DefaultMutableTreeNode setPointNode(DefaultMutableTreeNode point) {
		DefaultMutableTreeNode currentNode = treeModel.getRoot();

		while (currentNode != null) {
			if (currentNode.toString().equals(point.toString())) {
				return point;
			}
			currentNode = currentNode.getNextNode();
		}
		return null;
	}

	public void fork() {

		demoController dupliController = new demoController();
		DefaultMutableTreeNode newRootNode = treeModel.getGroupNode();
		/**
		 * node share
		 */
		this.SharedNode = newRootNode;
		this.pointNode = this.setPointNode(this.SharedNode);
		dupliController.setLocalNode(newRootNode);
		
		/**
		 * controller share
		 */
		
		this.setChildController(dupliController);
		dupliController.setParentController(this);

		Tree dupliTree = new Tree(newRootNode);

		Frame dupliFrame = new Frame(dupliController);
		
		dupliFrame.disableFileMenu();
		
		
		dupliController.setTreeModel(dupliTree);
		dupliController.setDsmModel(dsmModel);
		
		dupliTree.addTreeSelectionListener(dupliController);
		dupliTree.addMouseListener(dupliController.leftPanel);

		dupliController.collapseAll.notifyAction(true);
		dupliController.expand.notifyAction(true);
		dupliController.add.notifyAction(true);
		dupliController.setTableModel(new Table());

		dupliController.getLeftPanel().add(new JScrollPane(dupliTree));
		dupliController.getLeftPanel().revalidate();
		
		dupliController.update();
		dupliController.updateSharedNode();

	}

	public void copyFromChild() {
		
		DefaultMutableTreeNode newCopy = new DefaultMutableTreeNode(
				this.SharedNode.toString());
		DefaultMutableTreeNode currentNode = this.SharedNode;

		currentNode = currentNode.getNextNode();

		while (currentNode != null) {
			DefaultMutableTreeNode temp = null;
			if (currentNode.getChildCount() > 0) { // 리커시브
				temp = this.getParentNode(currentNode);
			} else { // 그냥 복사
				temp = new DefaultMutableTreeNode(currentNode.toString());
			}
			newCopy.add(temp);
			currentNode = currentNode.getNextSibling();
		}
		currentNode = this.treeModel.getRoot();

		while (currentNode != null) {
			if (currentNode.toString() == this.pointNode.toString()) {
				break;
			}
			currentNode = currentNode.getNextNode();
		}
		DefaultTreeModel model = (DefaultTreeModel) this.treeModel.getModel();

		model.insertNodeInto(newCopy,
				(MutableTreeNode) currentNode.getParent(), currentNode
						.getParent().getIndex(currentNode));
		this.treeModel.expandPath(new TreePath(model.getChild(
				(MutableTreeNode) currentNode.getParent(), currentNode
						.getParent().getIndex(currentNode) - 1)));
		model.removeNodeFromParent(currentNode);

		this.pointNode = newCopy;
	}

	public DefaultMutableTreeNode getParentNode(
			DefaultMutableTreeNode currentNode) {

		DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(
				currentNode.toString());
		currentNode = currentNode.getNextNode();

		while (currentNode != null) {

			DefaultMutableTreeNode temp = null;

			if (currentNode.getChildCount() > 0) { // 리커시브
				temp = this.getParentNode(currentNode);
			} else { // 그냥 복사
				temp = new DefaultMutableTreeNode(currentNode.toString());
			}
			parentNode.add(temp);
			currentNode = currentNode.getNextSibling();
		}

		return parentNode;
	}

	
	public void update() {
		
		if (this.parentController != null) {
			System.out.println("copy");
			this.parentController.copyFromChild();
			this.parentController.update();
		}
		if (this.childController != null) {
			System.out.println("asd");
			this.childController.updateSharedNode();
			DefaultTreeModel model = (DefaultTreeModel) this.childController.treeModel
					.getModel();
			model.reload();
		}
		
	}

	public void updateSharedNode() {
		
		DefaultMutableTreeNode newSharedNode = null;
		if (this.SharedNode != null) {
			DefaultMutableTreeNode currentNode = this.treeModel.getRoot();
			while (currentNode != null) {
				if (currentNode.toString().equals(this.pointNode.toString())) {
					newSharedNode = new DefaultMutableTreeNode(
							currentNode.toString());
					break;
				}
				currentNode = currentNode.getNextNode();
			}

			currentNode = currentNode.getNextNode();

			while (currentNode != null) {
				DefaultMutableTreeNode temp = null;
				if (currentNode.getChildCount() > 0) { // 리커시브
					temp = this.getParentNode(currentNode);
				} else { // 그냥 복사
					temp = new DefaultMutableTreeNode(currentNode.toString());
				}
				newSharedNode.add(temp);
				currentNode = currentNode.getNextSibling();
			}

			this.SharedNode = newSharedNode;
			DefaultTreeModel model = (DefaultTreeModel) this.childController
					.getTreeModel().getModel();
			this.childController.getTreeModel().getRoot()
					.setUserObject(this.SharedNode.toString());
			model.setRoot(this.SharedNode);
			model.reload();
		}

		
	}

	private void copyFromParent() {

	}

	/**
	 * icon 변경과 관련됨 함수들
	 */

	@Override
	public void valueChanged(TreeSelectionEvent e) {

		JTree tree = (JTree) e.getSource();
		TreePath[] paths = tree.getSelectionPaths();
		DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel
				.getRoot();

		List<DefaultMutableTreeNode> selectionList = new ArrayList<DefaultMutableTreeNode>();
		if (paths == null)
			return;
		for (int i = 0; i < paths.length; i++) {
			selectionList.add((DefaultMutableTreeNode) paths[i]
					.getLastPathComponent());
		}
		if (selectionList.get(0) == treeModel.getRoot()) {
			this.delete.notifyAction(false);
			this.moveDown.notifyAction(false);
			this.moveUp.notifyAction(false);
			this.group.notifyAction(false);
			this.unGroup.notifyAction(false);
		} else {
			this.delete.notifyAction(this.checkDelete(selectionList));
			this.moveDown.notifyAction(this.checkMoveDown(selectionList));
			this.moveUp.notifyAction(this.checkMoveUp(selectionList));
			this.group.notifyAction(this.checkGroup(selectionList));
			this.unGroup.notifyAction(this.checkUnGroup(selectionList));
		}

	}

	public boolean checkMoveUp(List<DefaultMutableTreeNode> selectionList) {
		DefaultMutableTreeNode tempNode;

		tempNode = selectionList.get(0);

		for (int i = 1; i < selectionList.size(); i++) {
			if (!tempNode.isNodeSibling(selectionList.get(i))) {
				return false;
			}
			tempNode = selectionList.get(i);
		}
		for (int i = 0; i < selectionList.size(); i++) {
			DefaultMutableTreeNode aaa = selectionList.get(i)
					.getPreviousSibling();
			if (aaa == null) {
				return false;
			}
		}
		return true;
	}

	public boolean checkMoveDown(List<DefaultMutableTreeNode> selectionList) {
		DefaultMutableTreeNode tempNode;
		tempNode = selectionList.get(0);

		for (int i = 1; i < selectionList.size(); i++) {
			if (!tempNode.isNodeSibling(selectionList.get(i))) {
				return false;
			}
			tempNode = selectionList.get(i);
		}
		for (int i = 0; i < selectionList.size(); i++) {
			if (selectionList.get(i).getNextSibling() == null) {
				return false;
			}
		}
		return true;
	}

	public boolean checkGroup(List<DefaultMutableTreeNode> selectionList) {
		DefaultMutableTreeNode tempNode;

		tempNode = selectionList.get(0);

		for (int i = 1; i < selectionList.size(); i++) {
			if (!tempNode.isNodeSibling(selectionList.get(i))) {
				return false;
			}
			tempNode = selectionList.get(i);
		}
		return true;
	}

	public boolean checkUnGroup(List<DefaultMutableTreeNode> selectionList) {

		for (int i = 0; i < selectionList.size(); i++) {
			if (selectionList.get(i).getChildCount() == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean checkDelete(List<DefaultMutableTreeNode> selectionList) {

		return true;
	}

}
