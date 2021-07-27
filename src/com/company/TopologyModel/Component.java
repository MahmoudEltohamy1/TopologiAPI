package com.company.TopologyModel;

import com.google.gson.annotations.SerializedName;

public class Component {
    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CircuitElement getResistance() {
        return resistance;
    }

    public void setResistance(CircuitElement resistance) {
        this.resistance = resistance;
    }

    public CircuitElement getP1() {
        return p1;
    }

    public void setP1(CircuitElement p1) {
        this.p1 = p1;
    }

    public CircuitElement getM1() {
        return m1;
    }

    public void setM1(CircuitElement m1) {
        this.m1 = m1;
    }

    public NetList getNetList() {
        return netList;
    }

    public void setNetList(NetList netList) {
        this.netList = netList;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Component()
    {
        resistance=new CircuitElement();
        p1=new CircuitElement();
        m1 =new CircuitElement();
    }
    public String type;
    public String id;
    public CircuitElement resistance;//resistance
    @SerializedName("p(1)")
    public CircuitElement p1;//pmos
    @SerializedName("m(1)")
    public CircuitElement m1;// nmos
    public NetList netList;

    /*public String netlist le kol resistance*/
}
