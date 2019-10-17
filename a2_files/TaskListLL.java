public class TaskListLL implements TaskList {


  private TaskListNode head;
  private int count;

    public TaskListLL() {
    }


    public int getLength() {
        return count;
    }


    public boolean isEmpty() {
        return (head==null);
    }


    public Task removeHead() {
        TaskListNode temp = head;
        head = head.next;
        count--;
        return temp.task;


    }


    public Task remove(int number) {
      TaskListNode temp = head;
      TaskListNode removal = null;

      if(count==0){
        return null;
      }

      if(head.task.getNumber() == number){
        removal = head;
        head = head.next;
        count--;
        return removal.task;
      }

      while(temp != null){
        if(temp.next.task.getNumber() == number){
          removal = temp.next;
          temp.next = temp.next.next;
          count--;
          return removal.task;
        }
        temp = temp.next;
      }
      return null;
  }  //end of remove method



    public boolean insert(int priority, int number) {
      TaskListNode temp = head;
      //TaskListNode previous = null;
      Task t = new Task (priority, number);
      TaskListNode node = new TaskListNode(t);
      if(temp==null){
        head = node;
        count++;
        return true;
      }

      for(int i = 0; i<count; i++){
        if(node.task.getNumber() == temp.task.getNumber()){
          return false;
        }
      }

      if(node.task.getPriority() == temp.task.getPriority()){
        if(node.task.getNumber() == temp.task.getNumber()){
          return false;
        }
      } // if first case equals

      if(node.task.getPriority() > temp.task.getPriority()){
        head = node;
        node.next = temp;
        count++;
        return true;
      } //if first case is bigger

      while(temp != null){
        if(temp.next == null){
          temp.next = node;
          count++;
          return true;
        }
        if(node.task.getPriority() > temp.next.task.getPriority()){
          node.next = temp.next;
          temp.next = node;
          count++;
          return true;
      }


        if(node.task.getPriority() == temp.next.task.getPriority()){
          if(node.task.getNumber() == temp.next.task.getNumber()){
            return false;
          }
          if(node.task.getPriority() > temp.next.task.getPriority()){
            node.next = temp.next;
            temp.next = node;
            count++;
            break;
          }
        }
        temp = temp.next;
      } // end of while loop
      return true;
  } // end of insert method



    public Task retrieve(int pos) {
        TaskListNode temp = head;
        if(pos>=count || pos<0){
          return null;
        }
      for(int i=0; i<pos; i++){
        temp = temp.next;
      }
        return temp.task;
    } // end of retrieve method

} // end of TaskListLL
