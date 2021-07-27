package com.company.TopologyModel;

import com.google.gson.annotations.SerializedName;

public class Component {
    public Component()
    {
      //  resistance=new CircuitElement();
        //p1=new CircuitElement();
        // m1 =new CircuitElement();
    }
    public String type;
    public String id;
    public CircuitElement resistance;//resistance
    @SerializedName("p(1)")
    public CircuitElement p1;//pmos
    @SerializedName("m(1)")
    public CircuitElement m1;// nmos
    public NetList netlist;

    /*public String netlist le kol resistance*/
}
