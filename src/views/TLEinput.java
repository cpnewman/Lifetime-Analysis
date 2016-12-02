/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author cpnewman
 */
public class TLEinput extends JPanel {
    
    private JLabel tleLabel;
    private JTextField tleTextField;
    
    private TleListener tleListener;
    
    public TLEinput()
    {
        Dimension dim = getPreferredSize();
        dim.width = 300;
        dim.height = 30;
        setPreferredSize(dim);
        
        tleLabel = new JLabel("TLEs : ");
        tleTextField = new JTextField(20);
        tleTextField.setText("C:\\work");
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(tleLabel, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        add(tleTextField, gc);
    }
        
    public String getTleFile()
    {
        return tleTextField.getText();
    }
    
    public void setTleListener(TleListener listener)
    {
        this.tleListener = listener;
    }
    
}
