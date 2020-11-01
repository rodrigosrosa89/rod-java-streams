package br.com.rod.java.streams;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1,"FulanoDeTal",3500,"Desenvolvedor Java"));
		empregados.add(new Empregado(2,"Amanda",6000,"Desenvolvedora IOS"));
		empregados.add(new Empregado(3,"Denise",8500,"Cosinheira"));
		empregados.add(new Empregado(4,"Rorisvaldo",10000,"Doutor"));
	
//		// Imprimindo o nome de todos os empregados
//		System.out.println("** Lista de empregados **");
//		for(Empregado empregado : empregados) {
//			System.out.println(empregado.getNome());
//		}
		
		// Com Streams API
		System.out.println("** Lista de empregados **");
		empregados.stream().forEach(emp -> {
			System.out.println(emp.getNome());
		});
		
		empregados.forEach(emp -> {
			System.out.println("Empregado: " + emp.getNome());
		});
		
		
		
		// Imprimindo a soma dos salarios de todos empregados
		double salarioTotal = 0;
		for(Empregado empregado : empregados) {
			salarioTotal+= empregado.getSalario(); // salarioTotal = salarioTotal + empregado.getSalario();
		}
		System.out.println("O total dos salários é igual a " + salarioTotal);
		
		
		// Com Streams API
		double salarioTotal2 = empregados.stream().mapToDouble(emp -> emp.getSalario()).sum();
		System.out.println("O total dos salários é igual a " + salarioTotal2);
		


	}

}
