package com.example.bookmyshow.models;

import com.example.bookmyshow.BookMyShowApplication;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class BaseModel {
    @Id
    // this id should auto increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;

}
