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
public class XmlProcessorTest {

    private XmlProcessor processor;

    private List<Mask> masks;

    @Before
    public void setUp() {
        masks = new ArrayList<>();
        processor = new XmlProcessor(masks);
    }

    @Test
    public void XmlTest() {
        String log = "=== <SOAP-ENV:Body>test</SOAP-ENV:Body>";
        assertEquals("=== <SOAP-ENV:Body>test</SOAP-ENV:Body>", processor.process(log));
    }

}