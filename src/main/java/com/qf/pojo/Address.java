package com.qf.pojo;

/**
 * @author zyh
 * @date 2019/9/10 0010 14:36
 * 佛祖保佑
 */
public class Address {
    private Integer id;
    private String detail;
    private String name;
    private String phone;
    private Integer uid;
    private Integer level;

    public Address() {
        super();
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", uid=" + uid +
                ", level=" + level +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Address(Integer id, String detail, String name, String phone, Integer uid, Integer level) {

        this.id = id;
        this.detail = detail;
        this.name = name;
        this.phone = phone;
        this.uid = uid;
        this.level = level;
    }
}
