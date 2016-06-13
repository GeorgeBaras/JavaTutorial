package com.apakgroup.training.comparators;

import java.util.Comparator;

public class MyFooComparator implements Comparator<MyFoo> {

    @Override
    public int compare(MyFoo a, MyFoo b) {
        // Get all the fields

        // compare the objects
        if (a == null) {
            if (b == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (b == null) {
            return 1;
        }

        String afield1 = a.getField1();
        String bfield1 = b.getField1();
        int afield2 = a.getField2();
        int bfield2 = b.getField2();
        Colour afield3 = a.getField3();
        Colour bfield3 = b.getField3();
        Integer afield4 = a.getField4();
        Integer bfield4 = b.getField4();
        double afield5 = a.getField5();
        double bfield5 = b.getField5();
        // compare the fields // the following works for a field that might be null
        if (afield1 == null) {
            if (bfield1 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (bfield1 == null) {
            return 1;
        } else if (afield1.compareTo(bfield1) != 0) {
            return afield1.compareTo(bfield1);
        }
        // int field2 which cannot be null
        if (afield2 < bfield2) {
            return 1;
        } else if (afield2 > bfield2) {
            return -1;
        }
        // dealing with enum field3 which can be null
        if (afield3 == null) {
            if (bfield3 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (bfield3 == null) {
            return 1;
        } else if (afield3.compareTo(bfield3) != 0) {
            return afield3.compareTo(bfield3);
        }
        // Integer field4

        if (afield4 == null) {
            if (bfield4 == null) {
                return 0;
            } else {
                return -1;
            }
        } else if (bfield4 == null) {
            return 1;
        } else if (afield4 < bfield4) {
            return 1;
        } else if (afield4 > bfield4) {
            return -1;
        }

        // double field5 which cannot be null
        if (afield5 < bfield5) {
            return 1;
        } else if (afield5 > bfield5) {
            return -1;
        }

        return 0;
    }

}
