package xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		Configuracaoinicial();
	}
	
	public PeçaXadrez[][] getPeças() {
		
		PeçaXadrez[][] mat = new PeçaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++) {
			for(int j=0;j<tabuleiro.getColunas();j++) {
				mat[i][j] = (PeçaXadrez) tabuleiro.peça(i,j);
			}
		}
		return mat;
	}
	
	private void Configuracaoinicial() {
		tabuleiro.posicionarPeça(new Torre(tabuleiro, Cor.BRANCO),new Posicao(2, 1));
		tabuleiro.posicionarPeça(new Rei(tabuleiro,Cor.PRETO),new Posicao(0, 4));
		tabuleiro.posicionarPeça(new Rei(tabuleiro,Cor.BRANCO), new Posicao(7, 4));
	}

}
