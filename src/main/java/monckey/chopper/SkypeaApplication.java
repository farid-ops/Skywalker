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
public class SkypeaApplication implements CommandLineRunner {

    private final AddressService addressService;
    private final UserService userService;

    public SkypeaApplication(AddressService addressService,
                             UserService userService){
        this.addressService = addressService;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SkypeaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        for (int franky=0; franky<10; franky++){
            Address address = new Address();
            address.setNumber(faker.address().streetAddressNumber());
            address.setResidence(faker.address().city());
            address.setStreet(faker.address().streetName());
            address.setCity(faker.address().cityName());
            address.setState(faker.address().country());
            address.setCountry(faker.address().country());
            address.setPincode(faker.address().zipCode());
            this.addressService.saveAddress(address);
        }
        for (int nami=0; nami<10; nami++){
            User user = new User();
            user.setFirstname(faker.name().firstName());
            user.setLastname(faker.name().lastName());
            user.setUsername("admin@gmail.com");
            user.setEmail(faker.internet().emailAddress());
            user.setPassword("admin");
            user.setPhone("70246523");
            user.setStatus("actif");
            this.userService.save(user);
        }
    }
}
