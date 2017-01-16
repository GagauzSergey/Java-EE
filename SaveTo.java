package hwAnnotationReflection.saveToAnnotation;

import java.lang.annotation.*;

/**
 * Created by user on 16.01.2017.
 */

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SaveTo {
    String path ();
}
