package my.taskslist.service;


import my.taskslist.model.Task;
import my.taskslist.repository.TasksRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Task> findAll(final String sortField) {
        return tasksRepository.findAll(Sort.by(sortField));
    }

    public Task findById(int id) {
        return tasksRepository.findById(id).orElse(null);
    }

    public void save(Task task) {
        tasksRepository.save(task);
    }

    public void edit(int id, Task task) {
        task.setId(id);
        tasksRepository.save(task);
    }

    public void delete(int id) {
        tasksRepository.deleteById(id);
    }

    public void makeCompleted(int id, boolean makeStatus) {
        Task updateTask = findById(id);
        updateTask.setCompleted(makeStatus);
        tasksRepository.save(updateTask);
    }
}
