package ${bean.packageName};

import java.util.Date;

public class ${bean.clazzName} {
    private Integer id = ${bean.id};
    private String name = ${bean.name};
    private String sex = ${bean.sex};

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
}

