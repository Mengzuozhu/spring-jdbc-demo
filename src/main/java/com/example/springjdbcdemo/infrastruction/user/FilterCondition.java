package com.example.springjdbcdemo.infrastruction.user;

import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.data.relational.core.sql.Visitor;

/**
 * @author zuozhu.meng
 * @since 2020/12/15
 **/
public class FilterCondition implements Condition {

    private Condition holder;

    public FilterCondition() {
        this(TrueCondition.INSTANCE);
    }

    public FilterCondition(Condition holder) {
        this.holder = holder;
    }

    public static FilterCondition create() {
        return new FilterCondition();
    }

    public FilterCondition and(Condition other, boolean filter) {
        if (filter) {
            holder = holder.and(other);
        }
        return this;
    }

    public FilterCondition or(Condition other, boolean filter) {
        if (filter) {
            holder = holder.or(other);
        }
        return this;
    }

    public FilterCondition not(boolean filter) {
        if (filter) {
            holder = holder.not();
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