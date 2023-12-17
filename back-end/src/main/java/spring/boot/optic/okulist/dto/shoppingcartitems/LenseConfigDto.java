package spring.boot.optic.okulist.dto.shoppingcartitems;

import lombok.Data;

@Data
public class LenseConfigDto { //TODO: check fields type and integrate it to mappers/service
    private String color;
    private String cylinder;
    private String degree;
    private String diopter;
    private String sphere;
}
