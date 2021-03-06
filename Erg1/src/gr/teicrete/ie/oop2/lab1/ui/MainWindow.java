package gr.teicrete.ie.oop2.lab1.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 *
 * @author manosgem
 */
public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel sliderPanel;
    private JPanel colourPanel;
    private JPanel colourPanell;
    private JPanel colourPanelr;
    private JPanel infoPanel;

    private JLabel bgColourLabel;
    private JLabel bgColourPreview;
    private JLabel fgColourLabel;
    private JLabel fgColourPreview;
    private JLabel buttonSelectedTextLabel;
    private JLabel buttonSelectedLabel;

    private JSlider slider;

    private ActionListener buttonSelectedAL;

    private int pperc = 0;

    public MainWindow() {

        //Initializations
        //Panels
        mainPanel = new JPanel();
        sliderPanel = new JPanel();
        colourPanel = new JPanel();
        colourPanell = new JPanel();
        colourPanelr = new JPanel();
        infoPanel = new JPanel();
        //Labels
        bgColourLabel = new JLabel("Background Colour:");
        bgColourPreview = new JLabel("     ");
        fgColourLabel = new JLabel("Foreground Colour:");
        fgColourPreview = new JLabel("     ");
        buttonSelectedTextLabel = new JLabel("ButtonSelected:");
        buttonSelectedLabel = new JLabel();
        //custom Font
        Font font = new Font("Sheriff", Font.ITALIC, 12);
        //slider
        slider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
        //Button ActionListener to write button text to the button selected label
        buttonSelectedAL = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                Object source = e.getSource();

                if (source instanceof JButton) {
                    buttonSelectedLabel.setText(((JButton) source).getText());
                }
            }
        };

        //Configurations of panels
        //the panel that has colourchoosers 
        colourPanel.setLayout(new GridLayout(1, 2));

        ((FlowLayout) colourPanell.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout) colourPanelr.getLayout()).setAlignment(FlowLayout.LEFT);

        //configuring the bgColourPreview label
        bgColourPreview.setBackground(new JButton().getBackground());
        bgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bgColourPreview.setOpaque(true);
        //mouse listener for the background colour chooser
        bgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color bgColour = JColorChooser.showDialog(bgColourPreview, "BackGround Colour:", Color.white);

                bgColourPreview.setBackground(bgColour);
                for (int i = 0; i < mainPanel.getComponentCount(); i++) {
                    mainPanel.getComponent(i).setBackground(bgColour);
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        colourPanelr.add(bgColourLabel);
        colourPanelr.add(bgColourPreview);
        //configuring the fgColourPreview label
        fgColourPreview.setBackground(new JButton().getForeground());
        fgColourPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        fgColourPreview.setOpaque(true);
        //mouse listener for the foreground colour chooser
        fgColourPreview.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Color fgColour = JColorChooser.showDialog(fgColourPreview, "Foreground Colour:", Color.white);

                fgColourPreview.setBackground(fgColour);
                for (int i = 0; i < mainPanel.getComponentCount(); i++) {
                    mainPanel.getComponent(i).setForeground(fgColour);
                }
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        colourPanell.add(fgColourLabel);
        colourPanell.add(fgColourPreview);

        colourPanel.add(colourPanell);
        colourPanel.add(colourPanelr);

        add(colourPanel, BorderLayout.NORTH);

        //configuring the infoPanel that contains info about the button selected
        ((FlowLayout) infoPanel.getLayout()).setAlignment(FlowLayout.LEFT);
        //configuring the button selected label font and adding its mouse listener
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        buttonSelectedLabel.setFont(font.deriveFont(attributes));
        buttonSelectedLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Mouse Adapter for hovering over button selected label , removes and draws the borders
        buttonSelectedLabel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                for (int i = 0; i < mainPanel.getComponentCount(); i++) {

                    if (mainPanel.getComponent(i) instanceof JButton) {
                        if (((JButton) mainPanel.getComponent(i)).getText() == buttonSelectedLabel.getText()) {
                            //((JButton) mainPanel.getComponent(i)).setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
                            ((JButton) mainPanel.getComponent(i)).setBorderPainted(false);
                        }
                    }
                }
            }

            public void mouseExited(MouseEvent me) {
                for (int i = 0; i < mainPanel.getComponentCount(); i++) {

                    if (mainPanel.getComponent(i) instanceof JButton) {
                        if (((JButton) mainPanel.getComponent(i)).getText() == buttonSelectedLabel.getText()) {
                            //((JButton) mainPanel.getComponent(i)).setBorder(UIManager.getBorder("Button.border"));
                            ((JButton) mainPanel.getComponent(i)).setBorderPainted(true);
                        }
                    }
                }
            }

        });
        
        infoPanel.add(buttonSelectedTextLabel);
        infoPanel.add(buttonSelectedLabel);

        add(infoPanel, BorderLayout.SOUTH);
        //configuring the main panel (the panel where the buttons are added
        ((FlowLayout) mainPanel.getLayout()).setAlignment(FlowLayout.LEFT);

        add(mainPanel, BorderLayout.CENTER);

        //configuring the slider
        sliderPanel.setLayout(new GridLayout(0, 1));

        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);

        Hashtable labelTable = new Hashtable();
        for (int i = 0; i < 101; i += 5) {
            labelTable.put(new Integer(i), new JLabel(Integer.toString(i)));
        }

        slider.setLabelTable(labelTable);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        //adding a change listener to the slider , what happens when slider value changes
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int perc = source.getValue();

                    //if slider value is increased , buttons increase
                    if (perc > pperc) {

                        for (int i = 1; i <= perc - pperc; i++) {
                            JButton button = new JButton("Button " + (pperc + i));

                            button.addActionListener(buttonSelectedAL);
                            button.setBackground(bgColourPreview.getBackground());
                            button.setForeground(fgColourPreview.getBackground());
                            mainPanel.add(button);
                        }

                        pperc = perc;
                    } //if slider value is decreased, buttons decrease 
                    else if (perc < pperc) {

                        for (int j = pperc; j > perc; j--) {
                            mainPanel.remove(mainPanel.getComponent(mainPanel.getComponentCount() - 1));
                        }
                        pperc = perc;
                    }

                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });

        sliderPanel.add(slider);

        add(sliderPanel, BorderLayout.EAST);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
