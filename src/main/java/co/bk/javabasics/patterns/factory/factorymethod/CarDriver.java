package co.bk.javabasics.patterns.factory.factorymethod;

public class CarDriver extends VehicleDriver {

	@Override
	public Vehicle getInstance() {
		return new Car();
	}
}
