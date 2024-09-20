import { Component } from '@angular/core';
import { User } from '../../models/user';
import { FormsModule, NgForm } from '@angular/forms';
import { TaskApiService } from '../../services/task-api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(private taskApiService:TaskApiService, private route:Router){}

  user:User={
    userName:'',
    password:''
  }
 

  register()
  {
    this.taskApiService.register(this.user).subscribe({
      next:(res)=>{
        console.log(res);
        localStorage.setItem('userData', JSON.stringify(res));
        this.route.navigateByUrl('tasks');
      },
      error: (error)=>console.log(error),
      complete: ()=>console.log('completed')
    })
    

  }



}
