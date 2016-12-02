/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author cpnewman
 */
public class RunCase implements Serializable{
    
    private static int count = 0;
    
    private int id;
    private String caseName;
    private String schatten;
    private Double initialCd;
    private String reentryEpoch;
    private int timeSpan;

    public RunCase(String caseName, String schatten, Double initialCd, int timeSpan)
    {
        this.caseName = caseName;
        this.schatten = schatten;
        this.initialCd = initialCd;
        this.timeSpan = timeSpan;
        this.reentryEpoch = null;
        
        this.id = count;
        count++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getInitialCd() {
        return initialCd;
    }

    public void setInitialCd(Double initialCd) {
        this.initialCd = initialCd;
    }
    
    public String getReentryEpoch() {
        return reentryEpoch;
    }

    public void setReentryEpoch(String reentryEpoch) {
        this.reentryEpoch = reentryEpoch;
    }
    
    public int getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(int timeSpan) {
        this.timeSpan = timeSpan;
    }
    
}
