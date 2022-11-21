package kba.optimization;

import java.util.Optional;

public interface Optimizer<T>
{
    Optional<T> optimize(T value);
}
