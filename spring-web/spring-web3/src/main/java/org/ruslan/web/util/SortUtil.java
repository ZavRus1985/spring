package org.ruslan.web.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class SortUtil {

    public static <T> void sortListByField(List<T> list, String fieldName) {
        list.sort((T o1, T o2) -> {
            try {
                Field field = o1.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value1 = field.get(o1);
                Object value2 = field.get(o2);

                if (value1 instanceof Comparable && value2 instanceof Comparable) {
                    return ((Comparable) value1).compareTo(value2);
                } else {
                    throw new IllegalArgumentException("Field values are not comparable");
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
