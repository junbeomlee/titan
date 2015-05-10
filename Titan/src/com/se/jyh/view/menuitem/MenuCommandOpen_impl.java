package com.se.jyh.view.menuitem;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

public class MenuCommandOpen_impl extends JMenuItem implements MenuCommand {

	private JFrame frame;
	public MenuCommandOpen_impl(String name, JFrame frame){
		super(name);
		this.frame= frame;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		
		int rsp = fc.showOpenDialog( frame );
        if( rsp == JFileChooser.APPROVE_OPTION )
        {
          try
          {
            File f = fc.getSelectedFile();
            FileReader fr = new FileReader( f );
            BufferedReader br = new BufferedReader( fr );
            String line = br.readLine();
            if( line.equals( "Avin Property File" ) )
            {
              // Clear current contents of pane.
              /*pane.removeAll();
              while( (line = br.readLine()) != null )
              {
                StringTokenizer st = new StringTokenizer( line, "," );
                if( st.countTokens() == 5)
                {
                  MoveableButton b = new MoveableButton( st.nextToken().trim() );
                  int xLoc = Integer.parseInt( st.nextToken().trim() );
                  int yLoc = Integer.parseInt( st.nextToken().trim() );
                  int w = Integer.parseInt( st.nextToken().trim() );
                  int h = Integer.parseInt( st.nextToken().trim() );
                  b.setBounds( xLoc, yLoc, w, h );
                  pane.add( b );
                }
              }*/
            }
            else
            {
              System.out.println( "Unsupport file format!" );
            }
            br.close();
            fr.close();
          }
          catch( IOException ioe )
          {};
        }
		
		System.out.println("Open execute");
		FileDialog dialog = new FileDialog(frame,"Open",FileDialog.LOAD);
		dialog.show();
	}
	@Override
	public String toString(){
		String a = "opena";
		return a;
		
	}

}
