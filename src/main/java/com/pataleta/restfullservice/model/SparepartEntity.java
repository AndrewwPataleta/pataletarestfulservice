package com.pataleta.restfullservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;


@Entity
@Table(name = "Sparepart", schema = "SparepartsDB", catalog = "")
public class SparepartEntity {

    private int idSparepart;
    private String vendorCode;
    private String nameSparepart;
    private String daysOfDelivery;
    private String quantity;
    private String producer;
    private double price;
    private String characteristics;
    private String phone;

    @Id
    @Column(name = "idSparepart")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getIdSparepart() {
        return idSparepart;
    }

    public void setIdSparepart(int idSparepart) {
        this.idSparepart = idSparepart;
    }

    @Basic
    @Column(name = "vendorCode")
    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }


    @Basic
    @Column(name = "nameSparepart")
    public String getNameSparepart() {
        return nameSparepart;
    }

    public void setNameSparepart(String nameSparepart) {
        this.nameSparepart = nameSparepart;
    }

    @Basic
    @Column(name = "daysOfDelivery")
    public String getDaysOfDelivery() {
        return daysOfDelivery;
    }

    public void setDaysOfDelivery(String daysOfDelivery) {
        this.daysOfDelivery = daysOfDelivery;
    }

    @Basic
    @Column(name = "quantity")
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "producer")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "characteristics")
    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SparepartEntity that = (SparepartEntity) o;

        if (idSparepart != that.idSparepart) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (vendorCode != null ? !vendorCode.equals(that.vendorCode) : that.vendorCode != null) return false;
        if (nameSparepart != null ? !nameSparepart.equals(that.nameSparepart) : that.nameSparepart != null)
            return false;
        if (daysOfDelivery != null ? !daysOfDelivery.equals(that.daysOfDelivery) : that.daysOfDelivery != null)
            return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (producer != null ? !producer.equals(that.producer) : that.producer != null) return false;
        if (characteristics != null ? !characteristics.equals(that.characteristics) : that.characteristics != null)
            return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    public String toString(){
        return "Запчасть: "+nameSparepart+ ", производитель: "+producer+" характеристики: "+characteristics+" цена: "+price;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idSparepart;
        result = 31 * result + (vendorCode != null ? vendorCode.hashCode() : 0);
        result = 31 * result + (nameSparepart != null ? nameSparepart.hashCode() : 0);
        result = 31 * result + (daysOfDelivery != null ? daysOfDelivery.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (characteristics != null ? characteristics.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
