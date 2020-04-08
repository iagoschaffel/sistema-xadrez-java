package aplicacao;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		PartidaXadrez Partidaxadrez = new PartidaXadrez();
		
		while(true) {
		
		UI.printTabuleiro(Partidaxadrez.getPeças());
		System.out.println();
		System.out.print("Origem: ");
		PosicaoXadrez origem = UI.lerPosicaoXadrez(teclado);
		System.out.println();
		System.out.print("Alvo: ");
		PosicaoXadrez alvo = UI.lerPosicaoXadrez(teclado);
		PeçaXadrez peçaCapturada = Partidaxadrez.realizarMovimentoXadrez(origem, alvo); 
		}
		

	}

}
