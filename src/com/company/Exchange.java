package com.company;

import sun.reflect.generics.tree.Tree;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public final class Exchange implements Request {

    private NavigableSet<SerialNumber> compatibleProducts;

    //Private constructor made using builder
    private Exchange(Builder builder) {
        this.compatibleProducts = builder.getCompatibleProducts();
    }


    @Override
    public void process(Product product, RequestStatus status) throws RequestException {
        ;
    }

    //Return set of compatible serial numbers
    public NavigableSet<SerialNumber> getCompatibleProducts() {
        return compatibleProducts;
    }


    //Builder nested class
    public static class Builder {

        private NavigableSet<SerialNumber> compatibleProducts;

        public Builder() {
            this.compatibleProducts = new TreeSet<SerialNumber>();
        }

        public Builder addCompatible(SerialNumber serialNumber) {
            compatibleProducts.add(serialNumber);
            return this;
        }

        public NavigableSet<SerialNumber> getCompatibleProducts() {
            return compatibleProducts;
        }

        public Exchange build() {
            Builder copyBuilder = new Builder();
            compatibleProducts.stream().forEach(e -> copyBuilder.addCompatible(e));
            return new Exchange(copyBuilder);
        }
    }
}