package com.company;

import com.company.TopologyModel.CircuitElement;
import com.company.TopologyModel.NetList;
import com.company.TopologyModel.Topology;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public String queryTopologies(){
        String topologiesInMemory="";
        for (var entry : topologyHashMap.entrySet()) {
            topologiesInMemory+=(entry.getValue().toString());
        }
        return topologiesInMemory;
    }

    public List<CircuitElement> queryDevices(String topologyID){
        Topology topology=topologyHashMap.get(topologyID);
        List<CircuitElement> circuitElementList=new ArrayList<CircuitElement>();
        for (int i=0;i<topology.components.size();i++)
        {
            if(topology.components.get(i).resistance!=null)
                circuitElementList.add(topology.components.get(i).resistance);
            else if(topology.components.get(i).p1!=null)
                circuitElementList.add(topology.components.get(i).p1);
            else if(topology.components.get(i).m1!=null)
                circuitElementList.add(topology.components.get(i).m1);

        }
        return circuitElementList;
    }

    public List<CircuitElement> queryDevicesWithNetlistNode(String topologyID, NetList netListNode){
        Topology topology=topologyHashMap.get(topologyID);
        List<CircuitElement> circuitElementList=queryDevices(topologyID);
        List<CircuitElement> devicesWithNetListNode=new ArrayList<CircuitElement>();
        for (int i=0;i<topology.components.size();i++)
        {
            if(topology.components.get(i).netlist.equals(netListNode)) {
                devicesWithNetListNode.add(circuitElementList.get(i));

            }
        }
        return devicesWithNetListNode;
    }

    public void deleteTopology(int topologyID){
        topologyHashMap.remove(topologyID);
    }

    
}
