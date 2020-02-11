package com.ramana.rebelcourse.model;

import com.ramana.rebelcourse.helper.DateHelper;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public abstract class AuditModel implements Serializable {

    @Column(name = "created_at")
    private long createdAt = DateHelper.getCurrentTime();

    @Column(name = "updated_at")
    private long updatedAt;
}
