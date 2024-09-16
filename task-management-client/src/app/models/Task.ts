export interface Task {
    id: number;              
    title: string;           
    description: string;     
    status: 'To Do' | 'Done'; 
    date: string;           
  }