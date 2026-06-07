package com.cis.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cis.api.repository.CollateralRepository;
import com.cis.api.repository.ProjectDetailsRepository;
import com.cis.api.repository.UserRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ProjectDetailsRepository projectRepository,
            CollateralRepository collateralRepository) {
        return args -> {
            // Create Client
//            User client = new User();
//            client.setUsername("client");
//            client.setPassword("password");
//            client.setName("John Doe");
//            client.setRole(Role.CLIENT);
//            userRepository.save(client);

            // Create Project
//            Project project = new Project();
//            project.setName("Alpha Transformation");
//            project.setDescription("Migration of legacy systems to cloud native architecture.");
//            project.setClientId(client.getId());
//            projectRepository.save(project);

            // Create Collaterals
//            createCollateral(collateralRepository, project, "Project Charter", "Initiation Document",
//                    "Approved charter document.", CollateralStatus.APPROVED);
//            createCollateral(collateralRepository, project, "High Level Design", "Design Document",
//                    "Initial draft of architecture.", CollateralStatus.PENDING_REVIEW);
//            createCollateral(collateralRepository, project, "Sprint 1 Report", "Status Report",
//                    "Weekly update for sprint 1.", CollateralStatus.CHANGES_REQUESTED);
        };
    }

//    private void createCollateral(CollateralRepository repo, Project project, String name, String type, String desc,
//            CollateralStatus status) {
//        Collateral c = new Collateral();
//        c.setProject(project);
//        c.setName(name);
//        c.setType(type);
//        c.setDescription(desc);
//        c.setStatus(status);
//        c.setUrl("http://example.com/doc.pdf"); // Mock URL
//        repo.save(c);
//    }
}
