package spring.boot.optic.okulist.specification.contactlenses.providers;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.model.ContactLenses;

public class CurveSpecificationProviderLenses
        implements SpecificationProviderLenses<ContactLenses> {
    @Override
    public String getKey() {
        return "curve";
    }

    @Override
    public Specification<ContactLenses> getContactLensesSpecification(String[] params) {
        if (params.length != 2) {
            throw new IllegalArgumentException("Invalid number of parameters for "
                    + "curve specification");
        }

        double minCurve = Double.parseDouble(params[0]);
        double maxCurve = Double.parseDouble(params[1]);

        return (root, query, builder) -> {
            if (minCurve == maxCurve) {
                return builder.equal(root.get("baseCurve"), minCurve);
            } else {
                return builder.between(root.get("baseCurve"), minCurve, maxCurve);
            }
        };
    }
}
