package ufp.esof.project.ws1_1.services.filters;

import ufp.esof.project.ws1_1.models.BaseModel;

import java.util.Set;

public interface FilterI<T extends BaseModel> {
    Set<T> filter(Set<T> entities);
}
