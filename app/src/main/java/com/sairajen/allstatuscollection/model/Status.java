package com.sairajen.allstatuscollection.model;

import java.io.Serializable;

/**
 * @author Gmonetix
 */

public class Status implements Serializable {

    private int id;
    private String status;
    private String title;
    private String extra;

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
