package org.sollbas.ext.jpa;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class EntityPredicate<T> {

    private List<SearchCriteria> criteria;
    
    private Class<? extends T> clazz;

    public EntityPredicate(final List<SearchCriteria> criteria, Class<? extends T> clazz) {
        this.criteria = criteria;
        this.clazz = clazz;
    }

    public List<BooleanExpression> getPredicate() {
        final PathBuilder<T> entityPath = new PathBuilder<T>(clazz, clazz.getName());

        List<BooleanExpression> boolExp = new ArrayList<BooleanExpression>();
        
        for (final SearchCriteria param : criteria) {
        	if (isNumeric(param.getValue().toString())) {

        		final NumberPath<Integer> path = entityPath.getNumber(param.getKey(), Integer.class);
                final int value = Integer.parseInt(param.getValue().toString());
                
                if (param.getOperation().equalsIgnoreCase("eq")) {
                    boolExp.add(path.eq(value));
                } else if (param.getOperation().equalsIgnoreCase("ge")) {
                	boolExp.add(path.goe(value));
                } else if (param.getOperation().equalsIgnoreCase("gt")) {
                	boolExp.add(path.gt(value));
                } else if (param.getOperation().equalsIgnoreCase("le")) {
                	boolExp.add(path.loe(value));
                } else if (param.getOperation().equalsIgnoreCase("lt")) {
                	boolExp.add(path.lt(value));
                }
            } else {
                final StringPath path = entityPath.getString(param.getKey());
                if (param.getOperation().equalsIgnoreCase("ct")) {
                	boolExp.add(path.containsIgnoreCase(param.getValue().toString()));
                } else if (param.getOperation().equalsIgnoreCase("eq")) {
                	boolExp.add(path.eq(param.getValue().toString()));
                }
            }
        }
        
        return boolExp;
    }

    public static boolean isNumeric(final String str) {
        try {
            Integer.parseInt(str);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }
}