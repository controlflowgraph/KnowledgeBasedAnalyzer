package kba.optimization.numeric;

import kba.model.numeric.NumericNode;
import kba.optimization.Optimizer;

public interface NumericOptimizerFactory
{
    Optimizer<NumericNode> create();
}
