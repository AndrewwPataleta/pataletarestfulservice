package com.pataleta.restfullservice.model;


public class Sparepart {

    private String vendorCode;

    private String nameSparepart;

    private String brandAuto;

    private String modelAuto;

    private String characteristics;

    private String price;

    private String phone;

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getNameSparepart() {
        return nameSparepart;
    }

    public void setNameSparepart(String nameSparepart) {
        this.nameSparepart = nameSparepart;
    }

    public String getBrandAuto() {
        return brandAuto;
    }

    public void setBrandAuto(String brandAuto) {
        this.brandAuto = brandAuto;
    }

    public String getModelAuto() {
        return modelAuto;
    }

    public void setModelAuto(String modelAuto) {
        this.modelAuto = modelAuto;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString(){
        return " Запчасть: "+nameSparepart+" цена: "+price+" телефон: "+phone+" ";
    }
}
