/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifetime_20;

import aisolutions.freeflyer.runtimeapi.RuntimeApiEngine;
import aisolutions.freeflyer.runtimeapi.RuntimeApiException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import model.Database;
import model.RunCase;
import views.CaseTable;
import views.OutputArea;
import views.OutputTab;
import views.StatusBar;

/**
 *
 * @author cpnewman
 */
public class LifetimeRun extends SwingWorker<String, String> {

    Database db;
    OutputArea outputArea;
    CaseTable caseTable;
    StatusBar statusBar;
    int row;
    String tle;
    RunCase runCase;
    
    OutputTab tab;
    
    boolean amIStopped;

    
    public LifetimeRun(Database db, final OutputArea outputArea, CaseTable caseTable, final StatusBar statusBar,
           int row, RunCase runCase, final String tle) {
        this.db = db;
        this.outputArea = outputArea;
        this.caseTable = caseTable;
        this.statusBar = statusBar;
        this.tle = tle;
        this.row = row;
        this.runCase = runCase;
        statusBar.setStatus("comenzando...");
        
    }
    
    public void go() {
        this.execute();
    }

    @Override
    protected String doInBackground() throws Exception {
        /////// lifetime code here ///////////

        String lifetimeMP = "C:\\Work\\Lifetime.MissionPlan";

        String lastTLEepoch;
        Double lastTLEheight;
        String lastPropEpoch;
        Double error;
        Double CD;

        String caseName = runCase.getCaseName();
        String schatten = runCase.getSchatten();
        Double initialCd = runCase.getInitialCd();
        int timeSpan = runCase.getTimeSpan();
        
        try {
            try (RuntimeApiEngine engine = new RuntimeApiEngine(ExampleUtilities.getFreeFlyerInstallDirectory())) {

                publish("Warming the Whimsical Astrodynamical Engine...\n\n");

                engine.loadMissionPlanFromFile(lifetimeMP);

                engine.prepareMissionPlan();
                engine.executeUntilApiLabel("readyForInput");
                publish("Whispering Wishes to Whimsical Astrodynamical Engine...\n\n");

                engine.setExpressionString("TLEfile", tle); // send TLE file to mission plan
                engine.setExpressionVariable("initialCD", initialCd);
                engine.setExpressionVariable("numDaysTLEs", timeSpan);

                Double initialCdReturned = engine.getExpressionVariable("initialCD");
                String tleFileReturned = engine.getExpressionString("TLEfile"); // return the TLE string name
                int timeSpanReturned = (int)engine.getExpressionVariable("numDaysTLEs");
                
                publish("initial CD set to:" + initialCdReturned + "\n");
                Thread.sleep(50);
                publish("TLE file set to:" + tleFileReturned + "\n");
                Thread.sleep(50);
                publish("Time span set to:" + timeSpanReturned + "\n");
                Thread.sleep(50);

                System.out.println("run one case");
                ////////// extract definitive state history /////////////

                publish("Extracting definitive state history...\n\n");
                engine.executeUntilApiLabel("endOfTles");

                lastTLEepoch = engine.getExpressionString("TLE.EpochFormat");
                lastTLEheight = engine.getExpressionVariable("TLE.PerigeeHeight");

                publish("Definitive state history extraction complete.\n");

                publish("Object's final definitive epoch is: " + lastTLEepoch + "\n");
                publish("Object's final definitive perigee height is: " + lastTLEheight.toString() + "\n\n");

                ////////////// targeting /////////////
                publish("Executing CD targeting on definitive state history...\n\n");

                engine.executeUntilApiLabel("stillTargeting");

                while (engine.getExpressionVariable("myDiff.Converged") < 1) {
                    error = engine.getExpressionVariable("error");
                    publish("Targeter iterating, error = " + error.toString() + "\n");
                    engine.executeUntilApiLabel("stillTargeting");
                }

                CD = engine.getExpressionVariable("CD");
                publish("\nTargeter Complete. CD = " + CD.toString() + "\n\n");
                Thread.sleep(50);

                /////////// propagation to re-entry //////////////
                publish("\nPropagating until re-entry...\n\n");

                engine.executeUntilApiLabel("endOfProp");

                lastPropEpoch = engine.getExpressionString("prop.EpochFormat");

                publish("Object re-enters the atmosphere at " + lastPropEpoch + "\n");

//                engine.cleanupMissionPlan();
            }
        } catch (RuntimeApiException exception) {
            System.out.println(exception.getMessage());
            lastPropEpoch = "execution error";
        }

        return lastPropEpoch;
    }

    @Override
    protected void process(List<String> list) {

        String output = list.get(list.size() - 1);

        tab.appendText(output);

        statusBar.setStatus("trabajando...");
    }
    
    @Override
    protected void done() {
        try {
            String reentry = get();
            if (row != -1) {
                db.updateCaseReentry(row, reentry);
            } else {
                statusBar.setStatus("single run re-entered on " + reentry);
            }
                
        } catch (InterruptedException ex) {
            tab.appendText("\n run interrupted.");
            statusBar.setStatus("case " + this.runCase.getCaseName() + " interrupted");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CancellationException ex) {
            tab.appendText("\n run cancelled.");
            statusBar.setStatus("case " + this.runCase.getCaseName() + " cancelled");
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            statusBar.setStatus("fracasó.");
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void setTab(OutputTab tab){
        this.tab = tab;
    }
}
