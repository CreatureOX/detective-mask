package com.github.creatureox.detectivemask.serialize;

import com.github.creatureox.detectivemask.entity.KeyValue;
import com.github.creatureox.detectivemask.mask.Mask;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class XmlProcessor implements Processor {
    private static final Pattern XML_END_ELEMENT_PATTERN = Pattern.compile("(?<=</).*?(?=>)");

    private final List<Mask> masks;

    public XmlProcessor(List<Mask> masks) {
        this.masks = masks;
    }

    public String process(String log) {
        Matcher elementMatcher = XML_END_ELEMENT_PATTERN.matcher(log);
        while (elementMatcher.find()) {
            String key = elementMatcher.group();
            int start = log.indexOf("<" + key + ">") + key.length() + 2;
            int end = log.indexOf("</" + key + ">");
            String value = log.substring(start, end);
            if (StringUtils.isBlank(value) || XML_END_ELEMENT_PATTERN.matcher(value).find()) {
                continue;
            }
            KeyValue keyValue = new KeyValue(key, value);
            for (Mask mask: masks) {
                mask.run(keyValue);
            }
            return log.replace(value, keyValue.getValue().toString());
        }
        return log;
    }

}
