package com.man.fota.exception;

public class VinNotFoundException extends RuntimeException {

    private String vin;

    public VinNotFoundException(String vin) {
        super("Vin id " + vin + " not found");
        this.vin = vin;
    }

    public String getVin() {
        return vin;
    }
}
