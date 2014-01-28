package wwasik.mpm.model;

import java.util.List;

/**
 * @author dom
 */
public class Product {

    private String name;
    private List<Release> roadMap;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Release> getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(List<Release> roadMap) {
        this.roadMap = roadMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
