package MyJavaParser;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class MyJavaParser{
    public CompilationUnit printSourceCode(String path){
        Path source = Paths.get(path);
        CompilationUnit unit=null;
        try {
            unit = JavaParser.parse(source);
            System.out.println("***********************************************");
            System.out.println(unit);
            System.out.println("***********************************************");
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return unit;

    }

}
