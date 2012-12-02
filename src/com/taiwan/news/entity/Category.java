package com.taiwan.news.entity;

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
        String message = "[{\"id\":1,\"name\":\"\u7126\u9ede\"},{\"id\":2,\"name\":\"\u653f\u6cbb\"},{\"id\":3,\"name\":\"\u8ca1\u7d93\"},{\"id\":4,\"name\":\"\u80a1\u5e02\"},{\"id\":5,\"name\":\"\u5169\u5cb8\"},{\"id\":6,\"name\":\"\u570b\u969b\"},{\"id\":7,\"name\":\"\u793e\u6703\"},{\"id\":8,\"name\":\"\u5730\u65b9\"},{\"id\":9,\"name\":\"\u5a1b\u6a02\"},{\"id\":10,\"name\":\"\u6a02\u6d3b\"},{\"id\":11,\"name\":\"\u79d1\u6280\"},{\"id\":12,\"name\":\"\u904b\u52d5\"},{\"id\":13,\"name\":\"\u85dd\u6587\"},{\"id\":14,\"name\":\"\u8ad6\u58c7\"},{\"id\":15,\"name\":\"\u8da3\u805e\"},{\"id\":16,\"name\":\"\u79d1\u6280\"},{\"id\":17,\"name\":\"\u8ca1\u7d93\"},{\"id\":18,\"name\":\"\u653f\u6cbb\"},{\"id\":19,\"name\":\"\u9ad4\u80b2\"},{\"id\":20,\"name\":\"\u5f71\u5287\"},{\"id\":21,\"name\":\"\u793e\u6703\"},{\"id\":22,\"name\":\"\u570b\u969b\"},{\"id\":23,\"name\":\"\u751f\u6d3b\"}]";
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
}
