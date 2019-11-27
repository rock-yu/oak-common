package com.oakware.common.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Collector {
    public static <T, K> Collection<T> collect(Collection<K> list, Function<K, T> transformer) {
        List<T> collected = list.stream().map(transformer).collect(Collectors.toList());
        return collected;
    }
}
