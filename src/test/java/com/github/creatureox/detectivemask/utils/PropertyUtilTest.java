package com.github.creatureox.detectivemask.utils;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class PropertyUtilTest {

    @Test
    public void loadPropertiesTest() throws IOException {
        Map<String, String> expected = new HashMap<>();
        expected.put("detective-mask.load-test-1", "true");
        Map<String, String> actual = PropertyUtil.loadProperties("property-util.load-test-1.properties");
        assertEquals(expected, actual);
    }

    @Test
    public void loadMultiplePropertiesTest() throws IOException {
        Map<String, String> expected = new HashMap<>();
        expected.put("detective-mask.load-test-1", "true");
        expected.put("detective-mask.load-test-2", "true");
        Set<String> filenames = new HashSet<>();
        filenames.add("property-util.load-test-1.properties");
        filenames.add("property-util.load-test-2.properties");
        Map<String, String> actual = PropertyUtil.loadProperties(filenames);
        assertEquals(expected, actual);
    }

}