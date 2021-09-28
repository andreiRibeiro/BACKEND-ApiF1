package br.com.aaribeiro.icarros.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "historic")
public class Historic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Builder.Default
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "register")
    private LocalDateTime date;

    @Column(name = "content", columnDefinition = "json")
    private String content;
}
