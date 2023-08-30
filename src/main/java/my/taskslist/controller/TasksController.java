package my.taskslist.controller;

import my.taskslist.model.Task;
import my.taskslist.service.TasksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "tasks/index";
    }

    @GetMapping("/{id}")
    public String show(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("task", tasksService.findById(id));
        return "tasks/show";
    }

    @GetMapping("/new")
    public String newTask(@ModelAttribute("task") Task task) {
        return "tasks/new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("task") Task task) {
        tasksService.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("task", tasksService.findById(id));
        return "tasks/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,
                       @ModelAttribute("task") Task task) {
        tasksService.edit(id, task);
        return "redirect:/tasks";
    }
}
