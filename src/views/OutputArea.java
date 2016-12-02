/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 *
 * @author cpnewman
 */
public class OutputArea extends JTabbedPane{
    
    private OutputTab[] tabs;
    
    public OutputArea()
    {
        Dimension dim = getPreferredSize();
        dim.width = 400;
        setPreferredSize(dim);
    }
    
    public OutputTab addTab(String caseName) {
        
        OutputTab outputTab = new OutputTab();
        
        this.addTab(caseName,outputTab);
        
        outputTab.setContainer(this);
        
        return outputTab;
       
    }
    
    public int getTabID() {
        
        return this.getSelectedIndex();
    }
   
    
}