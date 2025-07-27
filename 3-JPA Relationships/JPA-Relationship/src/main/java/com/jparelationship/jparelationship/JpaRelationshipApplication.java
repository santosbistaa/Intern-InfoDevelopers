package com.jparelationship.jparelationship;

import com.jparelationship.jparelationship.Repo.CategoryRepo;
import com.jparelationship.jparelationship.Repo.ProductRepo;
import com.jparelationship.jparelationship.Repo.StudentRepository;
import com.jparelationship.jparelationship.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpaRelationshipApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpaRelationshipApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(StudentRepository studentRepository) {
        return (args) -> {

//            ONE TO ONE MAPPING
/*
//             Creating  a student
            Student student = new Student();
            student.setName("Alex Lee");
            student.setAbout("Graphics Designing Student");


            // Creating a laptop
            Laptop laptop = new Laptop();
            laptop.setBrand("Dell");
            laptop.setModelNumber("XPS-15");

            // since there is a bi-directional relationship
            laptop.setStudent(student);  // Set student in laptop
            student.setLaptop(laptop);   // Set laptop in student

            // Save the student (laptop will be saved automatically due to cascade)
            studentRepository.save(student);

            System.out.println("Student and Laptop saved successfully!");

            Student student = studentRepository.findById(1).get();
            System.out.println("The student is:"+ student.getName());

            Laptop laptop = student.getLaptop();
            System.out.println("\nLaptop Details:");
            System.out.println("ID: " + laptop.getLaptopId());
            System.out.println("Brand: " + laptop.getBrand());
            System.out.println("Model: " + laptop.getModelNumber());

 */

            // ONE TO MANY
 /*           Student student = new Student();
            student.setName("Eric Lang");
            student.setAbout("AI Student");

            Address address1 = new Address();
            address1.setAddressId(123);
            address1.setCity("KTM");
            address1.setCountry("Nepal");
            address1.setStreet("Kirtipur");
            address1.setStudent(student);

            Address address2 = new Address();
            address2.setAddressId(456);
            address2.setCity("LTP");
            address2.setCountry("Nepal");
            address2.setStreet("Thecho");
            address2.setStudent(student);

            List<Address> addressList = new ArrayList<>();
            addressList.add(address1);
            addressList.add(address2);

            student.setAddressList(addressList);
            studentRepository.save(student);
            System.out.println("Student and Addresses saved successfully");

  */

            // MANY TO MANY

/*            Product product1 = new Product();
            product1.setPId("pid1");
            product1.setProductName("Iphone 16 pro max");

            Product product2 = new Product();
            product2.setPId("pid2");
            product2.setProductName("Samsung s25 ultra");

            Product product3 = new Product();
            product3.setPId("pid3");
            product3.setProductName("Samsung TV1234");

            Category category1 = new Category();
            category1.setCId("cid1");
            category1.setTitle("Electronics");

            Category category2 = new Category();
            category2.setCId("cid2");
            category2.setTitle("Mobile Phones");

            List<Product> category1Products = category1.getProducts();
            category1Products.add(product1);
            category1Products.add(product2);
            category1Products.add(product3);

            List<Product> category2Products = category2.getProducts();
            category2Products.add(product1);
            category2Products.add(product2);

            categoryRepo.save(category1);
            categoryRepo.save(category2);
*/
            Category category = categoryRepo.findById("cid1").get();
            System.out.println(category.getProducts().size());

        };

    }
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;




}
