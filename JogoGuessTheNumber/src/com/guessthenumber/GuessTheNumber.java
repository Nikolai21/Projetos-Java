package com.guessthenumber;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
	
	private static Integer dificuldadeDoJogo() {
		boolean dificuldadeCorreta = false;
		Integer dificuldade = 1;
		
		while (!dificuldadeCorreta) {
			try {
				Scanner input = new Scanner(new InputStreamReader(System.in));
				dificuldade = input.nextInt();
				if (dificuldade < 1 || dificuldade > 3) {
					System.out.println("Por favor, digite 1 para Fácil, 2 para Médio ou 3 para Difícil.");
				} else {
					System.out.println("Então vamos lá!");
					dificuldadeCorreta = true;
				}
			} catch (InputMismatchException ex) {
				System.out.println("Por favor, digite 1 para Fácil, 2 para Médio ou 3 para Difícil.");
			}
		}
		
		return dificuldade;
	}
	
	private static Integer defineDificuldade(Integer dificuldade) {
		Integer limiteMaiorNumeros = 0;
		
		if (dificuldade.equals(1)) {
			limiteMaiorNumeros = 10;
		} else if (dificuldade.equals(2)) {
			limiteMaiorNumeros = 50;
		} else if (dificuldade.equals(3)) {
			limiteMaiorNumeros = 100;
		}
		return limiteMaiorNumeros;
	}
	
	private static boolean verificaNumero(Integer limiteMaiorNumeros, boolean tentarNovamente, Integer numero) {
		if (numero > limiteMaiorNumeros) {
			System.out.println("Tem que ser um número de 1 a " + limiteMaiorNumeros + "!");
			tentarNovamente = false;
		} else {
			Random randomico = new Random();
			Integer numeroRandom = randomico.nextInt(limiteMaiorNumeros);
			
			if (numero == numeroRandom) {
				System.out.println("Você acertou, parabéns!");
			} else {
				System.out.println("ERRRROOOOOOOOOO!!! O número era " + numeroRandom);
			}
		}
		return tentarNovamente;
	}
	
	public static void executaJogo() {
		boolean jogar = true;
		
		System.out.println("Bem vindo ao Guess the Number! Em qual dificuldade você quer jogar?"
				+ "\nFácil(1) - Médio(2) - Difícil(3)");
				
		Integer limiteMaiorNumeros = defineDificuldade(dificuldadeDoJogo());
		
		while (jogar) {
			System.out.println("Digite um número de 1 a " + limiteMaiorNumeros);
			
			boolean tentarNovamente = true;
			
			try {
				Scanner tentativa = new Scanner(new InputStreamReader(System.in));
				
				Integer numero = tentativa.nextInt();
				
				tentarNovamente = verificaNumero(limiteMaiorNumeros, tentarNovamente, numero);
				
				while (tentarNovamente) {
					System.out.println("Você quer jogar novamente? (Y/N)");
					Scanner opcao = new Scanner(new InputStreamReader(System.in));
					
					String parametro = opcao.next().toUpperCase();
					String sim = "Y";
					String nao = "N";
					
					if (parametro.equals(sim)) {
						jogar = true;
						tentarNovamente = false;
					} else if (parametro.equals(nao)) {
						jogar = false;
						tentarNovamente = false;
						tentativa.close();
						opcao.close();
						System.out.println("Então até a próxima!");
					} else {
						System.out.println("Você precisa digitar Y ou N.");
					}
				}
			} 
			catch (InputMismatchException ex) {
				System.out.println("Você tem que digitar um nùmero de 1 a " + limiteMaiorNumeros + "!");
			} 
		}
	}

	public static void main(String[] args) {
		
		executaJogo();
	}
}
