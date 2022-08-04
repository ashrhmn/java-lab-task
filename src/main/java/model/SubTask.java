package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "subtasks")
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String subtaskName;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubTaskName() {
        return subtaskName;
    }

    public void setSubTaskName(String name) {
        this.subtaskName = name;
    }

}
