package xadrez.pe�as;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Bispo extends Pe�aXadrez {
	
	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}
	
	@Override
	
	public String toString() {
		return "B";
	}

	@Override
	public boolean[][] Movimentospossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		// Noroeste
		
			p.setValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		
			while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
			
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValores(p.getLinha() -1, p.getColuna() -1);
		}
		
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// Nordeste
		
			p.setValores(posicao.getLinha() -1, posicao.getColuna() + 1);
				
			while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
					
				mat[p.getLinha()][p.getColuna()] = true;
				p.setValores(p.getLinha() -1, p.getColuna() +1);
			}
				
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
		// Sudeste
			
				p.setValores(posicao.getLinha() +1, posicao.getColuna() + 1);
					
				while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
						
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() +1, p.getColuna() +1);
				}
					
				if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
			// Sudoeste
				
			p.setValores(posicao.getLinha() + 1, posicao.getColuna() -1);
				
				while(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
					
					mat[p.getLinha()][p.getColuna()] = true;
					p.setValores(p.getLinha() +1, p.getColuna() -1);
				}
				
				if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}	
			
			
		
		return mat;
	}

}
