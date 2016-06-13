package com.apakgroup.training.comparators;

public class MyFoo {

    private String field1;

    private int field2;

    private Colour field3;

    private Integer field4;

    private double field5;

    public MyFoo(String field1, int field2, Colour field3, Integer field4, double field5) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public Colour getField3() {
        return field3;
    }

    public void setField3(Colour field3) {
        this.field3 = field3;
    }

    public Integer getField4() {
        return field4;
    }

    public void setField4(Integer field4) {
        this.field4 = field4;
    }

    public double getField5() {
        return field5;
    }

    public void setField5(double field5) {
        this.field5 = field5;
    }

}
