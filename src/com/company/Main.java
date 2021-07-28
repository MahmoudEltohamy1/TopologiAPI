package com.company;

import com.company.TopologyModel.CircuitElement;
import com.company.TopologyModel.NetList;
import com.company.TopologyModel.Topology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
static HashMap<String,Topology> topologyHashMap= new HashMap<String,Topology>();
    public static void main(String[] args) {
	// write your code here
        API api=new API();
        String result = api.readJSON("topology.txt");
        System.out.println(result);
        JsonService jsonService=new JsonService();
        Topology topology=jsonService.fromJson(result, Topology.class);
        topologyHashMap.put(topology.id,topology);
        System.out.println(topology.id);
        System.out.println(topology.components.get(1).m1.defaultValue);
        api.writeJSON(topology.id);
        System.out.println(api.queryTopologies());
        List<CircuitElement> circuitElementList=api.queryDevices(topology.id);
        System.out.println(circuitElementList.get(0).max+"       "+circuitElementList.get(1).max);
        NetList netList =new NetList();
        netList.t1="vdd";
        netList.t2="n1";
        List<CircuitElement> circuitElementList1=api.queryDevicesWithNetlistNode(topology.id,netList);
        System.out.println(circuitElementList1.get(0).max);
    }
}
