package com.example.tasklistapp.Controller;

import com.example.tasklistapp.API.ApiResponse;
import com.example.tasklistapp.Model.TaskList;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskListController {
    List<TaskList> tasks = new ArrayList<>();

    @GetMapping("/get")
    public List <TaskList> getAllTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addBankManagement(@RequestBody TaskList task){
        for (TaskList taskId : tasks) {
            if(taskId.getId().equalsIgnoreCase(task.getId())){
                return new ApiResponse("This id already exists");
            }
        }
        tasks.add(task);
        return new ApiResponse("Successfully added " + task.getTitle() + " to the task list");
    }
    
    @PutMapping("/update/{id}")
    public ApiResponse updateBankManagement(@PathVariable String id, @RequestBody TaskList task){
        for(TaskList taskList : tasks){
            if(taskList.getId().equalsIgnoreCase(id)){
                taskList.setTitle(task.getTitle());
                taskList.setDescription(task.getDescription());
                taskList.setStatus(task.getStatus());
                return new ApiResponse("Successfully updated " + task.getId());

            }
        }
        return new ApiResponse("Account with this ID " + task.getId() + " not found");

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteBankManagement(@PathVariable String id){
        for(TaskList task: tasks){
            if(task.getId().equalsIgnoreCase(id)){
                tasks.remove(task);
            }
        }
        return new ApiResponse("Successfully deleted from the task list");
    }


    @PutMapping("/change/{id}")
    public ApiResponse changeStatus(@PathVariable String id, @RequestBody String status) {
            for(TaskList task : tasks){
                if(task.getId().equalsIgnoreCase(id)){
                    task.setStatus(status);
                    return new ApiResponse("Successfully changed status of task with ID " + id);
                }
            }
        return new ApiResponse("Account with this " + id + " not found");
    }

    @PostMapping("/search")
        public ApiResponse searchTasks(@RequestBody String title){
            for(TaskList task : tasks){
                if(task.getTitle().equalsIgnoreCase(title)){
                    return new ApiResponse("It's Found -> " + title + " with this ID: (" + task.getId() + ")");
                }
            }
            return new ApiResponse("Task not found");
    }
}
