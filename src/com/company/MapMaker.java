package com.company;

import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

public class MapMaker {
    public HashMap<ProductType, Product> makeProductMap(ProductType productType, SerialNumber serialNumber, Optional<Set<String>> description) {
        HashMap<ProductType, Product> products = new HashMap<>();
        products.put(ProductType.OPOD, new Opod(serialNumber, description));
        products.put(ProductType.OPAD, new Opad(serialNumber, description));
        products.put(ProductType.OPHONE, new Ophone(serialNumber, description));
        products.put(ProductType.OTV, new Otv(serialNumber, description));
        products.put(ProductType.OWATCH, new Owatch(serialNumber, description));
        return products;
    }


    public HashMap<ProductType, Boolean> makeValidMap(ProductType productType, SerialNumber serialNumber, Optional<Set<String>> description){
        HashMap<ProductType, Boolean> isValid = new HashMap<>();
        isValid.put(ProductType.OPAD, Opad.isValidSerialNumber(serialNumber));
        isValid.put(ProductType.OPOD, Opod.isValidSerialNumber(serialNumber));
        isValid.put(ProductType.OPHONE, Ophone.isValidSerialNumber(serialNumber));
        isValid.put(ProductType.OTV, Otv.isValidSerialNumber(serialNumber));
        isValid.put(ProductType.OWATCH, Owatch.isValidSerialNumber(serialNumber));
        return isValid;
    }
}

