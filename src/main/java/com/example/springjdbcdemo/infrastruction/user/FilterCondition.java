package com.example.springjdbcdemo.infrastruction.user;

import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.data.relational.core.sql.Visitor;

/**
 * The type Filter condition.
 *
 * @author zuozhu.meng
 * @since 2020 /12/15
 */
public class FilterCondition implements Condition {

    private static final TrueCondition DEFAULT_FILTER = TrueCondition.INSTANCE;
    private Condition holder;

    /**
     * Instantiates a new Filter condition.
     */
    public FilterCondition() {
        this(DEFAULT_FILTER);
    }

    /**
     * Instantiates a new Filter condition.
     *
     * @param holder the holder
     */
    public FilterCondition(Condition holder) {
        this.holder = holder;
    }

    /**
     * Create.
     *
     * @return the filter condition
     */
    public static FilterCondition create() {
        return new FilterCondition();
    }

    /**
     * And.
     *
     * @param other  the other
     * @param filter the filter
     * @return the filter condition
     */
    public FilterCondition and(Condition other, boolean filter) {
        if (filter) {
            if (DEFAULT_FILTER.equals(holder)) {
                holder = other;
            } else {
                holder = holder.and(other);
            }
        }
        return this;
    }

    /**
     * Or.
     *
     * @param other  the other
     * @param filter the filter
     * @return the filter condition
     */
    public FilterCondition or(Condition other, boolean filter) {
        if (filter) {
            if (DEFAULT_FILTER.equals(holder)) {
                holder = other;
            } else {
                holder = holder.or(other);
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return holder.toString();
    }

    @Override
    public void visit(Visitor visitor) {
        holder.visit(visitor);
    }
}
