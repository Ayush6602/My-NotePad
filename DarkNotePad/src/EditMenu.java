import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

class EditMenu extends JMenu implements ActionListener, UndoableEditListener {
    /**
     *
     */
    private static final String UNDO = "UNDO";
    private static final String REDO = "REDO";

    private GUI gui;
    private static final long serialVersionUID = 1L;
    private JMenuItem undoEdit;
    private JMenuItem redoEdit;
    private UndoManager undoManager;

    EditMenu(GUI gui) {
        this.gui = gui;

        setText("Edit");

        undoManager = new UndoManager();
        gui.getTextArea().getDocument().addUndoableEditListener(this);

        undoEdit = new JMenuItem("Undo");
        undoEdit.setBackground(Color.black);
        undoEdit.setForeground(Color.green);
        undoEdit.addActionListener(this);
        undoEdit.setActionCommand(UNDO);
        add(undoEdit);

        redoEdit = new JMenuItem("Redo");
        redoEdit.setBackground(Color.black);
        redoEdit.setForeground(Color.green);
        redoEdit.addActionListener(this);
        redoEdit.setActionCommand(REDO);
        add(redoEdit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case UNDO:
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
                break;
            
            case REDO:
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
                break;

            default:
                System.out.println("Invalid Edit Action Command : " + e.getActionCommand());
                break;
                
        }
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent e) {
        undoManager.addEdit(e.getEdit());
    }

}