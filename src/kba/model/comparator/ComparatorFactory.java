package kba.model.comparator;

import kba.model.numeric.NumericNode;

public interface ComparatorFactory
{
    Comparator create(NumericNode left, NumericNode right);
}
