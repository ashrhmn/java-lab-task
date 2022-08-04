package controllers;

import model.SubTask;
import model.Todo;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import services.SubTaskService;
import services.TodoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/subtask")
public class SubTaskController {
    private final SubTaskService subTaskService;
    private final TodoService todoService;

    public SubTaskController(SubTaskService subTaskService, TodoService todoService){
        this.subTaskService = subTaskService;
        this.todoService = todoService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping(value = "create",method = RequestMethod.GET)
    public String addPageGet(@RequestParam("id") int id, Model model){
        Todo todo = this.todoService.getOne(id);
        if(todo==null){
            return "404";
        }
        SubTask subTask = new SubTask();
        subTask.setTodo(todo);
        model.addAttribute("subtask",subTask);
        return "todos/subtask/create";
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public String saveNewSubtask(
            @Valid @ModelAttribute("subtask") SubTask subTask,
            @RequestParam(name = "todo_id",required = true) int todo_id,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            return "redirect:/todos?action=add&id="+todo_id;
        }
        Todo todo = new Todo();
        todo.setId(todo_id);
        subTask.setTodo(todo);
        this.subTaskService.create(subTask);
        return "redirect:/todos";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public String updateSubtask(@RequestBody MultiValueMap<String, String> formData){
        SubTask subTask = new SubTask();
        int subtaskId = Integer.parseInt(formData.get("id").get(0));
        int todoId = Integer.parseInt(formData.get("todo_id").get(0));
        String subtaskName = formData.get("subtaskName").get(0);
        Todo todo = this.todoService.getOne(todoId);
        if(todo==null){
            return "404";
        }
        subTask.setId(subtaskId);
        subTask.setSubTaskName(subtaskName);
        subTask.setTodo(todo);
        this.subTaskService.update(subTask);
        return "redirect:/todos";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String deleteSubtask(@RequestParam(name = "id") int id){
        SubTask subTask = this.subTaskService.getOne(id);
        if(subTask==null){
            return "404";
        }
        this.subTaskService.deleteOne(subTask.getId());
        return "redirect:/todos";
    }
}
