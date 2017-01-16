package hwAnnotationReflection.testLoaderAnnotation;

import java.lang.annotation.*;

/**
 * Created by user on 16.01.2017.
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    int a();
    int b();
}
