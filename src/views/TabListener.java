/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.util.EventListener;

/**
 *
 * @author cpnewman
 */
public interface TabListener extends EventListener{
    
    public void tabEventOccurred(TabEvent e);
    
}
