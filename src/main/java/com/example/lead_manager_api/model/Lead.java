package com.example.lead_manager_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_LEAD")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String message;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createAt;

    private Long userId;
}
