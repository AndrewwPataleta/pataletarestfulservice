package com.pataleta.restfullservice.model;

import javax.persistence.*;

/**
 * Created by andrew on 4/3/17.
 */
@Entity
@Table(name = "Requests", schema = "SparepartsDB", catalog = "")
public class RequestsEntity {
    private int idRequests;
    private String requestscol;

    @Id
    @Column(name = "idRequests")
    public int getIdRequests() {
        return idRequests;
    }

    public void setIdRequests(int idRequests) {
        this.idRequests = idRequests;
    }

    @Basic
    @Column(name = "Requestscol")
    public String getRequestscol() {
        return requestscol;
    }

    public void setRequestscol(String requestscol) {
        this.requestscol = requestscol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestsEntity that = (RequestsEntity) o;

        if (idRequests != that.idRequests) return false;
        if (requestscol != null ? !requestscol.equals(that.requestscol) : that.requestscol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRequests;
        result = 31 * result + (requestscol != null ? requestscol.hashCode() : 0);
        return result;
    }
}
