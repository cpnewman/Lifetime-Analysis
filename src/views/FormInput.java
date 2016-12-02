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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author cpnewman
 */
public class FormInput extends JPanel {
    
   private JLabel schattenLabel;
   private JTextField schattenField;
   
   private JLabel caseLabel;
   private JTextField caseField;
   
   private JLabel initialCdLabel;
   private JFormattedTextField initialCdField;
   private NumberFormat cdFormat;
   private double initialCd = 50.0;
   
   private JLabel timeSpanLabel;
   private JFormattedTextField timeSpanField;
   private NumberFormat timeSpanFormat;
   private JButton addButton;
   private JButton goButton;
   
   private FormListener formListener;
   
   public FormInput()
   {
       Dimension dim = getPreferredSize();
       dim.width = 350;
       setPreferredSize(dim);
       
       caseLabel = new JLabel("Case Name");
       caseField = new JTextField(10);
       
       schattenLabel = new JLabel("Schatten File");
       schattenField = new JTextField(10);
       
       initialCdLabel = new JLabel("Initial CD");
       initialCdField = new JFormattedTextField(cdFormat);
       initialCdField.setValue(new Double(initialCd));
       initialCdField.setColumns(5);
      
       timeSpanLabel = new JLabel("Time Span (d)");
       timeSpanField = new JFormattedTextField(timeSpanFormat);
       timeSpanField.setValue(365);
       timeSpanField.setColumns(5);
       
       addButton = new JButton("Add Case");
       goButton  = new JButton("Execute Current Case");
               
       Border innerBorder = BorderFactory.createTitledBorder("Input Parameters");
       Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
       setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
       
       setLayout(new GridBagLayout());
        
       GridBagConstraints gc = new GridBagConstraints();
       
       ///////// first row /////////
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 0;
       gc.gridy = 0;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_END;
       gc.insets = new Insets(0,0,0,5);
       add(caseLabel, gc);
       
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 1;
       gc.gridy = 0;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_START;
//       gc.insets = new Insets(0,0,0,5);
       add(caseField, gc);
       
       ///////// second row /////////
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 0;
       gc.gridy = 1;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_END;
       gc.insets = new Insets(0,0,0,5);
       add(schattenLabel, gc);
       
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 1;
       gc.gridy = 1;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_START;
//       gc.insets = new Insets(0,0,0,5);
       add(schattenField, gc);
       
       ///////// third row /////////
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 0;
       gc.gridy = 2;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_END;
       gc.insets = new Insets(0,0,0,5);
       add(initialCdLabel, gc);
       
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 1;
       gc.gridy = 2;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_START;
//       gc.insets = new Insets(0,0,0,5);
       add(initialCdField, gc);
       
       ///////// fourth row ///////////
       
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 0;
       gc.gridy = 3;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_END;
       gc.insets = new Insets(0,0,0,5);
       add(timeSpanLabel, gc);
       
       gc.weightx = 1;
       gc.weighty = 0.1;
       gc.gridx = 1;
       gc.gridy = 3;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.LINE_START;
       gc.insets = new Insets(0,0,0,5);
       add(timeSpanField, gc);
       
       /////////fifth row ///////////
       
       gc.weightx = 1;
       gc.weighty = 1;
       gc.gridx = 0;
       gc.gridy = 4;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.FIRST_LINE_START;
       add(goButton, gc);
       
       gc.weightx = 1;
       gc.weighty = 1;
       gc.gridx = 1;
       gc.gridy = 4;
       gc.fill = GridBagConstraints.NONE;
       gc.anchor = GridBagConstraints.FIRST_LINE_END;
       add(addButton, gc);
       
       /////// action listeners and stuff ///////////
       
       goButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e) 
           {
               String caseName = caseField.getText();
               String schatten = schattenField.getText();
               Double initialCd = ((Number)initialCdField.getValue()).doubleValue();
               int timeSpan = ((Number)timeSpanField.getValue()).intValue();
               String button = "goButton";
               
               FormEvent ev = new FormEvent(this, caseName, schatten, initialCd, timeSpan, button);
               
               if (formListener != null)
               {
                   formListener.formEventOccurred(ev);
               }
           }
       });
       
       addButton.addActionListener(new ActionListener()
       {
           public void actionPerformed(ActionEvent e) 
           {
               String caseName = caseField.getText();
               String schatten = schattenField.getText();
               Double initialCd = ((Number)initialCdField.getValue()).doubleValue();
               int timeSpan = ((Number)timeSpanField.getValue()).intValue();
               String button = "addButton";
               
               FormEvent ev = new FormEvent(this, caseName, schatten, initialCd, timeSpan, button);
               
               if (formListener != null)
               {
                   formListener.formEventOccurred(ev);
               }
           }
       });
       
   }
   
    public void setFormListener(FormListener listener)
    {
        this.formListener = listener;
    }    

}
