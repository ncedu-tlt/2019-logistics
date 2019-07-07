package ru.ncedu.logistics.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "products_ai_sequence_generator",sequenceName = "products_ai_sequence",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_ai_sequence_generator")
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OfferingEntity> offeringEntitySet = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
