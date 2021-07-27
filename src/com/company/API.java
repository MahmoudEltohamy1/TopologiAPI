package com.company;

import com.company.TopologyModel.Topology;

import java.io.*;

import static com.company.Main.topologyHashMap;

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


    public void writeJSON(String topologyID)  {

        Topology topologyToBeWritten=topologyHashMap.get(topologyID);
        JsonService jsonService=new JsonService();
        String topologyJson=jsonService.toJson(topologyToBeWritten);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("topologyOut.txt"));
            writer.write(topologyJson);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
