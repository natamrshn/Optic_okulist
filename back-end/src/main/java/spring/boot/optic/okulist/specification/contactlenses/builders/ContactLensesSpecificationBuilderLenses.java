package spring.boot.optic.okulist.specification.contactlenses.builders;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.dto.contactlenses.ContactLensesSearchParameter;
import spring.boot.optic.okulist.model.ContactLenses;
import spring.boot.optic.okulist.specification.contactlenses.managers.SpecificationProviderManagerLenses;

@Component
@RequiredArgsConstructor
@Tag(name = "Contact Lenses Specification Builder ",
        description = "Builds Contact Lenses specifications for filtering")
public class ContactLensesSpecificationBuilderLenses implements
        SpecificationBuilderLenses<ContactLenses> {
    private static final Logger logger = LogManager
            .getLogger(ContactLensesSpecificationBuilderLenses.class);

    private final SpecificationProviderManagerLenses<ContactLenses> specificationProviderManager;
    private final Map<String, Specification<ContactLenses>> specificationCache =
            new ConcurrentHashMap<>();

    @Operation(summary = "Build contact lenses specifications",
            description = "Builds specifications for filtering contact "
                    + "lenses based on search parameters.")
    @Override
    public Specification<ContactLenses> build(ContactLensesSearchParameter searchParameters) {
        List<Specification<ContactLenses>> specifications = new ArrayList<>();

        if (!String.valueOf(searchParameters.maxDiopter()).equals("0.0")
                || !String.valueOf(searchParameters.minDiopter()).equals("0.0")) {
            logger.info("Filtering by diopter: min={}, max={}",
                    searchParameters.minDiopter(), searchParameters.maxDiopter());
            specifications.add(getSpecificationFromCache("diopter",
                    searchParameters.minDiopter() + "-" + searchParameters.maxDiopter()));
        }

        if (!String.valueOf(searchParameters.minCylinder()).equals("0.0")
                || !String.valueOf(searchParameters.maxCylinder()).equals("0.0")) {
            logger.info("Filtering by cylinder: min={}, max={}",
                    searchParameters.minCylinder(), searchParameters.maxCylinder());
            specifications.add(getSpecificationFromCache("cylinder",
                    searchParameters.minCylinder() + "-" + searchParameters.maxCylinder()));
        }

        if (!String.valueOf(searchParameters.minAngle()).equals("0.0")
                || !String.valueOf(searchParameters.maxAngle()).equals("0.0")) {
            logger.info("Filtering by angle: min={}, max={}",
                    searchParameters.minAngle(), searchParameters.maxAngle());
            specifications.add(getSpecificationFromCache("angle",
                    searchParameters.minAngle() + "-" + searchParameters.maxAngle()));
        }

        if (!String.valueOf(searchParameters.minBaseCurve()).equals("0.0")
                || !String.valueOf(searchParameters.maxBaseCurve()).equals("0.0")) {
            logger.info("Filtering by Curve: min={}, max={}",
                    searchParameters.minBaseCurve(), searchParameters.maxBaseCurve());
            specifications.add(getSpecificationFromCache("curve",
                    searchParameters.minBaseCurve() + "-" + searchParameters.maxBaseCurve()));
        }
        return specifications.stream().reduce(Specification::and).orElse(null);
    }

    private Specification<ContactLenses> getSpecificationFromCache(String key, String param) {
        String[] params = param.split("-");
        return specificationCache.computeIfAbsent(key, k ->
                specificationProviderManager.getSpecificationProvider(k)
                        .getContactLensesSpecification(params));
    }
}
