import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class File {
	JMenu file;
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text files","txt");
	
	public File(JMenu file){
		this.file=file;
		chooser.setFileFilter(filter);
	}

	public void exit(){
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener( ) { //Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}   
		}); 
		file.add(exit);
	}

	public void load(final JTextArea textbox,final UndoManager manager){
		JMenuItem load = new JMenuItem("Load");
		
		load.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				int returnValue = chooser.showOpenDialog(chooser.getParent());//Open a dialog to get a file to load
				if(returnValue==JFileChooser.APPROVE_OPTION){
					System.out.println("You have chosen:"+chooser.getSelectedFile().getName());//print name of file to console
					manager.discardAllEdits(); //clear the UndoManager to prevent errors
					try{
						FileReader reader = new FileReader(chooser.getSelectedFile());
						BufferedReader br = new BufferedReader(reader);

						textbox.read( br, null ); //read the file in
						textbox.getDocument().addUndoableEditListener(manager); //reset the UndoManager
						br.close();
					}catch(Exception e3){}
				}
			}   
		}); 
		file.add(load);
	}

	public void save(final JTextArea textbox){
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				int returnValue = chooser.showSaveDialog(chooser.getParent()); //open dialog to get save location
				if(returnValue==JFileChooser.APPROVE_OPTION){
					System.out.println("Save to:"+chooser.getSelectedFile().getName());
				}
				try{
					BufferedWriter fw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
					String[] textboxContents = textbox.getText().split("\n"); //split the file into strings by line
					for(String s: textboxContents)//loop through list of strings, adding each with a newline
					{
						fw.write(s);
						fw.newLine();
					}
					fw.close();
				}catch(Exception e3){}
			}   
		}); 
		file.add(save);
	}
}
