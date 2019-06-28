package ru.ncedu.logistics.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offices")
public class OfficeEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "offices_ai_sequence_generator",sequenceName = "offices_ai_sequence",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offices_ai_sequence_generator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "phone")
    private Integer phone;

    @ManyToOne
    @JoinColumn(name = "town_id", nullable = false)
    private TownEntity town;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public TownEntity getTown() {
        return town;
    }

    public void setTown(TownEntity town) {
        this.town = town;
    }
}
