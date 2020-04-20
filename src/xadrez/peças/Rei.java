package xadrez.peças;

import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PeçaXadrez;

public class Rei extends PeçaXadrez {
	
	private PartidaXadrez Partidaxadrez;

	public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez Partidaxadrez) {
		super(tabuleiro, cor);
		this.Partidaxadrez = Partidaxadrez;
		
	}

	@Override
	
	public String toString() {
		return "R";
	}
	
	private boolean podeMover(Posicao posicao) {
		
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testeRoqueTorre(Posicao posicao) {
		PeçaXadrez p = (PeçaXadrez)getTabuleiro().peça(posicao);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContadorMovimento() == 0;
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
				
				// #Movimento Especial Roque
				
				if(getContadorMovimento() == 0 && !Partidaxadrez.getXeque()) {
					// Movimento Especial Roque do lado do Rei
					Posicao posT1 = new Posicao(posicao.getLinha(),posicao.getColuna() + 3);
					if(testeRoqueTorre(posT1)) {
						Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna() + 1);
						Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() + 2);
						if(getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null) {
							mat[posicao.getLinha()][posicao.getColuna() +2] = true;
						}
					}
					
					// Movimento Especial Roque do lado da Rainha
					Posicao posT2 = new Posicao(posicao.getLinha(),posicao.getColuna() - 4);
					if(testeRoqueTorre(posT2)) {
						Posicao p1 = new Posicao(posicao.getLinha(),posicao.getColuna() - 1);
						Posicao p2 = new Posicao(posicao.getLinha(),posicao.getColuna() - 2);
						Posicao p3 = new Posicao(posicao.getLinha(),posicao.getColuna() - 3);
						if(getTabuleiro().peça(p1) == null && getTabuleiro().peça(p2) == null && getTabuleiro().peça(p3) == null){
							mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
						}
					}
				}
				
				
		return mat;
	}
}
