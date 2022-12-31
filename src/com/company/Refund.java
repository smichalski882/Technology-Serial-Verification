package com.company;

import java.math.BigInteger;

public final class Refund implements Request {

    private BigInteger rma;

    //process method from Request
    @Override
    public void process(Product product, RequestStatus status) throws RequestException {
        ;
    }

    //Private constructor created from builder object
    private Refund (Builder builder) throws RequestException {
        this.rma = builder.getRma();
    }

    public BigInteger getRma(){
        return this.rma;
    }

    //Nested static builder class to create refunds
    public static class Builder{

        private BigInteger rma;

        public Builder setRma(BigInteger rma){
            this.rma = rma;
            return this;
        }

        public BigInteger getRma() throws RequestException {
            if(rma == null){
                throw new RequestException(RequestException.ErrorCode.NULL_RMA);
            }
            else{
                return rma;
            }
        }

        public Refund build() throws RequestException {
            Builder copyBuilder = new Builder();
            copyBuilder.setRma(this.getRma());
            return new Refund(copyBuilder);
        }
    }
}
