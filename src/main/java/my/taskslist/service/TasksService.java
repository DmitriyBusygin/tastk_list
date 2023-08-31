package my.taskslist.service;

import my.taskslist.model.Task;
import my.taskslist.repository.TasksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Page<Task> findAll(final int pageNumber,
                              final int pageSize,
                              final String sortField,
                              final String sortDirection,
                              final String query) {
        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        final Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        if (query == null) {
            return tasksRepository.findAll(pageable);
        } else {
            return tasksRepository.findTasksByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query, pageable);
        }
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
