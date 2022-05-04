package com.project.monitoringservice.model;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "monitored_endpoint")
@SQLDelete(sql = "UPDATE monitored_endpoint SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class MonitoredEndpoint extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "date_of_creation", nullable = false)
    private LocalDateTime dateOfCreation;

    @Column(name = "date_of_last_check", nullable = false)
    private LocalDateTime dateOfLastCheck;

    @Column(name = "monitored_interval", nullable = false)
    private Integer monitoredInterval;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
}
