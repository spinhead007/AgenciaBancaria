package model;

public class AprovaEmprestimo {
	public static boolean aprovaEmprestimo(double rendaAnual, double valorEmprestimo) {
		if (rendaAnual < valorEmprestimo * 3) {
			return false;
		}
		if(valorEmprestimo > 50000) {
			return false;
		}
		
		return true;
		
	}
}