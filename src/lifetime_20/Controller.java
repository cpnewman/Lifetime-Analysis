/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lifetime_20;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
public class Controller {

    Database db = new Database();

    public List<RunCase> getCases() {
        return db.getCases();
    }

    public Controller() {
    }

    public void saveToFile(File file) throws IOException {
        db.saveToFile(file);
    }

    public void loadFromFile(File file) throws IOException {
        db.loadFromFile(file);
    }

    public void callFFapi(String tle, String schatten, Double initialCd, OutputArea outputArea) {
        System.out.println("Calling FF API");
        System.out.println("TLE      : " + tle);
        System.out.println("schatten : " + schatten);
        System.out.println("initialCd: " + initialCd.toString());

        
    }

    public void addCaseToList(String caseName, String schatten, Double initialCd, int timeSpan) {
        System.out.println("Adding case to list");
        System.out.println("caseName : " + caseName);
        System.out.println("schatten : " + schatten);
        System.out.println("initialCd: " + initialCd.toString());
        System.out.println("time span: " + timeSpan);
        RunCase runCase = new RunCase(caseName, schatten, initialCd, timeSpan);
        db.addCase(runCase);
        
    }

    public void removeCase(int index) {
        db.removeCase(index);
    }

    public void runAllCases(OutputArea outputArea, CaseTable caseTable, final StatusBar statusBar, final String tle) {
        
        List<RunCase> runCases = db.getCases();

        int numOfCases = runCases.size();

        for (int i = 0; i < numOfCases; i++) {
            runOneCaseSwing(outputArea, caseTable, statusBar, i, tle);
        }

    }

    public void runOneCaseSwing(final OutputArea outputArea, CaseTable caseTable, final StatusBar statusBar,
            final int row, final String tle) {
        
        RunCase runCase = caseTable.getRunCase(row);
        
        OutputTab tab = outputArea.addTab(runCase.getCaseName());
        System.out.println("tab made");
        tab.getGraphics();
        LifetimeRun run = new LifetimeRun(db, outputArea, caseTable, statusBar, row, runCase, tle);
        
        run.setTab(tab);
        tab.setLifetimeRun(run);
        
        run.go();
        System.out.println("run started");
    }
    
    public void runThisCaseSwing(final OutputArea outputArea, CaseTable caseTable, final StatusBar statusBar,
            RunCase runCase, final String tle) {
        
        OutputTab tab = outputArea.addTab(runCase.getCaseName());
        System.out.println("tab made");
        tab.getGraphics();
        LifetimeRun run = new LifetimeRun(db, outputArea, caseTable, statusBar, -1, runCase, tle);
        
        run.setTab(tab);
        tab.setLifetimeRun(run);
        
        run.go();
        System.out.println("run started");
    }
    
    
    
}   
