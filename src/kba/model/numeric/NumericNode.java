package kba.model.numeric;

public interface NumericNode
{
    boolean isConstant();
    double fold();
    NumericNode normalize();
    NumericNode invert();
    NumericNode negate();
}
