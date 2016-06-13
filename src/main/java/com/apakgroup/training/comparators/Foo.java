package com.apakgroup.training.comparators;

public class Foo implements Comparable<Foo> {

    private int orderId = 0;

    private String name;

    public Foo(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    @Override
    public int compareTo(Foo o) {
        if (this.orderId < o.getOrderId()) {
            return -1;
        }
        return 1;
    }

    public String getName() {
        return name;
    }

}