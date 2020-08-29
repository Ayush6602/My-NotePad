import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;


public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTextArea textArea;
    private JMenuBar mainMenuBar;
    private FileMenu fileMenu;
    private EditMenu editMenu;
    private FormatMenu formatMenu;
    private JScrollPane scrollPane;

    public static void main(String[] args) {
        new GUI();
    }

    GUI() {

        textArea = new JTextArea();
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.green);
        textArea.setCaretColor(Color.green);
        textArea.setFont(new Font("Arial", Font.BOLD, 14));
        scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                
        add(scrollPane);

        mainMenuBar = new JMenuBar();
        mainMenuBar.setBackground(Color.black);
        setJMenuBar(mainMenuBar);

        fileMenu = new FileMenu(this);
        fileMenu.setForeground(Color.green);
        mainMenuBar.add(fileMenu);

        editMenu = new EditMenu(this);
        editMenu.setForeground(Color.green);
        mainMenuBar.add(editMenu);

        formatMenu = new FormatMenu(this);
        formatMenu.setForeground(Color.green);
        mainMenuBar.add(formatMenu);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dark NotePad");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}