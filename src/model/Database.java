/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author cpnewman
 */
public class Database {

    private List<RunCase> runCases;

    public Database() {
        runCases = new LinkedList<RunCase>();
    }

    public void addCase(RunCase runCase) {
        runCases.add(runCase);
    }

    public void removeCase(int index) {
        runCases.remove(index);
    }

    public void updateCaseReentry(int index, String epoch) {
        RunCase runCase = runCases.get(index);
        
        runCase.setReentryEpoch(epoch);
        
        runCases.set(index, runCase);   
    }

    public List<RunCase> getCases() {
        return Collections.unmodifiableList(runCases);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        RunCase[] cases = runCases.toArray(new RunCase[runCases.size()]);

        oos.writeObject(cases);

        oos.close();
    }

    public void loadFromFile(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            RunCase[] cases = (RunCase[]) ois.readObject();

            runCases.clear();

            runCases.addAll(Arrays.asList(cases));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ois.close();
    }
}
