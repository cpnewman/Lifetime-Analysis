/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import lifetime_20.LifetimeRun;

/**
 *
 * @author cpnewman
 */
public class OutputTab extends JPanel{
    
    private JTextArea output;
    private JPanel tabButtonArea;
    private JButton closeTabButton;
    private JButton stopRunButton;
    
    private TabListener listener;
    
    private LifetimeRun lifetimeRun;
    private OutputArea container;
    
    public OutputTab() {
        output = new JTextArea();
        tabButtonArea = new JPanel();
        closeTabButton = new JButton("close tab");
        stopRunButton = new JButton("stop run");
        
        Dimension dim = tabButtonArea.getPreferredSize();
        dim.height = 35;
        tabButtonArea.setPreferredSize(dim);
        
        tabButtonArea.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 1;
        tabButtonArea.add(stopRunButton,gc);
        gc.gridx = 2;
        tabButtonArea.add(closeTabButton,gc);
        
        this.setLayout(new BorderLayout());
        
        //////button listeners////////
        stopRunButton.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e) {
                String button = "stopRunButton";
                System.out.println("stop button hit");
                lifetimeRun.cancel(true);
            }
            
        });
        
        closeTabButton.addActionListener(new ActionListener(){
           
            public void actionPerformed(ActionEvent e){
                String button = "closeTabButton";
                if(!lifetimeRun.isCancelled()) {
                    lifetimeRun.cancel(true);
                }
                container.remove(OutputTab.this);
            }
        });
        
        this.add(output,BorderLayout.CENTER);
        this.add(tabButtonArea,BorderLayout.SOUTH);    
    }
    
    public void setContainer(OutputArea container) {
        this.container = container;
    }
    
    public void setLifetimeRun(LifetimeRun lifetimeRun) {
        this.lifetimeRun = lifetimeRun;
    }
        
    public void setListener(TabListener listener){
        this.listener = listener;
    }
    
    public void appendText(String text) {
        output.append(text);
        output.update(output.getGraphics());
    }
}
