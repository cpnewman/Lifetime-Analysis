/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.RunCase;

/**
 *
 * @author cpnewman
 */
public class CaseTableModel extends AbstractTableModel{

    private List<RunCase> db;
    
    private String[] colNames ={"Case Name", "Schatten File", "Initial CD" , "Time Span (d)" , "Re-entry Epoch"};
    
    public CaseTableModel() 
    {
    }

    @Override
    public String getColumnName(int i) {
        return colNames[i];
    }
    
    public void setData(List<RunCase> db)
    {
        this.db = db;
    }
    
    @Override
    public int getRowCount() {
        return db.size();
    }

    @Override
    public int getColumnCount() {
        return 5; // TODO: make this smarter...
    }

    @Override
    public Object getValueAt(int row, int col) {
//        return 0;
        RunCase runCase = db.get(row);
        
        switch(col)
        {
            case 0:
                return runCase.getCaseName();
            case 1:
                return runCase.getSchatten();
            case 2:
                return runCase.getInitialCd();
            case 3:
                return runCase.getTimeSpan();
            case 4:
                return runCase.getReentryEpoch();
        }
        
        return null;
    }
    
    public void setValueAt(int row, int col) {
        
        RunCase runCase = db.get(row);
        
        switch (col)
        {
            
        }
    }
    
    
}
