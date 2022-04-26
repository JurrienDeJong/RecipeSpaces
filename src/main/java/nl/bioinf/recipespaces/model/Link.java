package nl.bioinf.recipespaces.model;

public class Link {
    private Node source;
    private Node target;
    private Integer distance;

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Link(Node source, Node target, Integer distance) {
        this.source = source;
        this.target = target;
        this.distance = distance;
    }
}
