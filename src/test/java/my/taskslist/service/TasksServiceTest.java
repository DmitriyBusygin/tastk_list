package my.taskslist.service;

import my.taskslist.model.Task;
import my.taskslist.repository.TasksRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TasksServiceTest {
    @Mock
    private TasksRepository tasksRepository;
    @InjectMocks
    private TasksService tasksService;

    @DisplayName("Успешное нахождение задачи по переданному ID")
    @Test
    void findByIdHappyFlow() {
        int taskId = 1;

        Task mockTask = new Task();
        mockTask.setId(taskId);

        when(tasksRepository.findById(taskId))
                .thenReturn(Optional.of(mockTask));

        tasksService.findById(mockTask.getId());

        verify(tasksRepository).findById(taskId);
    }

    @DisplayName("Успешное изменение статуса выполнения задачи с false на true")
    @Test
    public void testMakeCompletedHappyFlow() {
        int taskId = 1;
        boolean beforeStatus = false;
        boolean makeStatus = true;

        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setCompleted(beforeStatus);

        when(tasksRepository.findById(taskId))
                .thenReturn(Optional.of(mockTask));

        tasksService.makeCompleted(taskId, makeStatus);

        assertTrue(mockTask.isCompleted());
    }
}