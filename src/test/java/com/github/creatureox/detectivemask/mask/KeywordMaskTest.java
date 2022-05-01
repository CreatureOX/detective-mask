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
public class KeywordMaskTest {
    public static class KeywordTestMask implements Mask {

        @Override
        public String doMask(String value) {
            return "keyword masked value: " + value;
        }
    }

    private KeywordTestMask keywordTestMask;

    @Before
    public void setUp() {
        keywordTestMask = new KeywordTestMask();
    }

    @Test
    public void keywordDetectedTest() {
        KeyValue keyValue = new KeyValue("keyword1", "keyword detected test");
        assertTrue(keywordTestMask.isDetected(keyValue));
    }

    @Test
    public void keywordNotDetectedTest() {
        KeyValue keyValue = new KeyValue("not keyword", "keyword not detected test");
        assertFalse(keywordTestMask.isDetected(keyValue));
    }

}
