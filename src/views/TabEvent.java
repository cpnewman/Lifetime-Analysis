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
public class TabEvent extends EventObject {

    OutputTab outputTab;
    String button;
    
    public TabEvent(Object o) {
        super(o);
    }
    
    public TabEvent(Object o, OutputTab outputTab, String button) {
        super(o);
        this.outputTab = outputTab;
        this.button = button;
    }
    
}
