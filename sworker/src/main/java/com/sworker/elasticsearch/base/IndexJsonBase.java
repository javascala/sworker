package com.sworker.elasticsearch.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public abstract class IndexJsonBase<E> {

//    public static final String ID = "id";
//    public static final String NAME = "name";
//    public static final String FUNCTION = "function";
//    public static final String TYPE = "type";

    public List<String> indexs;

    public IndexJsonBase() {
    }

    /**
     * 实现将实体对象转换成json对象
     * 
     * @param baseClass 应用对象
     * @return json对象
     */
    public String convert(E baseClass) {
        String jsonData = null;

        try {
            // 使用XContentBuilder创建json数据
            XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
            jsonBuild.startObject();
            prepareIndex(baseClass, jsonBuild);
            jsonBuild.endObject();
            
            jsonData = jsonBuild.string();
            System.out.println(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    /**
     * 实现将实体对象集合转换成json对象集合
     * 
     * @param medicineList Medicine对象集合
     * @return json对象集合
     */
    public List<String> convert(List<E> list) {
        List<String> jsonDataList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            jsonDataList.add(convert(list.get(i)));
        }
        return jsonDataList;
    }

    /**
     * 指明要创建索引的字段
     * @param baseClass
     * @param jsonBuild
     * @throws IOException
     */
    protected abstract void prepareIndex(E baseClass,
            XContentBuilder jsonBuild) throws IOException;

}
