-- 1st task
insert into tasks value('task01', 'Hello');
insert into conditions values('task01', 'Код должен компилироваться');
insert into classes (id, name, taskId, code)values ('1', 'Main', 'task01',
'package javaschool.task.section01.task01;

public class Main {
  public static void main(String[] args) {

  }
}');
commit;

