package ufp.esof.project.ws1.services.filters;

import ufp.esof.project.ws1.models.BaseModel;

import java.util.Set;

public interface FilterI<T extends BaseModel> {
    Set<T> filter(Set<T> entities);
}
