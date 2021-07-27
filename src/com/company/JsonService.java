package com.company;



import com.company.TopologyModel.Topology;
import com.google.gson.Gson;



public class JsonService  {
    private static Gson gson = new Gson();


    public <T> T fromJson(String jsonString, Class<T> classOfT) {
        if(jsonString.compareToIgnoreCase("")==0)
            throw new IllegalArgumentException();
        if(classOfT==null)
            throw new NullPointerException();
        Topology topology = gson.fromJson(jsonString, Topology.class);
        return (T) topology;
    }


    public String toJson(Object object) {
        if(object==null)
            throw new NullPointerException();
        String jsonResponse=gson.toJson(object);
        return jsonResponse;
    }
}
