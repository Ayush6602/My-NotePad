import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class FileMenu extends JMenu implements ActionListener {
    /**
     *
     */
    private static final String NEW_FILE = "NEW_FILE";
    private static final String OPEN_FILE = "OPEN_FILE";
    private static final String SAVE_FILE = "SAVE_FILE";
    private static final String SAVE_FILE_AS = "SAVE_FILE_AS";

    private GUI gui;
    private static final long serialVersionUID = 1L;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveAsFile;
    private File file;

    FileMenu(GUI gui) {

        this.gui = gui;

        setText("File");

        newFile = new JMenuItem("New");
        newFile.setBackground(Color.black);
        newFile.setForeground(Color.green);
        newFile.addActionListener(this);
        newFile.setActionCommand(NEW_FILE);
        add(newFile);

        openFile = new JMenuItem("Open");
        openFile.setBackground(Color.black);
        openFile.setForeground(Color.green);
        openFile.addActionListener(this);
        openFile.setActionCommand(OPEN_FILE);
        add(openFile);

        saveFile = new JMenuItem("Save");
        saveFile.setBackground(Color.black);
        saveFile.setForeground(Color.green);
        saveFile.addActionListener(this);
        saveFile.setActionCommand(SAVE_FILE);
        add(saveFile);

        saveAsFile = new JMenuItem("Save As");
        saveAsFile.setBackground(Color.black);
        saveAsFile.setForeground(Color.green);
        saveAsFile.addActionListener(this);
        saveAsFile.setActionCommand(SAVE_FILE_AS);
        add(saveAsFile);

    }

    private void readFile(File file) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            gui.getTextArea().setText(null);
            String line;
            while ((line = reader.readLine()) != null) {
                gui.getTextArea().append(line);
            }
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private void writeFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gui.getTextArea().getText());
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    private void createNewFile() {
        if (JOptionPane.showConfirmDialog(this,
                                            "Are you sure you want to discard current NotePad \n and create a new NotePad",
                                            "Confirm Choice",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            gui.getTextArea().setText(null);
        }
    }

    private void openNewFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            readFile(file);
        }
        else {
            System.out.println("Open operation cancelled by USER");
        }
    }

    private void saveNewFile() {
        if (file == null) {
            saveNewFileAs();
            return;
        }
        writeFile(file);
    }

    private void saveNewFileAs() {
        JFileChooser locationChooser = new JFileChooser();
        locationChooser.setCurrentDirectory(new File(System.getProperty("user.home") + File.separator + "Desktop"));
        if (locationChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = new File(locationChooser.getSelectedFile().getPath());
            try {
                if (file.createNewFile()) {
                    writeFile(file);
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        } 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {

            case NEW_FILE:
                createNewFile();
                break;

            case OPEN_FILE:
                openNewFile();
                break;

            case SAVE_FILE:
                saveNewFile();
                break;

            case SAVE_FILE_AS:
                saveNewFileAs();
                break;

            default:
                System.out.println("Invalid File Action Command : " + e.getActionCommand());
                break;
        }
    }
}