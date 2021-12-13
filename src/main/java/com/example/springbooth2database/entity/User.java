package com.example.springbooth2database.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="users")
@Setter
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int age;

    @CreationTimestamp
    @Column(name="created_at",nullable = false,updatable = false)//чтобы update не обновлял create date
    private Date createdAT;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date updatedAt;


    public User() {
    }
}
