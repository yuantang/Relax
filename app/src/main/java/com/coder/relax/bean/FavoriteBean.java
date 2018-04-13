package com.coder.relax.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by TUS on 2018/4/9.
 */

public class FavoriteBean implements Serializable {
    int id;
    String name;
    HashMap map;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap getMap() {
        return map;
    }

    public void setMap(HashMap map) {
        this.map = map;
    }
}
