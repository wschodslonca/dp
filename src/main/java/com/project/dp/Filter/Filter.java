package com.project.dp.Filter;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Filter {

    static Set<String> KEY_TABLES = new HashSet<>(Arrays.asList("acl", "users", "roles"));

    private String delSemicolon(String s) {
        int l = s.length();
        if (s.charAt(l-1)==';') {
            return s.substring(0,l-1);
        }
        else {
            return s;
        }
    }
    static List<String> clearList(List<String> l) {
        l.removeIf(i -> i.equals("") || i.equals(" "));
        return l;
    }
    private boolean ifComma(String s) {
        int l = s.length();
        return s.charAt(l - 1) == ',';
    }

    public String filter(String q, Long user_id) {
        QueryBuilder qb = new QueryBuilder();
        List<String> initwordList = new ArrayList<>(Arrays.asList((q.split(" ")).clone()));
        List<String> wordList = clearList(initwordList);
        int size = wordList.size();
        boolean prevFor = false;
        if (size>0) {
            qb.buildFirstElement(wordList.get(0));
            for (int i = 1; i < size; i++) {

                // previous word = for
                if (prevFor) {
                    String[] tabs = delSemicolon(wordList.get(i)).split(",");
                    for (int s = 0;s<tabs.length;s++) {
                        if (tabs[s].length()>0) {
                            if (KEY_TABLES.contains(tabs[s])) {
                                return "";
                            } else {
                                if (i+2>size) {
                                    qb.buildSubQuery(tabs[s], user_id);
                                }
                                else {
                                    if (wordList.get(i+1).equals("as")) {
                                        String alias = wordList.get(i+2);
                                        System.out.println(alias);
                                        qb.buildSubQueryWithAlias(tabs[s],user_id,alias);
                                        i+=2;
                                    }
                                    else {
                                        qb.buildSubQuery(tabs[s], user_id);
                                    }
                                }
                            }
                            if (s < tabs.length - 1) {
                                qb.buildComma();
                            }
                        }
                    }
                    if (!ifComma(wordList.get(i))) {
                        if (i+1<size) {
                            if (wordList.get(i+1).equals(",")) {
                                qb.buildComma();
                                i+=1;
                            }
                            else if (wordList.get(i+1).charAt(0)==',') {
                                qb.buildComma();
                            }
                            else {
                                prevFor = false;
                            }
                        }
                        else {
                            prevFor = false;
                        }
                    }
                    else {
                        qb.buildComma();
                    }
                }


                else {
                    qb.buildComponent(wordList.get(i));
                }

                if (wordList.get(i).equals("from")) {
                    prevFor = true;
                }
            }
        }
        return qb.build();
    }

    public static void main(String[] args) {
        //System.out.println(filter("select * from salaries where row_id=5",3L));

    }
}
