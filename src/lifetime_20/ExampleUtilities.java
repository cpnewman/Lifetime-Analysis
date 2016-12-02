/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifetime_20;

/**
 *
 * @author cpnewman
 */
import java.io.*;
import java.net.*;

class ExampleUtilities
{
   public static void setWorkingDirectoryToProgramDirectory() throws IOException, URISyntaxException
   {
      File classFile = new File(ExampleUtilities.class.getResource(ExampleUtilities.class.getSimpleName() + ".class").toURI());
      
      System.setProperty("user.dir",classFile.getCanonicalPath());
   }
   
   public static String getFreeFlyerInstallDirectory()
   {
      String ffInstallDir = null;
      
      String arch = System.getProperty("os.arch");
      
      String envVar = "";
      
      if(arch.contains("86"))
      {
         envVar = "FREEFLYER_32_INSTALL_DIRECTORY";
      }
      else
      {
         envVar = "FREEFLYER_32_INSTALL_DIRECTORY";
      }
      
      ffInstallDir = "C:\\Program Files (x86)\\a.i. solutions, Inc\\FreeFlyer 7.1.1.36220 (32-Bit)\\";
      
      if(ffInstallDir == null)
      {
         ffInstallDir = "";
      }
      
      if(ffInstallDir == "")
      {
         System.out.println("The environment variable '" + envVar + "' is not set. " +
                            "This is required for the example to run correctly.");
      }
      
      return ffInstallDir;
   }
   
   public static String getExamplesPath() throws IOException, URISyntaxException
   {
      File classFile = new File(ExampleUtilities.class.getResource(ExampleUtilities.class.getSimpleName() + ".class").toURI());
   
      return new File(classFile.getCanonicalPath(), "../../../../examples_common/").getCanonicalPath();
   }
}
