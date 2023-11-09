package spring.boot.optic.okulist.dto.contactlenses;

public record ContactLensesSearchParameter(
        double minDiopter,
        double maxDiopter,
        double minCylinder,
        double maxCylinder,
        double minAngle,
        double maxAngle,
        double minBaseCurve,
        double maxBaseCurve,
        String lensColor,
        String adidation
) {
}
