/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifetime_20;

import javax.swing.SwingUtilities;
import views.MainFrame;

/**
 *
 * @author cpnewman
 */
public class Lifetime_20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
                
            }
        });
    }
    
}
