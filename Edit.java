import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;

public class Edit {
	JMenu edit;
	public Edit(JMenu edit){
		this.edit=edit;
	}

	public void copy(final JTextArea textbox){
		JMenuItem copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				textbox.copy();
			}   
		}); 
		edit.add(copy);
	}

	public void paste(final JTextArea textbox){
		JMenuItem paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				textbox.paste();
			}   
		}); 
		edit.add(paste);
	}
	public void cut(final JTextArea textbox){
		JMenuItem cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				textbox.cut();
			}   
		});
		edit.add(cut);
	}

	public void find(final JFrame frame,final JTextArea textbox){
		JMenuItem find = new JMenuItem("Find");
		//create a highlighter and add it to the textbox
		final Highlighter hilite = new DefaultHighlighter();
		final Highlighter.HighlightPainter paint = new DefaultHighlighter.DefaultHighlightPainter(Color.LIGHT_GRAY);
		textbox.setHighlighter(hilite);

		find.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				String question = JOptionPane.showInputDialog("Search:");//ask for the string to search for
				if(question!=null) //Execute if "OK" is selected
				{
					int place=0;
					while(textbox.getText().indexOf(question, place)!=-1)//highlight each instance of the search string
					{
						place = textbox.getText().indexOf(question, place);
						try{
							hilite.addHighlight(place, (place+question.length()), paint);
						}catch(Exception e2){e2.printStackTrace();}
						place+=question.length();//move search start to end of found string
					}
					JOptionPane.showMessageDialog(null, "Search Complete");{//confirm the search and end the search
						hilite.removeAllHighlights();
					}
				}   
			}
		}); 
		edit.add(find);
	}

	public void undo(final UndoManager manager){
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				if(manager.canUndo())
					manager.undo();
			}   
		}); 
		edit.add(undo);
	}

	public void redo(final UndoManager manager){
		JMenuItem redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener( ) {//Add the action when clicked
			public void actionPerformed(ActionEvent e) {
				if(manager.canRedo())
					manager.redo();
			}   
		}); 
		edit.add(redo);
	}


}
