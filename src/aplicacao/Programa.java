package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		PartidaXadrez Partidaxadrez = new PartidaXadrez();
		List<Pe�aXadrez> capturada = new ArrayList<>();
		
		while(true) {
		
			try {	
				UI.limparTela();
				UI.printPartida(Partidaxadrez,capturada);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicaoXadrez(teclado);
				
				boolean[][] Movimentospossiveis = Partidaxadrez.Movimentospossiveis(origem);
				UI.limparTela();
				UI.printTabuleiro(Partidaxadrez.getPe�as(), Movimentospossiveis);
				
				
				System.out.println();
				System.out.print("Alvo: ");
				PosicaoXadrez alvo = UI.lerPosicaoXadrez(teclado);
				Pe�aXadrez pe�aCapturada = Partidaxadrez.realizarMovimentoXadrez(origem, alvo); 
				
				if(pe�aCapturada != null) {
					capturada.add(pe�aCapturada);
				}
		}
			catch(ExcecaoXadrez e) {
				System.out.println(e.getMessage());
				teclado.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				teclado.nextLine();
			}
		}
	}

	
}


