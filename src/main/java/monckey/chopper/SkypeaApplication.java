package monckey.chopper;

import com.github.javafaker.Faker;
import monckey.chopper.entity.Address;
import monckey.chopper.entity.User;
import monckey.chopper.service.AddressService;
import monckey.chopper.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkypeaApplication{

    public static void main(String[] args) {
        SpringApplication.run(SkypeaApplication.class, args);
    }

}
