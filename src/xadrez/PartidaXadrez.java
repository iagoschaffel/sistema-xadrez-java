package xadrez;

import jogotabuleiro.Pe�a;
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
	
	public Pe�aXadrez realizarMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao alvo = alvoPosicao.paraPosicao();
		validarOrigemPosicao(origem);
		validarAlvoPosicao(origem, alvo);
		Pe�a Pe�acapturada = fazerMovimento(origem, alvo);
		return (Pe�aXadrez)Pe�acapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPe�a(posicao)) {
			throw new ExcecaoXadrez("Nao existe pe�a na posi��o de origem.");
		}
		if(!tabuleiro.pe�a(posicao).ExisteMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existem movimentos possiveis para a pe�a escolhida.");
		}
	}
	
	private void validarAlvoPosicao(Posicao origem, Posicao alvo) {
		if(!tabuleiro.pe�a(origem).Movimentopossivel(alvo)) {
		throw new ExcecaoXadrez("A pe�a escolhida n�o pode se mover para a posi��o destino.");
		}
	}
	
	private Pe�a fazerMovimento(Posicao origem, Posicao alvo) {
		Pe�a p = tabuleiro.removerPe�a(origem);
		Pe�a pe�aCapturada = tabuleiro.removerPe�a(alvo);
		tabuleiro.posicionarPe�a(p, alvo);
		return pe�aCapturada;
	}
	
	private void posicionarPe�aNova(char coluna, int linha, Pe�aXadrez pe�a) {
		tabuleiro.posicionarPe�a(pe�a, new PosicaoXadrez(coluna,linha).paraPosicao());
	}
	
	private void Configuracaoinicial() {
		
		posicionarPe�aNova('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPe�aNova('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		posicionarPe�aNova('c', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('c', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('d', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('e', 7, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('e', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPe�aNova('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}

}
