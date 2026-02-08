package org.example.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// to ignore any other attribute not in this pojo file
@JsonIgnoreProperties(ignoreUnknown = true)

public class ItemIdPojo {
    private String id;

    public ItemIdPojo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}