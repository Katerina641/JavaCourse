package com.example.SystemwithSpringBoot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sales1")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;
    @Column(name="quantity")
    private int quantity;
    @Column(name="data")
    private String data;

    public Sales(){

    }

    public Sales(String name, int quantity, String data) {
        this.name = name;
        this.quantity = quantity;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", data='" + data + '\'' +
                '}';
    }
}
