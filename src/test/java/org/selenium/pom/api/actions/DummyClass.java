package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

import java.io.IOException;

public class DummyClass {

    public static void main(String[] args) throws IOException {
        User user = new User("demouser" + new FakerUtils().generateRandomNumber(),
                             "demopwd" + new FakerUtils().generateRandomNumber(),
                                "demouser" + new FakerUtils().generateRandomNumber() + "@a.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println("CART COOKIES:" + cartApi.getCookies());
    }
}
