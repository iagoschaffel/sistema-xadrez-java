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
	
	private void posicionarPeçaNova(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.posicionarPeça(peça, new PosicaoXadrez(coluna,linha).paraPosicao());
	}
	
	private void Configuracaoinicial() {
		posicionarPeçaNova('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 8,new Rei(tabuleiro,Cor.PRETO));
		posicionarPeçaNova('e', 1,new Rei(tabuleiro,Cor.BRANCO));
	}

}
