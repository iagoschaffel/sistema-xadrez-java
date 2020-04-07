package xadrez;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.pe�as.Rei;
import xadrez.pe�as.Torre;

public class PartidaXadrez {
	
	private Tabuleiro tabuleiro;
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		Configuracaoinicial();
	}
	
	public Pe�aXadrez[][] getPe�as() {
		
		Pe�aXadrez[][] mat = new Pe�aXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i=0;i<tabuleiro.getLinhas();i++) {
			for(int j=0;j<tabuleiro.getColunas();j++) {
				mat[i][j] = (Pe�aXadrez) tabuleiro.pe�a(i,j);
			}
		}
		return mat;
	}
	
	private void posicionarPe�aNova(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.posicionarPe�a(pe�a, new PosicaoXadrez(coluna,linha).paraPosicao());
	}
	
	private void Configuracaoinicial() {
		posicionarPe�aNova('b', 6, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('e', 8,new Rei(tabuleiro,Cor.PRETO));
		posicionarPe�aNova('e', 1,new Rei(tabuleiro,Cor.BRANCO));
	}

}
