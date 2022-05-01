package com.github.creatureox.detectivemask.mask;

import com.github.creatureox.detectivemask.config.MaskProperties;
import com.github.creatureox.detectivemask.entity.KeyValue;
import com.github.creatureox.detectivemask.utils.PropertyUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Objects;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public interface Mask {

    default void validateBindProperties() {
        MaskProperties maskProperties = PropertyUtil.bind(this.getClass());
        if (CollectionUtils.isEmpty(maskProperties.getKeywords())
                && Objects.isNull(maskProperties.getPattern())) {
            throw new RuntimeException(String.format("%s has no corresponding properties",
                    this.getClass().getName()));
        }
    }

    default boolean isDetected(KeyValue keyValue) {
        MaskProperties maskProperties = PropertyUtil.bind(this.getClass());
        boolean matchKeywords = maskProperties.getKeywords().contains(keyValue.getKey());
        if (CollectionUtils.isNotEmpty(maskProperties.getKeywords())
                && Objects.nonNull(maskProperties.getPattern())) {
            return matchKeywords || maskProperties.getPattern().matcher(keyValue.getValue().toString()).find();
        } else if (CollectionUtils.isNotEmpty(maskProperties.getKeywords())) {
            return matchKeywords;
        } else if (Objects.nonNull(maskProperties.getPattern())) {
            return maskProperties.getPattern().matcher(keyValue.getValue().toString()).find();
        } else {
            return false;
        }
    }

    String doMask(String value);

    default void run(KeyValue keyValue) {
        if (isDetected(keyValue)) {
            keyValue.setValue(doMask(keyValue.getValue().toString()));
        }
    }

}
