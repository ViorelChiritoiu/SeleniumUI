package org.selenium.pom.objects;

import io.qameta.allure.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.selenium.pom.utils.JacksonUtils;

import java.io.IOException;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private int id;
    private String name;

    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);
        for (Product product : products) {
            if (product.getId() == id) {
                this.id = id;
                this.name = product.getName();
            }
        }
    }
}

