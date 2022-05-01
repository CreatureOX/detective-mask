package com.github.creatureox.detectivemask.serialize;

import com.github.creatureox.detectivemask.entity.KeyValue;
import com.github.creatureox.detectivemask.mask.Mask;
import com.github.creatureox.detectivemask.utils.JsonUtil;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public class JsonProcessor implements Processor {
    private static final Pattern JSON_PATTERN = Pattern.compile("\\{.*(?=\\})\\}");

    private final List<Mask> masks;

    public JsonProcessor(List<Mask> masks) {
        this.masks = masks;
    }

    public String process(String log) {
        Matcher jsonMatcher = JSON_PATTERN.matcher(log);
        if (jsonMatcher.find()) {
            String raw = jsonMatcher.group();
            Map<String, Object> jsonMap = JsonUtil.toMap(raw);
            if (null == jsonMap) {
                return log;
            }
            for (Map.Entry<String, Object> entry: jsonMap.entrySet()) {
                KeyValue keyValue = new KeyValue(entry.getKey(), entry.getValue());
                for (Mask mask: masks) {
                    mask.run(keyValue);
                }
                entry.setValue(keyValue.getValue());
            }
            return log.replace(raw, JsonUtil.toString(jsonMap));
        }
        return log;
    }

}
