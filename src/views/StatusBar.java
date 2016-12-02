/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author cpnewman
 */
public class StatusBar extends JPanel{
    
    private JLabel status;
    private String statusMessage = "listo. ";
    
    public StatusBar()
    {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        
        Dimension dim = getPreferredSize();
        dim.height = 16;
        setPreferredSize(dim);
        
        status = new JLabel(statusMessage);
        
        status.setHorizontalAlignment(SwingConstants.RIGHT);
        
        this.setLayout(new BorderLayout());
        this.add(status , BorderLayout.EAST);
    }
    
    public void setStatus(String newStatus) {
        statusMessage = newStatus;
        
        status.setText(newStatus);        
    }
    
}
