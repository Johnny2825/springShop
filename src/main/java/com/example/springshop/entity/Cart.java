package com.example.springshop.entity;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import elemental.json.Json;
import elemental.json.JsonObject;
import liquibase.pro.packaged.S;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@ToString
@Entity
@Table(name = "cart")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Cart {

    @PrePersist
    public void init() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
        this.createdAt = LocalDateTime.now();
    }

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private Person person;

    @Type(type = "jsonb")
    private List<InnerProduct> products;

    @Getter
    private static class InnerProduct {
        private String id;
        private String name;
        private long count;
        private long price;
        private String vendorCode;
    }

}
