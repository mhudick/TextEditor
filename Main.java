import javax.swing.*;
import javax.swing.undo.UndoManager;

public class Main {
	public static void main(String[] args) {

		final JFrame frame = new JFrame("Text Editor");
		final UndoManager manager = new UndoManager();
		final JTextArea textbox = new JTextArea();
		textbox.getDocument().addUndoableEditListener(manager);

		//MenuBar settings
		JMenuBar menubar = new JMenuBar();

		//create "File" menu and add items to it
		JMenu file = new JMenu("File");
		File f =new File(file);
		f.exit();
		f.load(textbox,manager);
		f.save(textbox);

		//create "Edit" menu and add items to it
		JMenu edit = new JMenu("Edit");
		Edit e = new Edit(edit);
		e.copy(textbox);
		e.cut(textbox);
		e.paste(textbox);
		e.find(frame,textbox);
		e.undo(manager);
		e.redo(manager);

		//add items to menubar
		menubar.add(file);
		menubar.add(edit);

		//frame settings
		frame.add(textbox);
		frame.setSize(500,500);
		frame.setJMenuBar(menubar);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
