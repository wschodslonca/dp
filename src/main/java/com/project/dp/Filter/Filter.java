package com.project.dp.Filter;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Filter {

    static Set<String> KEY_TABLES = new HashSet<>(Arrays.asList("acl", "users", "roles"));

    private static String delsemicolon(String s) {
        int l = s.length();
        if (s.charAt(l-1)==';') {
            return s.substring(0,l-1);
        }
        else {
            return s;
        }
    }
    private static List<String> clearList(List<String> l) {
        l.removeIf(i -> i.equals("") || i.equals(" "));
        return l;
    }

    public static String filter(String q, Long user_id) {
        QueryBuilder qb = new QueryBuilder();
        List<String> initwordList = new ArrayList<String>(Arrays.asList((q.split(" ")).clone()));
        List<String> wordList = clearList(initwordList);
        int size = wordList.size();
        boolean prevTab = false;
        if (size>0) {
            qb.buildFirstElement(wordList.get(0));
            for (int i = 1; i < size; i++) {

                if (prevTab) {
                    String tabs = delsemicolon(wordList.get(i));
                    if (KEY_TABLES.contains(tabs)) {
                        return "";
                    }
                    else {
                        qb.buildSubQuery(tabs,user_id);
                    }
                    prevTab = false;
                }
                else {
                    qb.buildComponent(wordList.get(i));
                }


                if (wordList.get(i).equals("from")) {
                    prevTab = true;
                }
            }
        }
        return qb.build();
    }

    public static void main(String[] args) {
        System.out.println(filter("select * from salaries;",3L));

    }
}
