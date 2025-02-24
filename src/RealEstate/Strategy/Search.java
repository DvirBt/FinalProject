package RealEstate.Strategy;

import RealEstate.Asset;

import java.util.ArrayList;

public class Search {

    private IteratorStrategy strategy;

    public Search(IteratorStrategy strategy)
    {
        this.strategy = strategy;
    }

    public void executeStrategy()
    {
        strategy.search();
    }
}
