package xadrez.peças;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}

	@Override
	public boolean[][] Movimentospossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		// acima
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		
				p.setValores(posicao.getLinha() + 1, posicao.getColuna());
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

				// esquerda
				
				p.setValores(posicao.getLinha(), posicao.getColuna() -1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				// direita
				
				p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// NO
				
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// NE
				
				p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// SO
				
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				
				// SE
				
				p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExiste(p) && podeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		return mat;
	}
}
