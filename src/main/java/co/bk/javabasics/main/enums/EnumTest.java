package co.bk.javabasics.main.enums;

public class EnumTest {

	public static void main(String[] args) {
		
		for ( TaxType tt : TaxType.values() ){
			System.out.println("TaxType name: " + tt.name() + ", alias via toString=" + tt);
		}
	}

}
