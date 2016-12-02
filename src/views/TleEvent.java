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
public class TleEvent extends EventObject{
    
    private String tle;
    
    public TleEvent(Object o)
    {
        super(o);
    }
    
    public TleEvent(Object o, String tle)
    {
        super(o);
        this.tle = tle;
    }
    
    //// getters ///////
    
    public String getTle()
    {
        return tle;
    }
    
    /////// setters /////////
    public void setTle(String tle)
    {
        this.tle = tle;
    }
}
