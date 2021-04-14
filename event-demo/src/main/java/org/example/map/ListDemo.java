package org.example.map;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/20 18:11
 */
public class ListDemo {

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();

//        for (int i = 0; i < 20; i++) {
//            array.add(i);
//        }

        Integer capacity = getCapacity(array);
        int size = array.size();
        System.out.println("容量：" + capacity);
        System.out.println("大小：" + size);
    }

    public static Integer getCapacity(ArrayList list) {
        Integer length = null;
        Class c = ((Object) list).getClass();
        Field f;
        try {
            f = c.getDeclaredField("elementData");
            f.setAccessible(true);

            Object[] o = (Object[]) f.get(list);
            length = o.length;
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ListDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ListDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ListDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ListDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return length;
    }
}
