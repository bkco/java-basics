package co.bk.javabasics.patterns.factory.factorymethod;

public class Car implements Vehicle {

	public void drive() {
		System.out.println("Car drives to town.");
	}
	
	public void clean() {
		System.out.println("Car goes to car wash.");
	}
}
