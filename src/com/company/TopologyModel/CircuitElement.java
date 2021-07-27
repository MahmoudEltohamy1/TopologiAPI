package com.company.TopologyModel;

import com.google.gson.annotations.SerializedName;

public class CircuitElement {
    @SerializedName("default")
    public Double defaultValue;
    public Integer min;
    public Integer max;
}
