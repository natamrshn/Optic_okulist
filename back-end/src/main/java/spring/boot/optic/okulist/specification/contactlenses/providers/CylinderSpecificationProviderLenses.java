package spring.boot.optic.okulist.specification.contactlenses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.ContactLenses;

public class CylinderSpecificationProviderLenses
        implements SpecificationProviderLenses<ContactLenses> {
    @Override
    public String getKey() {
        return "cylinder";
    }

    @Override
    public Specification<ContactLenses> getContactLensesSpecification(String[] params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters "
                    + "for cylinder specification");
        }

        double minCylinder = Double.parseDouble(params[0]);
        double maxCylinder = Double.parseDouble(params[1]);

        return (root, query, builder) -> {
            if (minCylinder == maxCylinder) {
                return builder.equal(root.get("cylinder"), minCylinder);
            } else {
                return builder.between(root.get("cylinder"), minCylinder, maxCylinder);
            }
        };
    }
}



