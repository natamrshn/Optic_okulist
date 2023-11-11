package spring.boot.optic.okulist.specification.contactlenses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.ContactLenses;

public class AngleSpecificationProviderLenses
        implements SpecificationProviderLenses<ContactLenses> {
    @Override
    public String getKey() {
        return "angle";
    }

    @Override
    public Specification<ContactLenses> getContactLensesSpecification(String[] params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters for"
                    + " angle specification");
        }

        double minAngle = Double.parseDouble(params[0]);
        double maxAngle = Double.parseDouble(params[1]);

        return (root, query, builder) -> {
            if (minAngle == maxAngle) {
                return builder.equal(root.get("angle"), minAngle);
            } else {
                return builder.between(root.get("angle"), minAngle, maxAngle);
            }
        };
    }
}
