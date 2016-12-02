/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import lifetime_20.Controller;
import model.RunCase;

/**
 *
 * @author cpnewman
 */
public class MainFrame extends JFrame {

    private FormInput formInput;
    private TLEinput tleInput;
    private CaseTable caseTable;
    private OutputArea outputArea;
    private Controller controller;
    private JFileChooser fileChooser;
    private StatusBar statusBar;

    public MainFrame() {
        formInput = new FormInput();
        tleInput = new TLEinput();
        caseTable = new CaseTable();
        outputArea = new OutputArea();
        statusBar = new StatusBar();
        
        setJMenuBar(createMenuBar());

        controller = new Controller();

        caseTable.setData(controller.getCases()); // get cases into table
        caseTable.setController(controller); // connect table area to controller
        caseTable.setOutputArea(outputArea); // connect table area to output area
        
        caseTable.setCaseTableListener(new CaseTableListener() {
            public void rowDeleted(int row) {
                System.out.println(row);
                controller.removeCase(row);
            }
        });

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new RunCaseFileFilter());

        setLayout(new BorderLayout());

        add(formInput, BorderLayout.WEST);
        add(tleInput, BorderLayout.NORTH);
        add(caseTable, BorderLayout.CENTER);
        add(outputArea, BorderLayout.EAST);
        add(statusBar, BorderLayout.SOUTH);

        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        ////// set up menu bar ///////////
        ////// listeners ///////////
        formInput.setFormListener(new FormListener() {

            public void formEventOccurred(FormEvent e) {
                String caseName = e.getCaseName();
                String schatten = e.getSchatten();
                Double initialCd = e.getInitialCD();
                String tle = tleInput.getTleFile();
                int timeSpan = e.getTimeSpan();

                switch (e.getButton()) {
                    case "goButton":
                        RunCase runCase = new RunCase(caseName, schatten, initialCd, timeSpan);
                        controller.runThisCaseSwing(outputArea, caseTable, statusBar,runCase,tle);
                        break;
                    case "addButton":
                        controller.addCaseToList(caseName, schatten, initialCd, timeSpan);
                        caseTable.refresh();
                        break;
                }
            }
        });
        
        caseTable.setTableButtonListener(new TableButtonListener () {

            @Override
            public void tableButtonEventOccurred(TableButtonEvent e) {
                String tle = tleInput.getTleFile();
                int row;
                
                switch (e.getButton()) {
                    case "goOneButton":
                        row = e.getRow();
                        controller.runOneCaseSwing(outputArea,caseTable,statusBar, row, tle);
                        break;
                    case "goAllButton":
                        controller.runAllCases(outputArea, caseTable, statusBar, tle);
                        break;
                    case "deleteButton":
                        row = e.getRow();
                        System.out.println("delete row " + row);
                        controller.removeCase(row);
                        caseTable.refresh();
                        break;
                }
                
            }
        
            
        
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        importDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        caseTable.refresh();
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Could not load data from file.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exportDataItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException e1) {
                        JOptionPane.showMessageDialog(MainFrame.this,
                                "Could not save data to file.", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        return menuBar;
    }
}
