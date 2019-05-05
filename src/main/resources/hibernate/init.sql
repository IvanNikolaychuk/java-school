-- 1st task
insert into tasks value('task01', 'Hello');
insert into requirements(id, taskId, name) values('1','task01', 'Код должен компилироваться');
insert into requirements(id, taskId, name) values('2','task01', 'Программа должна выводить "Hello World"');
insert into classes (id, name, taskId, code)values ('1', 'Main', 'task01',
'package javaschool.task.section01.task01;

public class Main {
  public static void main(String[] args) {

  }
}');
insert into test(id, type, expectedOutput, programInput, requirementId) value ('1', 'PROGRAM_OUTPUT', 'Hello World', '', '2');
commit;

