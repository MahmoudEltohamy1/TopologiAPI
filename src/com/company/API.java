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

    /**
     * This method reads a topology from a given JSON file
     * and store it in the memory.
     *
     *
     * @param  fileName  name of given JSON file
     * @return      the JSON string read from the file
     */
    public String readJSON(String fileName) throws FileNotFoundException {

        File file = new File(fileName);
        if(fileName==null)
            throw new NullPointerException();
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                jsonString+=line;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;

}

    /**
     * This method writes a given topology to a given JSON file
     *
     *
     * @param  topologyID  ID of topology to be written in the JSON file
     */
    public void writeJSON(String topologyID)  {
        if(topologyHashMap.get(topologyID)==null)
            throw new NullPointerException();
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


    /**
     * This method query about which topologies
     * are currently in the memory
     *
     *
     * @return     it returns topologies that is stored in the memory
     */
    public String queryTopologies(){
        String topologiesInMemory="";
        for (var entry : topologyHashMap.entrySet()) {
            topologiesInMemory+=(entry.getValue().toString());
        }
        return topologiesInMemory;
    }


    /**
     * This method query about which devices are
     * in a given topology
     *
     *
     * @param  topologyID  id of topology to be queried about
     * @return      List of devices that is in the given topology
     */
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


    /**
     * This method queries about which devices is
     * connected to a given netlist node in a given
     * topology
     *
     *
     * @param  topologyID  id of topology to be queried about
     * @param netListNode netlistNode to search for devices connected to it
     * @return      list of devices that is connected to the given Netlist
     */
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


    /**
     * This method deletes a given topology form
     * the memory
     *
     *
     * @param  topologyID  id of topology to be deleted
     */
    public void deleteTopology(String topologyID){
        if(!(topologyHashMap.containsKey(topologyID)))
            throw new NullPointerException();
        topologyHashMap.remove(topologyID);
    }

    
}
