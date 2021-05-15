package com.example.demo.model;

public class Field
{
    private int field_id;
    private String field_name;
    private String field_type;

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getField_type() {
        return field_type;
    }

    public void setField_type(String field_type) {
        this.field_type = field_type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "field_id=" + field_id +
                ", field_name='" + field_name + '\'' +
                ", field_type='" + field_type + '\'' +
                '}';
    }
}
