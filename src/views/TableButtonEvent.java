/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.EventObject;
import model.RunCase;

/**
 *
 * @author cpnewman
 */
public class TableButtonEvent extends EventObject{
    
    private RunCase runCase;
    private String button;
    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public TableButtonEvent(Object o)
    {
        super(o);
    }
    
    public TableButtonEvent(Object o, String button, int row)
    {
        super(o);
        this.row = row;
        this.button = button;
    }
    
//    public TableButtonEvent(Object o, String button, RunCase runCase)
//    {
//        super(o);
//        this.runCase = runCase;
//        this.button = button;
//    }
    
    public RunCase getRunCase() {
        return runCase;
    }

    public void setRow(RunCase runCase) {
        this.runCase = runCase;
    }

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
    
}
