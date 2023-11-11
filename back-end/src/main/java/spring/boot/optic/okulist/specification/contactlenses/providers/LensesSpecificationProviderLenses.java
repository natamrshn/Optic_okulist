package spring.boot.optic.okulist.specification.contactlenses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.ContactLenses;

public class LensesSpecificationProviderLenses
        implements SpecificationProviderLenses<ContactLenses> {
    @Override
    public String getKey() {
        return "diopter";
    }

    @Override
    public Specification<ContactLenses> getContactLensesSpecification(String[] params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters "
                    + "for diopter specification");
        }
        double minDiopter = Double.parseDouble(params[0]);
        double maxDiopter = Double.parseDouble(params[1]);

        return (root, query, builder) -> {
            if (minDiopter == maxDiopter) {
                return builder.equal(root.get("diopter"), minDiopter);
            } else {
                return builder.between(root.get("diopter"), minDiopter, maxDiopter);
            }
        };
    }
}
