import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';
import { Task } from '../models/Task';
import { NewTask } from '../models/NewTask';

@Injectable({
  providedIn: 'root'
})
export class TaskApiService {
  urlApi:string='http://localhost:8080/';


  constructor(private http:HttpClient) { }

  register(user: User): Observable<any> {
    return this.http.post(this.urlApi + 'user/save', user);
  }

  getAllTasksByUserId(id: number): Observable<Task[]> {
    return this.http.get<Task[]>(`${this.urlApi}task/${id}`);
  }

  addNewTask(task:NewTask):Observable<Task>
  {
    return this.http.post<Task>(this.urlApi + 'task/add', task)
  }

  deleteTask(taskId:number)
  {
    return this.http.delete(`${this.urlApi}task/${taskId}`)
  }

  markTaskAsCompleted(taskId:number):Observable<Task>
  {
    return this.http.put<Task>(`${this.urlApi}task/${taskId}/complete`, {})
  }


}
