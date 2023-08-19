import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JavaParserExample {
    public static void main(String[] args) {
        try {

            Path source = Paths.get("src/Sample/App/Sample.java");
            CompilationUnit compilationUnit=JavaParser.parse(source);

            ClassVisitor classVisitor = new ClassVisitor();
            classVisitor.visit(compilationUnit, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClassVisitor extends VoidVisitorAdapter<Void> {
    @Override
    public void visit(ClassOrInterfaceDeclaration classDeclaration, Void arg) {
        System.out.println("Class name: " + classDeclaration.getNameAsString());

        //getParentNodeName most node is Exeption
        // System.out.println(((ClassOrInterfaceDeclaration)classDeclaration.getParentNode().get()).getNameAsString());

        List<FieldDeclaration> fields = classDeclaration.getFields();
        for (FieldDeclaration field : fields) {
            System.out.println("Field: " + field.getVariables().get(0).getNameAsString());
        }

        super.visit(classDeclaration, arg);
    }
}
