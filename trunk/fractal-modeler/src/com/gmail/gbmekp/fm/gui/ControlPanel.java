package com.gmail.gbmekp.fm.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.gmail.gbmekp.fm.engine.LSystem;
import com.gmail.gbmekp.fm.engine.Painter;

public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 2183516033772822907L;
    private JTextField axiom;
    private JSpinner angle;
    private JSpinner iteration;
    
    private JComboBox lSystems;
    
    private final ImagePanel imagePanel;
    
    private JButton paint;
    private JTable table;
    
    public ControlPanel(ImagePanel imagePanel) {
        super(new GridBagLayout());
        this.imagePanel = imagePanel;
        // TODO Auto-generated constructor stub
        initComponents();
        initListeners();
        initData();
    }


    private void initComponents() {
        axiom = new JTextField(12);
        
        lSystems = new JComboBox();
        
        add(lSystems, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        add(new JLabel("Аксиома"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(axiom, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        angle = new JSpinner(new SpinnerNumberModel(45, 1, 90, 1));
        
        add(new JLabel("Угол"), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(angle, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        iteration = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        
        add(new JLabel("Шагов"), new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        add(iteration, new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        paint = new JButton("Нарисовать");
        
        add(paint, new GridBagConstraints(0, 5, 2, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Правила"));
        
        table = new JTable(2, 2);
        panel.add(new JScrollPane(table));
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(0).setWidth(40);
        table.getColumnModel().getColumn(1).setWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        add(panel, new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
                new Insets(0, 0, 0, 0), 0, 0));
        

        
        lSystems.setRenderer(new DefaultListCellRenderer() {
            private static final long serialVersionUID = -5465254827330020387L;

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                
                LSystem system = (LSystem) value;
                setText(system.getName());
                return this;
            }
        });
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
        angle.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                paintFractal();
            }
        });
        lSystems.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                initFields((LSystem) lSystems.getSelectedItem());
            }
        });
    }

    private void initData() {
        lSystems.addItem(new LSystem("F++F++F+++F--F--F", new String[][] {
                    {"F", "FF++F++F++FFF"}}, "ADH258A"));
        lSystems.addItem(new LSystem("FX", new String[][] {
                {"X", "FX+FX+FXFY-FY-"},
                {"Y", "+FX+FXFY-FY-FY"},
                {"F", ""}}, "Cross"));
        lSystems.addItem(new LSystem("[F]+[F]+[F]+[F]+[F]+[F]", new String[][]{
                {"F", "F[++F][--F][-FF][+FF]FF[+F][-F]FF"}}, "Snowflake"));
        
    }
    
    private void paintFractal() {
        int depth = (Integer) iteration.getValue();
        int ang = (Integer) angle.getValue();
        String a = axiom.getText();
        
        String[][] ss = new String[table.getRowCount()][2];
        
        for (int i = 0; i < ss.length; i++) {
            ss[i][0] = (String) table.getValueAt(i, 0);
            ss[i][1] = (String) table.getValueAt(i, 1);
        }
        
        Image image = Painter.draw(new LSystem(a, ss),
            Math.PI * ang/ 180, depth);
        imagePanel.setImage(image);
        imagePanel.repaint();
    }
    
    private void initFields(LSystem system) {
        axiom.setText(system.getFirst());
        
        Map<Character, String> rules = system.getRules();
        
        ((DefaultTableModel) table.getModel()).setRowCount(rules.size());
        
        int i = 0;
        for (Character c : rules.keySet()) {
            table.setValueAt(c.toString(), i, 0);
            table.setValueAt(rules.get(c), i, 1);
            i++;
        }
    }
}
