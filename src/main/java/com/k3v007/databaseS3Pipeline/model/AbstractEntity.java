package com.k3v007.databaseS3Pipeline.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * The type Abstract entity.
 *
 * @param <T> the type parameter
 * @author Vivek
 */
@Data
@MappedSuperclass
public abstract class AbstractEntity<T extends Number> implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

    @Column(name = "created_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedAt;

    /**
     * On create.
     */
    @PostPersist
    protected void onCreate() {
        this.updatedAt = this.createdAt = LocalDateTime.now();
    }

    /**
     * On update.
     */
    @PostUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
