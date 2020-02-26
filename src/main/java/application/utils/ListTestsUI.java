package application.utils;

import application.enums.TableColumn;
import application.serializableObject.TestSerializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ListTestsUI {

    public static List<TestSerializable> getListTestUI(ArrayList<HashMap> list) {
        ArrayList<TestSerializable> tests = new ArrayList<>();
        for (HashMap<String, String> testString : list) {
            tests.add(new TestSerializable(
                    testString.get(TableColumn.DURATION.getTitle()),
                    testString.get(TableColumn.METHOD.getTitle()),
                    testString.get(TableColumn.TEST.getTitle()),
                    testString.get(TableColumn.START_TIME.getTitle()),
                    testString.get(TableColumn.END_TIME.getTitle()),
                    testString.get(TableColumn.RESULT.getTitle())));
        }
        return tests;
    }

    public static List sortCollection(List<TestSerializable> tests) {
        Collections.sort(tests, TestSerializable.COMPARE_BY_START_TIME);
        Collections.reverse(tests);
        return tests;
    }

    public static boolean contains(List l1, List l2) {
        boolean res = false;
        for (Object o : l1) {
            if (l2.contains(o)) {
                res = true;
            } else return res;
        }
        return res;
    }
}
