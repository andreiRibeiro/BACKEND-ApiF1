package br.com.aaribeiro.icarros.dao;

import br.com.aaribeiro.icarros.dto.questions.DealResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@AllArgsConstructor
@Repository
public class DealDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<DealResponseDTO> searchDealsByCustomerName(String customerName){
        return jdbcTemplate.query(String.format(
                "SELECT " +
                "   order_id, " +
                "   (SELECT SUM(value) FROM item WHERE id IN " +
                "       (SELECT items_id FROM deal_items WHERE deal_id = deal.id) " +
                "    ) AS total_price " +
                "FROM deal WHERE customer_name = '%s'", customerName),
                (resultSet, i) -> DealResponseDTO.builder()
                        .orderId(resultSet.getString("order_id"))
                        .totalPrice(resultSet.getBigDecimal("total_price"))
                        .build()
        );
    }
}
