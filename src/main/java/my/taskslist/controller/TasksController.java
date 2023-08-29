package my.taskslist.controller;

import my.taskslist.model.Task;
import my.taskslist.service.TasksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", tasksService.findAll());
        return "tasks/tasks";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task) {
        return "tasks/task_new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("task") Task task) {
        System.out.println(task.getTitle());
        tasksService.save(task);
        return "redirect:/tasks";
    }
}
