package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

//    @Column(name = "deadline")
//    private Date deadline;

    @OneToMany(mappedBy = "todo",fetch = FetchType.EAGER)
    private List<SubTask> subTasks;

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Date getDeadline() {
//        return deadline;
//    }
//
//    public void setDeadline(Date deadline) {
//        this.deadline = deadline;
//    }
}
