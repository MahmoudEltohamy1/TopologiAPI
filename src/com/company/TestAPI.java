package com.company;

import com.company.TopologyModel.CircuitElement;
import com.company.TopologyModel.Component;
import com.company.TopologyModel.NetList;
import com.company.TopologyModel.Topology;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.company.Main.topologyHashMap;
import static org.junit.Assert.*;

public class TestAPI {

    @Test(expected = NullPointerException.class)
    public void testReadJSONWithNullFile() {
        API api = new API();
        try {
            String result = api.readJSON(null);
        } catch (FileNotFoundException e) {
        }

    }

    @Test()
    public void testReadJSONWithWrongFileName() {
        API api = new API();
        try {
            String result = api.readJSON("wrongname");
        } catch (FileNotFoundException e) {
            assertTrue(true);

        }
    }

    @Test()
    public void testReadJSONWithExistingFileName() {
        API api = new API();
        try {
            String result = api.readJSON("topology.txt");
            String expected = "{\t\"id\": \"top1\",    \"components\": [        {            \"type\": \"resistor\",            \"id\": \"res1\",            \"resistance\": {                \"default\": 100,                \"min\": 10,                \"max\":1000            },            \"netlist\": {                \"t1\": \"vdd\",                \"t2\": \"n1\"            }        },        {            \"type\": \"nmos\",            \"id\": \"m1\",            \"m(1)\": {                \"default\": 1.5,                \"min\": 1,                \"max\": 2            },            \"netlist\": {                \"drain\": \"n1\",                \"gate\": \"vin\",                \"source\": \"vss\"            }        }    ]}";
            assertEquals(expected, result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWriteJSONWithExistingTopologyID() {
        API api = new API();
        String expected = "{\"id\":\"top1\",\"components\":[]}";
        try {
            Topology topology = new Topology();
            topology.id = "top1";
            topologyHashMap.put(topology.id, topology);
            api.writeJSON("top1");
            String result = api.readJSON("topologyOut.txt");
            assertEquals(expected, result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void testWriteJSONWithNotExistingTopologyID() {
        API api = new API();
        Topology topology = new Topology();
        topology.id = "top1";
        topologyHashMap.put(topology.id, topology);
        api.writeJSON("top2");
    }

    @Test
    public void testQueryTopologiesWithEmptyMemory()
    {
        topologyHashMap.clear();
        API api=new API();
        String result=api.queryTopologies();
        String expected="";
        assertEquals(expected,result);
    }

    @Test
    public void testQueryTopologies()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        String result=api.queryTopologies();
        String expected="Topology{id='top1', components=[Component{type='resistance', id='res1', resistance=null, p1=null, m1=null, netlist=null}]}\n";
        assertEquals(expected,result);
    }

    @Test
    public void testQueryDevicesWithExistingTopologyID()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        CircuitElement resistance=new CircuitElement();
        resistance.max=1000;
        resistance.defaultValue=100.5;
        resistance.min=50;
        component.resistance=resistance;
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        ArrayList<CircuitElement> result= (ArrayList<CircuitElement>) api.queryDevices("top1");
        List<CircuitElement> expected=new ArrayList<CircuitElement>();
        expected.add(resistance);
        assertEquals(expected,result);
    }
    @Test(expected = NullPointerException.class)
    public void testQueryDevicesWithNotExistingTopologyID()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        CircuitElement resistance=new CircuitElement();
        resistance.max=1000;
        resistance.defaultValue=100.5;
        resistance.min=50;
        component.resistance=resistance;
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        ArrayList<CircuitElement> result= (ArrayList<CircuitElement>) api.queryDevices("top2");
    }

    @Test
    public void testQueryDevicesWithNetlistNode()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        CircuitElement resistance=new CircuitElement();
        resistance.max=1000;
        resistance.defaultValue=100.5;
        resistance.min=50;
        component.resistance=resistance;
        NetList netList=new NetList();
        netList.t1="vdd";
        netList.t2="n1";
        component.netlist=netList;
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        ArrayList<CircuitElement> result= (ArrayList<CircuitElement>) api.queryDevicesWithNetlistNode(topology.id,netList);
        List<CircuitElement> expected=new ArrayList<CircuitElement>();
        expected.add(resistance);
        assertEquals(expected,result);
    }

    @Test(expected = NullPointerException.class)
    public void testQueryDevicesWithNetlistNodeWrongTopologyID()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        CircuitElement resistance=new CircuitElement();
        resistance.max=1000;
        resistance.defaultValue=100.5;
        resistance.min=50;
        component.resistance=resistance;
        NetList netList=new NetList();
        netList.t1="vdd";
        netList.t2="n1";
        component.netlist=netList;
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        ArrayList<CircuitElement> result= (ArrayList<CircuitElement>) api.queryDevicesWithNetlistNode("top2",netList);
    }

    @Test(expected = NullPointerException.class)
    public void testQueryDevicesWithNetlistNodeDiffrentNetlist()
    {
        API api=new API();
        Topology topology=new Topology();
        topology.id="top1";
        Component component=new Component();
        component.id="res1";
        component.type="resistance";
        CircuitElement resistance=new CircuitElement();
        resistance.max=1000;
        resistance.defaultValue=100.5;
        resistance.min=50;
        component.resistance=resistance;
        NetList netList=new NetList();
        netList.t1="vdd";
        netList.t2="n1";
        component.netlist=netList;
        topology.components.add(component);
        topologyHashMap.put(topology.id,topology);
        NetList netList1=new NetList();
        netList1.t1="vvdd";
        netList1.t2="n1";
        ArrayList<CircuitElement> result= (ArrayList<CircuitElement>) api.queryDevicesWithNetlistNode("top2",netList1);
    }

    @Test( expected = NullPointerException.class)
    public void testDeleteTopologyWithNotExistingID()
    {
        API api=new API();
        Topology topology =new Topology();
        topology.id="top1";
        topologyHashMap.put(topology.id,topology);
        api.deleteTopology("top2");
    }

    @Test
    public void testDeleteTopologyWithExistingID()
    {
        API api=new API();
        Topology topology =new Topology();
        topology.id="top1";
        topologyHashMap.put(topology.id,topology);
        api.deleteTopology("top1");
        if(!(topologyHashMap.containsKey("top1")))
            assertTrue(true);
    }


}