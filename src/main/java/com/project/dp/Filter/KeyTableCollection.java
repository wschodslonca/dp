package com.project.dp.Filter;
import org.springframework.stereotype.Component;

import java.util.*;

public class KeyTableCollection {

    List<String> keyTableNames = new ArrayList<>();
    List<KeyTable> keyTables = new ArrayList<>();
    Map<String,String> primaryKeyMap = new HashMap<>();
    Set<String> namesSet = new HashSet<>();

    public KeyTableCollection() {
        keyTables.add(new KeyTable("salaries","row_id"));
        keyTables.add(new KeyTable("employees","employee_id"));

        for (KeyTable k : keyTables) {
            keyTableNames.add(k.tableName);
            for (String s : k.getCombinations()) {
                primaryKeyMap.put(s,k.primaryKey);
                namesSet.add(s);
            }
        }
    }

    public Map<String,String> getPrimaryKeyMap() {
        return this.primaryKeyMap;
    }
    public List<String> getKeyTableNames() {
        return this.keyTableNames;
    }
    public Set<String> getNamesSet() {
        return this.namesSet;
    }


}
