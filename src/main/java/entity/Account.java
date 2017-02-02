package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Component;

/**
 * Created by zj on 2017-1-31.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Component
public class Account {
    int index;
    String address;
    String balance;
}
