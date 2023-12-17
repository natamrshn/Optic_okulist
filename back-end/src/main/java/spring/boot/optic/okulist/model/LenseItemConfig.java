package spring.boot.optic.okulist.model;

import lombok.Data;

@Data
public class LenseItemConfig { //TODO: check fields type and integrate it to mappers/services
    private String color;
    private String cylinder;
    private String degree;
    private String diopter;
    private String sphere;
}
