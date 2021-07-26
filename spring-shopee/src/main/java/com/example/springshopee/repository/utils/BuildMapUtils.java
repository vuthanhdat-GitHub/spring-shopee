package com.example.springshopee.repository.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Component
public class BuildMapUtils {

    public Map<String, Object> buildMapSearch(Object object) {
        Map<String, Object> result = new HashMap<>();
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(object) != null && !field.get(object).equals("")) {
                    if (field.get(object) instanceof Integer) {
                        if (!field.get(object).equals(0)) {
                            result.put(field.getName().toLowerCase(), field.get(object));
                        }
                    } else if (field.get(object) instanceof Long) {
                        result.put(field.getName().toLowerCase(), field.get(object));
                    } else {
                        if (!field.getName().contains("Date")) {
                            result.put(field.getName().toLowerCase(), BuildQueryUtils.formatLikeStringSql(field.get(object).toString().trim()));
                        } else {
                            if (field.getName().contains("Date")) {
                                result.put(field.getName().toLowerCase(), DateTimeUtils.convertStringRequestToTimesTamp(DateTimeUtils.formatDateTimeQuery(field.get(object).toString().trim()), "dd/MM/yyyy"));
                            }
                        }
                    }
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException | ParseException e) {
            System.out.println("loi con vert map");
        }
        return result;
    }
}
