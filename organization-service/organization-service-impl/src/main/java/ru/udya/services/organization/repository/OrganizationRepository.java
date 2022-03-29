package ru.udya.services.organization.repository;

import org.apache.commons.lang.SerializationUtils;
import ru.udya.services.organization.model.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrganizationRepository {

    private final List<Organization> organizations = new ArrayList<>();

    public Organization add(Organization organization) {
        organization.setId((long) (organizations.size() + 1));
        organizations.add(organization);
        return organization;
    }

    public Organization findById(Long id) {
        Optional<Organization> organization = organizations.stream()
                .filter(a -> a.getId().equals(id)).findFirst();

        return organization.map(d -> (Organization) SerializationUtils.clone(d)).orElse(null);
    }

    public List<Organization> findAll() {
        return organizations.stream()
                .map(d -> (Organization) SerializationUtils.clone(d))
                .collect(Collectors.toList());
    }

}
