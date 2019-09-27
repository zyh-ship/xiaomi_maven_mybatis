package com.qf.pojo;

/**
 * @author zyh
 * @date 2019/9/10 0010 14:29
 * 佛祖保佑
 */
public class GoodsType {
    private Integer id;
    private String name;
    private Integer level;
    private Integer Parent;

    public GoodsType() {
        super();
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", parent=" + Parent +
                '}';
    }

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParent() {
        return Parent;
    }

    public void setParent(Integer parent) {
        this.Parent = parent;
    }

    public GoodsType(Integer id, String name, Integer level, Integer parent) {

        this.id = id;
        this.name = name;
        this.level = level;
        this.Parent = parent;
    }
}
