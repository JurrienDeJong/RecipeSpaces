package nl.bioinf.recipespaces.model;

public class Node {
    private Integer id;
    private String group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Node(Integer id, String group) {
        this.id = id;
        this.group = group;
    }
}
