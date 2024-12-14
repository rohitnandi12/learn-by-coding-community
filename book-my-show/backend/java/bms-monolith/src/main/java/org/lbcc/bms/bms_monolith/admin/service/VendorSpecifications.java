package org.lbcc.bms.bms_monolith.admin.service;

import org.apache.commons.lang3.StringUtils;
import org.lbcc.bms.bms_monolith.admin.dto.VendorSearchRequest;
import org.lbcc.bms.bms_monolith.common.entity.Vendor;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class VendorSpecifications {

    public static Specification<Vendor> createSpecification(VendorSearchRequest searchRequest) {
        if (searchRequest == null) {
            // return all vendors without applying any filters (Conjunction)
            return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
        }

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(searchRequest.getVendorName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("vendorName")),
                        "%" + searchRequest.getVendorName().toLowerCase() + "%"
                ));
            }
            if (StringUtils.isNotBlank(searchRequest.getVendorStatus())) {
                predicates.add(criteriaBuilder.equal(root.get("vendorStatus"), searchRequest.getVendorStatus()));
            }
            if (searchRequest.getRegistrationDate() != null) {
                predicates.add(criteriaBuilder.equal(root.get("registrationDate"), searchRequest.getRegistrationDate()));
            }

            return predicates.isEmpty()
                    ? criteriaBuilder.conjunction()
                    : criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}