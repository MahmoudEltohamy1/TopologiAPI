package com.company;

import com.company.TopologyModel.Topology;

public class Main {

    public static void main(String[] args) {
	// write your code here
        API api=new API();
        String result = api.readJSON("C:\\Users\\dell\\Downloads\\topology.txt");
        System.out.println(result);
        JsonService jsonService=new JsonService();
        Topology topology=jsonService.fromJson(result, Topology.class);
        System.out.println(topology.id);
        System.out.println(topology.components.get(1).m1.defaultValue);
    }
}
