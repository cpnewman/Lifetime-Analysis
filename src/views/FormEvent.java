/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.EventObject;

/**
 *
 * @author cpnewman
 */
public class FormEvent extends EventObject{
    
    private String caseName;
    private String schatten;
    private Double initialCD;
    private String button;
    private int timeSpan;

    public FormEvent(Object o)
    {
        super(o);
    }
    
    public FormEvent(Object o, String caseName, String schatten, Double initialCD, int timeSpan, String button)
    {
        super(o);
        this.caseName = caseName;
        this.schatten = schatten;
        this.initialCD = initialCD;
        this.timeSpan = timeSpan;
        this.button = button;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getSchatten() {
        return schatten;
    }

    public void setSchatten(String schatten) {
        this.schatten = schatten;
    }

    public Double getInitialCD() {
        return initialCD;
    }

    public void setInitialCD(Double initialCD) {
        this.initialCD = initialCD;
    }
    
    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }
    
    public int getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(int timeSpan) {
        this.timeSpan = timeSpan;
    }
}
