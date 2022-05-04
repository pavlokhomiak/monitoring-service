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
@Table(name = "monitoring_result")
@SQLDelete(sql = "UPDATE monitoring_result SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class MonitoringResult extends BaseEntity {

    @Column(name = "date_of_check", nullable = false)
    private LocalDateTime dateOfCheck;

    @Column(name = "returned_http_status_code", nullable = false)
    private Integer returnedHttpStatusCode;

    @Column(name = "returned_payload")
    private String returnedPayload;

    @OneToOne
    @JoinColumn(name = "monitored_endpoint_id", referencedColumnName = "id", nullable = false)
    private MonitoredEndpoint monitoredEndpoint;
}
