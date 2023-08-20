package DDJavaParser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ClassVisitor extends VoidVisitorAdapter<Void> {
    String pathString = "";
    Path source;
    CompilationUnit compilationUnit;
    List<ClassOrInterfaceDeclaration> currentClass;
    ClassData data = new ClassData();

    public ClassVisitor(String pathString) throws IOException {
        this.pathString = pathString;
        this.source = Paths.get(this.pathString);
        this.compilationUnit = JavaParser.parse(source);
        this.visit(JavaParser.parse(source), null);
        List<ClassOrInterfaceDeclaration> topLevel = getCurrentClass();
        List<ClassData> d = new ArrayList<ClassData>();
        ClassData dat = new ClassData();
        d.add(dat);
        setClassDatas(topLevel, d);
        System.out.println(getClassDatas());
    }

    public ClassData getClassDatas() {
        return data;

    }

    private void setClassDatas(List<ClassOrInterfaceDeclaration> cls, List<ClassData> dat) {
        for (ClassOrInterfaceDeclaration c : cls) {
            for (int i = 0; i < getClassFields(c).size(); i++) {
                System.out.println("Class name: " + c.getNameAsString());
                dat.get(0).setClassName(c.getNameAsString());

                System.out.println("Field: " + getClassFields(c).get(i).getVariables().get(0).getNameAsString());
                dat.get(0).addFields(getClassFields(c).get(i).getVariables().get(0).getNameAsString());
            }
            List<ClassOrInterfaceDeclaration> child = getChildClass(c);
            List<ClassData> data = dat.get(1).getChild();
            setClassDatas(child, data);
        }

    }

    public List<ClassOrInterfaceDeclaration> getCurrentClass() {
        return currentClass;
    }

    public List<FieldDeclaration> getClassFields(ClassOrInterfaceDeclaration cls) {
        return cls.getFields();

    }

    public List<ClassOrInterfaceDeclaration> getChildClass(ClassOrInterfaceDeclaration cls) {
        List<ClassOrInterfaceDeclaration> ret = new ArrayList<ClassOrInterfaceDeclaration>();
        for (Node n : cls.getChildNodes()) {
            if (n.getClass().equals(ClassOrInterfaceDeclaration.class)) {
                ret.add((ClassOrInterfaceDeclaration) n);

            }
        }
        return ret;
    }

    public void moveChildClass(ClassOrInterfaceDeclaration cls) {
        for (Node n : cls.getChildNodes()) {
            currentClass.add((ClassOrInterfaceDeclaration) n);
        }
    }

    public void moveParentClass(ClassOrInterfaceDeclaration cls) {
        List<ClassOrInterfaceDeclaration> parentClass = new ArrayList<ClassOrInterfaceDeclaration>();
        parentClass.add((ClassOrInterfaceDeclaration) cls.getParentNode().get());
        currentClass = parentClass;
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration _classDeclaration, Void arg) {
        List<ClassOrInterfaceDeclaration> l = new ArrayList<ClassOrInterfaceDeclaration>();
        l.add(_classDeclaration);
        this.currentClass = l;
        // System.out.println("Class name: " + classDeclaration.getNameAsString());

        // getParentNodeName most node is Exeption
        // System.out.println(((ClassOrInterfaceDeclaration)classDeclaration.getParentNode().get()).getNameAsString());

        // super.visit(classDeclaration, arg);
    }
}
