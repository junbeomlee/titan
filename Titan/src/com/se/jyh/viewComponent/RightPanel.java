package com.se.jyh.viewComponent;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import com.se.jyh.controller.demoController;
import com.se.jyh.model.Table;

public class RightPanel extends JPanel {
	
	private demoController democontroller;
	private JPanel tablePanel;
	private JScrollPane scrollPane;
	private JTable table;
	
	public RightPanel(BorderLayout borderLayout,demoController democontroller){
			
			super(borderLayout);
			
			this.tablePanel=new JPanel();
			
			this.tablePanel.setPreferredSize(new Dimension(400,500));
			
			this.democontroller= democontroller;
			this.democontroller.setRightPanel(this);
			
			
			
	}
	public void showRowLabel(){
		
		for (int column = 0; column < table.getColumnCount(); column++)
		{
		    TableColumn tableColumn = table.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < table.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		        Component c = table.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        //  We've exceeded the maximum width, no need to check other rows
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		 
		    tableColumn.setPreferredWidth( preferredWidth );
		}
	}
	
	public void setTableModel(Table tableModel){
		
		this.table = new JTable(tableModel);
		this.scrollPane= new JScrollPane(table);
		
		this.add(this.scrollPane,BorderLayout.CENTER);
		
		table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		 
		for (int column = 0; column < table.getColumnCount(); column++)
		{
		    TableColumn tableColumn = table.getColumnModel().getColumn(column);
		    int preferredWidth = tableColumn.getMinWidth();
		    int maxWidth = tableColumn.getMaxWidth();
		 
		    for (int row = 0; row < table.getRowCount(); row++)
		    {
		        TableCellRenderer cellRenderer = table.getCellRenderer(row, column);
		        Component c = table.prepareRenderer(cellRenderer, row, column);
		        int width = c.getPreferredSize().width + table.getIntercellSpacing().width;
		        preferredWidth = Math.max(preferredWidth, width);
		 
		        //  We've exceeded the maximum width, no need to check other rows
		 
		        if (preferredWidth >= maxWidth)
		        {
		            preferredWidth = maxWidth;
		            break;
		        }
		    }
		 
		    tableColumn.setPreferredWidth( preferredWidth );
		}
	}

	public void clear() {
		// TODO Auto-generated method stub
		this.removeAll();
	}
	
	
	
	
}
