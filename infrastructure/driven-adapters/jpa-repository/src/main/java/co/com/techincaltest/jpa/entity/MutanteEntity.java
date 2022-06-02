package co.com.techincaltest.jpa.entity;

import lombok.*;

import javax.persistence.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mutante")
public class MutanteEntity {
    @Id
    private String id;
    @Column
    private String dna;
    @Column
    private boolean isMutant;
}
