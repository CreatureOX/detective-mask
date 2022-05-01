package com.github.creatureox.detectivemask;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class DetectiveMaskLogConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return DetectiveMaskStarter.getInstance().process(event.getFormattedMessage());
    }

}
