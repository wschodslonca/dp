package com.project.dp.Filter;

import org.springframework.stereotype.Component;

@Component
public class Filter {

    private static final String SUB_QUERY = "sgs";
    public static String filter(String query){
        return "elo";
    }
}
