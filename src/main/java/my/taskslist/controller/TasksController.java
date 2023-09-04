package my.taskslist.controller;

import my.taskslist.model.Task;
import my.taskslist.service.TasksService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TasksController {

    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public String getAll(Model model,
                         @RequestParam(name = "page-number", defaultValue = "1") final int pageNo,
                         @RequestParam(name = "page-size", defaultValue = "10") final int pageSize,
                         @RequestParam(name = "sort-field", defaultValue = "id") final String sortField,
                         @RequestParam(name = "sort-dir", defaultValue = "asc") final String sortDir,
                         @RequestParam(name = "query", required = false) final String query) {

        final Page<Task> page = tasksService.findAll(pageNo, pageSize, sortField, sortDir, query);
        final List<Task> taskList = page.getContent();

        // pagination parameters
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        // sorting parameters
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc")?"desc":"asc");
        // query fo search
        model.addAttribute("query", query);
        // tasks
        model.addAttribute("tasks", taskList);
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        tasksService.delete(id);
        return "redirect:/tasks";
    }

    @PostMapping("/completed/{id}")
    public String makeCompleted(@PathVariable("id") int id,
                                @RequestParam(name = "makeStatus") boolean makeStatus) {
        tasksService.makeCompleted(id, makeStatus);
        return "redirect:/tasks";
    }
}
