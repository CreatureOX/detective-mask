package com.github.creatureox.detectivemask;

import com.github.creatureox.detectivemask.mask.Mask;
import com.github.creatureox.detectivemask.serialize.JsonProcessor;
import com.github.creatureox.detectivemask.serialize.Processor;
import com.github.creatureox.detectivemask.serialize.XmlProcessor;
import lombok.SneakyThrows;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
public final class DetectiveMaskStarter {

    private final static DetectiveMaskStarter INSTANCE = new DetectiveMaskStarter();

    private static final String SCAN_PATH = findScanPath();
    private final List<Processor> processors;

    private DetectiveMaskStarter() {
        System.setProperty("logging.level.org.reflections", "OFF");
        List<Mask> maskList = loadMasks();
        this.processors = new ArrayList<>();
        this.processors.add(new JsonProcessor(maskList));
        this.processors.add(new XmlProcessor(maskList));
    }

    public static DetectiveMaskStarter getInstance() {
        return INSTANCE;
    }

    private static String findScanPath() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = stackTraceElements[stackTraceElements.length-1].getClassName();
        List<String> words = new ArrayList<>(Arrays.asList(className.split("\\.")));
        words.remove(words.size() - 1);
        return String.join(".", words);
    }

    @SneakyThrows({InstantiationException.class,IllegalAccessException.class})
    private List<Mask> loadMasks() {
        List<Mask> masks = new ArrayList<>();
        Reflections reflections = new Reflections(SCAN_PATH);
        for (Class<? extends Mask> maskImplementationClass: reflections.getSubTypesOf(Mask.class)) {
            Mask mask = maskImplementationClass.newInstance();
            mask.validateBindProperties();
            masks.add(mask);
        }
        return masks;
    }

    public String process(String log) {
        for (Processor processor: processors) {
            log = processor.process(log);
        }
        return log;
    }

}
