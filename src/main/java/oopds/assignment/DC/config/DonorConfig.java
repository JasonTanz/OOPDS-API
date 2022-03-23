// package oopds.assignment.DC.config;

// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import oopds.assignment.DC.DAOs.DonorDAO;
// import oopds.assignment.DC.models.Donor;

// @Configuration
// public class DonorConfig {
// // @Autowired
// // DonorDAO donorDao;

// @Bean
// CommandLineRunner CommandLineRunner(DonorDAO donorDAO) {
// return args -> {
// Donor chihzhen = new Donor(
// "Chih zhen",
// "testingpass", 2);
// Donor chihzhen2 = new Donor(
// "Chih zhen2",
// "testingpass2", 2);

// donorDAO.saveAll(List.of(chihzhen, chihzhen2));
// // donorDAO.save(chihzhen);
// };
// }
// }
