package com.taiwan.news.entity;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;

public class Category {
    int    id;
    String name;

    public Category() {
        this(-1, "");
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return name;
    }

    public void setCateName(String name) {
        this.name = name;
    }

    public static String getCategoryName(int id) {
        String message = "[{\"id\":1,\"name\":\"\u570b\u969b\",\"source_id\":1},{\"id\":2,\"name\":\"\u751f\u6d3b\",\"source_id\":1},{\"id\":3,\"name\":\"\u5a1b\u6a02\",\"source_id\":1},{\"id\":4,\"name\":\"\u793e\u6703\",\"source_id\":1},{\"id\":5,\"name\":\"\u653f\u6cbb\",\"source_id\":1},{\"id\":6,\"name\":\"\u9ad4\u80b2\",\"source_id\":1},{\"id\":7,\"name\":\"\u8ca1\u7d93\",\"source_id\":1},{\"id\":8,\"name\":\"\u751f\u6d3b\",\"source_id\":2},{\"id\":9,\"name\":\"\u8ca1\u7d93\",\"source_id\":2},{\"id\":10,\"name\":\"\u653f\u6cbb\",\"source_id\":2},{\"id\":11,\"name\":\"\u79d1\u6280\",\"source_id\":2},{\"id\":12,\"name\":\"\u570b\u969b\",\"source_id\":2},{\"id\":13,\"name\":\"\u9ad4\u80b2\",\"source_id\":2},{\"id\":14,\"name\":\"\u5f71\u5287\",\"source_id\":2},{\"id\":15,\"name\":\"\u8da3\u805e\",\"source_id\":2},{\"id\":16,\"name\":\"\u793e\u6703\",\"source_id\":2},{\"id\":17,\"name\":\"\u8981\u805e\",\"source_id\":3},{\"id\":18,\"name\":\"\u793e\u6703\",\"source_id\":3},{\"id\":19,\"name\":\"\u5730\u65b9\",\"source_id\":3},{\"id\":20,\"name\":\"\u5169\u5cb8\",\"source_id\":3},{\"id\":21,\"name\":\"\u5373\u6642\u65b0\u805e\",\"source_id\":5},{\"id\":22,\"name\":\"\u570b\u969b\",\"source_id\":3},{\"id\":23,\"name\":\"\u8ca1\u7d93\",\"source_id\":3},{\"id\":24,\"name\":\"\u904b\u52d5\",\"source_id\":3},{\"id\":25,\"name\":\"\u5a1b\u6a02\",\"source_id\":3},{\"id\":26,\"name\":\"\u751f\u6d3b\",\"source_id\":3},{\"id\":27,\"name\":\"\u7126\u9ede\",\"source_id\":4},{\"id\":28,\"name\":\"\u653f\u6cbb\",\"source_id\":4},{\"id\":29,\"name\":\"\u8ca1\u7d93\",\"source_id\":4},{\"id\":30,\"name\":\"\u80a1\u5e02\",\"source_id\":4},{\"id\":31,\"name\":\"\u5169\u5cb8\",\"source_id\":4},{\"id\":32,\"name\":\"\u570b\u969b\",\"source_id\":4},{\"id\":33,\"name\":\"\u793e\u6703\",\"source_id\":4},{\"id\":34,\"name\":\"\u5730\u65b9\",\"source_id\":4},{\"id\":35,\"name\":\"\u5a1b\u6a02\",\"source_id\":4},{\"id\":36,\"name\":\"\u6a02\u6d3b\",\"source_id\":4},{\"id\":37,\"name\":\"\u79d1\u6280\",\"source_id\":4},{\"id\":38,\"name\":\"\u904b\u52d5\",\"source_id\":4},{\"id\":39,\"name\":\"\u85dd\u6587\",\"source_id\":4},{\"id\":40,\"name\":\"\u8ad6\u58c7\",\"source_id\":4}]";
        HashMap hash = new HashMap();
        JSONArray categoryArray;
        try {
            categoryArray = new JSONArray(message.toString());
            for (int i = 0; i < categoryArray.length(); i++) {
                int category_id = categoryArray.getJSONObject(i).getInt("id");
                String name = categoryArray.getJSONObject(i).getString("name");
                hash.put(category_id, name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (String) hash.get(id);
    }

    public static ArrayList<Category> getCategory(int source_id) {
        String message = "[{\"id\":1,\"name\":\"\u570b\u969b\",\"source_id\":1},{\"id\":2,\"name\":\"\u751f\u6d3b\",\"source_id\":1},{\"id\":3,\"name\":\"\u5a1b\u6a02\",\"source_id\":1},{\"id\":4,\"name\":\"\u793e\u6703\",\"source_id\":1},{\"id\":5,\"name\":\"\u653f\u6cbb\",\"source_id\":1},{\"id\":6,\"name\":\"\u9ad4\u80b2\",\"source_id\":1},{\"id\":7,\"name\":\"\u8ca1\u7d93\",\"source_id\":1},{\"id\":8,\"name\":\"\u751f\u6d3b\",\"source_id\":2},{\"id\":9,\"name\":\"\u8ca1\u7d93\",\"source_id\":2},{\"id\":10,\"name\":\"\u653f\u6cbb\",\"source_id\":2},{\"id\":11,\"name\":\"\u79d1\u6280\",\"source_id\":2},{\"id\":12,\"name\":\"\u570b\u969b\",\"source_id\":2},{\"id\":13,\"name\":\"\u9ad4\u80b2\",\"source_id\":2},{\"id\":14,\"name\":\"\u5f71\u5287\",\"source_id\":2},{\"id\":15,\"name\":\"\u8da3\u805e\",\"source_id\":2},{\"id\":16,\"name\":\"\u793e\u6703\",\"source_id\":2},{\"id\":17,\"name\":\"\u8981\u805e\",\"source_id\":3},{\"id\":18,\"name\":\"\u793e\u6703\",\"source_id\":3},{\"id\":19,\"name\":\"\u5730\u65b9\",\"source_id\":3},{\"id\":20,\"name\":\"\u5169\u5cb8\",\"source_id\":3},{\"id\":21,\"name\":\"\u5373\u6642\u65b0\u805e\",\"source_id\":5},{\"id\":22,\"name\":\"\u570b\u969b\",\"source_id\":3},{\"id\":23,\"name\":\"\u8ca1\u7d93\",\"source_id\":3},{\"id\":24,\"name\":\"\u904b\u52d5\",\"source_id\":3},{\"id\":25,\"name\":\"\u5a1b\u6a02\",\"source_id\":3},{\"id\":26,\"name\":\"\u751f\u6d3b\",\"source_id\":3},{\"id\":27,\"name\":\"\u7126\u9ede\",\"source_id\":4},{\"id\":28,\"name\":\"\u653f\u6cbb\",\"source_id\":4},{\"id\":29,\"name\":\"\u8ca1\u7d93\",\"source_id\":4},{\"id\":30,\"name\":\"\u80a1\u5e02\",\"source_id\":4},{\"id\":31,\"name\":\"\u5169\u5cb8\",\"source_id\":4},{\"id\":32,\"name\":\"\u570b\u969b\",\"source_id\":4},{\"id\":33,\"name\":\"\u793e\u6703\",\"source_id\":4},{\"id\":34,\"name\":\"\u5730\u65b9\",\"source_id\":4},{\"id\":35,\"name\":\"\u5a1b\u6a02\",\"source_id\":4},{\"id\":36,\"name\":\"\u6a02\u6d3b\",\"source_id\":4},{\"id\":37,\"name\":\"\u79d1\u6280\",\"source_id\":4},{\"id\":38,\"name\":\"\u904b\u52d5\",\"source_id\":4},{\"id\":39,\"name\":\"\u85dd\u6587\",\"source_id\":4},{\"id\":40,\"name\":\"\u8ad6\u58c7\",\"source_id\":4}]";
        ArrayList<Category> apple = new ArrayList<Category>();
        ArrayList<Category> free = new ArrayList<Category>();
        ArrayList<Category> union = new ArrayList<Category>();
        ArrayList<Category> china = new ArrayList<Category>();
        ArrayList<Category> economy = new ArrayList<Category>();

        JSONArray categoryArray;
        try {
            categoryArray = new JSONArray(message.toString());
            for (int i = 0; i < categoryArray.length(); i++) {
                int category_id = categoryArray.getJSONObject(i).getInt("id");
                String name = categoryArray.getJSONObject(i).getString("name");
                int source = categoryArray.getJSONObject(i).getInt("source_id");
                Category c = new Category(category_id, name);
                switch (source) {
                case 1:
                    apple.add(c);
                    break;
                case 2:
                    free.add(c);
                    break;
                case 3:
                    union.add(c);
                    break;
                case 4:
                    china.add(c);
                    break;
                case 5:
                    economy.add(c);
                    break;
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        switch (source_id) {
        case 1:
            return apple;
        case 2:
            return free;
        case 3:
            return union;
        case 4:
            return china;
        case 5:
            return economy;
        default:
            return apple;
        }
    }
}
