package zj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by zj on 2017-1-31.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Account {
    int index;
    String address;
    String balance;
}
