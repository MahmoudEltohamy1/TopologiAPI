package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class API {
    public API()
    {
        jsonString="";
    }
    private String jsonString;
    public String readJSON(String fileName){
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString+=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;

}


    public void writeJSON(int topologyID){

    }
    public void queryTopologies(){

    }
    public void queryDevices(int topologyID){

    }
    public void queryDevicesWithNetlistNode(int topologyID,int netListNodeID){

    }
    public void deleteTopology(int topologyID){

    }

    
}
