package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by zj on 2017-2-4.
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class TransactionData {
    String transactionBlock;
    String transactionIndex;
    String transactionData;
}
