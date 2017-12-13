package co.bk.javabasics.main.lombok;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Serializable {

    private static final long serialVersionUID = 8804961580030622071L;

    private BigDecimal amount;
    private CurrencyCode currency;
}
