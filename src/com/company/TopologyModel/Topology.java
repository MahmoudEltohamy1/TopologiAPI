package com.company.TopologyModel;

import java.util.ArrayList;
import java.util.List;

public class Topology {
    public Topology()
    {
        components=new ArrayList<Component>();
    }
    public String id;
    public List<Component> components;

    @Override
    public String toString() {
        return "Topology{" +
                "id='" + id + '\'' +
                ", components=" + components +
                '}'+'\n';
    }
}
