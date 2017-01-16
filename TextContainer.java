package hwAnnotationReflection.saveToAnnotation;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by user on 16.01.2017.
 */

/*
* 2. Написать класс TextContainer, который содержит в себе строку. С помощью механизма аннотаций указать
1) в какой файл должен сохраниться текст 2) метод, который выполнит сохранение. Написать класс Saver,
который сохранит поле класса TextContainer в указанный файл.
@SaveTo(path=“c:\\file.txt”)
class Container {
String text = “…”;
@Saver
public void save(..) {…}
}
* */

@SaveTo(path = "E:\\Testing Libs\\Test1\\Annotation.txt")
class TextContainer {
    FileOutputStream fos1;
    ObjectOutputStream oos1;

    private String nameF = "Svetlana";


    @Saver
    public void saveToDir(String dataPath) {
        try {
            File f = new File(dataPath);
            f.createNewFile();
            fos1 = new FileOutputStream(dataPath);
            oos1 = new ObjectOutputStream(fos1);
            oos1.writeUTF(nameF);
            oos1.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
