package br.com.rod.java.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		List<Empregado> empregados = new ArrayList<Empregado>();
		empregados.add(new Empregado(1, "FulanoDeTal", 3500, "Desenvolvedor Java"));
		empregados.add(new Empregado(2, "Amanda", 6000, "Desenvolvedora IOS"));
		empregados.add(new Empregado(3, "Denise", 8500, "Cosinheira"));
		empregados.add(new Empregado(4, "Rorisvaldo", 10000, "Doutor"));

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
		for (Empregado empregado : empregados) {
			salarioTotal += empregado.getSalario(); // salarioTotal = salarioTotal + empregado.getSalario();
		}
		System.out.println("O total dos salários é igual a " + salarioTotal);

		// Com Streams API
		double salarioTotal2 = empregados.stream().mapToDouble(emp -> emp.getSalario()).sum();
		System.out.println("O total dos salários é igual a " + salarioTotal2);
		
		Mensageiro mensageiro = (mensagem) -> System.out.println("Mensagem gigantesca: " + mensagem);
		mensageiro.emitirMensagem("CRUZEIRO gigantesco");
		
		//Consumer
		//Entra um parâmetro e não retorna nada
		Consumer<Empregado> consumer = (emp) -> {
			System.out.println(emp.getNome() + ", R$ " + emp.getSalario());
		};
		// Com método (accept) é possível invocar a ação dentro do consumer, incluindo um objeto para utilização
		consumer.accept(new Empregado(10, "Rodrigo Rosa", 1000, "Java"));
		
		
		//Functions
		System.out.println("Execução da function:");
		// Recebe dois parâmetros: objeto + tipo retorno. Este diferente do Consumer, retorna sempre algo
		Function<Empregado, Double> function = (emp) -> emp.getSalario() * 10;
		// Com método apply() é possível invocar a ação da FUNCTION, incluindo um objeto para utilização
		double novoSalario = function.apply(new Empregado(10, "Rodrigo Rosa", 7, "Java"));
		System.out.println(novoSalario);
		
		//BinaryOperator
		System.out.println("Execução do BinaryOperator:");
		BinaryOperator<Empregado> binaryOperator = (emp1,emp2) ->
		new Empregado(-1, emp1.getNome(), emp1.getSalario() + emp2.getSalario(), "Junção");
		Empregado novoEmpregado = binaryOperator.apply(new Empregado(0, "Rod", 2000, ""), new Empregado(0, "Soma", 3000, ""));
		System.out.println(novoEmpregado.getNome() + ", R$ " + novoEmpregado.getSalario());
		
		//Predicate 
		System.out.println("Execução do Predicate:");
		Predicate<Empregado> predicate = (emp) -> emp.getNome().endsWith("Web");
		boolean terminaComWeb = predicate.test(new Empregado(10, "RodrigoWeb", 1, ""));
		System.out.println(terminaComWeb);
		
		// Supplier
		System.out.println("Execução do Supplier:");
		Supplier<Empregado> supplier = () -> new Empregado(new Random().nextInt(), "SupplierEmp", 1, "Java");
		Empregado emp1 = supplier.get();
		System.out.println(emp1.getId());
		Empregado emp2 = supplier.get();
		System.out.println(emp2.getId());

	}

}
