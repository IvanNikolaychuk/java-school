-- 1st task
insert into tasks value('task01', 'Hello');
insert into requirements(id, taskId, value) values('1','task01', 'Код должен компилироваться');
insert into requirements(id, taskId, value) values('2','task01', 'Программа должна выводить "Hello World"');
insert into classes (id, name, taskId, code)values ('1', 'Main', 'task01',
'package javaschool.task.section01.task01;

public class Main {
  public static void main(String[] args) {

  }
}');
insert into specification(id, expectedOutput, programInput, requirementId) value ('1', 'Hello World', '', '2');
commit;

