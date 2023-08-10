package com.ironhack.pharmacyapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;


   /* @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdAt = getTime();

    @Column(name = "created_by",nullable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_at",nullable = false)
    @LastModifiedDate
    private LocalDate updatedAt;
    @Column(name = "updated_by",nullable = false)
    @LastModifiedBy
    private String updatedBy;

    public LocalDate getTime(){
        LocalDate date = LocalDate.now();
        return date;
    }*/
}
