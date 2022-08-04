import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class View extends JFrame implements ActionListener {

    private Controller controller;

    private static final int ROWS_IN_AREA = 5, COLUMNS_IN_AREA = 20,
            ROWS_IN_BUTTON = 1, COLUMNS_IN_BUTTON = 2, ROWS_IN_MAIN = 3,
            COLUMNS_IN_MAIN = 1;

    private final JTextArea top, bottom;

    private final JButton enter, clear;

    public View() {

        super("Morgan sucks");

        this.top = new JTextArea("", ROWS_IN_AREA, COLUMNS_IN_AREA);
        this.bottom = new JTextArea("", ROWS_IN_AREA, COLUMNS_IN_AREA);

        this.enter = new JButton("Enter");
        this.clear = new JButton("Clear");

        this.top.setEditable(true);
        this.top.setLineWrap(true);
        this.top.setWrapStyleWord(true);

        this.bottom.setEditable(false);
        this.bottom.setLineWrap(true);
        this.bottom.setWrapStyleWord(true);

        JScrollPane topPane = new JScrollPane(this.top);
        JScrollPane bottomPane = new JScrollPane(this.bottom);

        JPanel buttonPanel = new JPanel(
                new GridLayout(ROWS_IN_BUTTON, COLUMNS_IN_BUTTON));
        buttonPanel.add(this.enter);
        buttonPanel.add(this.clear);

        JPanel mainPanel = new JPanel(
                new GridLayout(ROWS_IN_MAIN, COLUMNS_IN_MAIN));
        mainPanel.add(topPane);
        mainPanel.add(bottomPane);
        mainPanel.add(buttonPanel);

        this.add(mainPanel);

        this.enter.addActionListener(this);
        this.clear.addActionListener(this);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void registerObserver(Controller controller) {
        this.controller = controller;
    }

    public void updateTop(String top) {
        this.top.setText(top);
    }

    public void updateBottom(String bottom) {
        this.bottom.setText(bottom);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Object source = e.getSource();
        if (source == this.enter) {
            this.controller.processEnter(this.top.getText());
        } else if (source == this.clear) {
            this.controller.processClear();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

}
