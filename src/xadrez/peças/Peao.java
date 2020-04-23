package xadrez.pe�as;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.Pe�aXadrez;

public class Peao extends Pe�aXadrez {
	
	private PartidaXadrez Partidaxadrez;

	public Peao(Tabuleiro tabuleiro, Cor cor,PartidaXadrez Partidaxadrez) {
		super(tabuleiro, cor);
		this.Partidaxadrez = Partidaxadrez;
		
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
			
			// # movimento especial EnPassant BRANCO
			
			if(posicao.getLinha() == 3) {
				Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if(getTabuleiro().posicaoExiste(esquerda) && temUmaPe�aOponente(esquerda) && getTabuleiro().pe�a(esquerda) == Partidaxadrez.getEnPassantVulneravel()) {
					mat[esquerda.getLinha() -1 ][esquerda.getColuna()] = true;
				}
				Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if(getTabuleiro().posicaoExiste(direita) && temUmaPe�aOponente(direita) && getTabuleiro().pe�a(direita) == Partidaxadrez.getEnPassantVulneravel()) {
					mat[direita.getLinha() -1 ][direita.getColuna()] = true;
				}
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
			
			// # movimento especial EnPassant PRETO
			
						if(posicao.getLinha() == 4) {
							Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
							if(getTabuleiro().posicaoExiste(esquerda) && temUmaPe�aOponente(esquerda) && getTabuleiro().pe�a(esquerda) == Partidaxadrez.getEnPassantVulneravel()) {
								mat[esquerda.getLinha() +1 ][esquerda.getColuna()] = true;
							}
							Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
							if(getTabuleiro().posicaoExiste(direita) && temUmaPe�aOponente(direita) && getTabuleiro().pe�a(direita) == Partidaxadrez.getEnPassantVulneravel()) {
								mat[direita.getLinha() +1 ][direita.getColuna()] = true;
							}
						}
		
		}
		
		return mat;
	}
	
	@Override
	
	public String toString() {
		return "P";
	}

}
