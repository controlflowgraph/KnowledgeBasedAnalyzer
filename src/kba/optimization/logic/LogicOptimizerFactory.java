package kba.optimization.logic;

import kba.model.logic.LogicNode;
import kba.optimization.Optimizer;

public interface LogicOptimizerFactory
{
    Optimizer<LogicNode> create();
}
