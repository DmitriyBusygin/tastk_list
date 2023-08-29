package my.taskslist.service;


import my.taskslist.model.Task;
import my.taskslist.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Task> findAll() {
        return tasksRepository.findAll();
    }

    public void save(Task task) {
        tasksRepository.save(task);
    }
}
