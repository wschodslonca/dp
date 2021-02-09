package com.project.dp.Filter;
import java.util.ArrayList;
import java.util.List;

public class KeyTable {
    String tableName;
    List<String> combinations;
    String primaryKey;

    public KeyTable(String table, String primaryKey) {
        this.tableName = table;
        this.primaryKey = primaryKey;
        combinations = new ArrayList<>();
        combinations.add(this.tableName);
        combinations.add("public."+this.tableName);
    }

    public List<String> getCombinations() {
        return this.combinations;
    }
    public String getTableName() {
        return this.tableName;
    }
}
