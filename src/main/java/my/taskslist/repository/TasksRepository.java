package my.taskslist.repository;

import my.taskslist.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Task, Integer> {
    Page<Task> findTasksByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description, Pageable paging);
}
