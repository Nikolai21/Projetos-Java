package com.guessthenumber;

import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
	
	private static Integer quantidadeJogos = 0;
	private static Integer contadorAcertos = 0;
	private static Integer contadorErros = 0;
	private static Integer dificuldade;
	
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
	
	private static void defineDificuldade(Integer nivelDeDificuldade) {
		
		if (nivelDeDificuldade.equals(1)) {
			dificuldade = 10;
		} else if (nivelDeDificuldade.equals(2)) {
			dificuldade = 50;
		} else if (nivelDeDificuldade.equals(3)) {
			dificuldade = 100;
		}
	}

	private static Integer mudaDificuldade(Integer dificuldadeAtual) {
		Integer novaDificuldade = dificuldadeAtual;
		
		System.out.println("Você quer alterar a dificuldade? (Y/N)");

		Scanner alterarDificuldade = new Scanner(new InputStreamReader(System.in));
		String parametro = alterarDificuldade.next().toUpperCase();

		try {
			String sim = "Y";
			String nao = "N";
			
			if (parametro.equals(sim)) {
				System.out.println("Ok! Escolha a dificuldade:\nFácil(1) - Médio(2) - Difícil(3)");
				defineDificuldade(dificuldadeDoJogo());
			} else if (parametro.equals(nao)) {
				System.out.println("Ok, então vamos continuar!");
			} else {
				System.out.println("Você precisa digitar Y ou N.");
			}
		} catch (InputMismatchException ex) {
			System.out.println("Você precisa digitar Y ou N.");
		}
		
		return novaDificuldade;	
	}
	
	private static boolean verificaNumero(Integer limiteMaiorNumeros, boolean tentarNovamente, Integer numero) {
		if (numero > limiteMaiorNumeros) {
			System.out.println("Tem que ser um número de 0 a " + limiteMaiorNumeros + "!");
			tentarNovamente = false;
		} else {
			Random randomico = new Random();
			Integer numeroRandom = randomico.nextInt(limiteMaiorNumeros);
			
			if (numero == numeroRandom) {
				System.out.println("Você acertou, parabéns!");
				contadorAcertos++;
			} else {
				System.out.println("ERRRROOOOOOOOOO!!! O número era " + numeroRandom);
				contadorErros++;
			}
		}
		return tentarNovamente;
	}
	
	public static void executaJogo() {
		boolean jogar = true;
		
		System.out.println("Bem vindo ao Guess the Number! Em qual dificuldade você quer jogar?"
				+ "\nFácil(1) - Médio(2) - Difícil(3)");
				
		defineDificuldade(dificuldadeDoJogo());

		while (jogar) {
			
			if (((contadorErros - contadorAcertos > 5) | contadorAcertos - contadorErros > 5) && dificuldade != 10) {
				mudaDificuldade(dificuldade);
			}
			
			
			System.out.println("Digite um número de 0 a " + dificuldade);
			
			boolean tentarNovamente = true;
			
			try {
				Scanner tentativa = new Scanner(new InputStreamReader(System.in));
				Integer numero = tentativa.nextInt();
				
				tentarNovamente = verificaNumero(dificuldade, tentarNovamente, numero);
				
				quantidadeJogos++;
				
				while (tentarNovamente) {
					System.out.println("Erros: " + contadorErros);
					System.out.println("Acertos: " + contadorAcertos);
					System.out.println("Você quer jogar novamente? (Y/N)");
					Scanner opcao = new Scanner(System.in);
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
				System.out.println("Você tem que digitar um nùmero de 0 a " + dificuldade + "!");
			} 
		}
	}

	public static void main(String[] args) {
		executaJogo();
	}
}
