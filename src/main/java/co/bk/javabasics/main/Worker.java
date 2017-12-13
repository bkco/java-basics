package co.bk.javabasics.main;

//Employee.Java
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class Worker {
    private String workerName;
    private int salary;
}