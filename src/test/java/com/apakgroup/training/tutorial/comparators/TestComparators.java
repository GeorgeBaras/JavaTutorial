package com.apakgroup.training.tutorial.comparators;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.apakgroup.training.comparators.Colour;
import com.apakgroup.training.comparators.FooChainComparator;
import com.apakgroup.training.comparators.MyFoo;
import com.apakgroup.training.comparators.MyFooComparator;
import com.rc.retroweaver.runtime.Collections;

public class TestComparators {

    private List<MyFoo> fooList;

    @Before
    public void setUp() throws Exception {
        fooList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            fooList.add(MyFooCreator());
        }
    }

    @Test
    public final void testMyFooComparator() {
        Collections.sort(fooList, new MyFooComparator());
        MyFoo foo;
        MyFoo nextFoo;
        String message = "no Error occurred";
        boolean orderedCorrectly = false;
        for (MyFoo foo1 : fooList) {
            System.out.println(foo1.getField1() + " " + foo1.getField2() + " " + foo1.getField3() + " "
                    + foo1.getField4() + " " + foo1.getField5());
        }
        Iterator fooListIterator = fooList.iterator();

        foo = (MyFoo) fooListIterator.next();
        while (fooListIterator.hasNext()) {
            nextFoo = (MyFoo) fooListIterator.next();
            if (this.compareString(foo.getField1(), nextFoo.getField1()) < 0) {
                orderedCorrectly = true;
            } else if (this.compareString(foo.getField1(), nextFoo.getField1()) == 0) {
                if (foo.getField2() > nextFoo.getField2()) {
                    orderedCorrectly = true;
                } else if (foo.getField2() == nextFoo.getField2()) {
                    if (this.compareEnum(foo.getField3(), nextFoo.getField3()) > 0) {
                        orderedCorrectly = true;
                    } else if (this.compareEnum(foo.getField3(), nextFoo.getField3()) == 0) {
                        if (this.compareInteger(foo.getField4(), nextFoo.getField4()) > 0) {
                            orderedCorrectly = true;
                        } else if (this.compareInteger(foo.getField4(), nextFoo.getField4()) == 0) {
                            if (foo.getField5() >= nextFoo.getField5()) {
                                orderedCorrectly = true;
                            } else {
                                orderedCorrectly = false;
                                message = "the sorting failed at the double field5";
                                break;
                            }
                        } else {
                            orderedCorrectly = false;
                            message = "the sorting failed at the Integer field4";
                            break;
                        }
                    } else {
                        orderedCorrectly = false;
                        message = "the sorting failed at the Enum field3";
                        break;
                    }
                } else {
                    orderedCorrectly = false;
                    message = "the sorting failed at the int field2";
                    break;
                }
            } else {
                orderedCorrectly = false;
                message = "the sorting failed at the String field1";
                break;
            }

            foo = nextFoo;
        }

        assertEquals(message, true, orderedCorrectly);
    }

    @Test
    public final void testFooChainComparator() {
        Collections.sort(fooList, new FooChainComparator());
        // reverse the fooList
        Collections.reverse(fooList);

        MyFoo foo;
        MyFoo nextFoo;
        String message = "no Error occurred";
        boolean orderedCorrectly = false;
        for (MyFoo foo1 : fooList) {
            System.out.println(foo1.getField1() + " " + foo1.getField2() + " " + foo1.getField3() + " "
                    + foo1.getField4() + " " + foo1.getField5());
        }
        Iterator fooListIterator = fooList.iterator();

        foo = (MyFoo) fooListIterator.next();
        while (fooListIterator.hasNext()) {
            nextFoo = (MyFoo) fooListIterator.next();
            if (this.compareString(foo.getField1(), nextFoo.getField1()) > 0) {
                orderedCorrectly = true;
            } else if (this.compareString(foo.getField1(), nextFoo.getField1()) == 0) {
                if (foo.getField2() < nextFoo.getField2()) {
                    orderedCorrectly = true;
                } else if (foo.getField2() == nextFoo.getField2()) {
                    if (this.compareEnum(foo.getField3(), nextFoo.getField3()) < 0) {
                        orderedCorrectly = true;
                    } else if (this.compareEnum(foo.getField3(), nextFoo.getField3()) == 0) {
                        if (this.compareInteger(foo.getField4(), nextFoo.getField4()) < 0) {
                            orderedCorrectly = true;
                        } else if (this.compareInteger(foo.getField4(), nextFoo.getField4()) == 0) {
                            if (foo.getField5() <= nextFoo.getField5()) {
                                orderedCorrectly = true;
                            } else {
                                orderedCorrectly = false;
                                message = "the sorting failed at the double field5";
                                break;
                            }
                        } else {
                            orderedCorrectly = false;
                            message = "the sorting failed at the Integer field4";
                            break;
                        }
                    } else {
                        orderedCorrectly = false;
                        message = "the sorting failed at the Enum field3";
                        break;
                    }
                } else {
                    orderedCorrectly = false;
                    message = "the sorting failed at the int field2";
                    break;
                }
            } else {
                orderedCorrectly = false;
                message = "the sorting failed at the String field1";
                break;
            }

            foo = nextFoo;
        }

        assertEquals(message, true, orderedCorrectly);
    }

    public final MyFoo MyFooCreator() {
        // for the string field1
        String field1 = UUID.randomUUID().toString().substring(0, 2);
        // for the int field2
        Random random = new Random();
        int field2 = random.nextInt(100);
        // for the enum field3
        Colour field3;
        switch (field2 % 4) {
            case 0:
                field3 = Colour.Red;
                break;
            case 1:
                field3 = Colour.Green;
                break;
            case 2:
                field3 = Colour.Blue;
                break;
            default:
                field3 = null;
                break;
        }
        // for the Integer
        Integer field4;
        if (field2 % 15 == 0) {
            field4 = null;
        } else {
            field4 = field2 + random.nextInt(5);
        }
        // for the double field5
        double field5 = field2 / (random.nextInt(3) + 1);

        return new MyFoo(field1, field2, field3, field4, field5);
    }

    public int compareString(String a, String b) {
        int result;
        if (a == null) {
            if (b == null) {
                result = 0;
            } else {
                result = -1;
            }
        } else if (b == null) {
            result = 1;
        } else {
            result = a.compareTo(b);
        }

        return result;
    }

    public int compareEnum(Colour a, Colour b) {
        int result;
        if (a == null) {
            if (b == null) {
                result = 0;
            } else {
                result = -1;
            }
        } else if (b == null) {
            result = 1;
        } else {
            result = a.compareTo(b);
        }

        return result;
    }

    public int compareInteger(Integer a, Integer b) {
        int result;
        if (a == null) {
            if (b == null) {
                result = 0;
            } else {
                result = -1;
            }
        } else if (b == null) {
            result = 1;
        } else if (a > b) {
            result = 1;
        } else if (a < b) {
            result = -1;
        } else {
            result = 0;
        }
        return result;
    }

}
