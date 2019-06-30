package com.roll.casserole.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 利用反射实现赋值demo
 *
 * @author zongqiang.hao
 * created on 2019-02-27 11:30.
 */
public class EvaluationUtil {
    private static Map<String, Field> getAllField(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>(fields.length);
        for (Field field : fields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    public static void evaluationByName(Object object, Map<String, Object> valueMap) {
        Class clazz = object.getClass();
        Map<String, Field> fieldMap = getAllField(clazz);
        for (Map.Entry<String, Object> valueEntry : valueMap.entrySet()) {
            Field field = fieldMap.get(valueEntry.getKey());
            if (field != null) {
                try {
                    if (object.getClass().isAssignableFrom(clazz)) {
                        field.setAccessible(true);

                        if (valueEntry.getValue() instanceof Boolean) {
                            field.setBoolean(object, (Boolean) valueEntry.getValue());
                        } else {
                            field.set(object, valueEntry.getValue().toString());
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Cat cat = new Cat();
        Field field = cat.getClass().getField("name");
        field.set(cat, "haha");
        System.out.println(cat);

        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("name", "hahaCat");
        valueMap.put("isCat", true);
        evaluationByName(cat, valueMap);
        System.out.println(cat);
    }
}
