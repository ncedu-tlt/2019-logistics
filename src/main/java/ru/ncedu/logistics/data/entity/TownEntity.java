package ru.ncedu.logistics.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "towns")
public class TownEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "towns_ai_sequence_generator",sequenceName = "towns_ai_sequence",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "towns_ai_sequence_generator")
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoadEntity> roadEntitySet;

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
