package jaehoon._04주차.live;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/176962
public class Lv02_과제_진행하기 {

  public static void main(String[] args) {
    String[][] plans = {
        {"science", "12:40", "50"},
        {"music", "12:20", "40"},
        {"history", "14:00", "30"},
        {"computer", "12:30", "100"}
    };
    System.out.println("result = " + Arrays.toString(solution(plans)));
  }

  public static String[] solution(String[][] plans) {
    // 1. 작업 객체 생성 및 시작 시간 기준으로 정렬
    List<Task> tasks = new ArrayList<>();
    for (String[] plan : plans) {
      tasks.add(new Task(plan));
    }
    tasks.sort(Comparator.comparingInt((Task a) -> a.startTime));

    // 2. 멈춘 작업을 저장할 스택 초기화
    Stack<Task> pausedTasks = new Stack<>();

    // 3. 결과 배열, 작업 진행 인덱스, 현재 시간 초기화
    String[] answer = new String[plans.length];
    int index = 0;
    int currentTime = 0;
    int taskIndex = 0; // 정렬된 리스트에서 작업을 탐색할 인덱스

    // 4. 모든 작업이 완료될 때까지 반복
    while (taskIndex < tasks.size() || !pausedTasks.isEmpty()) {
      // 4-1. 멈춘 작업이 없으면 다음 작업 시작
      if (pausedTasks.isEmpty()) {
        Task nextTask = tasks.get(taskIndex++);
        pausedTasks.push(nextTask);
        currentTime = Math.max(currentTime, nextTask.startTime);
      }
      // 4-2. 현재 작업을 완료할 수 있는 경우
      else if (taskIndex >= tasks.size() || currentTime + pausedTasks.peek().remainingTime <= tasks.get(taskIndex).startTime) {
        Task completedTask = pausedTasks.pop();
        currentTime += completedTask.remainingTime;
        answer[index++] = completedTask.subject;
      }
      // 4-3. 다음 작업 시작 전에 현재 작업을 멈춰야 하는 경우
      else {
        Task currentTask = pausedTasks.peek();
        Task nextTask = tasks.get(taskIndex++);
        currentTask.remainingTime -= nextTask.startTime - currentTime;
        currentTime = nextTask.startTime;
        pausedTasks.push(nextTask);
      }
    }
    return answer;
  }

  public static class Task {
    public String subject;
    public int startTime;
    public int remainingTime;

    public Task(String[] info) {
      subject = info[0];
      String[] timeSplit = info[1].split(":");
      startTime = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);
      remainingTime = Integer.parseInt(info[2]);
    }
  }
}