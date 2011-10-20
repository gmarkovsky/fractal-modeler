package com.gmail.gbmekp.fm.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.Painter;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 2183516033772822907L;
    private JTextField axiom;
    private JTextField rule;
    private JTextField rule2;
    private JSpinner iteration;
    
    private final ImagePanel imagePanel;
    
    private JButton paint;
    
    public ControlPanel(ImagePanel imagePanel) {
        super(new GridBagLayout());
        this.imagePanel = imagePanel;
        // TODO Auto-generated constructor stub
        initComponents();
        initListeners();
    }


    private void initComponents() {
        axiom = new JTextField(12);
        
        add(new JLabel("Аксиома"), new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(axiom, new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        rule = new JTextField(1);
        rule2 = new JTextField(12);
        
        add(new JLabel("Правило"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(rule, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(rule2, new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        iteration = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        
        add(new JLabel("Шагов"), new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(iteration, new GridBagConstraints(1, 2, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        paint = new JButton("Нарисовать");
        
        add(paint, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
    }

    private void initListeners() {
        paint.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                paintFractal();
            }
        });
        
        iteration.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                paintFractal();
            }
        });
    }

    private void paintFractal() {
        int depth = (Integer) iteration.getValue();
        String a = axiom.getText();
        Image image = Painter.draw(new LSystem(a, new String[][]{
                {rule.getText(), rule2.getText()}}),
            Math.PI / 2, depth);
        imagePanel.setImage(image);
        imagePanel.repaint();
    }
}
