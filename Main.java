package hwAnnotationReflection.saveToAnnotation;

import hwAnnotationReflection.testLoaderAnnotation.testLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by user on 16.01.2017.
 */
public class Main {
    public static void main(String[] args) {
        String path;
        Class <?> cls = TextContainer.class;
        Method[] mth = cls.getMethods();
        for (Method m : mth) {
            if (m.isAnnotationPresent(Saver.class)) {
                System.out.println(m.isAnnotationPresent(Saver.class));
                path = cls.getAnnotation(SaveTo.class).path();
                System.out.println(path);

                TextContainer tc = new TextContainer();
                tc.saveToDir(path);
            }
        }
    }
}

