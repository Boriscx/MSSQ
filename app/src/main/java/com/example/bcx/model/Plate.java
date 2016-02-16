package com.example.bcx.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by bcx on 2016/1/30.
 * 板块（Plate）对象属性
 */
public class Plate {
    private UUID mplate_id;
    private String mplate_name;
    private static final String JSON_PLATE_ID="plate_id";
    private static final String JSON_PLATE_NAME="plate_name";

    public Plate(String PlateName){
        this.mplate_name=PlateName;
        this.mplate_id=UUID.randomUUID();
    }
    public JSONObject toJSON() throws JSONException {
        JSONObject json=new JSONObject();
        json.put(JSON_PLATE_ID,mplate_id.toString());
        json.put(JSON_PLATE_NAME,mplate_name);
        return json;
    }
    public Plate(JSONObject json) throws JSONException {
        mplate_id=UUID.fromString(json.getString(JSON_PLATE_ID));
        mplate_name=json.getString(JSON_PLATE_NAME);
    }

    public String getPlate_name() {
        return mplate_name;
    }

    public UUID getPlate_id() {
        return mplate_id;
    }

}
