package victor.training.spring.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import victor.training.spring.web.entity.Training;

import java.util.List;

public interface TrainingRepo extends JpaRepository<Training, Long>, JpaSpecificationExecutor<Training> {
    Training getByName(String name);
    Training getByNameAndIdNot(String name, Long currentId);
}
