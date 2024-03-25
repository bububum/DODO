package com.example.Dodo.base;

import com.example.Dodo.model.enums.Status;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {

    protected Long id;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    protected Status status;

    public BaseDTO(Long id) {
        this.id = id;
    }

    @PrePersist
    protected void onCreate(){
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
        status = Status.ACTIVE;
    }
}