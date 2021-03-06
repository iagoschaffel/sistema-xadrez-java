package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogotabuleiro.Peça;
import jogotabuleiro.Posicao;
import jogotabuleiro.Tabuleiro;
import xadrez.peças.Bispo;
import xadrez.peças.Cavalo;
import xadrez.peças.Peao;
import xadrez.peças.Rainha;
import xadrez.peças.Rei;
import xadrez.peças.Torre;

public class PartidaXadrez {
	
	private int turno;
	private Cor Jogadoratual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequeMate;
	private PeçaXadrez enPassantVulneravel;
	
	private List<Peça> peçasNoTabuleiro = new ArrayList<>();
	private List<Peça> peçasCapturadas = new ArrayList<>();
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		Jogadoratual = Cor.BRANCO;
		Configuracaoinicial();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return Jogadoratual;
	}
	
	public boolean getXeque() {
		return xeque;
	}
	
	public boolean getXequeMate() {
		return xequeMate;
	}
	
	public PeçaXadrez getEnPassantVulneravel() {
		return enPassantVulneravel;
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
	
	public boolean[][] Movimentospossiveis(PosicaoXadrez origemPosicao){
		Posicao posicao = origemPosicao.paraPosicao();
		validarOrigemPosicao(posicao);
		return tabuleiro.peça(posicao).Movimentospossiveis();
	}
	
	public PeçaXadrez realizarMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez alvoPosicao) {
		Posicao origem = origemPosicao.paraPosicao();
		Posicao alvo = alvoPosicao.paraPosicao();
		validarOrigemPosicao(origem);
		validarAlvoPosicao(origem, alvo);
		Peça Peçacapturada = fazerMovimento(origem, alvo);
		
		if(testeXeque(Jogadoratual)) {
			desfazerMovimento(origem, alvo, Peçacapturada);
			throw new ExcecaoXadrez("Voce nao pode se colocar em xeque.");
		}
		
		PeçaXadrez Peçamovida = (PeçaXadrez)tabuleiro.peça(alvo);
		
		xeque = (testeXeque(oponente(Jogadoratual))) ? true : false;
		
		if(testeXequeMate(oponente(Jogadoratual))) {
			xequeMate = true;
		}
		else {
			proximoTurno();
		}
		
		// #movimento especial En Passant
		
		if(Peçamovida instanceof Peao && (alvo.getLinha() == origem.getColuna() - 2 || alvo.getLinha() == origem.getColuna() + 2)) {
			enPassantVulneravel = Peçamovida;
		}
		else {
			enPassantVulneravel = null;
		}
		
		return (PeçaXadrez)Peçacapturada;
	}
	
	private void validarOrigemPosicao(Posicao posicao) {
		if(!tabuleiro.temUmaPeça(posicao)) {
			throw new ExcecaoXadrez("Nao existe peça na posição de origem.");
		}
		if(Jogadoratual != ((PeçaXadrez)tabuleiro.peça(posicao)).getCor()){
			throw new ExcecaoXadrez("A peça escolhida nao é sua.");
		}
		if(!tabuleiro.peça(posicao).ExisteMovimentoPossivel()) {
			throw new ExcecaoXadrez("Nao existem movimentos possiveis para a peça escolhida.");
		}
		
	}
	
	private void validarAlvoPosicao(Posicao origem, Posicao alvo) {
		if(!tabuleiro.peça(origem).Movimentopossivel(alvo)) {
		throw new ExcecaoXadrez("A peça escolhida não pode se mover para a posição destino.");
		}
	}
	
	private Peça fazerMovimento(Posicao origem, Posicao alvo) {
		PeçaXadrez p = (PeçaXadrez)tabuleiro.removerPeça(origem);
		p.incrementarContadorMovimento();
		Peça peçaCapturada = tabuleiro.removerPeça(alvo);
		tabuleiro.posicionarPeça(p, alvo);
		
		if(peçaCapturada != null) {
			peçasNoTabuleiro.remove(peçaCapturada);
			peçasCapturadas.add(peçaCapturada);
		}
		
		// Movimento especial Roque do lado do Rei
		
		if(p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			PeçaXadrez torre = (PeçaXadrez)tabuleiro.removerPeça(origemT);
			tabuleiro.posicionarPeça(torre, alvoT);
			torre.incrementarContadorMovimento();
		}
		
		// Movimento especial Roque do lado do Rei
		
				if(p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
					Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
					Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
					PeçaXadrez torre = (PeçaXadrez)tabuleiro.removerPeça(origemT);
					tabuleiro.posicionarPeça(torre, alvoT);
					torre.incrementarContadorMovimento();
		}
				
		// Movimento especial EnPassant
				
		if(p instanceof Peao) {
			if(origem.getColuna()!= alvo.getColuna() && peçaCapturada == null) {
				Posicao peaoPosicao;
				if(p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(alvo.getLinha() + 1, alvo.getColuna());
							
				}
				else {
					peaoPosicao = new Posicao(alvo.getLinha() - 1, alvo.getColuna());
				}
				peçaCapturada = tabuleiro.removerPeça(peaoPosicao);
				peçasCapturadas.add(peçaCapturada);
				peçasNoTabuleiro.remove(peçaCapturada);
			}
		}
		
		return peçaCapturada;
		
	}
	
	private void desfazerMovimento(Posicao origem, Posicao alvo, Peça peçaCapturada) {
		PeçaXadrez p = (PeçaXadrez)tabuleiro.removerPeça(alvo);
		p.decrementarContadorMovimento();
		tabuleiro.posicionarPeça(p,origem);
		
		if(peçaCapturada != null) {
			tabuleiro.posicionarPeça(peçaCapturada, alvo);
			peçasCapturadas.remove(peçaCapturada);
			peçasNoTabuleiro.add(peçaCapturada);
		}
		// Movimento especial Roque do lado do Rei
		
				if(p instanceof Rei && alvo.getColuna() == origem.getColuna() + 2) {
					Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
					Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
					PeçaXadrez torre = (PeçaXadrez)tabuleiro.removerPeça(alvoT);
					tabuleiro.posicionarPeça(torre, origemT);
					torre.decrementarContadorMovimento();
				}
				
			// Movimento especial Roque do lado do Rei
				
					if(p instanceof Rei && alvo.getColuna() == origem.getColuna() - 2) {
						Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
						Posicao alvoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
						PeçaXadrez torre = (PeçaXadrez)tabuleiro.removerPeça(alvoT);
						tabuleiro.posicionarPeça(torre, origemT);
						torre.decrementarContadorMovimento();
				}
					
					// Movimento especial EnPassant
					
					if(p instanceof Peao) {
						if(origem.getColuna()!= alvo.getColuna() && peçaCapturada == enPassantVulneravel) {
							PeçaXadrez peao = (PeçaXadrez)tabuleiro.removerPeça(alvo);
							Posicao peaoPosicao;
							if(p.getCor() == Cor.BRANCO) {
								peaoPosicao = new Posicao(3, alvo.getColuna());
										
							}
							else {
								peaoPosicao = new Posicao(4, alvo.getColuna());
							}
							tabuleiro.posicionarPeça(peao, peaoPosicao);
							
						}
					}
		
	}
	
	private void proximoTurno() {
		turno++;
		Jogadoratual = (Jogadoratual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor cor) {
		return(cor == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private PeçaXadrez rei(Cor cor) {
		List<Peça> lista = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peça p : lista) {
			if(p instanceof Rei) {
				return (PeçaXadrez)p;
			}
		}
		throw new IllegalStateException("Nao existe o rei " + cor + " no tabuleiro.");
	}
	
	private boolean testeXeque(Cor cor) {
		Posicao Posicaorei = rei(cor).getPosicaoXadrez().paraPosicao();
		List<Peça> Peçasoponente = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for(Peça p : Peçasoponente) {
			boolean [][] mat = p.Movimentospossiveis();
			if(mat[Posicaorei.getLinha()][Posicaorei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequeMate(Cor cor) {
		if(!testeXeque(cor)) {
			return false;
		}
		List<Peça> lista = peçasNoTabuleiro.stream().filter(x -> ((PeçaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peça p : lista) {
			boolean [][] mat = p.Movimentospossiveis();
			for(int i=0; i<tabuleiro.getLinhas();i++) {
				for(int j=0; j<tabuleiro.getColunas();j++) {
					if(mat[i][j]) {
						Posicao origem = ((PeçaXadrez)p).getPosicaoXadrez().paraPosicao();
						Posicao alvo = new Posicao(i,j);
						Peça PeçaCapturada = fazerMovimento(origem, alvo);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, alvo, PeçaCapturada);
						if(!testeXeque) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void posicionarPeçaNova(char coluna, int linha, PeçaXadrez peça) {
		tabuleiro.posicionarPeça(peça, new PosicaoXadrez(coluna,linha).paraPosicao());
		peçasNoTabuleiro.add(peça);
	}
	
	private void Configuracaoinicial() {
		posicionarPeçaNova('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('h', 1, new Torre(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('e', 1, new Rei(tabuleiro, Cor.BRANCO,this));
		posicionarPeçaNova('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
		posicionarPeçaNova('b', 1, new Cavalo(tabuleiro,Cor.BRANCO));
		posicionarPeçaNova('g', 1, new Cavalo(tabuleiro,Cor.BRANCO));
		posicionarPeçaNova('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
		posicionarPeçaNova('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));

		posicionarPeçaNova('a', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('h', 8, new Torre(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('e', 8, new Rei(tabuleiro, Cor.PRETO,this));
		posicionarPeçaNova('d', 8, new Rainha(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('c', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('f', 8, new Bispo(tabuleiro, Cor.PRETO));
		posicionarPeçaNova('b', 8, new Cavalo(tabuleiro,Cor.PRETO));
		posicionarPeçaNova('g', 8, new Cavalo(tabuleiro,Cor.PRETO));
		posicionarPeçaNova('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
		posicionarPeçaNova('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
		
	}

}
