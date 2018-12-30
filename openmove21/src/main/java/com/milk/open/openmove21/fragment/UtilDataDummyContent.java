package com.milk.open.openmove21.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilDataDummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Search tickets"));
        addItem(new DummyItem("2", "Search trips"));
        addItem(new DummyItem("3", "Timetable"));
        addItem(new DummyItem("4", "Advise"));
        addItem(new DummyItem("5", "My tickets"));
        addItem(new DummyItem("6", "Profile"));
        addItem(new DummyItem("7", "Credit"));
        addItem(new DummyItem("8", "Privilege cards"));
        addItem(new DummyItem("9", "Gift"));
        addItem(new DummyItem("10", "Statistics"));
        addItem(new DummyItem("11", "About"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String content;

        public DummyItem(String id, String content) {
            this.id = id;
            this.content = content;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
