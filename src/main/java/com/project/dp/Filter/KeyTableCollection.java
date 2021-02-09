package com.project.dp.Filter;

import java.util.*;

public class KeyTableCollection {

    Map<String,String> keyTableNames = new HashMap<>();
    List<KeyTable> keyTables = new ArrayList<>();
    Map<String,String> primaryKeyMap = new HashMap<>();
    Set<String> namesSet = new HashSet<>();

    public KeyTableCollection() {
        keyTables.add(new KeyTable("salaries","row_id"));
        keyTables.add(new KeyTable("employees","employee_id"));

        for (KeyTable k : keyTables) {
            for (String s : k.getCombinations()) {
                keyTableNames.put(s,k.getTableName());
                primaryKeyMap.put(s,k.primaryKey);
                namesSet.add(s);
            }
        }
    }

    public Map<String,String> getPrimaryKeyMap() {
        return this.primaryKeyMap;
    }
    public Map<String,String> getKeyTableNames() {
        return this.keyTableNames;
    }
    public Set<String> getNamesSet() {
        return this.namesSet;
    }


}
