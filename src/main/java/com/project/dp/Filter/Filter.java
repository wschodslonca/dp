package com.project.dp.Filter;

import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Filter {

    static KeyTableCollection keyTableCollection = new KeyTableCollection();
    static Set<String> FORBIDDEN_TABLES = new HashSet<>(Arrays.asList("acl", "roles","users","public.acl", "public.roles","public.users"));
    static Set<String> KEY_TABLES = keyTableCollection.getNamesSet();
    static Map<String,String> PRIMARY_KEY_MAP = keyTableCollection.primaryKeyMap;

    public String filter(String q, Long users_id) {
        QueryBuilder qb = new QueryBuilder();
        q = q.replaceAll(";","");
        List<String> initwordList = new ArrayList<>(Arrays.asList((q.split(" ")).clone()));
        List<String> wordListwComma = clearList(initwordList);
        List<String> wordList = sepCommas(wordListwComma);
        int size = wordList.size();
        if (size > 0) {
            qb.buildFirstElement(wordList.get(0));
            for (int i =1;i<size;i++) {
                String comp = wordList.get(i);
                if (FORBIDDEN_TABLES.contains(comp)) {
                    return "";
                }
                if (KEY_TABLES.contains(comp)) {
                    String pkey = PRIMARY_KEY_MAP.get(comp);
                    System.out.println(pkey);
                    if (i+2>size) {
                        qb.buildSubQuery(comp,users_id,pkey);
                    }
                    else {
                        if (wordList.get(i+1).equals("as")) {
                            String alias = wordList.get(i+2);
                            qb.buildSubQueryWithAlias(comp,users_id,pkey,alias);
                            i+=2;
                        }
                        else {
                            qb.buildSubQuery(comp,users_id,pkey);
                        }
                    }
                }
                else {
                    qb.buildComponent(comp);
                }
            }
        }
        String res = qb.build();
        System.out.println(res);
        return res;
    }

    private List<String> clearList(List<String> l) {
        l.removeIf(i -> i.equals("") || i.equals(" "));
        return l;
    }
    private boolean ifComma0(String s) {
        return s.charAt(0) == ',';
    }
    private boolean ifComman(String s) {
        return s.charAt(s.length()-1) == ',';
    }
    private List<String> sepCommas(List<String> list) {
        List<String> res = new ArrayList<>();
        for(String s :list) {
            if (!s.equals(",")) {
                if (ifComma0(s)) {
                    res.add(",");
                }
                String[] els = s.split(",");
                int lenels = els.length;
                for (int e=0;e<lenels;e++) {
                    res.add(els[e]);
                    if (e<lenels-1) {
                        res.add(",");
                    }
                }
                if (ifComman(s)) {
                    res.add(",");
                }
            }
            else {
                res.add(",");
            }
        }
        return res;
    }
}
