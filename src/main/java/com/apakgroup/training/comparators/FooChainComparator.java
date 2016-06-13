package com.apakgroup.training.comparators;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class FooChainComparator implements Comparator<MyFoo> {

    @Override
    public int compare(MyFoo o1, MyFoo o2) {
        return ComparisonChain.start().compare(o1.getField1(), o2.getField1(), Ordering.natural().nullsLast())
                .compare(o1.getField2(), o2.getField2())
                .compare(o1.getField3(), o2.getField3(), Ordering.natural().nullsLast())
                .compare(o1.getField4(), o2.getField4(), Ordering.natural().nullsLast())
                .compare(o1.getField5(), o2.getField5()).result();
    }

}
