package util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultToObject {
    /**
     * 根据clazz和ResultSet返回对象(这个对象里的全部属性)集合
     * @param rs
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> getDataByClassFiled(ResultSet rs, Class<T> clazz) {
        ArrayList<T> domains = new ArrayList<T>();
        try {
            // 获得自己的属性
            Field[] fields = clazz.getDeclaredFields();
            // 获得父类的属性
            Field[] superFields = clazz.getSuperclass().getDeclaredFields();
            // 自己的和父类的属性相加
            Field[] allFields = addFields(fields, superFields);
            while (rs.next()) {
                T vo = clazz.newInstance();
                // 历遍所有的属性
                for (Field field : allFields) {
                    String methodName = getSetterMethodName(field.getName());
                    Method method = clazz.getMethod(methodName, field.getType());
                    invokeMothod(rs, field, method, vo);
                }
                domains.add(vo);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return domains;
    }

    /**
     * 根据返回的resultSet来决定生成对象有那些属性
     *
     * @param rs
     * @param clazz
     * @return
     */
    public static <T> ArrayList<T> getDataByResultSet(ResultSet rs,Class<T> clazz) {
        ArrayList<T> domains = new ArrayList<T>();

        // 通过metaData获取到的属性生成对应的FieldList
        List<Field> allFields = new ArrayList<Field>();

        try {
            // 如果未空值返回
            if (null == rs) {
                return null;
            }
            // 先获取元数据再通过元数据进行细化的操作
            java.sql.ResultSetMetaData metaData = rs.getMetaData();

            // 通过ResultSet里面的column的名字来决定用这个类的哪一个Field
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                // 多表链接查询列明重启时用getColumnLabel获取
                allFields.add(clazz.getDeclaredField(metaData.getColumnLabel(i + 1)));
            }
            while (rs.next()) {
                T vo = clazz.newInstance();
                // 历遍所有的属性
                for (Field field : allFields) {
                    String methodName = getSetterMethodName(field.getName());
                    Method method = clazz
                            .getMethod(methodName, field.getType());
                    invokeMothod(rs, field, method, vo);
                }
                domains.add(vo);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return domains;
    }

    /**
     * 相加f1和f2的Field
     *
     * @param f1
     * @param f2
     * @return
     */
    private static Field[] addFields(Field[] f1, Field[] f2) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : f1) {
            fields.add(field);
        }
        for (Field field : f2) {
            fields.add(field);
        }
        return fields.toArray(new Field[f1.length + f2.length]);
    }

    /**
     * 根据属性名得到set的方法名
     *
     * @param name
     * @return
     */
    private static String getSetterMethodName(String name) {
        String begin = name.substring(0, 1).toUpperCase();
        String mothodName = "set" + begin + name.substring(1, name.length());
        return mothodName;
    }

    /**
     * 根据ResultSet和Field的getName从结果集取出
     *
     * @param rs
     * @param field
     * @param method
     * @param object
     */
    private static void invokeMothod(ResultSet rs, Field field, Method method, Object object) {
        try {
            Object o = rs.getObject(field.getName());
            method.invoke(object, o);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
