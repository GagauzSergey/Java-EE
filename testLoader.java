package hwAnnotationReflection.testLoaderAnnotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by user on 16.01.2017.
 */
public class testLoader {
    public static void tester1 (int a, int b){
        int c ;
        c=a+b;
        System.out.println(c);
    }

    public static int tester2(int a, int b){
        int c;
        c=b-a;
        return c;
    }

    @Test(a=2, b=5)
    public static void tester3(int a, int b){
        int c;
        c=(a+b)*(b+a);
        System.out.println(c);
    }

    public static void main(String[] args) {
        int a, b;
        Class <?> cls = testLoader.class;
        Method [] methods = cls.getMethods();
        for (Method m: methods){
            if (m.isAnnotationPresent(Test.class)){
                a = m.getAnnotation(Test.class).a();
                b = m.getAnnotation(Test.class).b();
                try {
                    m.invoke(null,a,b);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

