import DDJavaParser.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaParserExample {
    public static void main(String[] args) {
        try {
            ClassVisitor classVisitor = new ClassVisitor("src/Sample/App/Sample.java");
            classVisitor.getClassDatas();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
