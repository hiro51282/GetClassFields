package DDJavaParser;

import java.util.ArrayList;
import java.util.List;

public class ClassData {
    private List<ClassData> child;
    private String className;
    private List<String> fields;

    public ClassData() {
        child = new ArrayList<ClassData>();
        className = "";
        fields = new ArrayList<String>();
    }

    public List<ClassData> getChild() {
        return child;
    }

    public void setChild(ClassData child) {
        this.child.add(child);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public void addFields(String s) {
        this.fields.add(s);
    }
}
