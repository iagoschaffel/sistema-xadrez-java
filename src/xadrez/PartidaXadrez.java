package xadrez;

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
		
		posicionarPeçaNova('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarPeçaNova('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
