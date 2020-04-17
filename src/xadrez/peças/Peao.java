package xadrez.pe�as;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.Pe�aXadrez;

public class Peao extends Pe�aXadrez {

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
		
	}

	@Override
	public boolean[][] Movimentospossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		
		if(getCor() == Cor.BRANCO) {
			p.setValores(posicao.getLinha() -1 , posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -2 , posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() -1 , posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPe�a(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1 , posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() -1 , posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		else {
			p.setValores(posicao.getLinha() +1 , posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +2 , posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() +1 , posicao.getColuna());
			if(getTabuleiro().posicaoExiste(p) && !getTabuleiro().temUmaPe�a(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().temUmaPe�a(p2) && getContadorMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1 , posicao.getColuna() -1);
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValores(posicao.getLinha() +1 , posicao.getColuna() +1);
			if(getTabuleiro().posicaoExiste(p) && temUmaPe�aOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		
	}
		
		return mat;
	}
	
	@Override
	
	public String toString() {
		return "P";
	}

}
