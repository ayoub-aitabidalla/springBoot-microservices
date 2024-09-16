import { Component } from '@angular/core';
import { Task } from '../../models/Task';
import { NgClass } from '@angular/common';

@Component({
  selector: 'app-task-management',
  standalone: true,
  imports: [NgClass],
  templateUrl: './task-management.component.html',
  styleUrl: './task-management.component.css'
})
export class TaskManagementComponent {
  tasks: Task[] = [
    {
      id: 1,
      title: 'Complete project report',
      description: 'Finish the report for the project before the deadline.',
      status: 'To Do',
      date: '23 Mar, 2024',
    },
    {
      id: 2,
      title: 'Update team on progress',
      description: 'Send a progress update to the team.',
      status: 'Done',
      date: '20 Mar, 2024',
    },
  ];

}
