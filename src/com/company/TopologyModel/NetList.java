package com.company.TopologyModel;

import java.util.Objects;

public class NetList {
    public String t1;
    public String t2;
    public String drain;
    public String gate;
    public String source;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetList netList = (NetList) o;
        return Objects.equals(t1, netList.t1) && Objects.equals(t2, netList.t2) && Objects.equals(drain, netList.drain) && Objects.equals(gate, netList.gate) && Objects.equals(source, netList.source);
    }

    @Override
    public String toString() {
        return "NetList{" +
                "t1='" + t1 + '\'' +
                ", t2='" + t2 + '\'' +
                ", drain='" + drain + '\'' +
                ", gate='" + gate + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
