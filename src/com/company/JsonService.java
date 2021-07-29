package com.company;



import com.company.TopologyModel.Topology;
import com.google.gson.Gson;



public class JsonService  {
    private static Gson gson = new Gson();



    /**
     * This method takes a JSON string and convert it
     * into a java classes model.
     *
     *
     * @param  jsonString  JSON string to be converted
     * @param  classOfT    class that will contain the JSON string
     * @return  a java class that represents the JSON string
     */
    public <T> T fromJson(String jsonString, Class<T> classOfT) {
        if(jsonString.compareToIgnoreCase("")==0)
            throw new IllegalArgumentException();
        if(classOfT==null)
            throw new NullPointerException();
        Topology topology = gson.fromJson(jsonString, Topology.class);
        return (T) topology;
    }



    /**
     * This method takes an object and convert it's information
     * into JSON string.
     *
     *
     * @param  object  java object to be converted into JSON string
     * @return      JSON string that contains the object information
     */
    public String toJson(Object object) {
        if(object==null)
            throw new NullPointerException();
        String jsonResponse=gson.toJson(object);
        return jsonResponse;
    }
}
