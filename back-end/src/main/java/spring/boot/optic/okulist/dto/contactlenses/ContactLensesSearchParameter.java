package spring.boot.optic.okulist.dto.contactlenses;

public record ContactLensesSearchParameter(
        String minDiopter,
        String maxDiopter,
        String minCylinder,
        String maxCylinder,
        String minAngle,
        String maxAngle,
        String minBaseCurve,
        String maxBaseCurve,
        String lensColor,
        String adidation,
         String selectedParameter
) {
}
