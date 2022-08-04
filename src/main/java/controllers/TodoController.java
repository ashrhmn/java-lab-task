package controllers;

import model.SubTask;
import model.Todo;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import services.SubTaskService;
import services.TodoService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;
    private final SubTaskService subTaskService;

    public TodoController(TodoService todoService, SubTaskService subTaskService){
        this.todoService = todoService;
        this.subTaskService = subTaskService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String todoList(
            Model model,
            @RequestParam(name = "action",required = false) String action,
            @RequestParam(name = "id",required = false) String id
    ){
        List<Todo> todos = this.todoService.getAll();
        model.addAttribute("todos",todos);
        model.addAttribute("action",action==null?null:action.toLowerCase(Locale.ROOT));
        model.addAttribute("id",id==null?null:Integer.parseInt(id));
        model.addAttribute("actionAdd","add");
        model.addAttribute("actionEdit","edit");
        model.addAttribute("actionEditTodo","editTodo");
        model.addAttribute("todo",new Todo());
        if(id!=null && action.equals("add")){
            Todo subAddingTodo = this.todoService.getOne(Integer.parseInt(id));
            if(subAddingTodo==null){
                return "404";
            }
            SubTask newSubTask = new SubTask();
            newSubTask.setTodo(subAddingTodo);
            model.addAttribute("newSubtask", newSubTask);
        }
        if(id!=null && action.equals("editTodo")){
            Todo todo = this.todoService.getOne(Integer.parseInt(id));
            if(todo==null){
                return "404";
            }
            model.addAttribute("todo", todo);
        }
        else{
            model.addAttribute("todo",new Todo());
        }
        if(id!=null && action.equals("edit")){
            SubTask editingSubTask = this.subTaskService.getOne(Integer.parseInt(id));
            if(editingSubTask==null){
                return "404";
            }
            model.addAttribute("editingSubTask", editingSubTask);
        }
        return "todos/list";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createTodoPage(Model model){
        Todo todo = new Todo();
//        todo.setDeadline(new Date());
//        todo.setSubTasks(new ArrayList<SubTask>());
        model.addAttribute("todo",todo);
        return "todos/create";
    }
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createTodoPost(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todos/create";
        }
        this.todoService.add(todo);
        return "redirect:/todos";
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String editTodoPage(Model model, @RequestParam("id") int id){
        Todo todo = this.todoService.getOne(id);
        if(todo==null){
            return "404";
        }
        model.addAttribute("todo",todo);
        return "todos/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String updateTodoPost(@Valid @ModelAttribute("todo") Todo todo, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "todos/edit";
        }
        this.todoService.update(todo);
        return "redirect:/todos";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String deleteTodo(@RequestParam("id") int id){
        Todo todo = todoService.getOne(id);
        if(todo==null){
            return "404";
        }
        this.todoService.deleteOne(id);
        return "redirect:/todos";
    }

}
