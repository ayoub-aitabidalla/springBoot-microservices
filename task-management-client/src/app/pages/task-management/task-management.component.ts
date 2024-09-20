import { Component } from '@angular/core';
import { Task } from '../../models/Task';
import { NgClass } from '@angular/common';
import { TaskApiService } from '../../services/task-api.service';
import { NewTask } from '../../models/NewTask';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-management',
  standalone: true,
  imports: [NgClass, FormsModule],
  templateUrl: './task-management.component.html',
  styleUrl: './task-management.component.css'
})
export class TaskManagementComponent {

  constructor(private taskApiService:TaskApiService){}
  modal: HTMLDialogElement | null = null;
  userId:number=-1
  newTask:NewTask={
    title:"",
    description:"",
    userId:this.userId
  }
  ngOnInit()
  {
    const userData = JSON.parse(localStorage.getItem('userData') || '{}');
    this.userId = userData.id;
    this.getTasks();
    this.modal = document.querySelector('#my_modal_3');
 
  }

  tasks: Task[] = [];
  completedTask:boolean=false;
 
  addNewTaskClick()
  {
    if (this.modal) {
      this.modal.showModal();
    } else {
      console.error('!!');
    }

  }

  getTasks(){
    this.taskApiService.getAllTasksByUserId(this.userId).subscribe({
      next:(res)=>{
        console.log(res);
        this.tasks=res;
      },
      error:(err)=>console.log(err)
    })
  }



  createTask()
  {
    this.newTask.userId=JSON.parse(localStorage.getItem('userData') || '{}').id;
    this.taskApiService.addNewTask(this.newTask).subscribe({
      next:(res)=>{
        console.log(res);
        this.newTask.title = '';
        this.newTask.description = '';
        this.getTasks();
        if (this.modal) 
        {
          this.modal.close();
        }
      },
      error:(err)=>console.log(err)
    })
  }
  

  deleteTask(taskId:number)
  {
    this.taskApiService.deleteTask(taskId).subscribe({
      next:(res)=>{
        this.getTasks();
      },
      error:(err)=>console.log(err)
    })
  }

  completeTask(taskId:number)
  {
    this.taskApiService.markTaskAsCompleted(taskId).subscribe({
      next:(res)=>{
        console.log(res);
        this.completedTask=true;
        const taskIndex = this.tasks.findIndex(task => task.id === taskId);
            if (taskIndex !== -1) {
                this.tasks[taskIndex].status = 'Done'  // Update the status for the task in the array
            }
      },
      error:(err)=>console.log(err)
    })
  }



}
