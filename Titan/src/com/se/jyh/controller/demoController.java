package com.se.jyh.controller;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import com.se.jyh.model.DsmModel;
import com.se.jyh.model.Model;
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
	private JScrollPane scrollPane;
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

	public void setTreeModel(Model model) {

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
		System.out.println("controller loadCluster");

	}

	public void newDsm() {
		System.out.println("controller newdsm");
		/**
		 * new dsmName 받기
		 */
		if (this.treeModel != null) {
			treeModel.collapseAll();
			leftPanel.remove(leftPanel.getComponent(1));
		}
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

		this.dsmModel = new DsmModel();

		for (int i = 0; i < checkbox.length; i++) {
			dsmModel.addDsmRow(rowList[i].getText(), toThis[i]);
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
	}

	public void openDsm() {
		System.out.println("controller opendsm");

		if (this.treeModel != null) {
			treeModel.collapseAll();
			leftPanel.remove(leftPanel.getComponent(1));
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
						dsmModel.set(buffer.readLine());
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
			/**
			 * table은 tree model로 부터 결정됨 table에 tree model 넣기
			 */
			System.out.println("treeModel");
			tableModel.setModel(treeModel,dsmModel);
			//this.rightPanel.setTableModel(tableModel);
			//this.redraw();
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
	}

	public void saveClusterAs() {
		System.out.println("controller saveclusteras");
	}

	public void help() {
		System.out.println("controller help");
	}

	public void redraw() {
		//System.out.println("controller redraw");
		
		/**
		 * groupNodelist는 그룹으로 묵여져 있는 애를 가지고 있다.
		 */
		List<DefaultMutableTreeNode> groupNodeList = new ArrayList<DefaultMutableTreeNode>();
		List<Integer[]> mergedRow = new ArrayList<Integer[]>(); 
		List<Integer[]> mergedCol = new ArrayList<Integer[]>();
		
		this.treeModel.search2(this.treeModel.getRoot(), groupNodeList);
		
		/**
		 * 묶여져 있는 애들을 찾았으니까 묵여져 있는 애들의  번호를 찾는다.
		 * 
		 */
		
		for(int i=0;i<groupNodeList.size();i++){
			int[] numberList= new int[groupNodeList.get(i).getChildCount()];
			for(int j=0;j<groupNodeList.get(i).getChildCount();j++){
				numberList[j]=this.dsmModel.getNumFromName(groupNodeList.get(i).getChildAt(j).toString());
			}
			
			/**
			 * numberlist 기준으로 dsm에서 row와 col의 합집합을 가져온다
			 */
			
			mergedRow.add(this.dsmModel.getMergedRow(numberList));
			
			for(int z=0;z<this.dsmModel.getSize();z++){
				System.out.print(mergedRow.get(0)[z]);
			}
			//mergedRow.add(asd);
		}
		/**
		 * 넘버 가져오기
		 */
		
		leftPanel.revalidate();
	}

	public void showRowLabels() {
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
				dsmModel.addDsmRow(dsmName, fromThis);
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

		dupliController.setTreeModel(dupliTree);
		dupliController.setDsmModel(dsmModel);
		
		dupliTree.addTreeSelectionListener(dupliController);
		dupliTree.addMouseListener(dupliController.leftPanel);

		dupliController.collapseAll.notifyAction(true);
		dupliController.expand.notifyAction(true);
		dupliController.add.notifyAction(true);

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
