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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import lifetime_20.Controller;
import model.RunCase;

/**
 *
 * @author cpnewman
 */
public class CaseTable extends JPanel {
    
    private JTable table;
    private CaseTableModel caseTableModel;
    
    private JPopupMenu popup;
    
    private JPanel buttonArea;
    private JButton goOneButton;
    private JButton goAllButton;
    private JButton deleteButton;
    
    private CaseTableListener caseTableListener;
    private TableButtonListener tableButtonListener;
    
    private Controller controller;
    private OutputArea outputArea;
    
    public CaseTable()
    {
        caseTableModel = new CaseTableModel();
        table = new JTable(caseTableModel);
        buttonArea = new JPanel();
        
        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("delete case");
        popup.add(removeItem);
        
        table.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                int row = table.rowAtPoint(e.getPoint());
                        
                table.getSelectionModel().setSelectionInterval(row,row);
                
                
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    popup.show(table , e.getX() , e.getY());
                }
            }
        });
        
        removeItem.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int row = table.getSelectedRow();
                
                if (caseTableListener != null)
                {
                    caseTableListener.rowDeleted(row);
                    caseTableModel.fireTableRowsDeleted(row, row);
                }
            }
        });
        
        ////// set up button area ////////////
        
        Dimension dim = buttonArea.getPreferredSize();
        dim.height = 35;
        buttonArea.setPreferredSize(dim);
        
        goOneButton = new JButton("Run Selected");
        goAllButton = new JButton("Run All Cases");
        deleteButton= new JButton("Delete Case");
        
        buttonArea.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
        gc.gridy = 0;
        buttonArea.add(goOneButton);
        
        gc.gridx = 1;
        gc.gridy = 0;
        buttonArea.add(goAllButton);
        
        gc.gridx = 2;
        gc.gridy = 0;
        buttonArea.add(deleteButton);
        
        
        setLayout(new BorderLayout());
        
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonArea, BorderLayout.SOUTH);
        
        ///////listeners and such////////////
        
        goOneButton.addActionListener(new ActionListener()
        {
           @Override
           public void actionPerformed(ActionEvent e) 
           {
               int row = table.getSelectedRow();
               String button = "goOneButton";
               
               TableButtonEvent tableEvent = new TableButtonEvent(e,button,row);
               
               tableButtonListener.tableButtonEventOccurred(tableEvent);
               
           }
        });
        
        goAllButton.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String button = "goAllButton";
                
                TableButtonEvent tableEvent = new TableButtonEvent(e,button,-1);
                
                tableButtonListener.tableButtonEventOccurred(tableEvent);
            }
            
        });
        
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String button = "deleteButton";
                int row = table.getSelectedRow();
                TableButtonEvent tableEvent = new TableButtonEvent(e, button, row);
                
                tableButtonListener.tableButtonEventOccurred(tableEvent);
            }
        });
        
    }
    
    public RunCase getRunCase(int row) {
        RunCase runcase = new RunCase(null,null,null,-1);
        
        runcase.setCaseName((String)caseTableModel.getValueAt(row, 0));
        runcase.setSchatten((String)caseTableModel.getValueAt(row, 1));
        runcase.setInitialCd((Double)caseTableModel.getValueAt(row, 2));
        runcase.setTimeSpan((int)caseTableModel.getValueAt(row,3));
        runcase.setReentryEpoch((String)caseTableModel.getValueAt(row, 4));
        
               
        return runcase;
    }
    
    public int getSelectedRow()
    {
        return table.getSelectedRow();
    }
    
    public void setData(List<RunCase> db)
    {
        caseTableModel.setData(db);
    }
    
    public void refresh()
    {
        caseTableModel.fireTableDataChanged();
    }
    
    public void setCaseTableListener(CaseTableListener listener)
    {
        this.caseTableListener = listener;
    }
    
    public void setTableButtonListener(TableButtonListener listener)
    {
        this.tableButtonListener = listener;
    }
    
    public void setController(Controller controller)
    {
        this.controller = controller;
    }
    
    public void setOutputArea(OutputArea outputArea)
    {
        this.outputArea = outputArea;
    }
    
}
