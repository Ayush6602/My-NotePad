import paint.JFontChooser;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FormatMenu extends JMenu implements ActionListener {
    /**
     *
     */
    private static final String CHANGE_TEXT_FONT = "CHANGE_TEXT_FONT";
    private static final String CHANGE_TEXT_COLOR = "CHANGE_TEXT_COLOR";
    private static final String CHANGE_BG_COLOR = "CHANGE_BG_COLOR";
    private static final String CHANGE_TEXT_WRAP = "CHANGE_TEXT_WRAP";

    private GUI gui;
    private static final long serialVersionUID = 1L;
    private JMenuItem textWrapFormat;
    private JMenuItem textFontFormat;
    private JMenuItem textColorFormat;
    private JMenuItem bgColorFormat;

    FormatMenu(GUI gui) {

        this.gui = gui;

        setText("Format");

        textWrapFormat = new JMenuItem("Text Wrap: OFF");
        textWrapFormat.setBackground(Color.black);
        textWrapFormat.setForeground(Color.green);
        gui.getTextArea().setWrapStyleWord(true);
        textWrapFormat.addActionListener(this);
        textWrapFormat.setActionCommand(CHANGE_TEXT_WRAP);
        add(textWrapFormat);

        textFontFormat = new JMenuItem("Text Font");
        textFontFormat.setBackground(Color.black);
        textFontFormat.setForeground(Color.green);
        textFontFormat.addActionListener(this);
        textFontFormat.setActionCommand(CHANGE_TEXT_FONT);
        add(textFontFormat);

        textColorFormat = new JMenuItem("Text Color");
        textColorFormat.setBackground(Color.black);
        textColorFormat.setForeground(Color.green);
        textColorFormat.addActionListener(this);
        textColorFormat.setActionCommand(CHANGE_TEXT_COLOR);
        add(textColorFormat);

        bgColorFormat = new JMenuItem("Background Color");
        bgColorFormat.setBackground(Color.black);
        bgColorFormat.setForeground(Color.green);
        bgColorFormat.addActionListener(this);
        bgColorFormat.setActionCommand(CHANGE_BG_COLOR);
        add(bgColorFormat);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch (e.getActionCommand()) {

            case CHANGE_TEXT_WRAP:
                if (gui.getTextArea().getLineWrap()) {
                    textWrapFormat.setText("Text Wrap: OFF");
                    gui.getTextArea().setLineWrap(false);
                }
                else {
                    textWrapFormat.setText("Text Wrap: ON");
                    gui.getTextArea().setLineWrap(true);
                }
                break;

            case CHANGE_TEXT_COLOR:
                Color textColor = JColorChooser.showDialog(this, "Select Text Color", Color.green, true);
                if (textColor != null) {
                    gui.getTextArea().setForeground(textColor);
                }
                break;
        
            case CHANGE_BG_COLOR:
                Color bgColor = JColorChooser.showDialog(this, "Select Background Color", Color.green, true);
                if (bgColor != null) {
                    gui.getTextArea().setBackground(bgColor);
                }
                break;

            case CHANGE_TEXT_FONT:
                JFontChooser fontChooser = new JFontChooser();
                if (fontChooser.showDialog(this) == JFontChooser.OK_OPTION) {
                    gui.getTextArea().setFont(fontChooser.getSelectedFont());
                }
                break;

            default:
                break;
        }

    }
}