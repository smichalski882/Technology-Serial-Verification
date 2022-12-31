package com.company;

public enum ProductType{
    OPOD, OPAD, OPHONE, OWATCH, OTV;

    //field to store productName
    private String productName;

    //method to return the name of this enum
    public String getName(){
        switch(this){
            case OPOD:
                return "oPod";
            case OPAD:
                return "oPad";
            case OPHONE:
                return "oPhone";
            case OWATCH:
                return "oWatch";
            case OTV:
                return "oTv";
        }
        return null;
    }
}
