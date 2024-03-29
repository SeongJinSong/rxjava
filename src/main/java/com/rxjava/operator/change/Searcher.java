package com.rxjava.operator.change;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Searcher {
    public Map<String, List<String>> map = new HashMap<>();

    {
        map.put("M", Arrays.asList("Macau", "Malaysia", "Maldives", "Mexico", "Myanmar", "Macedonia"));
        map.put("Ma", Arrays.asList("Macau", "Malaysia", "Maldives", "Macedonia"));
        map.put("Mal", Arrays.asList("Malaysia", "Maldives"));
        map.put("Malay", Arrays.asList("Malaysia"));
    }

    public List<String> search(String keyword){
        List<String> results = map.get(keyword);
        if(results == null){
            results = new ArrayList<>();
        }
        return results;
    }
}
