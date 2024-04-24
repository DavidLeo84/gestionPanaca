package co.edu.uniquindio;

import co.edu.uniquindio.model.Role;
import co.edu.uniquindio.model.UserEntity;
import co.edu.uniquindio.model.enums.ERole;
import co.edu.uniquindio.repositories.UserRepository;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class GestionEmpleadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEmpleadosApplication.class, args);
    }


    @Autowired
    UserRepository userRepository;

    @Bean
    CommandLineRunner init(){

        /*return args -> {


            String destinationPath = "src" + File.separator +
                    "main" + File.separator +
                    "resources" +
                    File.separator +
                    "static" + File.separator +
                    "ReportGenerated.pdf";

            String filePath = "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "templates" + File.separator +
                    "report" + File.separator +
                    "Report.jrxml";

            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("voucher_id", "465465456");
            parameters.put("current_date",formatter.format(localDateTime));
            parameters.put("amountPaid", new BigDecimal(10000));
            parameters.put("paymentMethod","PSE");
            parameters.put("parentName","Pepe Perez");
            parameters.put("childName", "Pepito Perez");
            parameters.put("imageDir", "classpath:/static/images/");

            JasperReport report = JasperCompileManager.compileReport(filePath);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, destinationPath);
            System.out.println("Report Created Successfully");
        };

             */

       return args -> {

           BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

           UserEntity userEntity1 = UserEntity
                   .builder()
                   .username("Leonardo")
                   .password(passwordEncoder.encode("123456"))
                   .roles(Set.of(Role.builder()
                           .name(ERole.valueOf(ERole.ADMINISTRATOR.name()))
                           .build()))
                   .build();


           UserEntity userEntity2 = UserEntity
                   .builder()
                   .username("David")
                   .password(passwordEncoder.encode("123456"))
                   .roles(Set.of(Role.builder()
                           .name(ERole.valueOf(ERole.AUXILIAR.name()))
                           .build()))
                   .build();


           UserEntity userEntity3 = UserEntity
                   .builder()
                   .username("Pepito")
                   .password(passwordEncoder.encode("123456"))
                   .roles(Set.of(Role.builder()
                           .name(ERole.valueOf(ERole.RECEPTION.name()))
                           .build()))
                   .build();


           userRepository.save(userEntity1);
           userRepository.save(userEntity2);
           userRepository.save(userEntity3);


       };
    }
}
