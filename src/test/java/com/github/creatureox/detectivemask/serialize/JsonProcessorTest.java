package com.github.creatureox.detectivemask.serialize;

import com.github.creatureox.detectivemask.mask.Mask;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class JsonProcessorTest {

    private JsonProcessor processor;

    @Before
    public void setUp() {
        List<Mask> masks = new ArrayList<>();
        processor = new JsonProcessor(masks);
    }

    @Test
    public void singleQuotationMarksJsonTest() {
        String log = "== {\"name\": \"test\"}";
        assertEquals("== {\"name\":\"test\"}", processor.process(log));
    }

    @Test
    public void doubleQuotationMarksJsonTest() {
        String log = "== {'name': 'test'}";
        assertEquals("== {\"name\":\"test\"}", processor.process(log));
    }

}