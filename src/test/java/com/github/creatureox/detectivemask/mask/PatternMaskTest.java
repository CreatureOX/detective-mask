package com.github.creatureox.detectivemask.mask;

import com.github.creatureox.detectivemask.entity.KeyValue;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class PatternMaskTest {
    public static class PatternTestMask implements Mask {
        @Override
        public String doMask(String value) {
            return "regex masked value:" + value;
        }
    }

    private PatternTestMask patternTestMask;

    @Before
    public void setUp() {
        patternTestMask = new PatternTestMask();
    }

    @Test
    public void patternDetectedTest() {
        KeyValue keyValue = new KeyValue("lower case", "pattern");
        assertTrue(patternTestMask.isDetected(keyValue));
    }

    @Test
    public void patternNotDetectedTest() {
        KeyValue keyValue = new KeyValue("upper case", "PATTERN");
        assertFalse(patternTestMask.isDetected(keyValue));
    }

}
