import MyJavaParser.MyJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Main {
    public static void main(String[] args) {

        MyJavaParser mjp = new MyJavaParser();
        CompilationUnit compilationUnit = mjp.printSourceCode("src/Main.java");
        System.out.println(compilationUnit);
        
        System.out.println("end");
    }

}
