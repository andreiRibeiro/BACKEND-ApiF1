package br.com.aaribeiro.icarros.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Builder.Default
    @Column(name = "order_id")
    private String orderId = UUID.randomUUID().toString();

    @Column(name = "register")
    private LocalDateTime date;

    @Column(name = "customer_name")
    private String customerName;

    @JoinTable
    @OneToMany
    private List<Item> items;
}
