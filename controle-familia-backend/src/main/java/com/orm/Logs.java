package com.orm;

import com.enumeration.LogEnum;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "logs")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_log", sequenceName = "seq_log", allocationSize = 1)
public class Logs extends PanacheEntityBase {
    
    @Id
    @Column(name = "dt_ocorrencia", nullable = false, updatable = false, unique = true)
    private LocalDateTime dtOcorrencia;

    @Column(name = "ds_descricao", nullable = false)
    private String dsDescricao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fg_tipo_log")
    private LogEnum fgTipoLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Logs() {
    }

    public Logs(LocalDateTime dtOcorrencia, String dsDescricao, LogEnum fgTipoLog, Usuario usuario) {
        this.dtOcorrencia = dtOcorrencia;
        this.dsDescricao = dsDescricao;
        this.fgTipoLog = fgTipoLog;
        this.usuario = usuario;
    }
}
