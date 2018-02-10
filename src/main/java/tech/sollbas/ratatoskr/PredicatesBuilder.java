package tech.sollbas.ratatoskr;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;

public final class PredicatesBuilder<T> {
	
    private final List<SearchCriteria> params;
    private Class<? extends T> clazz;

    public PredicatesBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public PredicatesBuilder<T> with(SearchCriteria criteria, Class<T> clazz) {
		params.add(criteria);
		this.clazz = clazz;
		return this;
	}
	
	public PredicatesBuilder<T> with(List<SearchCriteria> criterias, Class<T> clazz) {
		params.addAll(criterias);
		this.clazz = clazz;
		return this;
	}

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        EntityPredicate<T> predicate = new EntityPredicate<T>(params, clazz);
        
        final List<BooleanExpression> predicates = predicate.getPredicate();

        BooleanExpression result = predicates.get(0);
        for (int i = 1; i < predicates.size(); i++) {
            result = result.and(predicates.get(i));
        }
        return result;
    }
}