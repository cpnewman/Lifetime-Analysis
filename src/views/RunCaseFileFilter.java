/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author cpnewman
 */
public class RunCaseFileFilter extends FileFilter {

    @Override
    public boolean accept(File file) {
        
        if(file.isDirectory())
        {
            return true;
        }
        
        String name = file.getName();
        
        String extension = Utils.getFileExtension(name);
        
        if(extension.equals("life"))
        {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Run Case Files (*.life)";
    }

  
    
}
