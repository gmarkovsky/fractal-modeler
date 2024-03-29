package com.gmail.gbmekp.fm;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.gmail.gbmekp.fm.engine.Vector;

/**
 * Панель управления отображения.
 * 
 * @author george
 *
 */
public class ControlPanel extends JPanel {
    private static final long serialVersionUID = 2183516033772822907L;
    
    /**
     * Аксиома.
     */
    private JTextField axiom;
    
    /**
     * Изменение угла.
     */
    private JSpinner deltaAngle;
    
    /**
     * Число итераций.
     */
    private JSpinner iteration;
    
    /**
     * Текст кода Л-системы.
     */
    private JTextField code;
    
    /**
     * Признак наличия силового поля.
     */
    private JCheckBox force;
    
    /**
     * Л-симтема.
     */
    private JComboBox lSystems;
    
    private JButton paint;
    private JTable table;
    
    private final Controller controller;
    
    private JSpinner forceX;
    
    private JSpinner forceY;
    
    public ControlPanel(Controller controller) {
        super(new GridBagLayout());
        
        this.controller = controller;
        
        initComponents();
        initListeners();
        initData();
        
        lSystems.setSelectedIndex(6);
    }


    private void initComponents() {
        axiom = new JTextField(12);
        
        lSystems = new JComboBox();
        
        add(lSystems, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(5, 5, 5, 5), 0, 0));
        
        add(new JLabel("Аксиома"), new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        add(axiom, new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 5, 5), 0, 0));
        
        deltaAngle = new JSpinner(new SpinnerNumberModel(35, 1, 90, 1));
        
        add(new JLabel("Изменение угла"), new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        add(deltaAngle, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 5, 5), 0, 0));
        
        iteration = new JSpinner(new SpinnerNumberModel(4, 0, 10, 1));
        
        add(new JLabel("Шагов"), new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.WEST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        add(iteration, new GridBagConstraints(1, 5, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 5, 5), 0, 0));
        
        paint = new JButton("Нарисовать");
        
        add(paint, new GridBagConstraints(0, 6, 2, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        
       JPanel wrap = new JPanel(new GridBagLayout());
       
       add(wrap, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0,
               GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
               new Insets(0, 5, 5, 5), 0, 0));
       
       force = new JCheckBox("Поле");
       wrap.add(force, new GridBagConstraints(0, 0, 4, 1, 1.0, 0.0,
               GridBagConstraints.WEST, GridBagConstraints.NONE, 
               new Insets(0, 5, 5, 5), 0, 0));
        
       
        SpinnerNumberModel sm = new SpinnerNumberModel(0.0, -100, 100, 0.1);
        forceX = new JSpinner(sm);
        wrap.add(new JLabel("x:"), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        wrap.add(forceX, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        
        sm = new SpinnerNumberModel(-1.0, -100, 100, 0.1);
        forceY = new JSpinner(sm);
        wrap.add(new JLabel("y:"), new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        wrap.add(forceY, new GridBagConstraints(3, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.NONE, 
                new Insets(0, 5, 5, 5), 0, 0));
        
        code = new JTextField();
        add(code, new GridBagConstraints(0, 8, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
                new Insets(0, 0, 0, 0), 0, 0));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Правила"));
        
        table = new JTable(2, 2);
        panel.add(new JScrollPane(table));
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(0).setWidth(40);
        table.getColumnModel().getColumn(1).setWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.setFillsViewportHeight(true);
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
        deltaAngle.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                paintFractal();
            }
        });
        lSystems.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                initFields((LSystem) lSystems.getSelectedItem());
                if (isDisplayable())
                paintFractal();
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) {
        			((DefaultTableModel) table.getModel()).setRowCount(table.getRowCount() + 1);
        		}
        	}
        });
        force.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				paintFractal();
			}
		});
        forceX.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                paintFractal();
            }
        });
        forceY.getModel().addChangeListener(new ChangeListener() {
            
            @Override
            public void stateChanged(ChangeEvent e) {
                paintFractal();
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
        lSystems.addItem(new LSystem("Y", new String[][]{
                {"X", "X[-FFF][+FFF]FX"},
                {"Y", "YFX[+Y][-Y]"}}, "Bush1"));
        
        lSystems.addItem(new LSystem("F", new String[][]{
                {"F", "FF+[+F-F-F]-[-F+F+F]"}}, "Bush2"));
        lSystems.addItem(new LSystem("F", new String[][]{
                {"F", "F[+FF][-FF]F[-F][+F]F"}}, "Bush3"));
        LSystem anObject = new LSystem("X", new String[][]{
                {"F", "FF"},
                {"X", "F[+X]F[-X]+X"}}, "Sticks");
		lSystems.addItem(anObject);
        lSystems.addItem(new LSystem("X", new String[][]{
                {"X", "F[++X]-F[--X]X"}}, "Tree"));
    }
    
    public void paintFractal() {
        int depth = (Integer) iteration.getValue();
        int dAng = (Integer) deltaAngle.getValue();
        String a = axiom.getText();
        
        String[][] ss = new String[table.getRowCount()][2];
        
        for (int i = 0; i < ss.length; i++) {
            ss[i][0] = (String) table.getValueAt(i, 0);
            ss[i][1] = (String) table.getValueAt(i, 1);
        }

        LSystem lSystem = new LSystem(a, ss);
        code.setText(lSystem.getResult(depth));
        
        double fx = (Double) forceX.getValue();
        double fy = (Double) forceY.getValue();
        
		controller.paintFractal(lSystem, dAng, depth, force.isSelected(), new Vector(fx, fy));
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
