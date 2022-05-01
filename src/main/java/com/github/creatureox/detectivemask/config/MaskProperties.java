package com.github.creatureox.detectivemask.config;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class MaskProperties {

    private String keywords;

    private String pattern;

    public List<String> getKeywords() {
        return StringUtils.isNotBlank(keywords)?Arrays.asList(keywords.split(",")):new ArrayList<>();
    }

    public Pattern getPattern() {
        return StringUtils.isNotBlank(pattern)?Pattern.compile(pattern):null;
    }

}
