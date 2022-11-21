package kba.model.logic;

public interface LogicNode
{
    boolean isConstant();
    boolean fold();
    LogicNode normalize();
    LogicNode negate();
}
