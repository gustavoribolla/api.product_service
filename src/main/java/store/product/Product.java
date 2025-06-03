package store.product;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Builder
@Data @Accessors(fluent = true)
public class Product implements Serializable{

    private String id;
    private String name;
    private Double price;
    private String unit;
}
